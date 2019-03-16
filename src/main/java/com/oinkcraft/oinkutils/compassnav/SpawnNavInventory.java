package com.oinkcraft.oinkutils.compassnav;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.bukkit.ChatColor.*;

import java.util.ArrayList;

/**
 * OinkUtils created/started by Mark Bacon (Mobkinz78 or ByteKangaroo) on 11/3/2018
 * Please do not use or edit without permission! (Being on GitHub counts as permission)
 * If you have any questions, reach out to me on Twitter! @Mobkinz78
 * §
 */
public class SpawnNavInventory {

    private SpawnNavInventory() {

    }

    public static Inventory getNavInventory() {
        Inventory hubInv = Bukkit.createInventory(null, 18,
                "" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Oinkcraft Nav " + ChatColor.GRAY + ChatColor.ITALIC + "- Click to warp!");
        ItemStack redstone = new ItemStack(Material.GRASS);
        ItemMeta redstoneMeta = redstone.getItemMeta();
        redstoneMeta.setDisplayName("" + AQUA + BOLD + "Creative Plots");
        ArrayList<String> redstoneLore = new ArrayList<>();
        redstoneLore.add("Teleport to the creative world!");
        redstoneLore.add("Plots are 101x101!");
        redstoneMeta.setLore(redstoneLore);
        redstone.setItemMeta(redstoneMeta);
        hubInv.setItem(0, redstone);
        redstone = new ItemStack(Material.STONE_AXE);
        redstoneMeta = redstone.getItemMeta();
        redstoneMeta.setDisplayName("" + GREEN + BOLD + STRIKETHROUGH + "Peaceful Survival/Towny");
        redstoneLore = new ArrayList<>();
        redstoneLore.add("Teleport to the towny world!");
        redstoneLore.add("Coming late February 2019");
        redstoneMeta.setLore(redstoneLore);
        redstone.setItemMeta(redstoneMeta);
        hubInv.setItem(2, redstone);
        redstone = new ItemStack(Material.GOLDEN_SWORD);
        redstoneMeta = redstone.getItemMeta();
        redstoneMeta.setDisplayName("" + GOLD + BOLD + "Four Civilizations");
        redstoneLore = new ArrayList<>();
        //redstoneLore.add("Teleport to the factions world!");
        redstoneLore.add("Join an ancient civilization to");
        redstoneLore.add("help rule the new world!");
        redstoneMeta.setLore(redstoneLore);
        redstone.setItemMeta(redstoneMeta);
        hubInv.setItem(4, redstone);
        redstone = new ItemStack(Material.RABBIT_FOOT);
        redstoneMeta = redstone.getItemMeta();
        redstoneMeta.setDisplayName("" + LIGHT_PURPLE + BOLD + "Parkour");
        redstoneLore = new ArrayList<>();
        redstoneLore.add("Teleport to the parkour world!");
        redstoneLore.add("All maps are made by players!");
        redstoneMeta.setLore(redstoneLore);
        redstone.setItemMeta(redstoneMeta);
        hubInv.setItem(6, redstone);
        redstone = new ItemStack(Material.POTION);
        redstoneMeta = redstone.getItemMeta();
        redstoneMeta.setDisplayName("" + DARK_GRAY + BOLD + "Minigames");
        redstoneLore = new ArrayList<>();
        redstoneLore.add("" + GREEN + BOLD + "Current Games");
        redstoneLore.add("" + AQUA + ITALIC + "Cauldron" + RED + BOLD + "Wars");
        redstoneLore.add("" + GOLD + BOLD + "Rocket" + DARK_AQUA + BOLD + "Bow");
        redstoneLore.add("" + DARK_GRAY + BOLD + "2 " + DARK_GRAY + BOLD + MAGIC + "Pu" + RESET + DARK_GRAY + BOLD +
                "z" + DARK_GRAY + BOLD + MAGIC + "zl" + RESET + DARK_GRAY + BOLD + "e " + RESET + GRAY + ITALIC + "(Soon™!)");
        redstoneMeta.setLore(redstoneLore);
        redstone.setItemMeta(redstoneMeta);
        hubInv.setItem(8, redstone);
        redstone = new ItemStack(Material.SPRUCE_LOG);
        redstoneMeta = redstone.getItemMeta();
        redstoneMeta.setDisplayName("" + DARK_PURPLE + BOLD + "Donation/VIP Plots");
        redstoneLore = new ArrayList<>();
        redstoneLore.add("Larger 200x200 plots!");
        redstoneLore.add("§5Available for donators only.");
        redstoneMeta.setLore(redstoneLore);
        redstone.setItemMeta(redstoneMeta);
        hubInv.setItem(11, redstone);
        redstone = new ItemStack(Material.MYCELIUM);
        redstoneMeta = redstone.getItemMeta();
        redstoneMeta.setDisplayName("" + DARK_AQUA + BOLD + "Advanced Builder World(s)");
        redstoneLore = new ArrayList<>();
        redstoneLore.add("Flatland and normal open worlds!");
        redstoneLore.add("" + DARK_AQUA + "Available only for Advanced Builders (not a donation rank)");
        redstoneMeta.setLore(redstoneLore);
        redstone.setItemMeta(redstoneMeta);
        hubInv.setItem(13, redstone);
        redstone = new ItemStack(Material.REDSTONE_BLOCK);
        redstoneMeta = redstone.getItemMeta();
        redstoneMeta.setDisplayName("" + DARK_RED + BOLD + "Redstone World");
        redstoneLore = new ArrayList<>();
        redstoneLore.add("Open flatland world for redstone!");
        redstoneLore.add(DARK_RED + "Available only to those of the Redstone Rank (not a donation rank)");
        redstoneMeta.setLore(redstoneLore);
        redstone.setItemMeta(redstoneMeta);
        hubInv.setItem(15, redstone);
        return hubInv;
    }
}
