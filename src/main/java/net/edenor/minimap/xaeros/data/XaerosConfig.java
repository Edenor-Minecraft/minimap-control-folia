package net.edenor.minimap.xaeros.data;

import net.edenor.minimap.MinimapConfig;

import java.util.HashMap;
import java.util.Map;

public class XaerosConfig {
    public boolean caveMode = true;
    public boolean netherCaveMode = true;
    public boolean radar = true;

    public void loadFromConfig(){
        this.caveMode = (boolean) MinimapConfig.get("global-xaeros-config.caveMode", caveMode);
        this.netherCaveMode = (boolean) MinimapConfig.get("global-xaeros-config.netherCaveMode", netherCaveMode);
        this.radar = (boolean) MinimapConfig.get("global-xaeros-config.radar", radar);
    }

    public Map<String, Object> toMap() { //global-xaeros-config
        Map<String, Object> ret = new HashMap<>();
        ret.put("caveMode", caveMode);
        ret.put("netherCaveMode", netherCaveMode);
        ret.put("radar", radar);

        return ret;
    }
}
