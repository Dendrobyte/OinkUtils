package com.oinkcraft.oinkutils.voting;

import com.oinkcraft.oinkutils.Main;
import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static org.bukkit.ChatColor.*;

/**
 * OinkUtils created/started by mobki (Mobkinz78/Dendrobyte)
 * Please do not use or edit without permission!
 * If you have any questions, reach out to me on Twitter: @Mobkinz78
 * §
 */
public class VoteListener implements Listener {

    String prefix = DARK_GRAY + "[" + YELLOW + "OinkVote" + DARK_GRAY +"] " + YELLOW;

    @EventHandler
    public void onPlayerVote(VotifierEvent event){

        Vote vote = event.getVote();
        Player player = Bukkit.getPlayer(vote.getUsername());
        Bukkit.getServer().broadcastMessage(prefix + GREEN + vote.getUsername() + YELLOW + " just voted on " + GREEN + vote.getServiceName() + "!");
        assert player != null;
        player.sendMessage(prefix + "Thank you for voting for Redstone Oinkcraft!");

        ConfigurationSection rewards = Main.getInstance().getConfig().getConfigurationSection("vote-rewards");
        ConfigurationSection playerSection = null;
        String playerWorldName = player.getWorld().getName();
        for(String worldName : rewards.getKeys(false)){
            if(worldName.equalsIgnoreCase(playerWorldName)){
                playerSection = Main.getInstance().getConfig().getConfigurationSection("vote-rewards." + worldName.toLowerCase());
                break;
            }
        }

        // If the world isn't configured, let 'em know
        if(playerSection == null){
            player.sendMessage(prefix + "This world doesn't have votes configured, but thank you for voting!");
            return;
        }

        // Otherwise, give items and execute commands based on rewards
        List<String> itemRewards = playerSection.getStringList("items"); // TODO: Check to see if this works
        List<String> cmdRewards = playerSection.getStringList("commands");

        for(String item : itemRewards){
            String itemMaterial = item.substring(0, item.indexOf(","));
            int itemAmount = Integer.parseInt(item.substring(item.indexOf(",")+1));
            ItemStack is = new ItemStack(Material.valueOf(itemMaterial), itemAmount);
            player.getInventory().addItem(is);
        }
        for(String cmd : cmdRewards){
            if(cmd.contains("%")){
                String modifiedCmd = cmd.substring(0, cmd.indexOf("%")) + player.getName() + cmd.substring(cmd.indexOf("&")+1);
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), modifiedCmd);
                return;
            } else {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd);
            }
        }

    }
}
