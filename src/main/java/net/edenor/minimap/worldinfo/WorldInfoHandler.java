package net.edenor.minimap.worldinfo;

import net.edenor.minimap.MinimapConfig;
import net.edenor.minimap.MinimapPlugin;
import net.edenor.minimap.network.NetworkUtils;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;

import java.util.UUID;

public class WorldInfoHandler{

    public static String WORLDINFO_CHANNEL = "worldinfo:world_id";

    public WorldInfoHandler() {}

    public void onPluginMessage(Player player) {
        UUID worldId = MinimapConfig.getWorldConfig(player.getLocation().getWorld().getName()).worldId;
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeByte(0);
        out.writeByte(42);
        NetworkUtils.writeUtf(worldId.toString(), out);
        player.sendPluginMessage(MinimapPlugin.getInstance(), WORLDINFO_CHANNEL, out.toByteArray());
    }
}
