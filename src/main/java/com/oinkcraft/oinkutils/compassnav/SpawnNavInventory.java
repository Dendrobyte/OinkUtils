package com.oinkcraft.oinkutils.compassnav;

import com.oinkcraft.oinkutils.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.bukkit.ChatColor.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * OinkUtils created/started by Mark Bacon (Mobkinz78 or ByteKangaroo) on 11/3/2018
 * Please do not use or edit without permission! (Being on GitHub counts as permission)
 * If you have any questions, reach out to me on Twitter! @Mobkinz78
 * §
 */
public class SpawnNavInventory {

    private SpawnNavInventory() {

    }

    private static Inventory hubInventory;
    private static String invName = "" + LIGHT_PURPLE + ChatColor.BOLD + "Oinkcraft Nav" + ChatColor.DARK_GRAY + ChatColor.ITALIC + " - Click to warp!";

    public static void initializeInventory(){
        // Initialize the inventory object
        hubInventory = Bukkit.createInventory(null, 18, invName);

        // Add items from configuration
        ConfigurationSection items = Main.getInstance().getConfig().getConfigurationSection("nav-items");
        try {
            for (String configItem : items.getKeys(false)) {
                ItemStack item = new ItemStack(Material.valueOf(items.getString(configItem + ".item-material")));
                ItemMeta itemMeta = item.getItemMeta();
                assert itemMeta != null;
                itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', items.getString(configItem + ".item-name")));

                List<String> descriptionLines = items.getStringList(configItem + ".item-description");
                ArrayList<String> newLore = new ArrayList<>(2);
                for(String line : descriptionLines){
                    newLore.add(ChatColor.translateAlternateColorCodes('&', line));
                }
                newLore.add(ChatColor.GRAY + "Destination: " + items.getString(configItem + ".destination-world-name"));
                itemMeta.setLore(newLore);

                item.setItemMeta(itemMeta);

                int invSlot = items.getInt(configItem + ".slot-number");
                hubInventory.setItem(invSlot, item);
            }
            for(int i = 0; i < hubInventory.getSize(); i++){
                if(hubInventory.getItem(i) == null){
                    hubInventory.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
                }
            }
            Bukkit.getLogger().log(Level.INFO, Main.getInstance().getPrefix() + "Navigation inventory loaded!");
        } catch (NullPointerException e){
            Bukkit.getLogger().log(Level.WARNING, "No navigation items to load!");
        }
    }

    public static Inventory getNavInventory() {
        return hubInventory;
    }

    public static String getInvName(){
        return invName;
    }
}
