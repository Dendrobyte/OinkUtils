package com.oinkcraft.oinkutils.modtools.clockbreaker;

import com.oinkcraft.oinkutils.Main;
import com.oinkcraft.oinkutils.modtools.ModToolsManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Mark on 6/13/2018.
 * Written for project ModTools
 * Please do not use or edit this code unless permissions has been given.
 * If you would like to use this code for modification and/or editing, do so with giving original credit.
 * Contact me on Twitter, @Mobkinz78
 * §
 */
public class ClockBreakInventoryMoveListener implements Listener {

    String prefix = ModToolsManager.getInstance().getPrefix();

    @EventHandler
    public void onPlayerMoveClockBreaker(InventoryClickEvent event){
        if(event.getCurrentItem() == null) {
            return;
        }
        BreakerItem breakerItem = new BreakerItem(Material.STICK);
        ItemStack item = event.getCurrentItem();
        if (item.isSimilar(breakerItem.getItem())) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            player.closeInventory();
            player.sendMessage(prefix + "§cPlease don't move and/or store the ClockBreaker.");
            player.sendMessage(prefix + "§c(It has been removed)");
            event.getCurrentItem().setAmount(0);
        }
    }

}