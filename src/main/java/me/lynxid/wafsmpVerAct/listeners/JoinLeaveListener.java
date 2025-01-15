package me.lynxid.wafsmpVerAct.listeners;

import me.lynxid.wafsmpVerAct.WafsmpVerAct;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.util.UUID;

import static me.lynxid.wafsmpVerAct.files.PlayerFile.userData;
import static me.lynxid.wafsmpVerAct.files.RulesFile.effectsGive;


public class JoinLeaveListener implements Listener {

    private final WafsmpVerAct plugin;

    public JoinLeaveListener(WafsmpVerAct plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        String joinMessage = this.plugin.getConfig().getString("join-message");
        UUID playerId = e.getPlayer().getUniqueId();
        File file = new File(userData, File.separator + playerId + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(file);

        if (joinMessage != null && playerData.getBoolean("Accepted Rules") ) {
            joinMessage = joinMessage.replace("%player%", e.getPlayer().getDisplayName());
            e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', joinMessage));
        } else {
            e.setJoinMessage("A new player has joined!");
            effectsGive(e.getPlayer());
            e.getPlayer().performCommand("rules");
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        String quitMessage = this.plugin.getConfig().getString("quit-message");
        UUID playerId = e.getPlayer().getUniqueId();
        File file = new File(userData, File.separator + playerId + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(file);
        if (quitMessage != null && playerData.getBoolean("Accepted Rules") ) {
            quitMessage = quitMessage.replace("%player%", e.getPlayer().getDisplayName());
            e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', quitMessage));
        } else {
            e.setQuitMessage("A new player has left!");
        }

    }
}




