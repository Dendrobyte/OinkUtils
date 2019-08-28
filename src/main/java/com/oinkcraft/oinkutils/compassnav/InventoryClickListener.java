package com.oinkcraft.oinkutils.compassnav;

import com.oinkcraft.oinkutils.Main;
import org.bukkit.*;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.ChatColor.*;

/**
 * OinkUtils created/started by Mark Bacon (Mobkinz78 or ByteKangaroo) on 11/3/2018
 * Please do not use or edit without permission! (Being on GitHub counts as permission)
 * If you have any questions, reach out to me on Twitter! @Mobkinz78
 * §
 */
public class InventoryClickListener implements Listener {

    private Main main = Main.getInstance();
    String prefix = main.getPrefix();

    @EventHandler
    public void onClickEvent(InventoryClickEvent event) {
        HumanEntity humanEntity = event.getWhoClicked();
        Player player = (Player) humanEntity;
        if (humanEntity != null && event.getView().getTitle().equalsIgnoreCase(SpawnNavInventory.getInvName())) {
            event.setCancelled(true);
            ItemStack itemClicked = event.getCurrentItem();
            event.setCancelled(true);
            if(itemClicked.getType() == Material.GRAY_STAINED_GLASS_PANE) return;
            // Destination - WORLDNAME
            String destinationLore = ChatColor.stripColor(itemClicked.getItemMeta().getLore().get(itemClicked.getItemMeta().getLore().size()-1));
            String worldDestination = destinationLore.substring(13);
            try {
                World worldToSendPlayer = Bukkit.getWorld(worldDestination);
                player.teleport(worldToSendPlayer.getSpawnLocation());
                player.sendMessage(prefix + GRAY + "Warping to " + DARK_PURPLE + BOLD + worldDestination + "...");
                player.playSound(player.getLocation(), Sound.ITEM_CHORUS_FRUIT_TELEPORT, 8, 1);
            } catch (NullPointerException e){
                player.sendMessage(prefix + ChatColor.RED + "It appears something went wrong! Contact an staff member.");
            }
        }

    }
}