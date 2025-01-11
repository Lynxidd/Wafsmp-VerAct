package me.lynxid.wafsmpVerAct.listeners;

import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.util.UUID;

import static me.lynxid.wafsmpVerAct.files.PlayerFile.userData;

public class RulesListener implements Listener {
private static Player p;


    public static void effectsGive() {
        p.setGameMode(GameMode.ADVENTURE);
        p.setCanPickupItems(false);
        p.setWalkSpeed(0);
        p.setInvulnerable(true);
    }

    public static void effectsTake() {
        p.setGameMode(GameMode.SURVIVAL);
        p.setCanPickupItems(true);
        p.setWalkSpeed(0.2F);
        p.setInvulnerable(false);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        p = e.getPlayer();
        UUID playerId = e.getPlayer().getUniqueId();
        File file = new File(userData, File.separator + playerId + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(file);

        if (!playerData.getBoolean("Accepted Rules") ) {
            effectsGive();
        } else {
            effectsTake();
        }
    }
}
