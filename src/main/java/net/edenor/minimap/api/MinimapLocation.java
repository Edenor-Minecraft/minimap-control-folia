package net.edenor.minimap.api;

import org.bukkit.Location;

public class MinimapLocation{
    private Location nativeLocation;

    public MinimapLocation(Location nativeLocation) {
        this.nativeLocation = nativeLocation;
    }

    public double getX() {
        return nativeLocation.getX();
    }

    public double getY() {
        return nativeLocation.getY();
    }

    public double getZ() {
        return nativeLocation.getZ();
    }

    public MinimapWorld getWorld() {
        return new MinimapWorld(nativeLocation.getWorld());
    }

    public Location getNativeLocation() {
        return nativeLocation;
    }
}
