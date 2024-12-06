package me.lynxid.wafsmpVerAct.files;

import org.bukkit.Bukkit;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static org.bukkit.Bukkit.getLogger;

public class PlayerFile {

//    private static File file;
    public static File userData;
    public static String date;

    public static void time() {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        date = format.format(now);
    }

    public static void setup() {
        userData = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("Wafsmp-VerAct")).getDataFolder(), File.separator + "UserData");

        if (!userData.exists()) {
            getLogger().info("[Wafsmp-VerAct] userdata folder not found, attempting to recreate");
            if (!userData.mkdir())
            {
                getLogger().info("Directory already exists!");
            }
        }

    }
}
