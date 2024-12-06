package me.lynxid.wafsmpVerAct.commands;

import me.lynxid.wafsmpVerAct.WafsmpVerAct;
import me.lynxid.wafsmpVerAct.files.CustomConfig;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import static me.lynxid.wafsmpVerAct.files.PlayerFile.date;
import static me.lynxid.wafsmpVerAct.files.PlayerFile.userData;
import static org.bukkit.Bukkit.*;

public class TestCommand implements CommandExecutor {


    private final WafsmpVerAct plugin;

    public TestCommand(WafsmpVerAct plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, String[] strings) {

        if (strings.length == 0) {
            sender.sendMessage(Objects.requireNonNull(CustomConfig.get().getString("test")));
            return true;
        }

        if(strings.length == 1) {
            Player target = getServer().getPlayer(strings[0]);

            if(target == null) {
                sender.sendMessage( "Player is not online!");
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
                    getLogger().severe(i.toString());
                }

                BukkitScheduler scheduler = Bukkit.getScheduler();
                scheduler.runTaskTimer(plugin, task -> {


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
                    target.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Rules of the WSMP");
                    target.sendMessage(" ");
                    target.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Rule 1. " + ChatColor.WHITE + "Don’t do anything intended to make someone unhappy. Don’t make fun of people, and no racism, homophobia, transphobia, etc. While swearing is allowed (in moderation), slurs are NEVER allowed.");
                    target.sendMessage(" ");
                    target.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Rule 2. " + ChatColor.WHITE + "Minecraft is a game that lots of younger kids play. No NSFW, In chat or built on the server, Don’t talk about anything unpleasant or controversial. Everyone is here to play a game and have fun.");
                    target.sendMessage(" ");
                    TextComponent msg = new TextComponent("[Next Page]");
                    msg.setColor(ChatColor.DARK_GREEN.asBungee());
                    msg.setBold(true);

                    msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rules p2"));
                    msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click to go to the next page")));
                    target.spigot().sendMessage(msg);


                    task.cancel();

                }, 0L, 20L );

            }
            return true;
        }
        return true;
    }
}
