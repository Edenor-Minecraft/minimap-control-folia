package net.edenor.minimap.voxel.data;

import net.edenor.minimap.MinimapConfig;
import org.bukkit.configuration.MemoryConfiguration;

import java.util.HashMap;
import java.util.Map;

public class VoxelConfig {
    public boolean radarAllowed = true;
    public boolean radarMobsAllowed = true;
    public boolean radarPlayersAllowed = true;
    public boolean cavesAllowed = true;
    public String teleportCommand = "tp %p %x %y %z";

    public void loadFromConfig(){
        this.radarAllowed = (boolean) MinimapConfig.get("global-voxel-config.radar-allowed", radarAllowed);
        this.radarMobsAllowed = (boolean) MinimapConfig.get("global-voxel-config.radar-mobs-allowed", radarMobsAllowed);
        this.radarPlayersAllowed = (boolean) MinimapConfig.get("global-voxel-config.radar-players-allowed", radarPlayersAllowed);
        this.cavesAllowed = (boolean) MinimapConfig.get("global-voxel-config.caves-allowed", cavesAllowed);
        this.teleportCommand = (String) MinimapConfig.get("global-voxel-config.teleport-command", teleportCommand);
    }

    public Map<String, Object> toMap() { //global-voxel-config
        Map<String, Object> ret = new HashMap<>();
        ret.put("radar-allowed", radarAllowed);
        ret.put("radar-mobs-allowed", radarMobsAllowed);
        ret.put("radar-players-allowed", radarPlayersAllowed);
        ret.put("caves-allowed", cavesAllowed);
        ret.put("teleport-command", teleportCommand);

        return ret;
    }
}
