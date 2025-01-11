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
import java.util.Objects;
import java.util.UUID;

import static me.lynxid.wafsmpVerAct.files.PlayerFile.date;
import static org.bukkit.Bukkit.getLogger;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws IOException, InvalidConfigurationException {

        Player player = e.getPlayer();
        UUID playerId = player.getUniqueId();
        String playerName = player.getDisplayName();

        File userData = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("Wafsmp-VerAct")).getDataFolder(), File.separator + "UserData");
        File file = new File(userData, File.separator + playerId + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(file);

        if (!file.exists()) {
            getLogger().info("[Wafsmp-VerAct] " + e.getPlayer().getDisplayName() + " does not have a config file! Attempting to generate a new one!");
            try {
                if (!file.createNewFile())
                {
                    // It is impossible for this to happen, it's just here to get rid of the stupid warning
                    getLogger().info("File already exists!");
                }
            } catch (IOException | SecurityException i) {
                getLogger().severe(i.toString());
            }

            try {
                PlayerFile.time();
                playerData.load(file);
                playerData.set("Player Name", playerName);
                playerData.set("Migrated date(dd-MM-yyyy)", date);
                playerData.save(file);
            } catch (IOException | InvalidConfigurationException i) {
                getLogger().severe(i.toString());
            }
        }

        if (e.getPlayer().getDisplayName().equals(playerData.getString("Player Name"))){
            e.getPlayer().sendMessage(" ");
        } else {
            playerData.load(file);
            playerData.set("Player Name", playerName);
            playerData.save(file);
        }


    }

}
