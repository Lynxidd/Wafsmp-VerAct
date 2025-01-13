package me.lynxid.wafsmpVerAct.commands;

import me.lynxid.wafsmpVerAct.WafsmpVerAct;
import me.lynxid.wafsmpVerAct.files.PlayerFile;
import me.lynxid.wafsmpVerAct.files.RulesFile;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
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

    private final WafsmpVerAct plugin;

    public RulesCommand(WafsmpVerAct plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, String[] strings) {

        Player p = (Player) sender;

        UUID playerId = p.getUniqueId();
        File file = new File(userData, File.separator + playerId + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(file);

        String logo = RulesFile.get().getString("logo");
        List<String> rulesList = RulesFile.get().getStringList("rules");

        if (rulesList.isEmpty()) return true;
        int totalPages = ((rulesList.size() % 2 == 0) ? rulesList.size() : rulesList.size() + 1) / 2;

        PersistentDataContainer pdc = p.getPersistentDataContainer();
        NamespacedKey storedRulesPage = new NamespacedKey(this.plugin, "currentRulesPage");

        if (strings.length < 1) {
            pdc.set(storedRulesPage, PersistentDataType.INTEGER, 1);

            String firstRule = rulesList.getFirst();
            String secondRule = (rulesList.size() > 1) ? rulesList.get(1) : "";
            sendRulesPage(p, logo, firstRule, secondRule, 1, totalPages);
        }
        else if (strings[0].equalsIgnoreCase("accept")) {
            pdc.set(storedRulesPage, PersistentDataType.INTEGER, -2);

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
        else if (strings[0].startsWith("p")) {
            int pageNum;
            try {
               pageNum = Integer.parseInt(strings[0].substring(1));
            } catch (NumberFormatException e) {
                p.sendMessage(ChatColor.RED + "Please enter a valid page number! Ex: p1");
                return true;
            }

            if (pageNum < 1) pageNum = 1;
            else if (pageNum > totalPages) pageNum = totalPages;

            pdc.set(storedRulesPage, PersistentDataType.INTEGER, pageNum);

            String firstRule = rulesList.get(((pageNum - 1) * 2) + 1);
            String secondRule = (((pageNum - 1) * 2) + 2 <= rulesList.size()) ? rulesList.get(((pageNum - 1) * 2) + 2) : "";
            sendRulesPage(p, logo, firstRule, secondRule, pageNum, totalPages);
        }

        return true;
    }

    public void sendRulesHeader(Player p, String logo) {
        p.sendMessage("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        p.sendMessage(logo + "\n\n\n");
        p.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Rules of the WSMP");
    }

    public void sendRulesPage(Player p, String logo, String firstRule, String secondRule, int page, int totalPages) {
        sendRulesHeader(p, logo);
        p.sendMessage(ChatColor.GOLD + "\n" + ChatColor.BOLD + "Rule " + (((page - 1) * 2) + 1) + ". " + ChatColor.WHITE + firstRule);
        if (!secondRule.isEmpty()) p.sendMessage(ChatColor.GOLD + "\n" + ChatColor.BOLD + "Rule " + (((page - 1) * 2) + 2) + ". " + ChatColor.WHITE + secondRule + "\n");

        TextComponent previousPage = new TextComponent("");
        TextComponent nextPage = new TextComponent("");
        TextComponent accept = new TextComponent("");

        if (page != 1) {
            previousPage = new TextComponent("[Previous Page] ");
            previousPage.setColor(ChatColor.DARK_GREEN.asBungee());
            previousPage.setBold(true);
            previousPage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rules p" + (page - 1)));
            previousPage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click to go to the previous page")));
        }
        if (page != totalPages) {
            nextPage = new TextComponent("[Next Page]");
            nextPage.setColor(ChatColor.DARK_GREEN.asBungee());
            nextPage.setBold(true);
            nextPage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rules p" + (page + 1)));
            nextPage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click to go to the next page")));
        }
        else {
            accept = new TextComponent("[Accept rules]");
            accept.setColor(ChatColor.DARK_GREEN.asBungee());
            accept.setBold(true);
            accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rules accept"));
            accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click to accept the rules")));
        }

        p.spigot().sendMessage(previousPage, nextPage, accept);

        Integer currentRulesPage = 0;
        PersistentDataContainer pdc = p.getPersistentDataContainer();
        NamespacedKey storedRulesPage = new NamespacedKey(this.plugin, "currentRulesPage");
        if (pdc.has(storedRulesPage, PersistentDataType.INTEGER)) {
            currentRulesPage = pdc.get(storedRulesPage, PersistentDataType.INTEGER);
        }

        if (currentRulesPage != null && page == currentRulesPage) {
            getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, () -> {
                sendRulesPage(p, logo, firstRule, secondRule, page, totalPages);
            }, 4L);
        }
    }
}