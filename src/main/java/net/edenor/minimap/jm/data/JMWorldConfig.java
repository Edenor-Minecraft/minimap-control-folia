package net.edenor.minimap.jm.data;

import org.bukkit.configuration.file.FileConfiguration;

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

    public void loadFromConfig(FileConfiguration cfg){
        this.enabled = cfg.getString("default-world-config.enabled");
        this.teleportEnabled = cfg.getString("default-world-config.teleportEnabled");
        this.renderRange = cfg.getString("default-world-config.renderRange");
        this.surfaceMapping = cfg.getString("default-world-config.surfaceMapping");
        this.topoMapping = cfg.getString("default-world-config.topoMapping");
        this.biomeMapping = cfg.getString("default-world-config.biomeMapping");
        this.caveMapping = cfg.getString("default-world-config.caveMapping");
        this.radarEnabled = cfg.getString("default-world-config.radarEnabled");
        this.playerRadarEnabled = cfg.getString("default-world-config.playerRadarEnabled");
        this.villagerRadarEnabled = cfg.getString("default-world-config.villagerRadarEnabled");
        this.animalRadarEnabled = cfg.getString("default-world-config.animalRadarEnabled");
        this.mobRadarEnabled = cfg.getString("default-world-config.mobRadarEnabled");
        this.configVersion = cfg.getString("default-world-config.configVersion");
    }

    public String toString(){
        return "enabled: " + enabled + "\n" +
                "configVersion: " + configVersion + "\n" +
                "renderRange: " + renderRange + "\n" +
                "teleportEnabled: " + teleportEnabled + "\n" +
                "surfaceMapping: " + surfaceMapping + "\n" +
                "topoMapping: " + topoMapping + "\n" +
                "biomeMapping: " + biomeMapping + "\n" +
                "caveMapping: " + caveMapping + "\n" +
                "radarEnabled: " + radarEnabled + "\n" +
                "playerRadarEnabled: " + playerRadarEnabled + "\n" +
                "villagerRadarEnabled: " + villagerRadarEnabled + "\n" +
                "animalRadarEnabled: " + animalRadarEnabled + "\n" +
                "mobRadarEnabled: " + mobRadarEnabled;
    }
}
