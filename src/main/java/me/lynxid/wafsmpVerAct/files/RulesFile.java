package me.lynxid.wafsmpVerAct.files;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

        List<String> rulesList = new ArrayList<>();
        rulesList.add("Don’t do anything intended to make someone unhappy. Don’t make fun of people, and no racism, homophobia, transphobia, etc will be tolerated. While swearing is allowed (in moderation), slurs are NEVER allowed.");
        rulesList.add("Minecraft is a game that lots of younger kids play. No NSFW, either in chat or built on the server, and don’t talk about anything unpleasant or controversial. Everyone is here to play a game and have fun.");
        rulesList.add("No cheating, hacking, or exploiting! Automatic farms are allowed but item duping is not, the only things you can duplicate are sand/gravel/concrete and tnt, as these are non-renewable resources. Don't use any form of finding ores or structures that wasn’t intended by Mojang.");
        rulesList.add("Be a good person - don’t share anyone else’s personal information, and don't discuss illegal activity, including cracked or free Minecraft accounts.");
        rulesList.add("Don't be a pest! This includes spam in chat or purposefully building at someone else’s base just to annoy them, or anything else meant to bother someone.");
        rulesList.add("All of the rules listed above apply everywhere in the server and are enforced by admins, but every player-made country has their own rules that their leaders enforce, and international laws (including no griefing / stealing) apply everywhere else. Make sure to follow the law.");

        RulesFile.get().set("rules", rulesList);
    }

    public static void effectsGive(Player p) {
        p.setGameMode(GameMode.ADVENTURE);
        p.setCanPickupItems(false);
        p.setWalkSpeed(0);
        p.setInvulnerable(true);
    }

    public static void effectsTake(Player p) {
        p.setGameMode(GameMode.SURVIVAL);
        p.setCanPickupItems(true);
        p.setWalkSpeed(0.2F);
        p.setInvulnerable(false);
    }
}
