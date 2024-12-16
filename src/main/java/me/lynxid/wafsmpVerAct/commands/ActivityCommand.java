package me.lynxid.wafsmpVerAct.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ActivityCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (sender instanceof Player p) {

            p.sendMessage("fuck you");
            // Please do remaining work in this branch

        }

        return true;
    }
}
