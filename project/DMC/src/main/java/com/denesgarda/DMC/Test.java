package com.denesgarda.DMC;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Test {
    public static String TOKEN = null;
    public static String CHANNEL = null;
    public static String GUILD = null;

    public static void main(String[] args) {
        System.out.println("initializing DMC");
        System.out.println("Loading config");
        File DMCDir = new File("DMC");
        if(!DMCDir.exists()) {
            DMCDir.mkdir();
        }
        File token = new File("DMC/token.txt");
        if (!token.exists()) {
            System.out.println("Could not find token config file; generating new one");
            try {
                token.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("After startup, configure new files");
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("DMC/token.txt"));
            TOKEN = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File channel = new File("DMC/channel.txt");
        if (!channel.exists()) {
            System.out.println("Could not find channels config file; generating new one");
            try {
                channel.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("After startup, configure new files");
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("DMC/channel.txt"));
            CHANNEL = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File guild  = new File("DMC/guild.txt");
        if (!guild.exists()) {
            System.out.println("Could not find channels config file; generating new one");
            try {
                guild.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("After startup, configure new files");
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("DMC/guild.txt"));
            GUILD = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Connecting to Discord Client");
        try {
            JDA jda = JDABuilder.createDefault(TOKEN).addEventListeners().build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
