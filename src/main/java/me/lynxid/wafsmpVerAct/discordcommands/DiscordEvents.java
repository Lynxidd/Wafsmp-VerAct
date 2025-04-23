package me.lynxid.wafsmpVerAct.discordcommands;

import me.lynxid.wafsmpVerAct.files.DiscordFile;
import me.lynxid.wafsmpVerAct.files.PlayerFile;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static me.lynxid.wafsmpVerAct.files.DiscordFile.userFiles;
import static me.lynxid.wafsmpVerAct.files.PlayerFile.date;
import static org.bukkit.Bukkit.getLogger;

public class DiscordEvents extends ListenerAdapter {

    private FileConfiguration discordFile;

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent e) {
        String userName;
        File file;
        String id;
        if (e.getName().equalsIgnoreCase("verify")) {
            id = e.getUser().getId();
            userName = e.getUser().getName();

            file = new File(userFiles,File.separator + id + ".yml");
            discordFile = YamlConfiguration.loadConfiguration(file);
            if (!file.exists()){
                try{
                    if (!file.createNewFile()) {
                        // It is impossible for this to happen, it's just here to get rid of the stupid warning
                        getLogger().info("File already exists!");
                    }
                    getLogger().info("Creating file for " + userName + "!");
                    PlayerFile.time();
                    discordFile.load(file);
                    discordFile.set("File created on",date);
                    discordFile.set("Username", userName);
                    discordFile.set("User Id", id);
                    discordFile.set("Minecraft IGN", "Account not linked");
                    discordFile.set("Whitelisted", false);
                    discordFile.set("Blocked", false);
                    discordFile.save(file);
                    getLogger().info("Created file for " + userName);
                    e.reply("""
                            # Welcome to the WSMP verification system!
                             You have just completed the first step to verify your account!\s
                             To continue with verifying your minecraft account there are a few more steps!\s
                            ## Step 2: Please run the following command: ```/link <Minecraft Username>```""").queue();
                } catch (IOException i) {
                    //
                } catch (InvalidConfigurationException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (file.exists() && Boolean.valueOf(discordFile.getBoolean("Whitelisted")).equals(false) && Objects.requireNonNull(discordFile.get("Blocked")).equals(false)) {
                getLogger().info(userName + " ran discord command '/verify'");
                e.reply("""
                            # Welcome back to the WSMP verification system!
                             You have already completed this step!\s
                             To continue with verifying your minecraft account please follow the **next step!!**\s
                            ## Step 2: Please run the following command: ```/link <Minecraft Username>```""").queue();
            } else if (file.exists() && Boolean.valueOf(discordFile.getBoolean("Whitelisted")).equals(true)) {
                String IGN = discordFile.getString("Minecraft IGN");
                String date = discordFile.getString("File created on");
                getLogger().info(userName + " ran discord command '/verify' and is already whitelisted!");
                e.reply("# Welcome back to the WSMP verification system!\n" +
                        " You have already completed this step! \n" +
                        " This account is already linked with a minecraft account! \n" +
                        " According to our records you linked this discord account to " + IGN + " on "+ date + "\n" +
                        " If you already have linked your minecraft and still can't connect to the WafflesSMP please contact our administrators!!").queue();
            } else if (file.exists() && Objects.requireNonNull(discordFile.get("Blocked")).equals(true)) {
                getLogger().info(userName + " ran discord command '/verify' and is blocked!");
                e.reply("""
                            # Warning from WSMP verification system!
                             This account is blocked from using our services!\s
                             If you believe this to be a mistake please contact our administrators""").queue();
           }
        }


        if (e.getName().equalsIgnoreCase("link")) {
            id = e.getUser().getId();
            userName = e.getUser().getName();
            file = new File(userFiles,File.separator + id + ".yml");
            String content = Objects.requireNonNull(e.getOption("username")).getAsString();

            if (content.length() > 16 || content.length() < 3) {

                e.reply("""
                        # Warning from WSMP verification system!
                         You have entered a invalid username!\s
                         Please check your spelling!\s
                         If you believe this to be a mistake please contact our administrators!!""").queue();
            } else if (content.length() < 16 && content.length() > 3) {
                if (!file.exists()){
                    getLogger().info(userName + " ran discord command '/link' but has not completed step one!");
                    e.reply("""
                            # Welcome to the WSMP verification system!
                            You have not completed the prior step!\s
                            To begin with verifying your minecraft account you must follow the first steps!\s
                            ## Step 1: Please run the following command: ```/verify```""").queue();
                } else if (file.exists() && Boolean.valueOf(discordFile.getBoolean("Whitelisted")).equals(false) && Objects.requireNonNull(discordFile.get("Blocked")).equals(false)) {
                    getLogger().info(userName + " ran discord command '/link'");
                    JDA j = e.getJDA();
                    try {
                        discordFile.load(file);
                        discordFile.set("Minecraft IGN", content);
                        discordFile.set("Whitelisted", true);
                        discordFile.set("Whitelisted on", date);
                        discordFile.save(file);
                        getLogger().info("Whitelisted " + content + " for " + userName);
                    } catch (IOException i) {
                        //
                    } catch (InvalidConfigurationException ex) {
                        throw new RuntimeException(ex);
                    }
                        DiscordFile.whitelist(content, j);
                    e.reply("""
                            # WSMP verification system!
                             You have just completed the final step to verify your account!\s
                             To continue with verifying your minecraft account there is one more step!\s
                            ## Step 3: Join the server at ```wafflessmp.mcserver.us```""").queue();

                } else if (file.exists() && Boolean.valueOf(discordFile.getBoolean("Whitelisted")).equals(true)) {
                    String IGN = discordFile.getString("Minecraft IGN");
                    String date = discordFile.getString("File created on");
                    getLogger().info(userName + " ran discord command '/verify' and is already whitelisted!");
                    e.reply("# Welcome back to the WSMP verification system!\n" +
                            " You have already completed this step! \n" +
                            " This account is already linked with a minecraft account! \n" +
                            " According to our records you linked this discord account to " + IGN + " on "+ date + "\n" +
                            " If you already have linked your minecraft and still can't connect to the WafflesSMP please contact our administrators!!").queue();
                } else if (file.exists() && Objects.requireNonNull(discordFile.get("Blocked")).equals(true)) {
                    getLogger().info(userName + " ran discord command '/verify' and is blocked!");
                    e.reply("""
                            # Warning from WSMP verification system!
                             This account is blocked from using our services!\s
                             If you believe this to be a mistake please contact our administrators""").queue();
                }
            }
        }
    }
}

