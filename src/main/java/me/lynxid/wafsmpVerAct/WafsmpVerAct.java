package me.lynxid.wafsmpVerAct;

import me.lynxid.wafsmpVerAct.commands.*;
import me.lynxid.wafsmpVerAct.files.PlayerFile;
import me.lynxid.wafsmpVerAct.listeners.JoinLeaveListener;
import me.lynxid.wafsmpVerAct.listeners.JoinListener;
import me.lynxid.wafsmpVerAct.listeners.PlayerActivityListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class WafsmpVerAct extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {
        // Plugin startup logic

        getLogger().info("Wafsmp VerAct has started!");

        saveDefaultConfig();

        PlayerFile.setup();


        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new JoinLeaveListener(this), this);
        getServer().getPluginManager().registerEvents(new JoinListener(),this);
        getServer().getPluginManager().registerEvents(new PlayerActivityListener(),this);
        Objects.requireNonNull(getCommand("activity")).setExecutor(new ActivityCommand());
        Objects.requireNonNull(getCommand("reviewrules")).setExecutor(new ReviewRulesCommand());
        Objects.requireNonNull(getCommand("rules")).setExecutor(new RulesCommand());
        Objects.requireNonNull(getCommand("test")).setExecutor(new TestCommand(this));
        Objects.requireNonNull(getCommand("map")).setExecutor(new MapCommand(this));
    }

}
