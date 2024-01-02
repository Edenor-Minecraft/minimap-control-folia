package net.edenor.minimap.api;

import org.bukkit.Location;
import org.bukkit.World;

public class MinimapWorld{
    private final World nativeWorld;

    public MinimapWorld(World nativeWorld) {
        this.nativeWorld = nativeWorld;
    }

    public String getName() {
        return nativeWorld.getName();
    }

    public MinimapLocation getLocation(double x, double y, double z) {
        Location location = new Location(nativeWorld, x, y, z);

        return new MinimapLocation(location);
    }
}
