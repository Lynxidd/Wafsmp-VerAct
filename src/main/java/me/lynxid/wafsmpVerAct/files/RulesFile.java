package me.lynxid.wafsmpVerAct.files;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import static org.bukkit.Bukkit.getLogger;

public class RulesFile {

    private static File file;
    public static FileConfiguration rulesFile;


    public static void setup(){
        file = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("Wafsmp-VerAct")).getDataFolder(), "rules-config.yml");

        if (!file.exists()){
            try{
                if (!file.createNewFile()) {
                    // It is impossible for this to happen, it's just here to get rid of the stupid warning
                    getLogger().info("File already exists!");
                }
            } catch (IOException e) {
                //
            }
        }
        rulesFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get(){
        return rulesFile;
    }

    public static void save(){
        try {
            rulesFile.save(file);
        } catch (IOException e) {
            System.out.println(ChatColor.DARK_RED + "ERROR could not save file");
        }
    }

    public static void reload(){
        rulesFile = YamlConfiguration.loadConfiguration(file);
    }

    public static void setDefault() {
        RulesFile.get().set("Setup run", true);
        RulesFile.get().set("logo", "\uE000");
        RulesFile.get().set("rule1", "Don’t do anything intended to make someone unhappy. Don’t make fun of people, and no racism, homophobia, transphobia, etc. While swearing is allowed (in moderation), slurs are NEVER allowed.");
        RulesFile.get().set("rule2", "Minecraft is a game that lots of younger kids play. No NSFW, In chat or built on the server, Don’t talk about anything unpleasant or controversial. Everyone is here to play a game and have fun.");
        RulesFile.get().set("rule3","All of the rules listed above apply everywhere in the server and are enforced by admins, but every player-made country has their own rules that their leaders enforce. Make sure that while in an in-game country, you follow their laws and international laws.");
        RulesFile.get().set("rule4","No cheating, hacking, or exploiting! Automatic farms are allowed but duping is not, the only things you can duplicate are sand/gravel/concrete and tnt, as these are non-renewable resources. Again any form of finding ores that wasn’t intended by Mojang is prohibited");
        RulesFile.get().set("rule5","This includes spam in chat or purposefully building at someone else’s base just to annoy them, or anything else meant to annoy someone.");
        RulesFile.get().set("rule6","Don’t share anyone else’s personal information, no discussion of illegal activity. the usage or discussion of cracked/free Minecraft accounts will get you banned!");
    }
}
