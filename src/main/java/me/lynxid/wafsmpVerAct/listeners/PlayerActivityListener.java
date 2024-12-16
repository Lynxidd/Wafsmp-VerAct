package me.lynxid.wafsmpVerAct.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.io.File;
import java.util.UUID;

import static me.lynxid.wafsmpVerAct.files.PlayerFile.userData;

public class PlayerActivityListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player player = e.getPlayer();
        UUID playerId = player.getUniqueId();
        File file = new File(userData, File.separator + playerId + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(file);

        if (playerData.getBoolean("Accepted Rules")) {
//            BukkitScheduler scheduler = Bukkit.getScheduler();
//            scheduler.runTaskTimer((Plugin) this, task -> {
//                try {
//                    playerData.load(file);
//                    playerData.set("Active Time", "+1");
//                    playerData.save(file);
//                    player.sendMessage( "Your activity time "+ playerData.getString("Active Time"));
//                    }
//                catch (IOException | InvalidConfigurationException i) {
//                    getLogger().severe(i.toString());
//                    }
//                }, 0L, 20L );
            e.getPlayer().sendMessage(":3");
        } else {
            e.setCancelled(true);
        }








    }

}
