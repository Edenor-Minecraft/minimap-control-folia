package net.edenor.minimap.jm.data;

import net.edenor.minimap.MinimapConfig;

import java.util.HashMap;
import java.util.Map;

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

    public void loadFromConfig(){
        this.journeymapEnabled = (String) MinimapConfig.get("global-journeymap-config.journeymapEnabled", journeymapEnabled);
        this.useWorldId = (String) MinimapConfig.get("global-journeymap-config.useWorldId", useWorldId);
        this.viewOnlyServerProperties = (String) MinimapConfig.get("global-journeymap-config.viewOnlyServerProperties", viewOnlyServerProperties);
        this.allowMultiplayerSettings = (String) MinimapConfig.get("global-journeymap-config.allowMultiplayerSettings", allowMultiplayerSettings);
        this.worldPlayerRadar = (String) MinimapConfig.get("global-journeymap-config.worldPlayerRadar", worldPlayerRadar);
        this.worldPlayerRadarUpdateTime = (String) MinimapConfig.get("global-journeymap-config.worldPlayerRadarUpdateTime", worldPlayerRadarUpdateTime);
        this.seeUndergroundPlayers = (String) MinimapConfig.get("global-journeymap-config.seeUndergroundPlayers", seeUndergroundPlayers);
        this.hideOps = (String) MinimapConfig.get("global-journeymap-config.hideOps", hideOps);
        this.hideSpectators = (String) MinimapConfig.get("global-journeymap-config.hideSpectators", hideSpectators);
        this.allowDeathPoints = (String) MinimapConfig.get("global-journeymap-config.allowDeathPoints", allowDeathPoints);
        this.showInGameBeacons = (String) MinimapConfig.get("global-journeymap-config.showInGameBeacons", showInGameBeacons);
        this.allowWaypoints = (String) MinimapConfig.get("global-journeymap-config.allowWaypoints", allowWaypoints);
        this.teleportEnabled = (String) MinimapConfig.get("global-journeymap-config.teleportEnabled", teleportEnabled);
        this.renderRange = (String) MinimapConfig.get("global-journeymap-config.renderRange", renderRange);
        this.surfaceMapping = (String) MinimapConfig.get("global-journeymap-config.surfaceMapping", surfaceMapping);
        this.topoMapping = (String) MinimapConfig.get("global-journeymap-config.topoMapping", topoMapping);
        this.biomeMapping = (String) MinimapConfig.get("global-journeymap-config.biomeMapping", biomeMapping);
        this.caveMapping = (String) MinimapConfig.get("global-journeymap-config.caveMapping", caveMapping);
        this.radarEnabled = (String) MinimapConfig.get("global-journeymap-config.radarEnabled", radarEnabled);
        this.playerRadarEnabled = (String) MinimapConfig.get("global-journeymap-config.playerRadarEnabled", playerRadarEnabled);
        this.villagerRadarEnabled = (String) MinimapConfig.get("global-journeymap-config.villagerRadarEnabled", villagerRadarEnabled);
        this.animalRadarEnabled = (String) MinimapConfig.get("global-journeymap-config.animalRadarEnabled", animalRadarEnabled);
        this.mobRadarEnabled = (String) MinimapConfig.get("global-journeymap-config.mobRadarEnabled", mobRadarEnabled);
    }

    public Map<String, String> toMap() {
        Map<String, String> ret = new HashMap<>(); //global-journeymap-config
        ret.put("journeymapEnabled", journeymapEnabled);
        ret.put("useWorldId", useWorldId);
        ret.put("viewOnlyServerProperties", viewOnlyServerProperties);
        ret.put("allowMultiplayerSettings", allowMultiplayerSettings);
        ret.put("worldPlayerRadar", worldPlayerRadar);
        ret.put("worldPlayerRadarUpdateTime", worldPlayerRadarUpdateTime);
        ret.put(".seeUndergroundPlayers", seeUndergroundPlayers);
        ret.put("hideOps", hideOps);
        ret.put("hideSpectators", hideSpectators);
        ret.put("allowDeathPoints", allowDeathPoints);
        ret.put("showInGameBeacons", showInGameBeacons);
        ret.put("allowWaypoints", allowWaypoints);
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
