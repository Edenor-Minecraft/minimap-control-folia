package net.edenor.minimap;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import net.edenor.minimap.api.MinimapPlayer;
import net.edenor.minimap.jm.JMHandler;
import net.edenor.minimap.worldinfo.WorldInfoHandler;
import net.edenor.minimap.xaeros.XaerosHandler;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinimapPlugin extends JavaPlugin implements @NotNull Listener, @NotNull PluginMessageListener {
    private static MinimapPlugin instance;
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
        instance = plugin = this;
        this.adventure = BukkitAudiences.create(this);
        getServer().getPluginManager().registerEvents(this, this);
        listenChannels.forEach(this::registerChannel);
        MinimapConfig.initConfig();
    }

    @Override
    public void onDisable() {
        saveConfig();
        // Plugin shutdown logic
        System.out.println("Disabled");
        if(this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }
    }
    public static MinimapPlugin getInstance() {
        return instance;
    }

    public void saveConfig() {
        try {
            MinimapConfig.saveConfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onPluginMessage(String channel, MinimapPlayer player, byte[] message) {
        switch (channel.split(":")[0]) {
            case "journeymap" -> jmHandler.onPluginMessage(channel, player, message);
            case "xaerominimap", "xaeroworldmap" -> xaerosHandler.onPluginMessage(channel, player, message);
            case "worldinfo" -> worldInfoHandler.onPluginMessage(channel, player, message);
        }
    }

    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, @NotNull byte[] message) {
        this.onPluginMessage(channel, new MinimapPlayer(player), message);
    }

    private final Map<String, ScheduledTask> playerTaskMap = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        plugin.getServer().getGlobalRegionScheduler().runDelayed(plugin,
                v->this.handlePackets(new MinimapPlayer(event.getPlayer())), 20L);

        ScheduledTask task = plugin.getServer().getGlobalRegionScheduler().runAtFixedRate(plugin,
                v->this.handlePackets(new MinimapPlayer(event.getPlayer())), 40L, 100L);
        playerTaskMap.put(event.getPlayer().getName(), task);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        ScheduledTask task = playerTaskMap.get(event.getPlayer().getName());
        if (task != null) {
            playerTaskMap.get(event.getPlayer().getName()).cancel();
            playerTaskMap.remove(event.getPlayer().getName());
        }
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        handlePackets(new MinimapPlayer(event.getPlayer()));
    }

    public void handlePackets(MinimapPlayer player) {
        //if (!MinimapConfig.globalVoxelConfig.enabled)
            //player.nativePlayer.sendPluginMessage(this, "voxel" Component.text("§3 §6 §3 §6 §3 §6 §e §r §3 §6 §3 §6 §3 §6 §d "));
        xaerosHandler.sendXaerosHandshake(player);
        xaerosHandler.sendXaerosConfig(player);
    }
}
