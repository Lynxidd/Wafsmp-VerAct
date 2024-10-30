package me.lynxid.wafsmpVerAct;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class WafsmpVerAct extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic

        System.out.println("Wafsmp VerAct has started!");

        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){

        System.out.println("A Player Has joined");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        System.out.println("Wafsmp VerAct has stopped.");
    }
}
