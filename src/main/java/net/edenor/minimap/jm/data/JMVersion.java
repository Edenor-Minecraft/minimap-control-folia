package net.edenor.minimap.jm.data;

import net.edenor.minimap.MinimapPlugin;

public class JMVersion {
    public static VersionDetails journeymap_version = new VersionDetails();
    public String minecraft_version = MinimapPlugin.getInstance().getServer().getMinecraftVersion();

    public static class VersionDetails {
        public String full;
        public int major = 5;
        public int minor = 9;
        public int micro = 15;
        public String patch = null;

        public VersionDetails() {
            this.full = String.format("%d.%d.%d",major,minor,micro);
        }
    }
}
