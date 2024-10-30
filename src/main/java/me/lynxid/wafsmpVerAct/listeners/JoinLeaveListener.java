package me.lynxid.wafsmpVerAct.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListener implements Listener{

    @EventHandler
    public void onLeave(PlayerQuitEvent e){

        Player player = e.getPlayer();

        e.setQuitMessage(ChatColor.YELLOW + player.getDisplayName() + ChatColor.RED + " has left! good riddance");

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        Player player = e.getPlayer();

        if(player.hasPlayedBefore()){
            e.setJoinMessage(ChatColor.DARK_GREEN + "Welcome back to the WSMP " + ChatColor.BOLD + player.getDisplayName() + ChatColor.DARK_GREEN + "!");
        }else{
            e.setJoinMessage(ChatColor.DARK_AQUA + "Welcome to the WSMP " + ChatColor.BOLD + player.getDisplayName() + ChatColor.DARK_AQUA + "! Have Fun!");
        }

    }
}
