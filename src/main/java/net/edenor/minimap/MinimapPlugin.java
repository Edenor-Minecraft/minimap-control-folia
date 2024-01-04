package net.edenor.minimap;

import net.edenor.minimap.jm.JMHandler;
import net.edenor.minimap.worldinfo.WorldInfoHandler;
import net.edenor.minimap.xaeros.XaerosHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
public class MinimapPlugin extends JavaPlugin {
    private static MinimapPlugin instance;
    public final JMHandler jmHandler = new JMHandler(this);
    public final XaerosHandler xaerosHandler = new XaerosHandler();
    public final WorldInfoHandler worldInfoHandler = new WorldInfoHandler();

    public MinimapPlugin plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = plugin = this;
        getServer().getPluginManager().registerEvents(new MinimapEvents(this), this);
        MinimapConfig.initConfig();
        new MinimapListener(this);
    }

    @Override
    public void onDisable() {
        saveConfig();
        // Plugin shutdown logic
        getLogger().info("Disabled");
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
}
