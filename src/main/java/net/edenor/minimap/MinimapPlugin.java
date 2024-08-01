package net.edenor.minimap;

import net.edenor.minimap.jm.JMHandler;
import net.edenor.minimap.voxel.VoxelHandler;
import net.edenor.minimap.worldinfo.WorldInfoHandler;
import net.edenor.minimap.xaeros.XaerosHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class MinimapPlugin extends JavaPlugin {
    private static MinimapPlugin instance;
    public final JMHandler jmHandler = new JMHandler(this);
    public final XaerosHandler xaerosHandler = new XaerosHandler();
    public final WorldInfoHandler worldInfoHandler = new WorldInfoHandler();
    public final VoxelHandler voxelHandler = new VoxelHandler();
    public MinimapPlugin plugin;
    public ViaHook viaHook;
    public boolean viaHooked;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = plugin = this;
        getServer().getPluginManager().registerEvents(new MinimapEvents(this), this);
        MinimapConfig.initConfig();
        new MinimapListener(this);
        try {
            this.viaHook = new ViaHook();
            this.viaHooked = true;
        } catch (ClassNotFoundException | NoClassDefFoundError e ) {
            // failed to hook viaversion. Expected if viaversion isn't installed.
        }
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
}
