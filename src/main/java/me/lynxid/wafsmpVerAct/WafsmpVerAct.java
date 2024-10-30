package me.lynxid.wafsmpVerAct;

import me.lynxid.wafsmpVerAct.listeners.ShearSheepListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class WafsmpVerAct extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic

        System.out.println("Wafsmp VerAct has started!");

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new ShearSheepListener(), this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){

        event.setJoinMessage("Welcome back to the WSMP!");

    }

    @EventHandler
    public void onLeaveBed(PlayerBedLeaveEvent event){

       Player player = event.getPlayer();
       player.setHealth(0);
       player.sendMessage("You left a bed. :( sowry");

    }

}
