package me.lynxid.wafsmpVerAct.discordcommands;

import me.lynxid.wafsmpVerAct.files.DiscordFile;
import me.lynxid.wafsmpVerAct.files.PlayerFile;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static me.lynxid.wafsmpVerAct.files.DiscordFile.jda;
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
        String server = DiscordFile.get().getString("Server ID");
        String botCommands = DiscordFile.get().getString("Bot Commands");
        String log = DiscordFile.get().getString("Logging Channel");
        String adminPing = "<@&" + DiscordFile.get().getString("Ping Role") + ">";


        if (e.getName().equalsIgnoreCase("verify")) {
            id = e.getUser().getId();
            userName = e.getUser().getName();


            file = new File(userFiles,File.separator + id + ".yml");
            discordFile = YamlConfiguration.loadConfiguration(file);
            if (!file.exists()){
                try{
                    String finalUserName = userName;
                    String finalId = id;
                    e.getUser().openPrivateChannel()
                            .flatMap(privateChannel -> privateChannel.sendMessage("""
                                # Welcome to the WSMP verification system!
                                You have just completed the first step to verify your account!\s
                                To continue with verifying your minecraft account there are a few more steps!\s
                                ## Step 2: Please run the following command: ```/link <Minecraft Username>```"""))
                            .onErrorFlatMap(throwable -> {
                                TextChannel channel;
                                if (botCommands != null && server != null) {
                                    getLogger().info("[Wafsmp-VerAct] " + finalUserName + " ran discord command '/verify' and does not have direct messages open!");
                                    channel = Objects.requireNonNull(jda.getGuildById(server)).getTextChannelById(botCommands);
                                    return Objects.requireNonNull(channel).createThreadChannel(finalUserName + "-Verification Thread")
                                            .flatMap(c -> c.sendMessage("""
                                 # Welcome to the WSMP verification system!
                                  have just completed the first step to verify your account!\s
                                 To continue with verifying your minecraft account there are a few more steps!\s
                                 ## Step 2: Please run the following command: ```/link <Minecraft Username>```""" + "<@" + finalId + ">"));
                                } else {
                                    getLogger().info("[Wafsmp-VerAct] bot commands channel not set! please fix this!");
                                }
                                return null;
                            }).queue();
                    if (!file.createNewFile()) {
                        // It is impossible for this to happen, it's just here to get rid of the stupid warning
                        getLogger().info("[Wafsmp-VerAct] File already exists!");
                    }

                    PlayerFile.time();
                    discordFile.load(file);
                    discordFile.set("File created on",date);
                    discordFile.set("Username", userName);
                    discordFile.set("User Id", id);
                    discordFile.set("Minecraft IGN", "Account not linked");
                    discordFile.set("Whitelisted", false);
                    discordFile.set("Blocked", false);
                    discordFile.save(file);
                    getLogger().info("[Wafsmp-VerAct] Created file for " + userName);
                    Objects.requireNonNull(e.getJDA().getTextChannelById(Objects.requireNonNull(log))).sendMessage(
                            "# WafflesSMP Verification and Activity Bot\n" +
                                userName + " has run the command ```/verify``` for the first time!").queue();
                } catch (IOException i) {
                    //
                } catch (InvalidConfigurationException ex) {
                    throw new RuntimeException(ex);
                }
                e.reply("Command logged, please check your @mentions, or Direct Messages").setEphemeral(true).queue();

            } else if (file.exists() && Objects.requireNonNull(discordFile.get("Blocked")).equals(true)) {
                getLogger().info("[Wafsmp-VerAct] " + userName + " ran discord command '/verify' and is blocked!");
                e.reply("""
                            # Warning from WSMP verification system!
                             This account is **blocked** from using our services!\s
                             If you believe this to be a mistake please contact our administrators""").queue();
                Objects.requireNonNull(e.getJDA().getTextChannelById(Objects.requireNonNull(log))).sendMessage(
                        "# WafflesSMP Verification and Activity Bot\n" +
                        userName + " has run the command ```/verify``` and is blocked from using our systems!!\n" + "||" + adminPing + "||").queue();
            } else if (file.exists() && Boolean.valueOf(discordFile.getBoolean("Whitelisted")).equals(false) && Objects.requireNonNull(discordFile.get("Blocked")).equals(false)) {
                getLogger().info("[Wafsmp-VerAct] " + userName + " ran discord command '/verify'");
                e.reply("""
                            # Welcome back to the WSMP verification system!
                             You have already completed this step!\s
                             To continue with verifying your minecraft account please follow the **next step!!**\s
                            ## Step 2: Please run the following command: ```/link <Minecraft Username>```""").queue();
                Objects.requireNonNull(e.getJDA().getTextChannelById(Objects.requireNonNull(log))).sendMessage(
                        "# WafflesSMP Verification and Activity Bot\n" +
                        userName + " has run the command ```/verify``` and has already completed this step, but has not moved on to the next step.").queue();
            } else if (file.exists() && Objects.requireNonNull(discordFile.get("Whitelisted")).equals(true) && Objects.requireNonNull(discordFile.get("Blocked")).equals(false)) {
                String IGN = discordFile.getString("Minecraft IGN");
                String date = discordFile.getString("File created on");
                getLogger().info("[Wafsmp-VerAct] " + userName + " ran discord command '/verify' and is already whitelisted!");
                e.reply("# Welcome back to the WSMP verification system!\n" +
                        " You have already completed this step! \n" +
                        " This account is already linked with a minecraft account! \n" +
                        " According to our records you linked this discord account to " + IGN + " on "+ date + "\n" +
                        " If you already have linked your minecraft and still can't connect to the WafflesSMP please contact our administrators!!").queue();
                Objects.requireNonNull(e.getJDA().getTextChannelById(Objects.requireNonNull(log))).sendMessage(
                        "# WafflesSMP Verification and Activity Bot\n" +
                        userName + " has run the command ```/verify``` and is already whitelisted.").queue();
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
            } else {
                if (!file.exists()){
                    getLogger().info("[Wafsmp-VerAct] " + userName + " ran discord command '/link' but has not completed step one!");
                    e.reply("""
                            # Welcome to the WSMP verification system!
                            You have not completed the prior step!\s
                            To begin with verifying your minecraft account you must follow the first steps!\s
                            ## Step 1: Please run the following command: ```/verify```""").queue();
                    Objects.requireNonNull(e.getJDA().getTextChannelById(Objects.requireNonNull(log))).sendMessage(
                            "# WafflesSMP Verification and Activity Bot\n" +
                            userName + " has run the command ```/link``` but has not completed step one.").queue();
                } else if (file.exists() && Boolean.valueOf(discordFile.getBoolean("Whitelisted")).equals(false) && Objects.requireNonNull(discordFile.get("Blocked")).equals(false)) {
                    getLogger().info("[Wafsmp-VerAct] " + userName + " ran discord command '/link'");
                    JDA j = e.getJDA();
                    try {
                        discordFile.load(file);
                        discordFile.set("Minecraft IGN", content);
                        discordFile.set("Whitelisted", true);
                        discordFile.set("Whitelisted on", date);
                        discordFile.save(file);
                        getLogger().info("[Wafsmp-VerAct] Whitelisted " + content + " for " + userName);
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
                    Objects.requireNonNull(e.getJDA().getTextChannelById(Objects.requireNonNull(log))).sendMessage(
                            "# WafflesSMP Verification and Activity Bot\n" +
                            userName + " has run the command ```/link``` and whitelisted the account " + content).queue();
                }else if (file.exists() && Objects.requireNonNull(discordFile.get("Blocked")).equals(true)) {
                    getLogger().info("[Wafsmp-VerAct] " + userName + " ran discord command '/verify' and is blocked!");
                    e.reply("""
                            # Warning from WSMP verification system!
                             This account is blocked from using our services!\s
                             If you believe this to be a mistake please contact our administrators""").queue();
                    Objects.requireNonNull(e.getJDA().getTextChannelById(Objects.requireNonNull(log))).sendMessage(
                            "# WafflesSMP Verification and Activity Bot\n" +
                            userName + " has run the command ```/link``` and is blocked from using our systems!!\n" + "||" + adminPing + "||\n" + adminPing).queue();
                } else if (file.exists() && Boolean.valueOf(discordFile.getBoolean("Whitelisted")).equals(true)) {
                    String IGN = discordFile.getString("Minecraft IGN");
                    String date = discordFile.getString("File created on");
                    getLogger().info("[Wafsmp-VerAct] " + userName + " ran discord command '/verify' and is already whitelisted!");
                    e.reply("# Welcome back to the WSMP verification system!\n" +
                            " You have already completed this step! \n" +
                            " This account is already linked with a minecraft account! \n" +
                            " According to our records you linked this discord account to " + IGN + " on "+ date + "\n" +
                            " If you already have linked your minecraft and still can't connect to the WafflesSMP please contact our administrators!!").queue();
                    Objects.requireNonNull(e.getJDA().getTextChannelById(Objects.requireNonNull(log))).sendMessage(
                            "# WafflesSMP Verification and Activity Bot\n" +
                            userName + " has run the command ```/link``` and is already whitelisted.").queue();
                }
            }
        }
    }
}

