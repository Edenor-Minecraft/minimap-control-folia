package net.edenor.minimap.voxel;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import net.edenor.minimap.MinimapConfig;
import net.edenor.minimap.MinimapPlugin;
import net.edenor.minimap.network.NetworkUtils;
import net.edenor.minimap.voxel.data.VoxelConfig;
import net.edenor.minimap.voxel.data.VoxelWorldConfig;
import org.bukkit.entity.Player;

public class VoxelHandler {

    public String VOXEL_SETTINGS_CHANNEL = "voxelmap:settings";

    public VoxelHandler() {}

    public void sendSettings(Player player) {
        VoxelWorldConfig worldConfig = MinimapConfig.getWorldConfig(player.getLocation().getWorld().getName()).voxelWorldConfig;
        VoxelConfig config = MinimapConfig.globalVoxelConfig;
        if (worldConfig != null && worldConfig.enabled) {
            config = worldConfig;
        }

        String configJson = new Gson().toJson(config);

        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeByte(42);
        NetworkUtils.writeUtf(configJson, out);
        player.sendPluginMessage(MinimapPlugin.getInstance(), VOXEL_SETTINGS_CHANNEL, out.toByteArray());
    }
}
