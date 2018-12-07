package com.oinkcraft.oinkutils.compassnav;

import com.oinkcraft.oinkutils.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * OinkUtils created/started by Mark Bacon (Mobkinz78 or ByteKangaroo) on 11/3/2018
 * Please do not use or edit without permission! (Being on GitHub counts as permission)
 * If you have any questions, reach out to me on Twitter! @Mobkinz78
 * §
 */
public class InventoryClickListener implements Listener {

    private Main main = Main.getInstance();
    String prefix = main.getPrefix();

    public InventoryClickListener() {
    }

    @EventHandler
    public void onClickEvent(InventoryClickEvent event) {
        HumanEntity humanEntity = event.getWhoClicked();
        Player player = (Player)humanEntity;
        if (humanEntity != null && event.getInventory().getName().equalsIgnoreCase(SpawnNavInventory.getNavInventory().getName())) {
            event.setCancelled(true);
            ItemStack itemClicked = event.getCurrentItem();
            if (itemClicked != null) {
                if (itemClicked.getType() == Material.GRASS) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "warp creative " + player.getName());
                    player.sendMessage(prefix + "§2§oWarping to the creative world...");
                    return;
                }

                if (itemClicked.getType() == Material.STONE_AXE) {
                    // TODO: Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "warp towny " + player.getName());
                    // player.sendMessage(prefix + "§2§oWarping to the towny world...");
                    player.sendMessage(prefix + "This world is currently closed due to the 1.13.2 update. Sorry!");
                    return;
                }

                if (itemClicked.getType() == Material.DIAMOND_SWORD) {
                    // TODO: Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "warp factions " + player.getName());
                    // player.sendMessage(prefix + "§2§oWarping to the factions world...");
                    player.sendMessage(prefix + "This world is currently closed due to the 1.13.2 update. Sorry!");
                    return;
                }

                if(itemClicked.getType() == Material.GOLDEN_SWORD){
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "warp fourfac " + player.getName());
                    player.sendMessage(prefix + "§2§oWarping to the Four Factions world...");
                    return;
                }

                if (itemClicked.getType() == Material.RABBIT_FOOT) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "warp parkour " + player.getName());
                    player.sendMessage(prefix + "§2§oWarping to the parkour world...");
                    return;
                }

                if (itemClicked.getType() == Material.POTION) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "warp minigames " + player.getName());
                    player.sendMessage(prefix + "§7§lWarping to the minigames lobby...");
                    return;
                }

                if (itemClicked.getType() == Material.SPRUCE_LOG) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "warp donationplots " + player.getName());
                    player.sendMessage(prefix +"§2§oWarping to the donation plot world...");
                    return;
                }

                if (itemClicked.getType() == Material.MYCELIUM) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "warp advbuilder " + player.getName());
                    player.sendMessage(prefix + "§2§oWarping to the Advanced Builder world...");
                    return;
                }

                if (itemClicked.getType() == Material.REDSTONE_BLOCK) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "warp redstone " + player.getName());
                    player.sendMessage(prefix + "§2§oWarping to the Redstone flatland world...");
                    return;
                }
            }
        }

    }

}
