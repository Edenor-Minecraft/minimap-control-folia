package net.edenor.minimap;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MinimapEvents implements Listener {

    private final MinimapPlugin plugin;
    private final Map<String, ScheduledTask> playerTaskMap = new HashMap<>();

    public MinimapEvents(MinimapPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        plugin.getServer().getGlobalRegionScheduler().runDelayed(plugin,
                v->this.handlePackets(event.getPlayer()), 20L);
        ScheduledTask task = this.plugin.getServer().getGlobalRegionScheduler().runAtFixedRate(this.plugin,
                (v) -> this.handlePackets(event.getPlayer()), 40L, 40L);
        this.playerTaskMap.put(event.getPlayer().getName(), task);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        ScheduledTask task = this.playerTaskMap.get(event.getPlayer().getName());
        if (task != null) {
            this.playerTaskMap.get(event.getPlayer().getName()).cancel();
            this.playerTaskMap.remove(event.getPlayer().getName());
        }
        MinimapPlugin.getInstance().jmHandler.playerLeft(event.getPlayer());
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        this.handlePackets(event.getPlayer());
    }

    public void handlePackets(@NotNull Player player) {
        plugin.xaerosHandler.sendXaerosHandshake(player);
        plugin.xaerosHandler.sendXaerosConfig(player);
        plugin.voxelHandler.sendSettings(player);
    }
}
