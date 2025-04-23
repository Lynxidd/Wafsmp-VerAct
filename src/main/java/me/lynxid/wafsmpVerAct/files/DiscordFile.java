package me.lynxid.wafsmpVerAct.files;


import me.lynxid.wafsmpVerAct.discordcommands.DiscordEvents;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.exceptions.InvalidTokenException;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;


import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static me.lynxid.wafsmpVerAct.files.PlayerFile.date;
import static net.dv8tion.jda.api.interactions.commands.OptionType.*;
import static org.bukkit.Bukkit.getLogger;

public class DiscordFile {


    private static File file;
    public static FileConfiguration discordFile;
    public static File discordData;
    public static File userFiles;



    public static void setup(){
        discordData = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("Wafsmp-VerAct")).getDataFolder(), File.separator + "DiscordData");
        userFiles = new File(discordData, File.separator + "UserFiles");
        if (!discordData.exists()) {
            getLogger().info("Discord folder not found, attempting to recreate");
            if (!discordData.mkdir()) {
                getLogger().info("Directory already exists!");
            }
            getLogger().info("Discord UserFiles not found, attempting to recreate");
            if (!userFiles.mkdir()) {
                getLogger().info("Directory already exists!");
            }

        } else if (discordData.exists()) {
            if (!userFiles.exists()) {
                getLogger().info("Discord UserFiles not found, attempting to recreate");
                if (!userFiles.mkdir()) {
                    getLogger().info("Directory already exists!");
                }
            }
        }

        file = new File(discordData,File.separator + "discord-config.yml");


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
        discordFile = YamlConfiguration.loadConfiguration(file);
    }

    public static void startUp(){

        getLogger().info("Loading Discord...");
        DiscordFile.setup();

        if (discordFile.get("token") == null) {
            try {
                DiscordFile.setDefault();
            } catch (IOException | InvalidConfigurationException e) {
                throw new RuntimeException(e);
            }
            DiscordFile.reload();
        } else {
            DiscordFile.save();
        }

        getLogger().info("Loading discord connections...");
        try {
            JDA jda = JDABuilder.createDefault(getToken(), GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS)
                    .setActivity(Activity.watching("your messages"))
                    .addEventListeners(new DiscordEvents())
                    .build();
            getLogger().info("Discord bot connected!");


            getLogger().info("Loading Discord commands...");
            CommandListUpdateAction commands = jda.updateCommands();
            commands.addCommands(
                    Commands.slash("link","Link your minecraft account to your discord account")
                            .addOption(STRING, "username", "Your Minecraft username", true),
                    Commands.slash("verify", "Whitelist your minecraft account on the server")
            ).queue();

            jda.awaitReady();
        } catch (InvalidTokenException e) {
            getLogger().severe("The token in discord-config is invalid! Please change the token in the config and restart/reload the server to apply the changes.");
            getLogger().info("Disabling discord bot");
        } catch (InterruptedException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    public static FileConfiguration get(){
        return discordFile;
    }

    public static String getToken(){
      return discordFile.getString("token");
    }

    public static void save(){
        try {
            discordFile.save(file);
        } catch (IOException e) {
            System.out.println(ChatColor.DARK_RED + "ERROR could not save file");
        }
    }

    public static void reload(){
        discordFile = YamlConfiguration.loadConfiguration(file);
    }

    public static void setDefault() throws IOException, InvalidConfigurationException {
        DiscordFile.get().load(file);
        DiscordFile.get().set("token", "");
        DiscordFile.get().set("migrated-date(dd-MM-yyyy)", date);
        DiscordFile.get().save(file);
    }

    public static void whitelist(String IGN, JDA j){
        Objects.requireNonNull( j.getTextChannelById("1317993365924745277")).sendMessage("whitelist add " + IGN).queue();
    }
}
