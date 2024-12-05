package me.lynxid.wafsmpVerAct.commands;

import me.lynxid.wafsmpVerAct.files.CustomConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class ReloadCommand implements CommandExecutor {
    @Override


    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, String[] strings) {

        Player player = (Player) sender;
        CustomConfig.reload();


        player.sendMessage("Custom Config Reloaded!");

        return true;
    }
}
