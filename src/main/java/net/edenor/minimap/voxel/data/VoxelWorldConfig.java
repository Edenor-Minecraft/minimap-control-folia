package net.edenor.minimap.voxel.data;

import net.edenor.minimap.MinimapConfig;

import java.util.HashMap;
import java.util.Map;

public class VoxelWorldConfig extends VoxelConfig{

    public boolean enabled = false;

    public VoxelWorldConfig(){
        loadDefault();
    }

    public void loadDefault(){
        this.enabled = (boolean) MinimapConfig.get("default-world-config.voxel.enabled", enabled);
        this.radarAllowed = (boolean) MinimapConfig.get("global-voxel-config.radar-allowed", radarAllowed);
        this.radarMobsAllowed = (boolean) MinimapConfig.get("global-voxel-config.radar-mobs-allowed", radarMobsAllowed);
        this.radarPlayersAllowed = (boolean) MinimapConfig.get("global-voxel-config.radar-players-allowed", radarPlayersAllowed);
        this.cavesAllowed = (boolean) MinimapConfig.get("global-voxel-config.caves-allowed", cavesAllowed);
        this.teleportCommand = (String) MinimapConfig.get("global-voxel-config.teleport-command", teleportCommand);
    }

    public void loadFromConfig(String worldName){
        this.enabled = (boolean) MinimapConfig.get("worlds." + worldName + ".voxelConfig.enabled", enabled);
        this.radarAllowed = (boolean) MinimapConfig.get("worlds." + worldName + ".voxelConfig.radar-allowed", radarAllowed);
        this.radarMobsAllowed = (boolean) MinimapConfig.get("worlds." + worldName + ".voxelConfig.radar-mobs-allowed", radarMobsAllowed);
        this.radarPlayersAllowed = (boolean) MinimapConfig.get("worlds." + worldName + ".voxelConfig.radar-players-allowed", radarPlayersAllowed);
        this.cavesAllowed = (boolean) MinimapConfig.get("worlds." + worldName + ".voxelConfig.caves-allowed", cavesAllowed);
        this.teleportCommand = (String) MinimapConfig.get("worlds." + worldName + ".voxelConfig.teleport-command", teleportCommand);
    }

    public Map<String, Object> toMap() { //default-world-config.voxel
        Map<String, Object> ret = new HashMap<>();
        ret.put("enabled", enabled);
        ret.put("radar-allowed", radarAllowed);
        ret.put("radar-mobs-allowed", radarMobsAllowed);
        ret.put("radar-players-allowed", radarPlayersAllowed);
        ret.put("caves-allowed", cavesAllowed);
        ret.put("teleport-command", teleportCommand);

        return ret;
    }
}
