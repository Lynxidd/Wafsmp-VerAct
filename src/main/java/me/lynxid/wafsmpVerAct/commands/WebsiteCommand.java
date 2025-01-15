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

public class WebsiteCommand implements CommandExecutor {

    private final WafsmpVerAct plugin;

    public WebsiteCommand(WafsmpVerAct plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, String[] strings) {

        if (strings.length < 1) {
            String url = this.plugin.getConfig().getString("websiteURL");

            if (url == null) return false;
            {
                sender.sendMessage(ChatColor.GREEN + "You can view the website here:");

                TextComponent msg = new TextComponent("[Website!]");
                msg.setColor(ChatColor.BLUE.asBungee());
                msg.setBold(true);

                msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
                msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click here to see our website!")));
                sender.spigot().sendMessage(msg);
            }
        } else if (strings[0].equalsIgnoreCase("countries") && strings.length < 2) {
            String url = this.plugin.getConfig().getString("countriesURL");
            if (url == null) return false;
            {
                sender.sendMessage(ChatColor.GREEN + "You can view our countries here:");

                TextComponent msg = new TextComponent("[Countries!]");
                msg.setColor(ChatColor.BLUE.asBungee());
                msg.setBold(true);

                msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
                msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click here to see our countries!")));
                sender.spigot().sendMessage(msg);
            }
        }

        return true;
    }
}
