package com.denesgarda.DMC;

import com.denesgarda.DMC.properties.PropertiesFile;

import java.io.IOException;

public class Config extends PropertiesFile {
    public String TOKEN = null;
    public String CHANNEL = null;
    public String GUILD = null;
    public boolean ONLINE;
    public boolean JOIN_LEAVE;
    public boolean ADVANCEMENTS;
    public boolean DEATHS;

    public Config(String path) {
        super(path);
    }

    public void reload() throws IOException {
        TOKEN = this.getPropertyNotNull("bot-token", "");
        CHANNEL = this.getPropertyNotNull("channel-id", "");
        GUILD = this.getPropertyNotNull("guild-id", "");
        ONLINE = Boolean.parseBoolean(this.getPropertyNotNull("display-online-players", "true"));
        JOIN_LEAVE = Boolean.parseBoolean(this.getPropertyNotNull("send-leave-and-join-messages", "true"));
        ADVANCEMENTS = Boolean.parseBoolean(this.getPropertyNotNull("send-advancement-messages", "true"));
        DEATHS = Boolean.parseBoolean(this.getPropertyNotNull("send-death-messages", "true"));
    }
}
