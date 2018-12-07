package com.oinkcraft.oinkutils.modtools;

import com.oinkcraft.oinkutils.Main;
import com.oinkcraft.oinkutils.modtools.clockbreaker.BreakerItem;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Mark on 6/12/2018.
 * Written for project ModTools
 * Please do not use or edit this code unless permissions has been given.
 * If you would like to use this code for modification and/or editing, do so with giving original credit.
 * Contact me on Twitter, @Mobkinz78
 * §
 */
public class CommandBase implements CommandExecutor {

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
                player.sendMessage(prefix + "§2If you are a moderator... make sure you're in the creative world.");
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
                        player.sendMessage(prefix + "§cIt appears your inventory already contains a ClockBreaker!");
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
                player.sendMessage(prefix + "§2If you are a moderator... make sure you're in the creative world.");
                return true;
            }
        }
        return false;
    }

    private void sendArrayList(Player player){
        player.sendMessage("§8--------§3ModTools§8--------");
        player.sendMessage("§3Clock Breaker - §b/mtools clockbreaker");
        player.sendMessage("§3Sneeze (Clear Entities) - §b/sneeze <radius>");
        player.sendMessage("§8--------§3End of Help Menu§8--------");
        return;
    }

}