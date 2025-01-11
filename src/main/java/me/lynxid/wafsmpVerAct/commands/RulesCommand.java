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
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static me.lynxid.wafsmpVerAct.files.PlayerFile.date;
import static me.lynxid.wafsmpVerAct.files.PlayerFile.userData;
import static me.lynxid.wafsmpVerAct.listeners.RulesListener.effectsTake;
import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

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
        List<String> rulesList = RulesFile.get().getStringList("rules");


        if (strings.length < 1 || strings[0].equalsIgnoreCase("p1")) {
            // TODO: Store that the player is on page 1
            page1(p, logo, rule1, rule2);

        } else if (strings[0].equalsIgnoreCase("p2")) {
            // TODO: Store that the player is on page 2
            page2(p, logo, rule3, rule4);

        } else if (strings[0].equalsIgnoreCase("p3")) {
            // TODO: Store that the player is on page 3
            page3(p, logo, rule5, rule6);

        } else if (strings[0].equalsIgnoreCase("accept")) {
            // TODO: Store that the player is on page -1
            p.sendMessage("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
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

    public void sendRulesHeader(Player p, String logo) {
        p.sendMessage("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        p.sendMessage(logo + "\n\n\n");
        p.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Rules of the WSMP");
    }

    public void page1(Player p, String logo, String rule1, String rule2) {
        sendRulesHeader(p, logo);
        p.sendMessage(ChatColor.GOLD + "\n" + ChatColor.BOLD + "Rule 1. " + ChatColor.WHITE + rule1);
        p.sendMessage(ChatColor.GOLD + "\n" + ChatColor.BOLD + "Rule 2. " + ChatColor.WHITE + rule2 + "\n");

        TextComponent msg = new TextComponent("[Next Page]");
        msg.setColor(ChatColor.DARK_GREEN.asBungee());
        msg.setBold(true);
        msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rules p2"));
        msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click to go to the next page")));

        p.spigot().sendMessage(msg);

        p.sendMessage();

        if (true) { // TODO: Make sure the player is still on page 1
            getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this, () -> {
                page1(p, logo, rule1, rule2);

            }, 4L);
        }
    }



    public void page2(Player p, String logo, String rule3, String rule4) {
        sendRulesHeader(p, logo);
        p.sendMessage(ChatColor.GOLD + "\n" + ChatColor.BOLD + "Rule 3. " + ChatColor.WHITE + rule3);
        p.sendMessage(ChatColor.GOLD + "\n" + ChatColor.BOLD + "Rule 4. " + ChatColor.WHITE + rule4 + "\n");

        TextComponent msg2 = new TextComponent("[Previous Page] ");
        msg2.setColor(ChatColor.DARK_GREEN.asBungee());
        msg2.setBold(true);
        msg2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rules"));
        msg2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click to go to the previous page")));

        TextComponent msg = new TextComponent("[Next Page]");
        msg.setColor(ChatColor.DARK_GREEN.asBungee());
        msg.setBold(true);
        msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rules p3"));
        msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click to go to the next page")));

        p.spigot().sendMessage(msg2, msg);

        if (true) { // TODO: Make sure the player is still on page 2
            getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this, () -> {
                page2(p, logo, rule3, rule4);

            }, 4L);
        }
    }

    public void page3(Player p, String logo, String rule5, String rule6) {
        sendRulesHeader(p, logo);
        p.sendMessage(ChatColor.GOLD + "\n" + ChatColor.BOLD + "Rule 5. " + ChatColor.WHITE + rule5);
        p.sendMessage(ChatColor.GOLD + "\n" + ChatColor.BOLD + "Rule 6. " + ChatColor.WHITE + rule6 + "\n");

        TextComponent msg2 = new TextComponent("[Previous Page] ");
        msg2.setColor(ChatColor.DARK_GREEN.asBungee());
        msg2.setBold(true);
        msg2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rules p2"));
        msg2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click to go to the previous page")));

        TextComponent msg = new TextComponent("[Accept rules]");
        msg.setColor(ChatColor.DARK_GREEN.asBungee());
        msg.setBold(true);
        msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rules accept"));
        msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click to accept the rules")));

        p.spigot().sendMessage(msg2, msg);

        if (true) { // TODO: Make sure the player is still on page 3
            getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this, () -> {
                page3(p, logo, rule5, rule6);

            }, 4L);
        }
    }
}