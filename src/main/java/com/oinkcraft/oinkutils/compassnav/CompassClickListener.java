package com.oinkcraft.oinkutils.compassnav;

import com.oinkcraft.oinkutils.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * OinkUtils created/started by Mark Bacon (Mobkinz78 or ByteKangaroo) on 11/3/2018
 * Please do not use or edit without permission! (Being on GitHub counts as permission)
 * If you have any questions, reach out to me on Twitter! @Mobkinz78
 * §
 */
public class CompassClickListener implements Listener {

    @EventHandler
    public void onPlayerRightClickCompass(PlayerInteractEvent event){
        String world = "world";
        Player player = event.getPlayer();
        if(event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return; // Do nothing if they're not right clicking
        if(!player.getWorld().getName().equalsIgnoreCase(world)) return; // Don't check anything if it isn't the spawn world
        ItemStack compass = Main.getInstance().navCompass();
        if(event.getItem() == null) return;
        if(!event.getItem().equals(compass)) return; // If it isn't the special compass, don't worry about it.

        // They've right clicked with the special nav compass. Now open an inventory of items.
        player.openInventory(SpawnNavInventory.getNavInventory());
    }

}
