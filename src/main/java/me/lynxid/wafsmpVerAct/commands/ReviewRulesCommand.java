package me.lynxid.wafsmpVerAct.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import static me.lynxid.wafsmpVerAct.files.PlayerFile.date;
import static me.lynxid.wafsmpVerAct.files.PlayerFile.userData;
import static me.lynxid.wafsmpVerAct.files.RulesFile.effectsGive;
import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

public class ReviewRulesCommand implements CommandExecutor {
//    private static Player target;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, String[] strings) {

        if (strings.length == 0) {
            sender.sendMessage("Usage: /reviewrules <player>");
            return true;
        }

        if(strings.length == 1) {
            Player target = getServer().getPlayer(strings[0]);
            String playerN = Objects.requireNonNull(getServer().getPlayer(strings[0])).getDisplayName();

            if(target == null) {
                sender.sendMessage( "Player is not online!");
            }else {
                UUID playerId = target.getUniqueId();
                File file = new File(userData, File.separator + playerId + ".yml");
                FileConfiguration playerData = YamlConfiguration.loadConfiguration(file);

                try {
                    playerData.load(file);
                    playerData.set("Accepted Rules", false);
                    playerData.set("Time Accepted", date);
                    playerData.save(file);
                    sender.sendMessage(ChatColor.GREEN + "Showing " + playerN + " the rules!");
                } catch (IOException | InvalidConfigurationException i) {
                    getLogger().severe(i.toString());
                }

                effectsGive(target);
                target.performCommand("rules");
            }
            return true;
        }
        return true;
    }
}

