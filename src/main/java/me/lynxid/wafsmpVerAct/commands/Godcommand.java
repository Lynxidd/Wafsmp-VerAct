package me.lynxid.wafsmpVerAct.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Godcommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (sender instanceof Player p){

            if (p.isInvulnerable()){

                    p.setInvulnerable(false);
                    p.sendMessage(ChatColor.DARK_RED + "You are weak");
            } else {

                p.setInvulnerable(true);
                p.sendMessage(ChatColor.DARK_GREEN + "You are stronk");

            }

        }

        return true;
    }
}
