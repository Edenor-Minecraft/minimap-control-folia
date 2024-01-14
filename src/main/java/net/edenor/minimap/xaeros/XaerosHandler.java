package net.edenor.minimap.xaeros;

import net.edenor.minimap.MinimapConfig;
import net.edenor.minimap.MinimapPlugin;
import net.edenor.minimap.network.NetworkUtils;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import dev.dewy.nbt.Nbt;
import dev.dewy.nbt.tags.collection.CompoundTag;
import net.edenor.minimap.xaeros.data.XaerosConfig;
import net.edenor.minimap.xaeros.data.XaerosWorldConfig;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

import java.io.IOException;

public class XaerosHandler{

    public static String XAEROS_CHANNEL = "xaerominimap:main";
    public static String XAEROS_MAP_CHANNEL = "xaeroworldmap:main";


    public XaerosHandler() {}

    public void sendXaerosHandshake(Player player) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeByte(1);
        out.writeInt(2);
        player.sendPluginMessage(MinimapPlugin.getInstance(), XAEROS_CHANNEL, out.toByteArray());
        player.sendPluginMessage(MinimapPlugin.getInstance(), XAEROS_MAP_CHANNEL, out.toByteArray());
    }

    public void sendXaerosConfig(Player player) {
        XaerosWorldConfig worldConfig = MinimapConfig.getWorldConfig(player.getLocation().getWorld().getName()).xaerosConfig;
        XaerosConfig config = MinimapConfig.globalXaerosConfig;
        if (worldConfig != null && worldConfig.enabled) {
            config = worldConfig;
        }

        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeByte(4);
        CompoundTag tag = new CompoundTag();
        tag.putByte("cm", NetworkUtils.booleanToByte(config.caveMode));
        tag.putByte("ncm", NetworkUtils.booleanToByte(config.netherCaveMode));
        tag.putByte("r", NetworkUtils.booleanToByte(config.radar));
        try {
            new Nbt().toStream(tag, out);
            byte[] arr = out.toByteArray();
            player.sendPluginMessage(MinimapPlugin.getInstance(), XAEROS_CHANNEL, arr);
            player.sendPluginMessage(MinimapPlugin.getInstance(), XAEROS_MAP_CHANNEL, out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onPluginMessage(Player player, byte[] message) {
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        if (in.readByte() == 1) {
            int version = in.readInt();
            if (version < 2) {
                player.kick(MiniMessage.miniMessage().deserialize("<red>Xaero's Minimap is outdated.\n Please update to 23.7.0 or later."));
                return;
            }

            sendXaerosConfig(player);
        }
    }
}
