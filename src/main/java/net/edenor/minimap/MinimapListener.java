package net.edenor.minimap;

import net.edenor.minimap.api.MinimapPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class MinimapListener implements PluginMessageListener {

    private static final List<String> listenChannels = Arrays.asList(
            "journeymap:version",
            "journeymap:perm_req",
            "journeymap:admin_req",
            "journeymap:admin_save",
            "journeymap:teleport_req",
            "journeymap:common",
            "worldinfo:world_id",
            "xaerominimap:main",
            "xaeroworldmap:main",
            "voxelmap:settings"
    );

    private MinimapPlugin plugin;

    public MinimapListener(MinimapPlugin plugin){
        this.plugin = plugin;

        listenChannels.forEach(this::registerChannel);
    }

    public void registerChannel(String channel) {
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(MinimapPlugin.getInstance(), channel);
        plugin.getServer().getMessenger().registerIncomingPluginChannel(MinimapPlugin.getInstance(), channel, this);
    }

    public void onPluginMessage(String channel, MinimapPlayer player, byte[] message) {
        switch (channel.split(":")[0]) {
            case "journeymap" -> plugin.jmHandler.onPluginMessage(channel, player, message);
            case "xaerominimap", "xaeroworldmap" -> plugin.xaerosHandler.onPluginMessage(channel, player, message);
            case "worldinfo" -> plugin.worldInfoHandler.onPluginMessage(channel, player, message);
        }
    }

    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, @NotNull byte[] message) {
        this.onPluginMessage(channel, new MinimapPlayer(player), message);
    }
}
