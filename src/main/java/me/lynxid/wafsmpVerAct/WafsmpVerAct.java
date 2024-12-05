package me.lynxid.wafsmpVerAct;

import me.lynxid.wafsmpVerAct.commands.*;
import me.lynxid.wafsmpVerAct.files.CustomConfig;
import me.lynxid.wafsmpVerAct.files.PlayerFile;
import me.lynxid.wafsmpVerAct.listeners.JoinLeaveListener;
import me.lynxid.wafsmpVerAct.listeners.JoinListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class WafsmpVerAct extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {
        // Plugin startup logic

        getLogger().info("Wafsmp VerAct has started!");

        saveDefaultConfig();

        CustomConfig.setup();
        CustomConfig.get().addDefault("test", "this is the default test message");
        CustomConfig.get().options().copyDefaults(true);
        CustomConfig.save();

        PlayerFile.setup();


        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new JoinLeaveListener(this), this);
        getServer().getPluginManager().registerEvents(new JoinListener(),this);
        getCommand("reviewrules").setExecutor(new ReviewRulesCommand());
        getCommand("rules").setExecutor(new RulesCommand());
        getCommand("setjoinmessage").setExecutor(new SetMessageCommand(this));
        getCommand("test").setExecutor(new TestCommand(this));
        getCommand("pReload").setExecutor(new ReloadCommand());
        getCommand("map").setExecutor(new MapCommand(this));
    }

}
