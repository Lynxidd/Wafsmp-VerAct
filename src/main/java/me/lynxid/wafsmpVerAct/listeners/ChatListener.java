package me.lynxid.wafsmpVerAct.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.File;
import java.util.UUID;

import static me.lynxid.wafsmpVerAct.files.PlayerFile.userData;

public class ChatListener implements Listener {
    private static Player p;

    public void onPlayerChat(AsyncPlayerChatEvent e) {
        UUID playerId = p.getUniqueId();
        File file = new File(userData, File.separator + playerId + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(file);
        p = e.getPlayer();
        if (!playerData.getBoolean("Accepted Rules")) {
            e.setCancelled(true);
        } else {
            p.sendMessage("stupid");
        }
    }
}
