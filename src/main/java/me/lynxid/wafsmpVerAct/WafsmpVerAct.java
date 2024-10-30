package me.lynxid.wafsmpVerAct;

import me.lynxid.wafsmpVerAct.listeners.JoinLeaveListener;
import org.bukkit.ChatColor;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class WafsmpVerAct extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic

        System.out.println("Wafsmp VerAct has started!");

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new JoinLeaveListener(), this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // /die - kills the player
        if (command.getName().equalsIgnoreCase("die")) {

            if (sender instanceof Player p) {

                p.setHealth(0);
                p.sendMessage(ChatColor.RED + "You died lol");

            } else if (sender instanceof ConsoleCommandSender) {

            System.out.println("command was consoled ");

            } else if (sender instanceof BlockCommandSender) {

            System.out.println("Command was run by command block");

            }
        }
        return true;
    }
}