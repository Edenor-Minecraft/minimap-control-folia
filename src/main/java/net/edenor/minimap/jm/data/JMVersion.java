package net.edenor.minimap.jm.data;

public class JMVersion {
    public static VersionDetails journeymap_version = new VersionDetails();

    public static class VersionDetails {
        public String full;
        public int major = 5;
        public int minor = 9;
        public int micro = 15;

        public VersionDetails() {
            this.full = String.format("%d.%d.%d",major,minor,micro);
        }
    }
}
