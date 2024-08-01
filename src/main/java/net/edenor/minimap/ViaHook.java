package net.edenor.minimap;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.ViaAPI;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import com.viaversion.viaversion.api.protocol.version.VersionType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static java.lang.Integer.parseInt;

public class ViaHook {
    private final ViaAPI api;

    public ViaHook() throws ClassNotFoundException {
        api = Via.getAPI();
    }

    public Version getPlayerVersion(Player p) {
        int protoVersion = api.getPlayerVersion(p);
        ProtocolVersion version = ProtocolVersion.getProtocol(protoVersion);

        if (!ProtocolVersion.isRegistered(protoVersion)) {
            MinimapPlugin.getInstance().getLogger().info("ViaVersion returned unknown for player "+p.getName()
                    +" (protocol version "+protoVersion+"). This may cause issues if they're using Xaero's minimap. Consider updating ViaVersion");
            return Version.fromString(Bukkit.getMinecraftVersion());
        }

        String[] ver = version.getName().replaceAll("x","0").split("-")[0].split("\\.");
        if (ver.length < 3) {
            return new Version(parseInt(ver[0]), parseInt(ver[1]), 0);
        } else if (ver.length == 3) {
            return new Version(parseInt(ver[0]), parseInt(ver[1]), parseInt(ver[2]));
        } else {
            throw new RuntimeException("Cannot parse version "+ version.getName());
        }
    }
}