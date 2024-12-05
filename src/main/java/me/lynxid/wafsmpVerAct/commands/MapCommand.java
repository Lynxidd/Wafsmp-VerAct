package me.lynxid.wafsmpVerAct.commands;

import me.lynxid.wafsmpVerAct.WafsmpVerAct;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class MapCommand implements CommandExecutor {

    private  final WafsmpVerAct plugin;

    public MapCommand(WafsmpVerAct plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, String[] strings) {

        String url = this.plugin.getConfig().getString("mapURL");

        if (url == null) return false;{

            sender.sendMessage(ChatColor.GREEN + "You can view the map here:");

            TextComponent msg = new TextComponent("[Map!]");
            msg.setColor(ChatColor.GOLD.asBungee());
            msg.setBold(true);

            msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new Text("Click here to see the map!")));

           sender.spigot().sendMessage(msg);


        }

        return true;
    }
}
