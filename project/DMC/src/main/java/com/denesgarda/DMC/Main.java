package com.denesgarda.DMC;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.io.*;

public class Main extends JavaPlugin implements Listener {
    public static String TOKEN = null;
    public static String CHANNEL = null;
    public static String GUILD = null;

    @Override
    public void onEnable() {
        getLogger().info("initializing DMC");
        this.getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Loading config");
        File token = new File("DMC/token.txt");
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
            BufferedReader bufferedReader = new BufferedReader(new FileReader("DMC/token.txt"));
            TOKEN = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File channel = new File("DMC/channel.txt");
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
            BufferedReader bufferedReader = new BufferedReader(new FileReader("DMC/channel.txt"));
            CHANNEL = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File guild  = new File("DMC/guild.txt");
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
            BufferedReader bufferedReader = new BufferedReader(new FileReader("DMC/guild.txt"));
            GUILD = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getLogger().info("Connecting to Discord Client");
        try {
            JDA jda = JDABuilder.createDefault(TOKEN).addEventListeners().build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
