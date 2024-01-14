package net.edenor.minimap;

import net.edenor.minimap.jm.data.JMWorldConfig;
import net.edenor.minimap.voxel.data.VoxelWorldConfig;
import net.edenor.minimap.xaeros.data.XaerosWorldConfig;
import org.bukkit.configuration.file.FileConfiguration;

public class DefaultWorldConfig {

    public DefaultWorldConfig(){
        defaultXaerosWorldConfig = new XaerosWorldConfig();
        defaultVoxelWorldConfig = new VoxelWorldConfig();
        defaultJMWorldConfig = new JMWorldConfig();
    }
    XaerosWorldConfig defaultXaerosWorldConfig;
    VoxelWorldConfig defaultVoxelWorldConfig;
    public JMWorldConfig defaultJMWorldConfig;

    public void addDefaultsToConfig(FileConfiguration cfg){
        cfg.createSection("default-world-config");
        cfg.createSection("default-world-config.xaeros", defaultXaerosWorldConfig.toMap());
        cfg.createSection("default-world-config.jm", defaultJMWorldConfig.toMap());
        cfg.createSection("default-world-config.voxel", defaultVoxelWorldConfig.toMap());
    }
}
