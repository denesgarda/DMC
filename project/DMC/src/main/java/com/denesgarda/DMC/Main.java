package com.denesgarda.DMC;

import com.denesgarda.DMC.properties.PropertiesFile;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.io.*;

public class Main extends JavaPlugin implements Listener {
    public static Config CONFIG = null;
    public static JDA JDA;

    @Override
    public void onEnable() {
        getLogger().info("initializing DMC");
        this.getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Loading config");
        try {
            CONFIG = Config.returnObject("plugins" + File.separator + "DMC" + File.separator + "config.properties");
            CONFIG.reload();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getLogger().info("Connecting to Discord Client");
        try {
            JDA = JDABuilder.createDefault(CONFIG.TOKEN).addEventListeners().build();
            JDA.addEventListener(new ChannelReader());
            if (CONFIG.ONLINE) {
                JDA.getPresence().setPresence(Activity.watching("0 Players Play"), true);
            }
        } catch (IllegalArgumentException | LoginException e) {
            getLogger().info("DMC is not configured; stop the server to configure");
        }
    }

    @EventHandler
    public void chatReader(PlayerChatEvent event) {
        String text = "[" + event.getPlayer().getDisplayName() + "]: " + event.getMessage();
        JDA.getTextChannelCache().getElementById(CONFIG.CHANNEL).sendMessage(text).queue();
    }

    @EventHandler
    public void joins(PlayerJoinEvent event) {
        if (CONFIG.ONLINE) {
            int players = this.getServer().getOnlinePlayers().size();
            if (players == 1) {
                JDA.getPresence().setPresence(Activity.watching(players + " Player Play"), true);
            } else {
                JDA.getPresence().setPresence(Activity.watching(players + " Players Play"), true);
            }
        }
        if (CONFIG.JOIN_LEAVE) {
            JDA.getTextChannelCache().getElementById(CONFIG.CHANNEL).sendMessage("**" + event.getPlayer().getDisplayName() + " joined the server**").queue();
        }
    }

    @EventHandler
    public void leaves(PlayerQuitEvent event) {
        if (CONFIG.ONLINE) {
            int players = this.getServer().getOnlinePlayers().size() - 1;
            if (players == 1) {
                JDA.getPresence().setPresence(Activity.watching(players + " Player Play"), true);
            } else {
                JDA.getPresence().setPresence(Activity.watching(players + " Players Play"), true);
            }
        }
        if (CONFIG.JOIN_LEAVE) {
            JDA.getTextChannelCache().getElementById(CONFIG.CHANNEL).sendMessage("**" + event.getPlayer().getDisplayName() + " left the server**").queue();
        }
    }

    @EventHandler
    public void advancementHandler(PlayerAdvancementDoneEvent event) {
        if (CONFIG.ADVANCEMENTS) {
            JDA.getTextChannelCache().getElementById(CONFIG.CHANNEL).sendMessage("**" + event.getPlayer().getDisplayName() + " made the advancement " + event.getEventName() + "**").queue();
        }
    }

    @EventHandler
    public void deathHandler(PlayerDeathEvent event) {
        if (CONFIG.DEATHS) {
            JDA.getTextChannelCache().getElementById(CONFIG.CHANNEL).sendMessage("**" + event.getDeathMessage() + "**").queue();
        }
    }
}
