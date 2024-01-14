package net.edenor.minimap.jm;

import net.edenor.minimap.MinimapPlugin;
import net.edenor.minimap.MinimapConfig;
import net.edenor.minimap.jm.data.JMConfig;
import net.edenor.minimap.jm.data.JMVersion;
import net.edenor.minimap.jm.data.JMWorldConfig;
import net.edenor.minimap.jm.data.ServerPropType;
import net.edenor.minimap.network.NetworkUtils;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JMHandler{
    private final MinimapPlugin plugin;

    public JMHandler(MinimapPlugin plugin) {
        this.plugin = plugin;
    }

    public void handleMPOptions(Player player) {
        player.sendMessage(MiniMessage.miniMessage().deserialize("<red>Multiplayer options are not implemented."));
    }

    public void handleTeleport(Player player, byte[] message) {
        if (!player.hasPermission("minimap.jm.teleport")) {
            player.sendMessage(MiniMessage.miniMessage().deserialize("<red>You don't have permission to teleport."));
        }

        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        in.readByte();
        double x = in.readDouble();
        double y = in.readDouble();
        double z = in.readDouble();
        String dim = NetworkUtils.readUtf(in);

        Optional<World> world = plugin.getServer().getWorlds().stream().filter(w->w.getName().equals(dim)).findFirst();
        if (world.isEmpty()){
            player.sendMessage(MiniMessage.miniMessage().deserialize("<red>That world doesn't exist"));
            return;
        }

        player.teleportAsync(new Location(world.get(), x, y, z));
    }

    public void handleAdminReq(Player player, byte[] message, String replyChannel, int replyByte) {
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        in.readByte();
        in.readByte();
        ServerPropType type = ServerPropType.getFromType(in.readInt());
        Gson gson = new Gson();
        if (type != ServerPropType.GLOBAL) {
            return;
        }

        HashMap<String, String> payloads = new HashMap<>();

        payloads.put("GLOBAL", gson.toJson(MinimapConfig.globalJourneymapConfig));
        payloads.put("DEFAULT", gson.toJson(MinimapConfig.defaultWorldConfig.defaultJMWorldConfig));

        for (World world : plugin.getServer().getWorlds()){
            payloads.put(world.getName(),gson.toJson(MinimapConfig.getWorldConfig(world.getName())));
        }

        for (Map.Entry<String, String> ent : payloads.entrySet()) {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeByte(replyByte);
            out.writeByte(42);
            if (ent.getKey().equals("GLOBAL")) {
                out.writeInt(ServerPropType.GLOBAL.getId());
                NetworkUtils.writeUtf("", out);
            } else if (ent.getKey().equals("DEFAULT")) {
                out.writeInt(ServerPropType.DEFAULT.getId());
                NetworkUtils.writeUtf("", out);
            } else {
                out.writeInt(ServerPropType.DIMENSION.getId());
                NetworkUtils.writeUtf(ent.getKey(), out);
            }
            NetworkUtils.writeUtf(ent.getValue(), out);
            player.sendPluginMessage(plugin, replyChannel, out.toByteArray());
        }
    }

    public void handleAdminSave(Player player, byte[] message, String replyChannel) {
        if (!player.hasPermission("minimap.jm.admin")) return;

        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        in.readByte();
        in.readByte();
        int type = in.readInt();
        String dimension = NetworkUtils.readUtf(in);
        String payload = NetworkUtils.readUtf(in);

        Gson gson = new Gson();
        if (type == 1) {
            MinimapConfig.globalJourneymapConfig = gson.fromJson(payload, JMConfig.class);
        } else if (type == 2 || type == 3) {
            JMWorldConfig newConfig = gson.fromJson(payload, JMWorldConfig.class);
            if (type == 3) {
                MinimapConfig.WorldConfig worldConfig = MinimapConfig.getWorldConfig(dimension);
                worldConfig.journeymapConfig = newConfig;
                System.out.println(dimension);
            } else {
                MinimapConfig.defaultWorldConfig.defaultJMWorldConfig = newConfig;
            }
        }
        plugin.saveConfig();

        String permChannel = "journeymap:perm_req";
        int replyInt = 0;
        if (replyChannel.equals("journeymap:common")) {
            permChannel = "journeymap:common";
            replyInt = 2;
        }

        String finalPermChannel = permChannel;
        int finalReplyInt = replyInt;
        plugin.getServer().getOnlinePlayers().forEach(p->handlePerm(p, finalPermChannel, finalReplyInt));
    }

    public void handleVersion(Player player, String replyChannel) {
        Gson gson = new Gson();
        String payload = gson.toJson(new JMVersion());
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeByte(0);
        NetworkUtils.writeUtf(payload, out);
        player.sendPluginMessage(plugin, replyChannel, out.toByteArray());
    }

    public void handlePerm(Player player, String replyChannel, int replyByte) {
        JMWorldConfig worldConfig = MinimapConfig.getWorldConfig(player.getLocation().getWorld().getName()).journeymapConfig;
        JMConfig config = MinimapConfig.globalJourneymapConfig;
        if (worldConfig != null) {
            config = worldConfig.applyToConfig(config);
        }

        Gson gson = new Gson();
        String payload = gson.toJson(config);
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeByte(replyByte);
        out.writeByte(42);
        out.writeBoolean(player.hasPermission("minimap.jm.admin"));
        NetworkUtils.writeUtf(payload, out);
        out.writeBoolean(false);
        player.sendPluginMessage(MinimapPlugin.getInstance().plugin, replyChannel, out.toByteArray());
    }

    public void onPluginMessage(String channel, Player player, byte[] message) {
        switch (channel.split(":")[1]) {
            case "version" -> handleVersion(player, channel);
            case "perm_req" -> handlePerm(player, channel, 0);
            case "admin_req" -> handleAdminReq(player, message, channel, 0);
            case "admin_save" -> handleAdminSave(player, message, channel);
            case "teleport_req" -> handleTeleport(player, message);
            case "common" -> {
                ByteArrayDataInput in = ByteStreams.newDataInput(message);
                byte type = in.readByte();
                switch (type) {
                    case 0 -> handleAdminReq(player, message, channel, type);
                    case 1 -> handleAdminSave(player, message, channel);
                    case 2 -> handlePerm(player, channel, type);
                    case 4 -> handleTeleport(player, message);
                    case 5 -> handleMPOptions(player);
                }
            }
        }
    }
}
