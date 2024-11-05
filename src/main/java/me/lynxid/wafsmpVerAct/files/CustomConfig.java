package me.lynxid.wafsmpVerAct.files;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CustomConfig {

    private static File file;
    private static FileConfiguration customFile;

    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Wafsmp-VerAct").getDataFolder(), "customconfig.yml");

        if (!file.exists()){
            try{
                file.createNewFile();
            } catch (IOException e) {
              //
            }


        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get(){
        return customFile;
    }

    public static void save(){
        try {
            customFile.save(file);
        } catch (IOException e) {
            System.out.println(ChatColor.DARK_RED + "ERROR could not save file");
        }
    }

    public static void reload(){
        customFile = YamlConfiguration.loadConfiguration(file);
    }
}
