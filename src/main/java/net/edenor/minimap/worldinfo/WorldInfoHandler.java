package net.edenor.minimap.worldinfo;

import net.edenor.minimap.MinimapConfig;
import net.edenor.minimap.MinimapPlugin;
import net.edenor.minimap.api.MessageHandler;
import net.edenor.minimap.api.MinimapPlayer;
import net.edenor.minimap.network.NetworkUtils;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import java.util.UUID;

public class WorldInfoHandler implements MessageHandler {

    public static String WORLDINFO_CHANNEL = "worldinfo:world_id";

    public WorldInfoHandler() {}

    @Override
    public void onPluginMessage(String channel, MinimapPlayer player, byte[] message) {
        /*if (!MinimapConfig.globalVoxelConfig.enabled){ //TODO Fix voxel map
            player.sendPluginMessage("§3 §6 §3 §6 §3 §6 §d §3 §6 §3 §6 §3 §6 §e".getBytes(), WORLDINFO_CHANNEL);
        }*/

        UUID worldId = MinimapConfig.getWorldConfig(player.getLocation().getWorld().getName()).worldId;
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeByte(0);
        out.writeByte(42);
        NetworkUtils.writeUtf(worldId.toString(), out);
        player.sendPluginMessage(out.toByteArray(), WORLDINFO_CHANNEL);
    }
}
