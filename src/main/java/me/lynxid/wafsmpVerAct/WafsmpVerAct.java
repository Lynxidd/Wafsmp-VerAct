package me.lynxid.wafsmpVerAct;

import me.lynxid.wafsmpVerAct.commands.*;
import me.lynxid.wafsmpVerAct.files.PlayerFile;
import me.lynxid.wafsmpVerAct.files.RulesFile;
import me.lynxid.wafsmpVerAct.listeners.ChatListener;
import me.lynxid.wafsmpVerAct.listeners.JoinLeaveListener;
import me.lynxid.wafsmpVerAct.listeners.JoinListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class WafsmpVerAct extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {
        // Plugin startup logic

        getLogger().info("Wafsmp VerAct has started!");

        saveDefaultConfig();
        RulesFile.setup();
        RulesFile.setDefault();
        RulesFile.save();

        PlayerFile.setup();
        RulesFile.reload();

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new JoinLeaveListener(this), this);
        getServer().getPluginManager().registerEvents(new JoinListener(),this);
        getServer().getPluginManager().registerEvents(new ChatListener(),this);
        Objects.requireNonNull(getCommand("website")).setExecutor(new WebsiteCommand(this));
        Objects.requireNonNull(getCommand("website")).setTabCompleter(new WebsiteTabCompleter());
        Objects.requireNonNull(getCommand("reviewrules")).setExecutor(new ReviewRulesCommand());
        Objects.requireNonNull(getCommand("rules")).setExecutor(new RulesCommand(this));
        Objects.requireNonNull(getCommand("test")).setExecutor(new TestCommand());
        Objects.requireNonNull(getCommand("map")).setExecutor(new MapCommand(this));
    }
}
