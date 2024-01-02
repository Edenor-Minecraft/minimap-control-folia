package net.edenor.minimap.jm.data;

import org.bukkit.configuration.file.FileConfiguration;

public class JMConfig {
    public String journeymapEnabled = "true";
    public String useWorldId = "true";
    public String viewOnlyServerProperties = "true";
    public String allowMultiplayerSettings = "ALL";
    public String worldPlayerRadar = "ALL";
    public String worldPlayerRadarUpdateTime = "5";
    public String seeUndergroundPlayers = "ALL";
    public String hideOps = "false";
    public String hideSpectators = "false";
    public String allowDeathPoints = "true";
    public String showInGameBeacons = "true";
    public String allowWaypoints = "true";
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

    public JMConfig copy() {
        JMConfig clone = new JMConfig();
        clone.journeymapEnabled = this.journeymapEnabled;
        clone.useWorldId = this.useWorldId;
        clone.viewOnlyServerProperties = this.viewOnlyServerProperties;
        clone.allowMultiplayerSettings = this.allowMultiplayerSettings;
        clone.worldPlayerRadar = this.worldPlayerRadar;
        clone.worldPlayerRadarUpdateTime = this.worldPlayerRadarUpdateTime;
        clone.seeUndergroundPlayers = this.seeUndergroundPlayers;
        clone.hideOps = this.hideOps;
        clone.hideSpectators = this.hideSpectators;
        clone.allowDeathPoints = this.allowDeathPoints;
        clone.showInGameBeacons = this.showInGameBeacons;
        clone.allowWaypoints = this.allowWaypoints;
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

        return clone;
    }

    public void loadFromConfig(FileConfiguration cfg){
        this.journeymapEnabled = cfg.getString("global-journeymap-config.journeymapEnabled");
        this.useWorldId = cfg.getString("global-journeymap-config.useWorldId");
        this.viewOnlyServerProperties = cfg.getString("global-journeymap-config.viewOnlyServerProperties");
        this.allowMultiplayerSettings = cfg.getString("global-journeymap-config.allowMultiplayerSettings");
        this.worldPlayerRadar = cfg.getString("global-journeymap-config.worldPlayerRadar");
        this.worldPlayerRadarUpdateTime = cfg.getString("global-journeymap-config.worldPlayerRadarUpdateTime");
        this.seeUndergroundPlayers = cfg.getString("global-journeymap-config.seeUndergroundPlayers");
        this.hideOps = cfg.getString("global-journeymap-config.hideOps");
        this.hideSpectators = cfg.getString("global-journeymap-config.hideSpectators");
        this.allowDeathPoints = cfg.getString("global-journeymap-config.allowDeathPoints");
        this.showInGameBeacons = cfg.getString("global-journeymap-config.showInGameBeacons");
        this.allowWaypoints = cfg.getString("global-journeymap-config.allowWaypoints");
        this.teleportEnabled = cfg.getString("global-journeymap-config.teleportEnabled");
        this.renderRange = cfg.getString("global-journeymap-config.renderRange");
        this.surfaceMapping = cfg.getString("global-journeymap-config.surfaceMapping");
        this.topoMapping = cfg.getString("global-journeymap-config.topoMapping");
        this.biomeMapping = cfg.getString("global-journeymap-config.biomeMapping");
        this.caveMapping = cfg.getString("global-journeymap-config.caveMapping");
        this.radarEnabled = cfg.getString("global-journeymap-config.radarEnabled");
        this.playerRadarEnabled = cfg.getString("global-journeymap-config.playerRadarEnabled");
        this.villagerRadarEnabled = cfg.getString("global-journeymap-config.villagerRadarEnabled");
        this.animalRadarEnabled = cfg.getString("global-journeymap-config.animalRadarEnabled");
        this.mobRadarEnabled = cfg.getString("global-journeymap-config.mobRadarEnabled");
    }

    public String toString(){
        return "journeymapEnabled: " + journeymapEnabled + "\n" +
                "useWorldId: " + useWorldId + "\n" +
                "viewOnlyServerProperties: " + viewOnlyServerProperties + "\n" +
                "allowMultiplayerSettings: " + allowMultiplayerSettings + "\n" +
                "worldPlayerRadar: " + worldPlayerRadar + "\n" +
                "worldPlayerRadarUpdateTime: " + worldPlayerRadarUpdateTime + "\n" +
                "seeUndergroundPlayers: " + seeUndergroundPlayers + "\n" +
                "hideOps: " + hideOps + "\n" +
                "hideSpectators: " + hideSpectators + "\n" +
                "allowDeathPoints: " + allowDeathPoints + "\n" +
                "showInGameBeacons: " + showInGameBeacons + "\n" +
                "allowWaypoints: " + allowWaypoints + "\n" +
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
