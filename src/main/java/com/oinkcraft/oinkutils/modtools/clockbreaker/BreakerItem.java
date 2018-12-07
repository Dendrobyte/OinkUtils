package com.oinkcraft.oinkutils.modtools.clockbreaker;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

/**
 * Created by Mark on 6/12/2018.
 * Written for project ModTools
 * Please do not use or edit this code unless permissions has been given.
 * If you would like to use this code for modification and/or editing, do so with giving original credit.
 * Contact me on Twitter, @Mobkinz78
 * §
 */
public class BreakerItem {

    private ItemStack item;

    public BreakerItem(Material mat){
        this.item = new ItemStack(mat);
        setData(item);
    }

    public ItemStack getItem(){
        return item;
    }

    public void setData(ItemStack item){
        item.setAmount(1);
        ItemMeta breakerMeta = item.getItemMeta();
        breakerMeta.setDisplayName("§3§o§lClockBreaker");
        ArrayList<String> breakerLore = new ArrayList<>();
        breakerLore.add("This item is to be used by ONLY Moderators!");
        breakerLore.add("Please use to destroy ONLY clocks!");
        breakerMeta.setLore(breakerLore);
        item.setItemMeta(breakerMeta);
        return;
    }

}