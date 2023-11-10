package com.funniray.minimap.common;

import com.funniray.minimap.common.api.MinimapWorld;
import com.funniray.minimap.common.jm.data.JMConfig;
import com.funniray.minimap.common.jm.data.JMWorldConfig;
import com.funniray.minimap.common.xaeros.XaerosConfig;
import com.funniray.minimap.common.xaeros.XaerosWorldConfig;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@ConfigSerializable
public class MinimapConfig {
    public JMConfig globalJourneymapConfig = new JMConfig();
    public XaerosConfig globalXaerosConfig = new XaerosConfig();
    public JMWorldConfig defaultWorldConfig = new JMWorldConfig();
    private Map<String, WorldConfig> worlds = JavaMinimapPlugin.getInstance().getServer().getWorlds().stream()
            .map(MinimapWorld::getName)
            .collect(Collectors.toMap(s->s, s->new WorldConfig()));

    public WorldConfig getWorldConfig(String world) {
        WorldConfig conf = worlds.get(world);

        if (conf == null) {
            conf = new WorldConfig();
            worlds.put(world, conf);
            JavaMinimapPlugin.getInstance().saveConfig();
        }

        return conf;
    }

    @ConfigSerializable
    public static class WorldConfig {
        public UUID worldId = UUID.randomUUID();
        public JMWorldConfig journeymapConfig = new JMWorldConfig();
        public XaerosWorldConfig xaerosConfig = new XaerosWorldConfig();
    }
}
