package me.lynxid.wafsmpVerAct.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.File;
import java.util.UUID;

import static me.lynxid.wafsmpVerAct.files.PlayerFile.userData;

public class ChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        UUID playerId = p.getUniqueId();
        File file = new File(userData, File.separator + playerId + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(file);
        if (!playerData.getBoolean("Accepted Rules")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onRulesCommand(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (e.getPlayer().performCommand("/rules")) {

            p.sendMessage("stupid fucking wokrdf");

        }
    }
}
