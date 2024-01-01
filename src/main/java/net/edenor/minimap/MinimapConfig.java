package net.edenor.minimap;

import net.edenor.minimap.jm.data.JMConfig;
import net.edenor.minimap.jm.data.JMWorldConfig;
import net.edenor.minimap.xaeros.XaerosConfig;
import net.edenor.minimap.xaeros.XaerosWorldConfig;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ConfigSerializable
public class MinimapConfig extends @NotNull FileConfiguration {
    public JMConfig globalJourneymapConfig = new JMConfig();
    public XaerosConfig globalXaerosConfig = new XaerosConfig();
    public JMWorldConfig defaultWorldConfig = new JMWorldConfig();
    private Map<String, WorldConfig> worlds = new HashMap<>();

    public void addWorld(World world){
        worlds.put(world.getName(), new WorldConfig());
    }

    public WorldConfig getWorldConfig(String world) {
        WorldConfig conf = worlds.get(world);

        if (conf == null) {
            conf = new WorldConfig();
            worlds.put(world, conf);
            MinimapPlugin.getInstance().saveConfig();
        }

        return conf;
    }

    @Override
    public @NotNull String saveToString() {
        return null;
    }

    @Override
    public void loadFromString(@NotNull String contents) throws InvalidConfigurationException {

    }

    @ConfigSerializable
    public static class WorldConfig {
        public UUID worldId = UUID.randomUUID();
        public JMWorldConfig journeymapConfig = new JMWorldConfig();
        public XaerosWorldConfig xaerosConfig = new XaerosWorldConfig();
    }
}
