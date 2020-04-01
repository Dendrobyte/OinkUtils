package com.oinkcraft.oinkutils.voting;

import com.oinkcraft.oinkutils.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.ChatColor.DARK_GRAY;
import static org.bukkit.ChatColor.YELLOW;

/**
 * OinkUtils created/started by mobki (Mobkinz78/Dendrobyte)
 * Please do not use or edit without permission!
 * If you have any questions, reach out to me on Twitter: @Mobkinz78
 * §
 */
public class VoteCommand implements CommandExecutor, TabCompleter {

    String prefix = DARK_GRAY + "[" + YELLOW + "OinkVote" + DARK_GRAY +"] " + YELLOW;
    Main instance = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(prefix + "Vote command only usable by players!");
            return true;
        }
        Player player = (Player)sender;
        if(cmd.getName().equalsIgnoreCase("vote")) {
            if (args.length == 0) {
                // No args, send list
                player.sendMessage("§8§l--------§e§lOinkcraft Voting Links§8§l--------");
                // player.sendMessage("§6§oClick the links below to vote!");
                player.sendMessage("§6§l✧§e Planet Minecraft: §7" + instance.getConfig().getString("vote-links.planet-minecraft"));
                player.sendMessage("§6§l✧§e MC Server List: §7" + instance.getConfig().getString("vote-links.mc-server-list"));
                player.sendMessage("§6§l✧§e MC-Index: §7" + instance.getConfig().getString("vote-links.mc-index"));
                player.sendMessage("§6§l✧§e Minestatus: §7" + instance.getConfig().getString("vote-links.minestatus"));
                player.sendMessage("§c§l(!) §7Rewards are world dependent. Inventory space may be needed.");
                return true;
            } else {
                if (!player.hasPermission("oinkutils.admin")) {
                    player.sendMessage(prefix + "You must be an admin to reload the Votifier config.");
                    return true;
                }
                if (args[0].equalsIgnoreCase("reload")) {
                    Main.getInstance().reloadConfig();
                    Main.getInstance().saveConfig();
                    player.sendMessage(prefix + "Votifier successfully reloaded!");
                    return true;
                }
            }
            return true;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> tabCompleteList = new ArrayList<>();
        if (commandSender.hasPermission("oinkutils.admin") && strings.length == 1) {
            tabCompleteList.add("reload");
            tabCompleteList.removeIf(itm -> !itm.startsWith(strings[0]));
        }
        return tabCompleteList;
    }
}
