package me.lynxid.wafsmpVerAct.commands;

import me.lynxid.wafsmpVerAct.WafsmpVerAct;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Mutecommand implements CommandExecutor {
    FileConfiguration config = WafsmpVerAct.plugin.getConfig();
    @Override
    public boolean onCommand(CommandSender s, org.bukkit.command.Command c, String l, String[] args) {
        if (l.equalsIgnoreCase("mute")) {
            Player t = Bukkit.getServer().getPlayer(args[0]);
            if (s instanceof Player) {
                Player p = (Player)	s;
                if (p.hasPermission("Mute")) {
                    config.set("Players." + t.getName() + ".muted", true);
                }
                else { p.sendMessage(ChatColor.RED + "You do not have the permissions to use that command!"); }
            }
            else {
                config.set("Players." + t.getName() + ".muted", true);
            }
        }
        return false;
    }

}
