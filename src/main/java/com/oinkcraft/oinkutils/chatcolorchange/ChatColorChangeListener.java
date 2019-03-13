package com.oinkcraft.oinkutils.chatcolorchange;

import com.oinkcraft.oinkutils.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatColorChangeListener implements Listener {

    @EventHandler
    public void onAsyncColorChangeChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String originMessage = event.getMessage();
        FileConfiguration config = Main.getInstance().getConfig();
        if (config.isSet("colorchange-users." + player.getName())) {
            if (config.getStringList("colorchange-userlist").contains(player.getName())) {
                String color = config.getString("colorchange-users." + player.getName());
                byte var7 = -1;
                switch(color.hashCode()) {
                    case -1852648987:
                        if (color.equals("dark_aqua")) {
                            var7 = 3;
                        }
                        break;
                    case -1852623997:
                        if (color.equals("dark_blue")) {
                            var7 = 1;
                        }
                        break;
                    case -1852469876:
                        if (color.equals("dark_gray")) {
                            var7 = 8;
                        }
                        break;
                    case -1846156123:
                        if (color.equals("dark_purple")) {
                            var7 = 5;
                        }
                        break;
                    case -1591987974:
                        if (color.equals("dark_green")) {
                            var7 = 2;
                        }
                        break;
                    case -734239628:
                        if (color.equals("yellow")) {
                            var7 = 14;
                        }
                        break;
                    case 112785:
                        if (color.equals("red")) {
                            var7 = 12;
                        }
                        break;
                    case 3002044:
                        if (color.equals("aqua")) {
                            var7 = 11;
                        }
                        break;
                    case 3027034:
                        if (color.equals("blue")) {
                            var7 = 9;
                        }
                        break;
                    case 3178592:
                        if (color.equals("gold")) {
                            var7 = 6;
                        }
                        break;
                    case 3181155:
                        if (color.equals("gray")) {
                            var7 = 7;
                        }
                        break;
                    case 3441014:
                        if (color.equals("pink")) {
                            var7 = 13;
                        }
                        break;
                    case 93818879:
                        if (color.equals("black")) {
                            var7 = 0;
                        }
                        break;
                    case 98619139:
                        if (color.equals("green")) {
                            var7 = 10;
                        }
                        break;
                    case 113101865:
                        if (color.equals("white")) {
                            var7 = 15;
                        }
                        break;
                    case 1741368392:
                        if (color.equals("dark_red")) {
                            var7 = 4;
                        }
                }

                switch(var7) {
                    case 0:
                        event.setMessage(ChatColor.getByChar('0') + originMessage);
                        break;
                    case 1:
                        event.setMessage(ChatColor.getByChar('1') + originMessage);
                        break;
                    case 2:
                        event.setMessage(ChatColor.getByChar('2') + originMessage);
                        break;
                    case 3:
                        event.setMessage(ChatColor.getByChar('3') + originMessage);
                        break;
                    case 4:
                        event.setMessage(ChatColor.getByChar('4') + originMessage);
                        break;
                    case 5:
                        event.setMessage(ChatColor.getByChar('5') + originMessage);
                        break;
                    case 6:
                        event.setMessage(ChatColor.getByChar('6') + originMessage);
                        break;
                    case 7:
                        event.setMessage(ChatColor.getByChar('7') + originMessage);
                        break;
                    case 8:
                        event.setMessage(ChatColor.getByChar('8') + originMessage);
                        break;
                    case 9:
                        event.setMessage(ChatColor.getByChar('9') + originMessage);
                        break;
                    case 10:
                        event.setMessage(ChatColor.getByChar('a') + originMessage);
                        break;
                    case 11:
                        event.setMessage(ChatColor.getByChar('b') + originMessage);
                        break;
                    case 12:
                        event.setMessage(ChatColor.getByChar('c') + originMessage);
                        break;
                    case 13:
                        event.setMessage(ChatColor.getByChar('d') + originMessage);
                        break;
                    case 14:
                        event.setMessage(ChatColor.getByChar('e') + originMessage);
                        break;
                    case 15:
                        event.setMessage(ChatColor.getByChar('f') + originMessage);
                }

            }
        }
    }
}
