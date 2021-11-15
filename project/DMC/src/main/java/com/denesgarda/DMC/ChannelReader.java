package com.denesgarda.DMC;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

public class ChannelReader extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getGuild().getId().equals(Main.GUILD)) {
            if (event.getChannel().getId().equals(Main.CHANNEL)) {
                if (!event.getAuthor().getId().equals(Main.JDA.getSelfUser().getId())) {
                    String text = "[" + event.getAuthor().getName() + " | " + event.getChannel().getName() + "]: " + event.getMessage().getContentRaw();
                    Bukkit.broadcastMessage(text);
                }
            }
        }
    }
}
