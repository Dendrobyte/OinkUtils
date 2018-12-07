package com.oinkcraft.oinkutils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BaseCommand implements CommandExecutor {

    String prefix = Main.getInstance().getPrefix();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + "Sorry, this command is only meant for players!");
            return true;
        }
        Player player = (Player) sender;
        if(command.getName().equals("oinkutils")){
            player.sendMessage(prefix + "§d§lOINK!");
        }
        return true;
    }

}