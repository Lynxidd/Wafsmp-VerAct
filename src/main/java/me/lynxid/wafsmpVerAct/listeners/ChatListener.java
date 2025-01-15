package me.lynxid.wafsmpVerAct.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.io.File;
import java.util.UUID;

import static me.lynxid.wafsmpVerAct.files.PlayerFile.userData;
import static me.lynxid.wafsmpVerAct.files.RulesFile.effectsGive;

public class ChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(@SuppressWarnings("deprecation") PlayerChatEvent e) {
        Player p = e.getPlayer();
        UUID playerId = p.getUniqueId();
        File file = new File(userData, File.separator + playerId + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(file);
        if (!playerData.getBoolean("Accepted Rules")) {
            e.setCancelled(true);
            p.performCommand("rules");
            effectsGive(p);
        }
    }
}
