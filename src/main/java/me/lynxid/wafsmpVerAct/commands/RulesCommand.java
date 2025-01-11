package me.lynxid.wafsmpVerAct.commands;

import me.lynxid.wafsmpVerAct.files.PlayerFile;
import me.lynxid.wafsmpVerAct.files.RulesFile;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
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
import static me.lynxid.wafsmpVerAct.listeners.RulesListener.effectsTake;
import static org.bukkit.Bukkit.getLogger;

public class RulesCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, String[] strings) {

        Player p = (Player) sender;

        UUID playerId = p.getUniqueId();
        File file = new File(userData, File.separator + playerId + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(file);

        String logo = RulesFile.get().getString("logo");
        String rule1 = RulesFile.get().getString("rule1");
        String rule2 = RulesFile.get().getString("rule2");
        String rule3 = RulesFile.get().getString("rule3");
        String rule4 = RulesFile.get().getString("rule4");
        String rule5 = RulesFile.get().getString("rule5");
        String rule6 = RulesFile.get().getString("rule6");

        if (strings.length < 1) {
            p.sendMessage(" ");
            p.sendMessage(" ");
            p.sendMessage(" ");
            p.sendMessage(" ");
            p.sendMessage(" ");
            p.sendMessage(" ");
            p.sendMessage(" ");
            p.sendMessage(" ");
            p.sendMessage(logo + " ");
            p.sendMessage(" ");
            p.sendMessage(" ");
            p.sendMessage(" ");
            p.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Rules of the WSMP");
            p.sendMessage(" ");
            p.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Rule 1. " + ChatColor.WHITE + rule1);
            p.sendMessage(" ");
            p.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Rule 2. " + ChatColor.WHITE + rule2);
            p.sendMessage(" ");

            TextComponent msg = new TextComponent("[Next Page]");
            msg.setColor(ChatColor.DARK_GREEN.asBungee());
            msg.setBold(true);

            msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rules p2"));
            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new Text("Click to go to the next page")));
            p.spigot().sendMessage(msg);

        } else if (strings[0].equalsIgnoreCase("p2")) {
            p.sendMessage(" ");
            p.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Rules of the WSMP");
            p.sendMessage(" ");
            p.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Rule 3. " + ChatColor.WHITE + rule3);
            p.sendMessage(" ");
            p.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Rule 4. " + ChatColor.WHITE + rule4);
            p.sendMessage(" ");

            TextComponent msg2 = new TextComponent("[Previous Page] ");
            msg2.setColor(ChatColor.DARK_GREEN.asBungee());
            msg2.setBold(true);

            msg2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rules"));
            msg2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new Text("Click to go to the previous page")));

            TextComponent msg = new TextComponent("[Next Page]");
            msg.setColor(ChatColor.DARK_GREEN.asBungee());
            msg.setBold(true);

            msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rules p3"));
            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new Text("Click to go to the next page")));
            p.spigot().sendMessage(msg2, msg);

        } else if (strings[0].equalsIgnoreCase("p3")) {
            p.sendMessage(" ");
            p.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Rules of the WSMP");
            p.sendMessage(" ");
            p.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Rule 5. " + ChatColor.WHITE + rule5);
            p.sendMessage(" ");
            p.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Rule 6. " + ChatColor.WHITE + rule6);
            p.sendMessage(" ");

            TextComponent msg2 = new TextComponent("[Previous Page] ");
            msg2.setColor(ChatColor.DARK_GREEN.asBungee());
            msg2.setBold(true);

            msg2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rules p2"));
            msg2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new Text("Click to go to the previous page")));

            TextComponent msg = new TextComponent("[Accept rules]");
            msg.setColor(ChatColor.DARK_GREEN.asBungee());
            msg.setBold(true);

            msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rules accept"));
            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new Text("Click to go to the next page")));
            p.spigot().sendMessage(msg2, msg);

        } else if (strings[0].equalsIgnoreCase("accept")) {
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
            effectsTake();

            try {
                PlayerFile.time();
                playerData.load(file);
                playerData.set("Accepted Rules", true);
                playerData.set("Time Accepted", date);
                playerData.save(file);
            } catch (IOException | InvalidConfigurationException i) {
                getLogger().severe(i.toString());
            }
        }


        return true;
    }
}