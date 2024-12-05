package me.lynxid.wafsmpVerAct.commands;

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
import static me.lynxid.wafsmpVerAct.files.PlayerFile.userData;
import static org.bukkit.Bukkit.getServer;

public class ReviewRulesCommand implements CommandExecutor {
//    private static Player target;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (strings.length == 0) {
            sender.sendMessage("Usage: /reviewrules <player>");
            return true;
        }

        if(strings.length == 1) {
            Player target = getServer().getPlayer(strings[0]);
            Player player = getServer().getPlayer(strings[0]);

            if(target == null) {
                sender.sendMessage( player + " is not online!");
            }else {
                UUID playeru = target.getUniqueId();
                File file = new File(userData, File.separator + playeru + ".yml");
                FileConfiguration playerData = YamlConfiguration.loadConfiguration(file);

                try {
                    playerData.load(file);
                    playerData.set("Accepted Rules", false);
                    playerData.set("Time Accepted", date);
                    playerData.save(file);
                    sender.sendMessage("Set Accepted Rules to False");
                } catch (IOException | InvalidConfigurationException i) {
                    i.printStackTrace();
                }
            }
            return true;
        }
        return true;
    }
}
