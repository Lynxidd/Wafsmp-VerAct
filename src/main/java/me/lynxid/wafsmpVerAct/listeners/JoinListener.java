package me.lynxid.wafsmpVerAct.listeners;

import me.lynxid.wafsmpVerAct.files.PlayerFile;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static me.lynxid.wafsmpVerAct.files.PlayerFile.date;
import static org.bukkit.Bukkit.getLogger;

public class JoinListener implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        Player player = e.getPlayer();
        UUID playeru = player.getUniqueId();
        String playern = player.getDisplayName();

        File UserData = new File(Bukkit.getServer().getPluginManager().getPlugin("Wafsmp-VerAct").getDataFolder(), File.separator + "UserData");
        File file = new File(UserData, File.separator + playeru + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()) {
            getLogger().info("[Wafsmp-VerAct] " + e.getPlayer().getDisplayName() + " does not have a config file! Attempting to generate a new one!");
            try {
                file.createNewFile();
            } catch (IOException i) {
                i.printStackTrace();
            }

            try {
                PlayerFile.time();
                playerData.load(file);
                playerData.set("Player Name", playern);
                playerData.set("Migrated date(dd-MM-yyyy)", date);
                playerData.save(file);
            } catch (IOException | InvalidConfigurationException i) {
                i.printStackTrace();
            }

        }
    }
}
