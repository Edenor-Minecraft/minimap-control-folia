package net.edenor.minimap.jm.data;

public class JMVersion {
    public static VersionDetails journeymap_version = new VersionDetails();

    public static class VersionDetails {
        public String full;
        public int major = 6;
        public int minor = 0;
        public int micro = 0;
        public String patch = "-beta.8";

        public VersionDetails() {
            this.full = String.format("%d.%d.%d%s",major,minor,micro,patch);
        }
    }
}
