package me.lynxid.wafsmpVerAct.commands;

import me.lynxid.wafsmpVerAct.files.CustomConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static me.lynxid.wafsmpVerAct.files.PlayerFile.date;
import static me.lynxid.wafsmpVerAct.files.PlayerFile.userdata;

public class testcommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (sender instanceof Player p){
            p.sendMessage(CustomConfig.get().getString("test"));

            UUID playeru = p.getUniqueId();
            File file = new File(userdata, File.separator + playeru + ".yml");
            FileConfiguration playerData = YamlConfiguration.loadConfiguration(file);

            try {
                playerData.load(file);
                playerData.set("Accepted Rules", true);
                playerData.set("Time Accepted", date);
                playerData.save(file);
            } catch (IOException | InvalidConfigurationException i) {
                i.printStackTrace();
            }
            p.sendMessage(playerData.getString("Accepted Rules"));

            if (playerData.getBoolean("Accepted Rules")){
                p.sendMessage("Value = True");
            } else {
                p.sendMessage("Value = False");
            }

        }

        return true;
    }
}
