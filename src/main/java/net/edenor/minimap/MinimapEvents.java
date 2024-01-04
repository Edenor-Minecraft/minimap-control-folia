package net.edenor.minimap;

import net.edenor.minimap.api.MinimapPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class MinimapEvents implements @NotNull Listener {

    private MinimapPlugin plugin;

    public MinimapEvents(MinimapPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        plugin.getServer().getGlobalRegionScheduler().runDelayed(plugin,
                v->this.handlePackets(new MinimapPlayer(event.getPlayer())), 20L);
        plugin.getServer().getGlobalRegionScheduler().runDelayed(plugin,
                v->this.handlePackets(new MinimapPlayer(event.getPlayer())), 100L); //Resend later
        plugin.getServer().getGlobalRegionScheduler().runDelayed(plugin,
                v->this.handlePackets(new MinimapPlayer(event.getPlayer())), 200L); //Resend later
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        handlePackets(new MinimapPlayer(event.getPlayer()));
    }

    public void handlePackets(MinimapPlayer player) {
        plugin.xaerosHandler.sendXaerosHandshake(player);
        plugin.xaerosHandler.sendXaerosConfig(player);
    }
}
