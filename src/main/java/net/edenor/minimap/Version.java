package net.edenor.minimap;

import org.bukkit.entity.Player;

public class Version {
    private final int release;
    private final int major;
    private final int minor;

    public Version(int release, int major, int minor) {
        this.release = release;
        this.major = major;
        this.minor = minor;
    }

    public boolean greaterThanEqual(Version otherVersion) {
        if (this.release > otherVersion.release) return true;
        if (this.release < otherVersion.release) return false;
        if (this.major > otherVersion.major) return true;
        if (this.major < otherVersion.major) return false;
        if (this.minor > otherVersion.minor) return true;
        return this.minor >= otherVersion.minor;
    }

    public boolean greaterThanEqual(String otherVer) {
        Version otherVersion = fromString(otherVer);
        if (this.release > otherVersion.release) return true;
        if (this.release < otherVersion.release) return false;
        if (this.major > otherVersion.major) return true;
        if (this.major < otherVersion.major) return false;
        if (this.minor > otherVersion.minor) return true;
        return this.minor >= otherVersion.minor;
    }

    public int getRelease() {
        return release;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public String toString() {
        return String.format("%d.%d.%d",release,major,minor);
    }

    public static Version fromString(String version) {
        String[] ver = version.split("-")[0].split("\\.");
        return new Version(Integer.parseInt(ver[0]), Integer.parseInt(ver[1]), ver.length > 2 ? Integer.parseInt(ver[2]) : 0);
    }
}
