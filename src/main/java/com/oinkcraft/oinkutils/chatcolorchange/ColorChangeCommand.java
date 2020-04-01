package com.oinkcraft.oinkutils.chatcolorchange;

import java.util.ArrayList;
import java.util.List;
import com.oinkcraft.oinkutils.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

public class ColorChangeCommand implements CommandExecutor, TabCompleter {
    String prefix = Main.getInstance().getPrefix();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("chatcolorchange")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(this.prefix + "This command can only be used by players!");
                return false;
            }

            Player player = (Player)sender;
            ArrayList<String> validColors = new ArrayList<>();
            FileConfiguration config = Main.getInstance().getConfig();
            validColors = this.initiateValidColors();
            if (!player.hasPermission("oinkutils.colorchange")) {
                player.sendMessage(this.prefix + "Sorry, no access! "+ RED +"Required permission: oinkutils.colorchange");
                return false;
            }

            if (args.length == 0) {
                player.sendMessage(this.prefix + "Please provide a color for your chat.");
                player.sendMessage(this.prefix + "Valid colors: " + validColors.toString());
                return false;
            }

            if (args.length > 1) {
                player.sendMessage(this.prefix + "Only one color allowed! [Text font unavailable]");
                player.sendMessage(this.prefix + "Valid colors: " + validColors.toString());
                return false;
            }

            if (args.length == 1) {
                if (!validColors.contains(args[0].toLowerCase())) {
                    player.sendMessage(this.prefix + "Sorry, color not recognized.");
                    player.sendMessage(this.prefix + "Valid colors: " + validColors.toString());
                    return false;
                }

                List<String> colorchangeUserlist = config.getStringList("colorchange-userlist");
                if (!colorchangeUserlist.contains(player.getName())) {
                    colorchangeUserlist.add(player.getName());
                    config.set("colorchange-userlist", colorchangeUserlist);
                }

                if (!config.isSet("colorchange-users." + player.getName())) {
                    config.createSection("colorchange-users." + player.getName());
                }

                if (args[0].toLowerCase().equalsIgnoreCase("reset")) {
                    if (colorchangeUserlist.contains(player.getName())) {
                        colorchangeUserlist.remove(player.getName());
                        config.set("colorchange-userlist", colorchangeUserlist);
                    }

                    config.set("colorchange-users." + player.getName(), (Object)null);
                    player.sendMessage(this.prefix + "Chat color has been reset!");
                    Main.getInstance().saveConfig();
                    Main.getInstance().reloadConfig();
                    return true;
                }

                config.set("colorchange-users." + player.getName(), args[0].toLowerCase());
                player.sendMessage(this.prefix + GREEN +"Chat color successfully set to " + DARK_BLUE + args[0]);
                Main.getInstance().saveConfig();
                Main.getInstance().reloadConfig();
                return true;
            }
        }

        return true;
    }

    private ArrayList<String> initiateValidColors() {
        ArrayList<String> validList = new ArrayList();
        validList.add("reset");
        validList.add("black");
        validList.add("dark_blue");
        validList.add("dark_green");
        validList.add("dark_aqua");
        validList.add("dark_red");
        validList.add("dark_purple");
        validList.add("gold");
        validList.add("gray");
        validList.add("dark_gray");
        validList.add("blue");
        validList.add("green");
        validList.add("aqua");
        validList.add("red");
        validList.add("pink");
        validList.add("yellow");
        validList.add("white");
        return validList;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> tabCompleteList = new ArrayList<>();
        if (commandSender.hasPermission("oinkutils.colorchange") && strings.length == 1) {
            tabCompleteList.addAll(this.initiateValidColors());
            tabCompleteList.removeIf(itm -> !itm.startsWith(strings[0]));
        }
        return tabCompleteList;
    }
}
