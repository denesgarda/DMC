package com.denesgarda.DMC;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.io.*;

public class Main extends JavaPlugin implements Listener {
    public static String TOKEN = null;
    public static String CHANNEL = null;
    public static String GUILD = null;
    public static String BOT = null;
    public static JDA JDA;

    @Override
    public void onEnable() {
        getLogger().info("initializing DMC");
        this.getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Loading config");
        File DMCDir = new File("plugins/DMC");
        if(!DMCDir.exists()) {
            DMCDir.mkdir();
        }
        File token = new File("plugins/DMC" + File.separator + "token.txt");
        if (!token.exists()) {
            getLogger().info("Could not find token config file; generating new one");
            try {
                token.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            getLogger().info("After startup, configure new files");
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("plugins/DMC" + File.separator + "token.txt"));
            TOKEN = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File channel = new File("plugins/DMC" + File.separator + "channel.txt");
        if (!channel.exists()) {
            getLogger().info("Could not find channels config file; generating new one");
            try {
                channel.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            getLogger().info("After startup, configure new files");
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("plugins/DMC" + File.separator + "channel.txt"));
            CHANNEL = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File guild  = new File("plugins/DMC" + File.separator + "guild.txt");
        if (!guild.exists()) {
            getLogger().info("Could not find channels config file; generating new one");
            try {
                guild.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            getLogger().info("After startup, configure new files");
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("plugins/DMC" + File.separator + "guild.txt"));
            GUILD = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getLogger().info("Connecting to Discord Client");
        try {
            JDA = JDABuilder.createDefault(TOKEN).addEventListeners().build();
            JDA.addEventListener(new ChannelReader());
        } catch (LoginException e) {
            e.printStackTrace();
        }
        File bot  = new File("plugins/DMC" + File.separator + "bot.txt");
        if (!bot.exists()) {
            getLogger().info("Could not find channels config file; generating new one");
            try {
                bot.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            getLogger().info("After startup, configure new files");
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("plugins/DMC" + File.separator + "bot.txt"));
            BOT = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getLogger().info("Connecting to Discord Client");
        try {
            JDA = JDABuilder.createDefault(TOKEN).addEventListeners().build();
            JDA.addEventListener(new ChannelReader());
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void chatReader(PlayerChatEvent event) {
        String text = "[" + event.getPlayer().getDisplayName() + "]: " + event.getMessage();
        JDA.getTextChannelCache().getElementById(CHANNEL).sendMessage(text).queue();
    }
}
