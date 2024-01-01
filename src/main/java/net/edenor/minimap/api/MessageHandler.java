package net.edenor.minimap.api;

public interface MessageHandler {
    void onPluginMessage(String channel, MinimapPlayer player, byte[] message);
}
