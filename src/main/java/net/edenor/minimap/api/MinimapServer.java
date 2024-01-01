package net.edenor.minimap.api;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.stream.Collectors;

public class MinimapServer{
    public String getMinecraftVersion() {
        return Bukkit.getVersion();
    }

    public String getLoaderVersion() {
        return Bukkit.getBukkitVersion();
    }

    public String getLoaderName() {
        return Bukkit.getName();
    }

    public List<MinimapPlayer> getPlayers() {
        return Bukkit.getServer().getOnlinePlayers().stream()
                .map(MinimapPlayer::new)
                .collect(Collectors.toList());
    }

    public List<MinimapWorld> getWorlds() {
        return Bukkit.getWorlds().stream()
                .map(MinimapWorld::new)
                .collect(Collectors.toList());
    }
}
