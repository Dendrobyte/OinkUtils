package com.oinkcraft.oinkutils.modtools;

import com.oinkcraft.oinkutils.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * OinkUtils created/started by markb (Mobkinz78/Dendrobyte)
 * Please do not use or edit without permission!
 * If you have any questions, reach out to me on Twitter: @Mobkinz78
 * §
 */
public class PreventVisitorMobPlacementListener implements Listener {

    @EventHandler
    public void onPlayerPlaceSpawnegg(PlayerInteractEvent event){
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)){
            Player player = event.getPlayer();
            ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
            ItemStack itemInOffHand = player.getInventory().getItemInOffHand();
            if(itemInMainHand.getType().toString().contains("SPAWN_EGG") || itemInOffHand.getType().toString().contains("SPAWN_EGG")) {
                if(!player.hasPermission("oinkutils.mobspawn")) {
                    player.sendMessage(Main.getInstance().getPrefix() + ChatColor.RED + "You need to be a [Member] or above to do that!");
                    event.setCancelled(true);
                }
            }
        }
    }

}
