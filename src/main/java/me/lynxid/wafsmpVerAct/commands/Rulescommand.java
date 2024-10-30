package me.lynxid.wafsmpVerAct.commands;

import me.lynxid.wafsmpVerAct.WafsmpVerAct;
import me.lynxid.wafsmpVerAct.listeners.PlayerListener;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Rulescommand implements CommandExecutor {
    FileConfiguration config = WafsmpVerAct.plugin.getConfig();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (sender instanceof Player p) {
            if (strings.length < 1) {
                p.sendMessage(" ");
                p.sendMessage(" ");
                p.sendMessage(" ");
                p.sendMessage(" ");
                p.sendMessage(" ");
                p.sendMessage(" ");
                p.sendMessage(" ");
                p.sendMessage(" ");
                p.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Rules of the WSMP");
                p.sendMessage(" ");
                p.sendMessage( ChatColor.GOLD + "" + ChatColor.BOLD + "Rule 1. " + ChatColor.WHITE + "Don’t do anything intended to make someone unhappy. Don’t make fun of people, and no racism, homophobia, transphobia, etc. While swearing is allowed (in moderation), slurs are NEVER allowed.");
                p.sendMessage(" ");
                p.sendMessage( ChatColor.GOLD + "" + ChatColor.BOLD + "Rule 2. " + ChatColor.WHITE + "Minecraft is a game that lots of younger kids play. No NSFW, In chat or built on the server, Don’t talk about anything unpleasant or controversial. Everyone is here to play a game and have fun.");

                TextComponent msg = new TextComponent("[Next Page]");
                msg.setColor(ChatColor.DARK_GREEN.asBungee());
                msg.setBold(true);

                msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/rules p2"));
                msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new Text("Click to go to the next page")));
                p.spigot().sendMessage(msg);
            }else if (strings[0].equalsIgnoreCase("p2")) {
                              p.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Rules of the WSMP");
                p.sendMessage(" ");
                p.sendMessage( ChatColor.GOLD + "" + ChatColor.BOLD + "Rule 3. " + ChatColor.WHITE + "All of the rules listed above apply everywhere in the server and are enforced by admins, but every player-made country has their own rules that their leaders enforce. Make sure that while in an in-game country, you follow their laws and international laws.");
                p.sendMessage(" ");
                p.sendMessage( ChatColor.GOLD + "" + ChatColor.BOLD + "Rule 4. " + ChatColor.WHITE + "No cheating, hacking, or exploiting! Automatic farms are allowed but duping is not, the only things you can duplicate are sand/gravel/concrete and tnt, as these are non-renewable resources. Again any form of finding ores that wasn’t intended by Mojang is prohibited!");

                TextComponent msg2 = new TextComponent("[Previous Page] ");
                msg2.setColor(ChatColor.DARK_GREEN.asBungee());
                msg2.setBold(true);

                msg2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/rules"));
                msg2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new Text("Click to go to the previous page")));

                TextComponent msg = new TextComponent("[Next Page]");
                msg.setColor(ChatColor.DARK_GREEN.asBungee());
                msg.setBold(true);

                msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/rules p3"));
                msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new Text("Click to go to the next page")));
                p.spigot().sendMessage(msg2,msg);

            }else if (strings[0].equalsIgnoreCase("p3")){
                p.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Rules of the WSMP");
                p.sendMessage(" ");
                p.sendMessage( ChatColor.GOLD + "" + ChatColor.BOLD + "Rule 5. " + ChatColor.WHITE + "This includes spam in chat or purposefully building at someone else’s base just to annoy them, or anything else meant to annoy someone.");
                p.sendMessage(" ");
                p.sendMessage( ChatColor.GOLD + "" + ChatColor.BOLD + "Rule 6. " + ChatColor.WHITE + "Don’t share anyone else’s personal information, no discussion of illegal activity. the usage or discussion of cracked/free Minecraft accounts will get you banned!");

                TextComponent msg2 = new TextComponent("[Previous Page] ");
                msg2.setColor(ChatColor.DARK_GREEN.asBungee());
                msg2.setBold(true);

                msg2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/rules p2"));
                msg2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new Text("Click to go to the previous page")));

                TextComponent msg = new TextComponent("[Accept rules]");
                msg.setColor(ChatColor.DARK_GREEN.asBungee());
                msg.setBold(true);

                msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/rules accept"));
                msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new Text("Click to go to the next page")));
                p.spigot().sendMessage(msg2,msg);

            }else if (strings[0].equalsIgnoreCase("accept")){

                p.setCanPickupItems(true);
                p.setGameMode(GameMode.SURVIVAL);
                p.sendMessage(" ");
                p.sendMessage(" ");
                p.sendMessage(" ");
                p.sendMessage(" ");
                p.sendMessage(" ");
                p.sendMessage(" ");
                p.sendMessage(" ");
                p.sendMessage(" ");
                p.sendMessage(" ");
                p.sendMessage(" ");
                sender.getServer().broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + p.getDisplayName() + " has accepted the rules! Welcome to the server!");

            }
        }







        return true;
    }
}