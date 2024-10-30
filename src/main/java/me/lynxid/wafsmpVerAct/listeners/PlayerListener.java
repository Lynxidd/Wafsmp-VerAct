package me.lynxid.wafsmpVerAct.listeners;

import me.lynxid.wafsmpVerAct.WafsmpVerAct;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerListener implements Listener {
    FileConfiguration config = WafsmpVerAct.plugin.getConfig();
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        if (config.getBoolean("Players." + e.getPlayer().getName() + ".muted")) {
            e.setCancelled(true);
        }
    }
}
