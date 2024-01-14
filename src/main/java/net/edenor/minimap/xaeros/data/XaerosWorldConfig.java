package net.edenor.minimap.xaeros.data;

import net.edenor.minimap.MinimapConfig;

import java.util.HashMap;
import java.util.Map;

public class XaerosWorldConfig extends XaerosConfig {
    public boolean enabled = false;

    public XaerosWorldConfig(){
        loadDefault();
    }

    public void loadDefault(){
        this.enabled = (boolean) MinimapConfig.get("default-world-config.xaeros.enabled", enabled);
        this.caveMode = (boolean) MinimapConfig.get("default-world-config.xaeros.caveMode", caveMode);
        this.netherCaveMode = (boolean) MinimapConfig.get("default-world-config.xaeros.netherCaveMode", netherCaveMode);
        this.radar = (boolean) MinimapConfig.get("default-world-config.xaeros.radar", radar);
    }

    public void loadFromConfig(String worldName){
        this.enabled = (boolean) MinimapConfig.get("worlds." + worldName + ".xaerosConfig.enabled", enabled);
        this.caveMode = (boolean) MinimapConfig.get("worlds." + worldName + ".xaerosConfig.caveMode", caveMode);
        this.netherCaveMode = (boolean) MinimapConfig.get("worlds." + worldName + ".xaerosConfig.netherCaveMode", netherCaveMode);
        this.radar = (boolean) MinimapConfig.get("worlds." + worldName + ".xaerosConfig.radar", radar);
    }

    public Map<String, Object> toMap() { //default-world-config.xaeros
        Map<String, Object> ret = new HashMap<>();
        ret.put("enabled",enabled);
        ret.put("caveMode", caveMode);
        ret.put("netherCaveMode", netherCaveMode);
        ret.put("radar", radar);

        return ret;
    }
}
