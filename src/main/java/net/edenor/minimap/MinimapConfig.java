package net.edenor.minimap;

import net.edenor.minimap.jm.data.JMConfig;
import net.edenor.minimap.jm.data.JMWorldConfig;
import net.edenor.minimap.voxel.VoxelMapConfig;
import net.edenor.minimap.xaeros.XaerosConfig;
import net.edenor.minimap.xaeros.XaerosWorldConfig;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MinimapConfig{
    public static JMConfig globalJourneymapConfig = new JMConfig();
    public static XaerosConfig globalXaerosConfig = new XaerosConfig();
    public static JMWorldConfig defaultWorldConfig = new JMWorldConfig();
    public static VoxelMapConfig globalVoxelConfig = new VoxelMapConfig();
    private static final Map<String, WorldConfig> worlds = new HashMap<>();
    private static FileConfiguration config;
    public static void initConfig(){
        config = MinimapPlugin.getInstance().getConfig();

        globalJourneymapConfig.loadFromConfig(config);
        globalXaerosConfig.loadFromConfig(config);
        globalVoxelConfig.loadFromConfig(config);
        defaultWorldConfig.loadFromConfig(config);

        for (World world : MinimapPlugin.getInstance().getServer().getWorlds()){
            addWorld(world);
        }
    }

    public static void saveConfig() throws IOException {
        config.save("config.yml");
    }

    public static void addWorld(World world){
        if (!config.contains("worlds"))
        {
            config.createSection("worlds");
        }
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

        public void fromConfig(FileConfiguration cfg, String worldName) {
            this.worldId = cfg.getObject("worlds." + worldName + ".worldId", UUID.class);
            this.journeymapConfig.loadFromConfig(cfg);
            this.xaerosConfig.loadFromConfig(cfg);
        }

        public String toString(){
            return "worldId: " + worldId + "\n" +
                    "journeymapConfig: " + '\n' + journeymapConfig.toString() + "\n" +
                    "xaerosConfig: "  + '\n' + xaerosConfig.toString();
        }

        public Map<String, String> toMap() {
            Map<String, String> ret = new HashMap<>();
            ret.put("worldId", worldId.toString());
            ret.put("journeymapConfig", journeymapConfig.toString());
            ret.put("xaerosConfig", xaerosConfig.toString());

            return ret;
        }
    }
}
