package net.edenor.minimap;

import net.edenor.minimap.jm.data.JMConfig;
import net.edenor.minimap.jm.data.JMWorldConfig;
import net.edenor.minimap.voxel.data.VoxelConfig;
import net.edenor.minimap.voxel.data.VoxelWorldConfig;
import net.edenor.minimap.xaeros.XaerosConfig;
import net.edenor.minimap.xaeros.XaerosWorldConfig;
import org.bukkit.World;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MinimapConfig{
    public static JMConfig globalJourneymapConfig = new JMConfig();
    public static XaerosConfig globalXaerosConfig = new XaerosConfig();
    public static VoxelConfig globalVoxelConfig = new VoxelConfig();
    public static DefaultWorldConfig defaultWorldConfig;
    private static final Map<String, WorldConfig> worlds = new HashMap<>();
    private static FileConfiguration config;
    public static void initConfig(){
        config = MinimapPlugin.getInstance().getConfig();

        defaultWorldConfig = new DefaultWorldConfig();

        globalJourneymapConfig.loadFromConfig();
        globalXaerosConfig.loadFromConfig();
        globalVoxelConfig.loadFromConfig();

        for (World world : MinimapPlugin.getInstance().getServer().getWorlds()){
            addWorld(world);
        }

        try {
            if (!config.getBoolean("generated", false))
            {
                config.createSection("global-journeymap-config", globalJourneymapConfig.toMap());
                config.createSection("global-voxel-config", globalVoxelConfig.toMap());
                config.createSection("global-xaeros-config", globalXaerosConfig.toMap());
                defaultWorldConfig.addDefaultsToConfig(config);
                config.set("generated", true);
                saveConfig();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object get(String id, Object def) {
        if (config == null)
            throw new RuntimeException("Config is not initialized!");

        if (!config.contains(id)) {
            config.set(id, def);
        }
        return config.get(id);
    }

    public static void saveConfig() throws IOException {
        MinimapPlugin.getInstance().saveConfig();
    }

    public static void addWorld(World world){
        if (config.contains("worlds." + world.getName())){
            WorldConfig worldConfig = new WorldConfig();
            worldConfig.fromConfig(config, world.getName());
            worlds.put(world.getName(), worldConfig);
        }
        else {
            WorldConfig worldConfig = new WorldConfig();
            worlds.put(world.getName(), worldConfig);

            config.createSection("worlds." + world.getName(), worldConfig.toMap());
        }
    }

    public static WorldConfig getWorldConfig(String world) {
        WorldConfig conf = worlds.get(world);

        if (conf == null) {
            conf = new WorldConfig();
            worlds.put(world, conf);
            try {
                saveConfig();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return conf;
    }

    public static class WorldConfig {
        public UUID worldId = UUID.randomUUID();
        public JMWorldConfig journeymapConfig = new JMWorldConfig();
        public XaerosWorldConfig xaerosConfig = new XaerosWorldConfig();
        public VoxelWorldConfig voxelWorldConfig = new VoxelWorldConfig();

        public void fromConfig(FileConfiguration cfg, String worldName) {
            this.worldId = cfg.getObject("worlds." + worldName + ".worldId", UUID.class);
            this.journeymapConfig.loadFromConfig(worldName);
            this.xaerosConfig.loadFromConfig(worldName);
            this.voxelWorldConfig.loadFromConfig(worldName);
        }

        public Map<String, Object> toMap() {
            Map<String, Object> ret = new HashMap<>();
            ret.put("worldId", worldId.toString());
            ret.put("journeymapConfig", journeymapConfig.toMap());
            ret.put("xaerosConfig", xaerosConfig.toMap());
            ret.put("voxelConfig", voxelWorldConfig.toMap());

            return ret;
        }
    }
}
