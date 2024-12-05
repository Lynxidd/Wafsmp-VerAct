package me.lynxid.wafsmpVerAct.commands;

import me.lynxid.wafsmpVerAct.WafsmpVerAct;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class SetMessageCommand implements CommandExecutor {

    private final WafsmpVerAct plugin;

    public SetMessageCommand(WafsmpVerAct plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, String[] strings) {

        if (strings.length > 0){
            StringBuilder message = new StringBuilder();
            for (String string : strings) {
                message.append(string).append(" ");
            }

            this.plugin.getConfig().set("join-message", message);
            this.plugin.saveConfig();
            sender.sendMessage("Join message set to: " + message);

        }else{
            sender.sendMessage("you must provide a new message!");
        }

        return true;
    }

}
