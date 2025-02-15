package com.funniray.minimap.spigot.impl;

import com.funniray.minimap.common.api.MinimapLocation;
import com.funniray.minimap.common.api.MinimapPlayer;
import com.funniray.minimap.common.version.Version;
import com.funniray.minimap.spigot.SpigotMinimap;
import net.kyori.adventure.platform.bukkit.MinecraftComponentSerializer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SpigotPlayer implements MinimapPlayer {
    private final Player nativePlayer;

    public SpigotPlayer(Player player) {
        nativePlayer = player;
    }

    @Override
    public void sendPluginMessage(byte[] message, String channel) {
        Bukkit.getGlobalRegionScheduler().run(SpigotMinimap.getInstance(), v->nativePlayer.sendPluginMessage(SpigotMinimap.getInstance(), channel, message));
    }

    @Override
    public void sendMessage(Component message) {
        nativePlayer.sendMessage(message);
    }

    @Override
    public void teleport(MinimapLocation location) {
        nativePlayer.teleportAsync(((SpigotLocation) location).getNativeLocation());
    }

    @Override
    public MinimapLocation getLocation() {
        return new SpigotLocation(nativePlayer.getLocation());
    }

    @Override
    public void disconnect(Component reason) {
        nativePlayer.kick(reason);
    }

    @Override
    public UUID getUniqueId() {
        return nativePlayer.getUniqueId();
    }

    @Override
    public String getUsername() {
        return nativePlayer.getName();
    }

    @Override
    public boolean hasPermission(String string) {
        return nativePlayer.hasPermission(string);
    }

    @Override
    public Version getVersion() {
        SpigotMinimap plugin = SpigotMinimap.getInstance();
        if (plugin.viaHooked) {
            return plugin.viaHook.getPlayerVersion(this);
        } else {
            return new SpigotServer().getMinecraftVersion();
        }
    }

    public Player getNativePlayer() {
        return nativePlayer;
    }
}
