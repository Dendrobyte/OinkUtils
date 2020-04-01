package com.oinkcraft.oinkutils;

import com.oinkcraft.oinkutils.compassnav.SpawnNavInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.ChatColor.*;

public class BaseCommand implements CommandExecutor, TabCompleter {

    String prefix = Main.getInstance().getPrefix();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + "Sorry, this command is only meant for players!");
            return true;
        }
        Player player = (Player) sender;
        if(command.getName().equalsIgnoreCase("oinkutils")){
            player.sendMessage(prefix + LIGHT_PURPLE + BOLD +"OINK!");
            if(!player.hasPermission("oinkutils.admin")) return true;
            if(args.length == 0) return true;
            if(args[0].equalsIgnoreCase("reload")){
                Main.getInstance().reloadConfig();
                Main.getInstance().saveConfig();
                SpawnNavInventory.initializeInventory();
                player.sendMessage(prefix + "OinkUtils configuration reloaded.");
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> tabCompleteList = new ArrayList<>();
        if (strings.length == 1) {
            if (commandSender.hasPermission("oinkutils.admin")) {
                tabCompleteList.add("reload");
                tabCompleteList.removeIf(itm -> !itm.startsWith(strings[0]));
            }
        }
        return  tabCompleteList;
    }
}