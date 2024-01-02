package net.edenor.minimap.voxel;

import org.bukkit.configuration.file.FileConfiguration;

public class VoxelMapConfig {
    public boolean enabled = true;

    public void loadFromConfig(FileConfiguration cfg){
        this.enabled = cfg.getBoolean("global-voxel-config.enabled");
    }

    public String toString(){
        return "caveMode: " + enabled;
    }
}
