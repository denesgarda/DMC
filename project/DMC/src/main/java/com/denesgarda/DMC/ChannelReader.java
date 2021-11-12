package com.denesgarda.DMC;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

public class ChannelReader implements EventListener {
    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if(event instanceof MessageReceivedEvent) {
            System.out.println("Message Received:" + ((MessageReceivedEvent) event).getMessage().getContentRaw());
        }
    }
}
