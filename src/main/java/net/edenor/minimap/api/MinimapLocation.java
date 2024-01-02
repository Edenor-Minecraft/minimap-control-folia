package net.edenor.minimap.api;

import org.bukkit.Location;

public record MinimapLocation(Location nativeLocation) {
    public MinimapWorld getWorld() {
        return new MinimapWorld(nativeLocation.getWorld());
    }
}
