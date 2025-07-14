package com.funniray.minimap.common.xaeros;

import com.funniray.minimap.common.api.MinimapPlayer;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class XaerosConfig {
    public boolean caveMode = true;
    public boolean netherCaveMode = true;
    public boolean radar = true;

    private boolean getOverride(String nodeSuffix, boolean def, MinimapPlayer player) {
        if (player.hasPermission("minimap.override."+nodeSuffix+".enabled")) {
            return true;
        } else if (player.hasPermission("minimap.override."+nodeSuffix+".disabled")) {
            return false;
        }

        return def;
    }

    public XaerosConfig applyOverrides(MinimapPlayer player) {
        XaerosConfig newConfig = new XaerosConfig();
        newConfig.radar = getOverride("radar", this.radar, player);
        newConfig.caveMode = getOverride("cave-mode", this.caveMode, player);
        newConfig.netherCaveMode = getOverride("nether-cave-mode", this.netherCaveMode, player);

        return newConfig;
    }
}
