package net.edenor.minimap;

import net.edenor.minimap.api.MinimapPlayer;
import net.edenor.minimap.api.MinimapWorld;
import net.edenor.minimap.jm.JMHandler;
import net.edenor.minimap.worldinfo.WorldInfoHandler;
import net.edenor.minimap.xaeros.XaerosHandler;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.loader.ConfigurationLoader;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class MinimapPlugin extends JavaPlugin implements @NotNull Listener, @NotNull PluginMessageListener {
    private static MinimapPlugin instance;

    private MinimapConfig config;

    private static final List<String> listenChannels = Arrays.asList(
            "journeymap:version",
            "journeymap:perm_req",
            "journeymap:admin_req",
            "journeymap:admin_save",
            "journeymap:teleport_req",
            "journeymap:common",
            "worldinfo:world_id",
            "xaerominimap:main",
            "xaeroworldmap:main"
    );

    private final JMHandler jmHandler = new JMHandler(this);
    private final XaerosHandler xaerosHandler = new XaerosHandler(this);
    private final WorldInfoHandler worldInfoHandler = new WorldInfoHandler(this);

    public MinimapPlugin plugin;

    public void registerChannel(String channel) {
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, channel);
        plugin.getServer().getMessenger().registerIncomingPluginChannel(plugin, channel, this);
    }

    private BukkitAudiences adventure;

    public BukkitAudiences adventure() {
        if(this.adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        }
        return this.adventure;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        this.adventure = BukkitAudiences.create(this);
        getServer().getPluginManager().registerEvents(this, this);
        enableSelf();
    }

    @Override
    public @NotNull ComponentLogger getComponentLogger() {
        return super.getComponentLogger();
    }

    @Override
    public @NotNull Logger getSLF4JLogger() {
        return super.getSLF4JLogger();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        disableSelf();
        if(this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }
    }

    public static MinimapPlugin getInstance() {
        return instance;
    }


    public void enableSelf() {
        instance = this;
        listenChannels.forEach(this::registerChannel);
        loadConfig();
    }

    public void disableSelf() {
        System.out.println("Disabled");
    }

    public void loadConfig() {
        try {
            final CommentedConfigurationNode node = getConfigLoader().load();
            config = node.get(MinimapConfig.class);
            for (World world : plugin.getServer().getWorlds()){
                config.addWorld(world);
            }
            node.set(MinimapConfig.class, config);
            getConfigLoader().save(node);
        } catch (ConfigurateException e) {
            e.printStackTrace();
        }
    }

    public void handleSwitchWorld(MinimapWorld world, MinimapPlayer player) {
        xaerosHandler.sendXaerosConfig(player);
    }

    public void handlePlayerJoined(MinimapPlayer player) {
        xaerosHandler.sendXaerosHandshake(player);
        xaerosHandler.sendXaerosConfig(player);
    }

    public void saveConfig() {
        try {
            final CommentedConfigurationNode node = getConfigLoader().load();
            node.set(MinimapConfig.class, config);
            getConfigLoader().save(node);
        } catch (ConfigurateException e) {
            e.printStackTrace();
        }
    }

    public MinimapConfig getConfig() {
        return config;
    }

    public void onPluginMessage(String channel, MinimapPlayer player, byte[] message) {
        switch (channel.split(":")[0]) {
            case "journeymap":
                jmHandler.onPluginMessage(channel, player, message);
                break;
            case "xaerominimap":
            case "xaeroworldmap":
                xaerosHandler.onPluginMessage(channel, player, message);
                break;
            case "worldinfo":
                worldInfoHandler.onPluginMessage(channel, player, message);
                break;
        }
    }

    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, @NotNull byte[] message) {
        this.onPluginMessage(channel, new MinimapPlayer(player), message);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        // The player join event is slightly too early. I unfortunately don't know an event that fires late enough for Xaeros to recognize the packet
        // If anyone knows, please let me know
        plugin.getServer().getScheduler().runTaskLater(plugin, ()->this.handlePlayerJoined(new MinimapPlayer(event.getPlayer())), 40L);
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        this.handleSwitchWorld(new MinimapWorld(event.getPlayer().getWorld()), new MinimapPlayer(event.getPlayer()));
    }

    public ConfigurationLoader<CommentedConfigurationNode> getConfigLoader() {
        File defaultConfig = plugin.getDataFolder();

        if (!defaultConfig.exists()) defaultConfig.mkdirs();
        return YamlConfigurationLoader.builder()
                .defaultOptions(opts -> opts.shouldCopyDefaults(true))
                .path(defaultConfig.toPath().resolve("config.yml"))
                .build();
    }
}
