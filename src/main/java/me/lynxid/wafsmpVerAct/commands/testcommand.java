package me.lynxid.wafsmpVerAct.commands;

import me.lynxid.wafsmpVerAct.files.CustomConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class testcommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (sender instanceof Player p){
            p.sendMessage(CustomConfig.get().getString("test"));
        }

        return true;
    }
}
