package me.lynxid.wafsmpVerAct;

import me.lynxid.wafsmpVerAct.commands.*;
import me.lynxid.wafsmpVerAct.files.DiscordFile;
import me.lynxid.wafsmpVerAct.files.PlayerFile;
import me.lynxid.wafsmpVerAct.files.RulesFile;
import me.lynxid.wafsmpVerAct.listeners.ChatListener;
import me.lynxid.wafsmpVerAct.listeners.JoinLeaveListener;
import me.lynxid.wafsmpVerAct.listeners.JoinListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Objects;

public final class WafsmpVerAct extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getLogger().info("Loading WafflesSMP...");
        saveDefaultConfig();

        RulesFile.setup();
        if (RulesFile.get().get("Setup Run") == null) {
            getLogger().info("Resetting Rules...");
            try {
                RulesFile.setDefault();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            getLogger().info("Rules Ready!");
            RulesFile.save();
        }

        getLogger().info("Loading PlayerFiles...");
        PlayerFile.setup();
        RulesFile.reload();


        DiscordFile.startUp();
        getLogger().info("Discord ready!");

        getLogger().info("Wafsmp VerAct has started!");



        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new JoinLeaveListener(this), this);
        getServer().getPluginManager().registerEvents(new JoinListener(),this);
        getServer().getPluginManager().registerEvents(new ChatListener(),this);
        Objects.requireNonNull(getCommand("link")).setExecutor(new LinkCommand());
        Objects.requireNonNull(getCommand("website")).setExecutor(new WebsiteCommand(this));
        Objects.requireNonNull(getCommand("website")).setTabCompleter(new WebsiteTabCompleter());
        Objects.requireNonNull(getCommand("reviewrules")).setExecutor(new ReviewRulesCommand());
        Objects.requireNonNull(getCommand("rules")).setExecutor(new RulesCommand(this));
        Objects.requireNonNull(getCommand("test")).setExecutor(new TestCommand());
        Objects.requireNonNull(getCommand("map")).setExecutor(new MapCommand(this));
    }
}
