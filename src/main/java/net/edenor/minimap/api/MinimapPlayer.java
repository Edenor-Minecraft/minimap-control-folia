package net.edenor.minimap.api;

import net.edenor.minimap.MinimapPlugin;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MinimapPlayer{
    public Player nativePlayer;

    public MinimapPlayer(Player player) {
        nativePlayer = player;
    }

    public void sendPluginMessage(byte[] message, String channel) {
        Bukkit.getScheduler().runTask(MinimapPlugin.getInstance(), ()->nativePlayer.sendPluginMessage(MinimapPlugin.getInstance(), channel, message));
    }

    public void sendMessage(Component message) {
        MinimapPlugin.getInstance().adventure().player(nativePlayer).sendMessage(message);
    }

    public void teleport(MinimapLocation location) {
        nativePlayer.teleport(location.nativeLocation());
    }

    public MinimapLocation getLocation() {
        return new MinimapLocation(nativePlayer.getLocation());
    }

    public void disconnect(Component reason) {
        nativePlayer.kick(reason);
    }

    public boolean hasPermission(String string) {
        return nativePlayer.hasPermission(string);
    }
}
