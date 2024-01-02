package net.edenor.minimap.xaeros;

import org.bukkit.configuration.file.FileConfiguration;

public class XaerosConfig {
    public boolean caveMode = true;
    public boolean netherCaveMode = true;
    public boolean radar = true;

    public void loadFromConfig(FileConfiguration cfg){
        this.caveMode = cfg.getBoolean("global-xaeros-config.caveMode");
        this.netherCaveMode = cfg.getBoolean("global-xaeros-config.netherCaveMode");
        this.radar = cfg.getBoolean("global-xaeros-config.radar");
    }

    public String toString(){
        return "caveMode: " + caveMode + "\n" +
                "netherCaveMode: " + netherCaveMode + "\n" +
                "radar: " + radar;
    }
}
