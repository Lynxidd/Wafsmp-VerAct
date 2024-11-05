package me.lynxid.wafsmpVerAct.listeners;

import me.lynxid.wafsmpVerAct.WafsmpVerAct;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinLeaveListener implements Listener{

    private  final WafsmpVerAct plugin;

    public JoinLeaveListener(WafsmpVerAct plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();

        String joinmessage = this.plugin.getConfig().getString("join-message");

        if (joinmessage != null) {
            if (player.hasPlayedBefore()) {
                joinmessage = joinmessage.replace("%player%", e.getPlayer().getDisplayName());
                e.setJoinMessage(ChatColor.translateAlternateColorCodes('&',joinmessage));
            } else {

                e.setJoinMessage(" ");
                e.getPlayer().sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Please read and accept the rules!!");

                TextComponent msg = new TextComponent("[Click here]");
                msg.setColor(ChatColor.DARK_GREEN.asBungee());
                msg.setBold(true);

                msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rules"));
                msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new Text("Click here to read the rules")));

                e.getPlayer().spigot().sendMessage(msg);

            }

        }
    }
}
