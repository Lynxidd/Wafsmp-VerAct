package me.lynxid.wafsmpVerAct.listeners;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static me.lynxid.wafsmpVerAct.files.PlayerFile.userData;
import static org.bukkit.Bukkit.getLogger;

public class PlayerActivityListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player player = e.getPlayer();
        UUID playerId = player.getUniqueId();
        File file = new File(userData, File.separator + playerId + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(file);

        if (playerData.getBoolean("Accepted Rules")) {
            BukkitScheduler scheduler = Bukkit.getScheduler();
            scheduler.runTaskTimer((Plugin) this, task -> {
                try {
                    playerData.load(file);
                    playerData.set("Active Time", +1);
                    playerData.save(file);
                    player.sendMessage( "Your activity time "+ playerData.getString("Active Time"));
                    }
                catch (IOException | InvalidConfigurationException i) {
                    getLogger().severe(i.toString());
                    }
                }, 0L, 20L );
        } else {
            e.setCancelled(true);
        }








    }

}
