package net.edenor.minimap.voxel;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import net.edenor.minimap.MinimapConfig;
import net.edenor.minimap.MinimapPlugin;
import net.edenor.minimap.api.MinimapPlayer;
import net.edenor.minimap.network.NetworkUtils;
import net.edenor.minimap.voxel.data.VoxelConfig;
import net.edenor.minimap.voxel.data.VoxelWorldConfig;

public class VoxelHandler {
    private final MinimapPlugin plugin;

    public String VOXEL_SETTINGS_CHANNEL = "voxelmap:settings";

    public VoxelHandler(MinimapPlugin plugin) {
        this.plugin = plugin;
    }

    public void sendSettings(MinimapPlayer player) {
        VoxelWorldConfig worldConfig = MinimapConfig.getWorldConfig(player.getLocation().getWorld().getName()).voxelWorldConfig;
        VoxelConfig config = MinimapConfig.globalVoxelConfig;
        if (worldConfig != null && worldConfig.enabled) {
            config = worldConfig;
        }

        String configJson = new Gson().toJson(config);

        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeByte(42);
        NetworkUtils.writeUtf(configJson, out);
        player.sendPluginMessage(out.toByteArray(), VOXEL_SETTINGS_CHANNEL);
        System.out.println("sent: "+configJson);
    }
}
