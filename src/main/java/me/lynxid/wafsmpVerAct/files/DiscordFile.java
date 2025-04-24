package me.lynxid.wafsmpVerAct.files;


import me.lynxid.wafsmpVerAct.discordcommands.DiscordEvents;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.exceptions.InvalidTokenException;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;


import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static net.dv8tion.jda.api.interactions.commands.OptionType.*;
import static org.bukkit.Bukkit.*;

public class DiscordFile {


    private static File file;
    public static FileConfiguration discordFile;
    public static File discordData;
    public static File userFiles;
    public static JDA jda;


    public static void setup(){
        discordData = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("Wafsmp-VerAct")).getDataFolder(), File.separator + "DiscordData");
        userFiles = new File(discordData, File.separator + "UserFiles");
        if (!discordData.exists()) {
            getLogger().info("[Wafsmp-VerAct] Discord folder not found, attempting to recreate");
            if (!discordData.mkdir()) {
                getLogger().info("[Wafsmp-VerAct] Directory already exists!");
            }
            getLogger().info("[Wafsmp-VerAct] Discord UserFiles not found, attempting to recreate");
            if (!userFiles.mkdir()) {
                getLogger().info("[Wafsmp-VerAct] Directory already exists!");
            }

        } else if (discordData.exists()) {
            if (!userFiles.exists()) {
                getLogger().info("[Wafsmp-VerAct] Discord UserFiles not found, attempting to recreate");
                if (!userFiles.mkdir()) {
                    getLogger().info("[Wafsmp-VerAct] Directory already exists!");
                }
            }
        }

        file = new File(discordData,File.separator + "discord-config.yml");


        if (!file.exists()){
            try{
                if (!file.createNewFile()) {
                    // It is impossible for this to happen, it's just here to get rid of the stupid warning
                    getLogger().info("[Wafsmp-VerAct] File already exists!");
                }
            } catch (IOException e) {
                //
            }

        }
        discordFile = YamlConfiguration.loadConfiguration(file);
    }

    public static void startUp(){

        getLogger().info("[Wafsmp-VerAct] Loading Discord...");
        DiscordFile.setup();

        if (discordFile.get("Token") == null) {
            try {
                DiscordFile.setDefault();
            } catch (IOException | InvalidConfigurationException e) {
                throw new RuntimeException(e);
            }
            DiscordFile.reload();
        } else {
            DiscordFile.save();
        }

        getLogger().info("[Wafsmp-VerAct] Loading discord connections...");
        try {
            jda = JDABuilder.createDefault(getToken(), GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_MEMBERS)
                    .setActivity(Activity.customStatus("Run /verify to get started!"))
                    .addEventListeners(new DiscordEvents())
                    .setMemberCachePolicy(MemberCachePolicy.ALL.and(MemberCachePolicy.lru(1000)))
                    .build();
            getLogger().info("[Wafsmp-VerAct] Discord bot connected!");


            getLogger().info("[Wafsmp-VerAct] Loading Discord commands...");
            CommandListUpdateAction commands = jda.updateCommands();
            commands.addCommands(
                    Commands.slash("link","Link your minecraft account to your discord account")
                            .addOption(STRING, "username", "Your Minecraft username", true),
                    Commands.slash("verify", "Whitelist your minecraft account on the server")
            ).queue();



            jda.awaitReady();
        } catch (InvalidTokenException e) {
            getLogger().severe("[Wafsmp-VerAct] The token in discord-config is invalid! Please change the token in the config and restart/reload the server to apply the changes.");
        } catch (InterruptedException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    public static FileConfiguration get(){
        return discordFile;
    }

    public static String getToken(){
      return discordFile.getString("Token");
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
        DiscordFile.get().set("Token", "<Put Token Here>");
        DiscordFile.get().set("Logging Channel", "<Put Logging Channel ID Here>");
        DiscordFile.get().set("Server Console", "<Put Server Console Channel ID Here>");
        DiscordFile.get().save(file);
    }

    public static void whitelist(String IGN, @NotNull JDA j){
        String console = DiscordFile.get().getString("Server Console");
        if (console != null) {
            Objects.requireNonNull( j.getTextChannelById(console)).sendMessage("whitelist add " + IGN).queue();
        } else {
            getLogger().info("[WafflesSMP]");
        }
    }
}
