package com.oinkcraft.oinkutils.modtools;

import com.oinkcraft.oinkutils.Main;
import com.oinkcraft.oinkutils.modtools.clockbreaker.BreakerItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Mark on 6/12/2018.
 * Written for project ModTools
 * Please do not use or edit this code unless permissions has been given.
 * If you would like to use this code for modification and/or editing, do so with giving original credit.
 * Contact me on Twitter, @Mobkinz78
 * §
 */
public class CommandBase implements CommandExecutor, TabCompleter {

    String prefix = ModToolsManager.getInstance().getPrefix();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage(prefix + "ModTools only accessible for players!");
            return true;
        }
        Player player = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("modtools")){
            if(!player.hasPermission("modtools.use")){
                player.sendMessage(prefix + "You must be a Moderator to use ModTools!");
                player.sendMessage(prefix + DARK_GREEN + "If you are a moderator... make sure you're in the creative world.");
                return true;
            } else {
                if(args.length == 0){
                    player.sendMessage(prefix + "Please provide an argument!");
                    player.sendMessage(prefix + "Try /modtools help");
                    return true;
                }
                if(args[0].equalsIgnoreCase("help")){
                    sendArrayList(player);
                    return true;
                }
                if(args[0].equalsIgnoreCase("clockbreaker")){
                    BreakerItem clockBreaker = new BreakerItem(Material.STICK);
                    if(player.getInventory().contains(clockBreaker.getItem())){
                        player.sendMessage(prefix + RED + "It appears your inventory already contains a ClockBreaker!");
                        return true;
                    }
                    player.getInventory().setItem(0, clockBreaker.getItem());
                    player.sendMessage(prefix + "You have received a legendary Clock Breaker!");
                    player.sendMessage(prefix + "You have a 4 block limit before the item is removed from your inventory.");
                    return true;
                }
                else {
                    player.sendMessage("It appears your argument wasn't recognized.");
                    player.sendMessage(prefix + "Try /modtools help");
                }
            }
        }
        if(cmd.getName().equalsIgnoreCase("sneeze")){
            if(!player.hasPermission("modtools.use")){
                player.sendMessage(prefix + "You must be a Moderator to use ModTools!");
                player.sendMessage(prefix + DARK_GREEN +"If you are a moderator... make sure you're in the creative world.");
                return true;
            } else {
                if (args.length == 0) {
                    player.sendMessage(prefix + "Please provide a radius!");
                    player.sendMessage(prefix + "Try /modtools help");
                    return true;
                }
                String radiusStr = args[0];
                try {
                    int radius = Integer.parseInt(radiusStr);
                    if(radius > 80){
                        player.sendMessage(prefix + "A radius greater than 80 is too much for this world.");
                        return true;
                    }
                    List<Entity> entities = player.getNearbyEntities(radius, radius, radius);
                    int clearedEnts = ModToolsManager.getInstance().sneezeAwayEntities(entities);
                    player.sendMessage(prefix + ChatColor.BOLD + "ACHOOOOOOOO!");
                    if(clearedEnts == 0){
                        player.sendMessage(prefix + "Looks like you didn't blow away any entities...");
                        return true;
                    }
                    else if(clearedEnts > 800){
                        player.sendMessage(prefix + "My my... that was a VERY impressive sneeze! Cleared " + clearedEnts + " entities.");
                    } else {
                        player.sendMessage(prefix + "À tes souhaits! Cleared " + clearedEnts + " entities.");
                    }
                    return true;

                } catch(NumberFormatException e){
                    player.sendMessage(prefix + "Please enter a valid whole number.");
                    return true;
                }
            }
        }
        if(cmd.getName().equalsIgnoreCase("townyban")){
            if(!player.hasPermission("modtools.use")){
                player.sendMessage(prefix + "You must be a Moderator to use ModTools!");
                return true;
            } else {
                if(args.length != 2){
                    player.sendMessage(prefix + ChatColor.RED + "Improper usage! " + AQUA + "/townyban <name> <temp/perm>");
                }
                String username = args[0];
                if(args[1].equalsIgnoreCase("temp")){
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + username + " permission settemp multiverse.access.oink_towny false 1w");
                    player.sendMessage(prefix + "User " + username + " has been banned from the towny world for 1 week.");
                    return true;
                }
                else if(args[1].equalsIgnoreCase("perm")){
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + username + " permission settemp multiverse.access.oink_towny false");
                    player.sendMessage(prefix + "User " + username + " has been banned from the towny world PERMANENTLY.");
                    return true;
                } else {
                    player.sendMessage(prefix + ChatColor.RED + "Improper argument! " + AQUA + "Use either \'temp\' or \'perm\'");
                    return true;
                }
            }
        }

        if(cmd.getName().equalsIgnoreCase("rankup")){
            if(!player.hasPermission("modtools.use")){
                player.sendMessage(prefix + "You must be a Moderator to use ModTools!");
                return true;
            }
            if(args.length <= 1){
                player.sendMessage(prefix + "Please input an online user's username to rank them to Member.");
                return true;
            } else {
                String playerName = args[0];
                Player playerToRank = Bukkit.getServer().getPlayer(playerName);
                if(playerToRank == null){
                    player.sendMessage(prefix + "That player could not be found! You entered: " + ChatColor.RED + ITALIC + playerName);
                    return true;
                } else {
                    if(playerToRank.hasPermission("group.member")){
                        player.sendMessage(prefix + "That play is already a Member (or above)!");
                        return true;
                    } else {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + playerToRank + " parent set Member");
                        playerToRank.sendMessage("" + GOLD + BOLD + "CONGRATULATIONS!" + GRAY + ITALIC + " You've just been ranked to Member.");
                        player.sendMessage(prefix + playerToRank + " has successfully been made a Member!");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void sendArrayList(Player player){
        player.sendMessage("§8--------§3ModTools§8--------");
        player.sendMessage("§3Clock Breaker - §b/mtools clockbreaker");
        player.sendMessage("§3Sneeze (Clear Entities) - §b/sneeze <radius>");
        player.sendMessage("§3Towny Ban - §b/townyban <name> <temp/perm>");
        player.sendMessage("§3Member Rankup - §b/rankup <name>");
        player.sendMessage("§8--------§3End of Help Menu§8--------");
        return;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> tabCompleteList = new ArrayList<>();
        if (cmd.getName().equalsIgnoreCase("modtools") && sender.hasPermission("modtools.use")) {
            if (args.length != 1) return null;
            tabCompleteList.add("help");
            tabCompleteList.add("clockbreaker");
            tabCompleteList.removeIf(itm -> !itm.startsWith(args[0]));
        } else if (cmd.getName().equalsIgnoreCase("sneeze") && sender.hasPermission("modtools.use")) {
            if (args.length != 1) return null;
            tabCompleteList.add("5");
            tabCompleteList.add("10");
            tabCompleteList.add("20");
            tabCompleteList.add("40");
            tabCompleteList.add("80");
            tabCompleteList.removeIf(itm -> !itm.startsWith(args[0]));
        }
        return tabCompleteList;
    }
}