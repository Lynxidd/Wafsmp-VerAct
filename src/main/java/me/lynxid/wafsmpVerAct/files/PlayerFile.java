package me.lynxid.wafsmpVerAct.files;

import org.bukkit.Bukkit;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.bukkit.Bukkit.getLogger;

public class PlayerFile {

//    private static File file;
    public static File userdata;
    public static String date;


    public static void setup() {

        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        date = format.format(now);

        userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("Wafsmp-VerAct").getDataFolder(), File.separator + "UserData");

        if (!userdata.exists()) {
            getLogger().info("[Wafsmp-VerAct] userdata folder not found, attempting to recreate");
            userdata.mkdir();
        }

    }
}
