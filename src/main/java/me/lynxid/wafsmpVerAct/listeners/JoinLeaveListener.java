package me.lynxid.wafsmpVerAct.listeners;

import me.lynxid.wafsmpVerAct.WafsmpVerAct;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static me.lynxid.wafsmpVerAct.files.PlayerFile.userData;


public class JoinLeaveListener implements Listener {

    private final WafsmpVerAct plugin;

    public JoinLeaveListener(WafsmpVerAct plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws IOException, InvalidConfigurationException {

        String joinMessage = this.plugin.getConfig().getString("join-message");
        UUID playerId = e.getPlayer().getUniqueId();
        File file = new File(userData, File.separator + playerId + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(file);
        String playerName = e.getPlayer().getDisplayName();

        if (joinMessage != null && playerData.getBoolean("Accepted Rules") ) {
            joinMessage = joinMessage.replace("%player%", e.getPlayer().getDisplayName());
            e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', joinMessage));
        } else {
            e.setJoinMessage(" ");
            e.getPlayer().sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Please read and accept the rules!!");

            TextComponent msg = new TextComponent("[Click here]");
            msg.setColor(ChatColor.DARK_GREEN.asBungee());
            msg.setBold(true);

            msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rules"));
            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click here to read the rules")));

            e.getPlayer().spigot().sendMessage(msg);


        }

        if (e.getPlayer().getDisplayName().equals(playerData.getString("Player Name"))){
            e.getPlayer().sendMessage(" ");
        } else {

            playerData.load(file);
            playerData.set("Player Name", playerName);
            playerData.save(file);

        }


    }
}




