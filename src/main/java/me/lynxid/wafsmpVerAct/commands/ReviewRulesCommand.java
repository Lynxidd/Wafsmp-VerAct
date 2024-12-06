package me.lynxid.wafsmpVerAct.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
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
import java.util.UUID;

import static me.lynxid.wafsmpVerAct.files.PlayerFile.date;
import static me.lynxid.wafsmpVerAct.files.PlayerFile.userData;
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
            String playerN = getServer().getPlayer(strings[0]).getDisplayName();

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

                target.sendMessage(" ");
                target.sendMessage(" ");
                target.sendMessage(" ");
                target.sendMessage(" ");
                target.sendMessage(" ");
                target.sendMessage(" ");
                target.sendMessage(" ");
                target.sendMessage(" ");
                target.sendMessage(" ");
                target.sendMessage(" ");
                target.sendMessage(" ");
                target.sendMessage(" ");
                target.sendMessage(" ");
                target.sendMessage(" ");
                target.sendMessage(" ");
                target.sendMessage(" ");
                target.sendMessage(" ");
                target.sendMessage(" ");
                target.sendMessage(" ");
                target.sendMessage(" ");
                target.sendMessage(" ");
                target.sendMessage(" ");
                target.sendMessage(" ");
                target.sendMessage(" ");
                target.sendMessage("\uE000");
                target.sendMessage(" ");
                target.sendMessage(" ");
                target.sendMessage(" ");
                target.sendMessage(org.bukkit.ChatColor.DARK_AQUA + "" + org.bukkit.ChatColor.BOLD + "Rules of the WSMP");
                target.sendMessage(" ");
                target.sendMessage(org.bukkit.ChatColor.GOLD + "" + org.bukkit.ChatColor.BOLD + "Rule 1. " + org.bukkit.ChatColor.WHITE + "Don’t do anything intended to make someone unhappy. Don’t make fun of people, and no racism, homophobia, transphobia, etc. While swearing is allowed (in moderation), slurs are NEVER allowed.");
                target.sendMessage(" ");
                target.sendMessage(org.bukkit.ChatColor.GOLD + "" + org.bukkit.ChatColor.BOLD + "Rule 2. " + org.bukkit.ChatColor.WHITE + "Minecraft is a game that lots of younger kids play. No NSFW, In chat or built on the server, Don’t talk about anything unpleasant or controversial. Everyone is here to play a game and have fun.");
                target.sendMessage(" ");
                TextComponent msg = new TextComponent("[Next Page]");
                msg.setColor(org.bukkit.ChatColor.DARK_GREEN.asBungee());
                msg.setBold(true);

                msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rules p2"));
                msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click to go to the next page")));
                target.spigot().sendMessage(msg);

            }
            return true;
        }
        return true;
    }
}

