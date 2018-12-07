package com.oinkcraft.oinkutils.compassnav;

import com.oinkcraft.oinkutils.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * OinkUtils created/started by Mark Bacon (Mobkinz78 or ByteKangaroo) on 11/3/2018
 * Please do not use or edit without permission! (Being on GitHub counts as permission)
 * If you have any questions, reach out to me on Twitter! @Mobkinz78
 * §
 */
public class PlayerGiveCompassListener implements Listener {

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        String world = Main.getInstance().getConfig().getString("spawn-world");
        if (!player.getWorld().getName().equalsIgnoreCase(world)) return; // Do nothing if it isn't the spawn world

        // Clear inventory
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        // Give compass
        player.getInventory().setItem(0, Main.getInstance().navCompass());

    }

    @EventHandler
    public void joinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.getWorld().getName().equals("world")) {
            // Clear inventory
            player.getInventory().clear();
            player.getInventory().setArmorContents(null);

            // Give compass
            player.getInventory().setItem(0, Main.getInstance().navCompass());
        }

    }

}
