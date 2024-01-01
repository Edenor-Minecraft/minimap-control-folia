package net.edenor.minimap.api;

import net.edenor.minimap.MinimapPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

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
        nativePlayer.teleport(((MinimapLocation) location).getNativeLocation());
    }

    public MinimapLocation getLocation() {
        return new MinimapLocation(nativePlayer.getLocation());
    }

    public void disconnect(Component reason) {
        nativePlayer.kickPlayer(LegacyComponentSerializer.legacy('\u00a7').serialize(reason));
    }

    public UUID getUniqueId() {
        return nativePlayer.getUniqueId();
    }

    public String getUsername() {
        return nativePlayer.getName();
    }

    public boolean hasPermission(String string) {
        return nativePlayer.hasPermission(string);
    }
}
