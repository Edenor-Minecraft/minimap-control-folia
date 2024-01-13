package net.edenor.minimap.jm.data;

import net.edenor.minimap.MinimapConfig;

import java.util.HashMap;
import java.util.Map;

public class JMWorldConfig {
    public String enabled = "false";
    public String teleportEnabled = "false";
    public String renderRange = "0";
    public String surfaceMapping = "ALL";
    public String topoMapping = "ALL";
    public String biomeMapping = "ALL";
    public String caveMapping = "ALL";
    public String radarEnabled = "ALL";
    public String playerRadarEnabled = "true";
    public String villagerRadarEnabled = "true";
    public String animalRadarEnabled = "true";
    public String mobRadarEnabled = "true";
    public String configVersion = JMVersion.journeymap_version.full;

    public JMConfig applyToConfig(JMConfig config) {
        if (!enabled.equalsIgnoreCase("true")) return config;

        JMConfig clone = config.copy();
        clone.teleportEnabled = this.teleportEnabled;
        clone.renderRange = this.renderRange;
        clone.surfaceMapping = this.surfaceMapping;
        clone.topoMapping = this.topoMapping;
        clone.biomeMapping = this.biomeMapping;
        clone.caveMapping = this.caveMapping;
        clone.radarEnabled = this.radarEnabled;
        clone.playerRadarEnabled = this.playerRadarEnabled;
        clone.villagerRadarEnabled = this.villagerRadarEnabled;
        clone.animalRadarEnabled = this.animalRadarEnabled;
        clone.mobRadarEnabled = this.mobRadarEnabled;
        clone.configVersion = this.configVersion;
        return clone;
    }

    public JMWorldConfig(){
        loadDefault();
    }

    public void loadDefault(){
        this.enabled = (String) MinimapConfig.get("default-world-config.jm.enabled", enabled);
        this.teleportEnabled = (String) MinimapConfig.get("default-world-config.jm.teleportEnabled", teleportEnabled);
        this.renderRange = (String) MinimapConfig.get("default-world-config.jm.renderRange", renderRange);
        this.surfaceMapping = (String) MinimapConfig.get("default-world-config.jm.surfaceMapping", surfaceMapping);
        this.topoMapping = (String) MinimapConfig.get("default-world-config.jm.topoMapping", topoMapping);
        this.biomeMapping = (String) MinimapConfig.get("default-world-config.jm.biomeMapping", biomeMapping);
        this.caveMapping = (String) MinimapConfig.get("default-world-config.jm.caveMapping", caveMapping);
        this.radarEnabled = (String) MinimapConfig.get("default-world-config.jm.radarEnabled", radarEnabled);
        this.playerRadarEnabled = (String) MinimapConfig.get("default-world-config.jm.playerRadarEnabled", playerRadarEnabled);
        this.villagerRadarEnabled = (String) MinimapConfig.get("default-world-config.jm.villagerRadarEnabled", villagerRadarEnabled);
        this.animalRadarEnabled = (String) MinimapConfig.get("default-world-config.jm.animalRadarEnabled", animalRadarEnabled);
        this.mobRadarEnabled = (String) MinimapConfig.get("default-world-config.jm.mobRadarEnabled", mobRadarEnabled);
        this.configVersion = (String) MinimapConfig.get("default-world-config.jm.configVersion", configVersion);
    }

    public void loadFromConfig(String worldName){
        this.enabled = (String) MinimapConfig.get("worlds." + worldName + ".journeymapConfig.enabled", enabled);
        this.teleportEnabled = (String) MinimapConfig.get("worlds." + worldName + ".journeymapConfig.teleportEnabled", teleportEnabled);
        this.renderRange = (String) MinimapConfig.get("worlds." + worldName + ".journeymapConfig.renderRange", renderRange);
        this.surfaceMapping = (String) MinimapConfig.get("worlds." + worldName + ".journeymapConfig.surfaceMapping", surfaceMapping);
        this.topoMapping = (String) MinimapConfig.get("worlds." + worldName + ".journeymapConfig.topoMapping", topoMapping);
        this.biomeMapping = (String) MinimapConfig.get("worlds." + worldName + ".journeymapConfig.biomeMapping", biomeMapping);
        this.caveMapping = (String) MinimapConfig.get("worlds." + worldName + ".journeymapConfig.caveMapping", caveMapping);
        this.radarEnabled = (String) MinimapConfig.get("worlds." + worldName + ".journeymapConfig.radarEnabled", radarEnabled);
        this.playerRadarEnabled = (String) MinimapConfig.get("worlds." + worldName + ".journeymapConfig.playerRadarEnabled", playerRadarEnabled);
        this.villagerRadarEnabled = (String) MinimapConfig.get("worlds." + worldName + ".journeymapConfig.villagerRadarEnabled", villagerRadarEnabled);
        this.animalRadarEnabled = (String) MinimapConfig.get("worlds." + worldName + ".journeymapConfig.animalRadarEnabled", animalRadarEnabled);
        this.mobRadarEnabled = (String) MinimapConfig.get("worlds." + worldName + ".journeymapConfig.mobRadarEnabled", mobRadarEnabled);
        this.configVersion = (String) MinimapConfig.get("worlds." + worldName + ".journeymapConfig.configVersion", configVersion);
    }

    public Map<String, String> toMap() { //default-world-config.jm
        Map<String, String> ret = new HashMap<>();
        ret.put("enabled", enabled);
        ret.put("teleportEnabled", teleportEnabled);
        ret.put("renderRange", renderRange);
        ret.put("surfaceMapping", surfaceMapping);
        ret.put("topoMapping", topoMapping);
        ret.put("biomeMapping", biomeMapping);
        ret.put("caveMapping", caveMapping);
        ret.put("radarEnabled", radarEnabled);
        ret.put("playerRadarEnabled", playerRadarEnabled);
        ret.put("villagerRadarEnabled", villagerRadarEnabled);
        ret.put("animalRadarEnabled", animalRadarEnabled);
        ret.put("mobRadarEnabled", mobRadarEnabled);

        return ret;
    }
}
