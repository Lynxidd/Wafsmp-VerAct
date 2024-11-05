package me.lynxid.wafsmpVerAct;

import me.lynxid.wafsmpVerAct.commands.*;
import me.lynxid.wafsmpVerAct.files.CustomConfig;
import me.lynxid.wafsmpVerAct.listeners.JoinLeaveListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class WafsmpVerAct extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {
        // Plugin startup logic

        System.out.println("Wafsmp VerAct has started!");

        saveDefaultConfig();

        CustomConfig.setup();
        CustomConfig.get().addDefault("test", "this is the default test message");
        CustomConfig.get().options().copyDefaults(true);
        CustomConfig.save();



        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new JoinLeaveListener(this), this);
        getCommand("rules").setExecutor(new Rulescommand());
        getCommand("setjoinmessage").setExecutor(new SetMessageCommand(this));
        getCommand("test").setExecutor(new testcommand());
        getCommand("pReload").setExecutor(new Reloadcommand());
        getCommand("map").setExecutor(new Mapcommand(this));
    }

}
