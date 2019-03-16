package com.oinkcraft.oinkutils.modtools.clockbreaker;

import com.oinkcraft.oinkutils.Main;
import com.oinkcraft.oinkutils.modtools.ModToolsManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import static org.bukkit.ChatColor.*;
/**
 * Created by Mark on 6/12/2018.
 * Written for project ModTools
 * Please do not use or edit this code unless permissions has been given.
 * If you would like to use this code for modification and/or editing, do so with giving original credit.
 * Contact me on Twitter, @Mobkinz78
 * §
 */
public class ClockBreakListener implements Listener {

    String prefix = ModToolsManager.getInstance().getPrefix();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        BreakerItem clockBreaker = new BreakerItem(Material.STICK);
        if(!itemInHand.equals(clockBreaker.getItem())){
            return;
        }
        else if(itemInHand.equals(clockBreaker.getItem())){
            // TODO: Check to make sure they are in the creative world (Add a config value!)
            // TODO: Redstone block check
            if(!player.hasPermission("modtools.use")){
                player.sendMessage(prefix + DARK_RED + ",You can't use that tool!");
                player.getInventory().getItemInMainHand().setAmount(0);
                return;
            }
            // Careful! This bypasses other event checks!
            event.setCancelled(false);
            player.sendMessage(prefix + "Clockpart cleared!");

            // Block limit. HashMap in the Main class for static reasons.
            if(!Main.getClockBreakerClicks().containsKey(player)){
                Main.getClockBreakerClicks().put(player, 0);
            }
            int currentClicks = Main.getClockBreakerClicks().get(player);
            currentClicks++;
            Main.getClockBreakerClicks().replace(player, currentClicks);
            if(currentClicks > 3){
                player.getInventory().remove(clockBreaker.getItem());
                Main.getClockBreakerClicks().remove(player);
                player.sendMessage(prefix + "You have hit your 4 block limit. The ClockBreaker has been removed from your inventory!");
            }

            return;
        }

    }

}