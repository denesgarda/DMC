package com.denesgarda.DMC;

import com.denesgarda.DMC.properties.PropertiesFile;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.io.*;

public class Main extends JavaPlugin implements Listener {
    public static PropertiesFile CONFIG = null;
    public static String TOKEN = null;
    public static String CHANNEL = null;
    public static String GUILD = null;
    public static boolean ONLINE;
    public static boolean JOIN_LEAVE;
    public static JDA JDA;

    @Override
    public void onEnable() {
        getLogger().info("initializing DMC");
        this.getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Loading config");
        try {
            CONFIG = PropertiesFile.returnObject("plugins" + File.separator + "DMC" + File.separator + "config.properties");
            TOKEN = CONFIG.getPropertyNotNull("bot-token", "");
            CHANNEL = CONFIG.getPropertyNotNull("channel-id", "");
            GUILD = CONFIG.getPropertyNotNull("guild-id", "");
            ONLINE = Boolean.parseBoolean(CONFIG.getPropertyNotNull("display-online-players", "true"));
            JOIN_LEAVE = Boolean.parseBoolean(CONFIG.getPropertyNotNull("send-leave-and-join-messages", "true"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        getLogger().info("Connecting to Discord Client");
        try {
            JDA = JDABuilder.createDefault(TOKEN).addEventListeners().build();
            JDA.addEventListener(new ChannelReader());
            if (ONLINE) {
                JDA.getPresence().setPresence(Activity.watching("0 Players Play"), true);
            }
        } catch (IllegalArgumentException | LoginException e) {
            getLogger().info("DMC is not configured; stop the server to configure");
        }
    }

    @EventHandler
    public void chatReader(PlayerChatEvent event) {
        String text = "[" + event.getPlayer().getDisplayName() + "]: " + event.getMessage();
        JDA.getTextChannelCache().getElementById(CHANNEL).sendMessage(text).queue();
    }

    @EventHandler
    public void joins(PlayerJoinEvent event) {
        if (ONLINE) {
            JDA.getPresence().setPresence(Activity.watching(event.getPlayer().getServer().getOnlinePlayers().size() + " Players Play"), true);
        }
        if (JOIN_LEAVE) {
            JDA.getTextChannelCache().getElementById(CHANNEL).sendMessage("**" + event.getPlayer().getDisplayName() + " joined the server**").queue();
        }
    }

    @EventHandler
    public void leaves(PlayerQuitEvent event) {
        if (ONLINE) {
            JDA.getPresence().setPresence(Activity.watching((event.getPlayer().getServer().getOnlinePlayers().size() - 1) + " Players Play"), true);
        }
        if (JOIN_LEAVE) {
            JDA.getTextChannelCache().getElementById(CHANNEL).sendMessage("**" + event.getPlayer().getDisplayName() + " left the server**").queue();
        }
    }
}
