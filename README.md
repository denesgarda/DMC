# DMC
Discord Minecraft Chat Integration
DMC allows you to sync a Minecraft server chat with a Discord text channel.

## Tutorial

1. Download the latest version [here](https://github.com/DenDen747/DMC/raw/main/builds/DMC_1.0.jar).
2. Put it in the ``./plugins`` folder in your spigot server.
3. Launch the server. The first time it launches, it will probably produce an error. This is normal.
4. Stop the server after it fully starts up.
5. Go to the [Discord Developer Portal](https://discord.com/developers/applications).
6. Click "New Application" in the top right.
7. Give it a name. The recommended name is "DMC".
8. Optional: Upload an icon
9. On the left, go to the "Bot" tab.
10. Click "Add Bot" and confirm the action.
11. Scroll down and, under "Bot Permissions", check "Administrator".
12. On the left, go to the "OAuth2" tab.
13. Under "Scopes", check "Bot".
14. Scroll down and, under "Bot Permissions", check "Administrator".
15. Copy the link, then open it in a new tab.
16. Invite the bot to your discord server.
17. On the left, go to the "Bot" tab.
18. Copy the token.
19. Go to the directory ``./plugins/DMC``.
20. Paste the token into the file ``token.txt``.
21. Go to your discord server the bot is in.
22. Make sure developer mode is enabled on discord. If it's not, go to user settings, advanced, and turn developer mode on.
23. Right click on the discord server icon and click "Copy ID".
24. Go to the directory ``./plugins/DMC``.
25. Paste the server ID into the file ``guild.txt``.
23. Right click on the text channel you want to sync and click "Copy ID".
24. Go to the directory ``./plugins/DMC``.
25. Paste the channel ID into the file ``channel.txt``.
26. Launch the server and you're set!
