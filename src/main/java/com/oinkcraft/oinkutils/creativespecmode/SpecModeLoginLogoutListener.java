package com.oinkcraft.oinkutils.creativespecmode;

import com.oinkcraft.oinkutils.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

/**
 * OinkUtils created/started by markb (Mobkinz78/Dendrobyte)
 * Please do not use or edit without permission!
 * If you have any questions, reach out to me on Twitter: @Mobkinz78
 * §
 */
public class SpecModeLoginLogoutListener implements Listener {

    String advBuilderString = Main.getInstance().getConfig().getString("advbuilder-world");

    @EventHandler
    public void onSpecModeLogin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        World world = player.getWorld();
        if(world.getName().toLowerCase().contains(advBuilderString)){
            if(!player.hasPermission("oinkutils.advbuilder.build")){
                BukkitTask quickTimer = new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage("" + ChatColor.BLUE + ChatColor.ITALIC + "You've logged into the Adv Builder World, so we set your gamemode to spectator.");
                    }
                }.runTaskLater(Main.getInstance(), 20L);
            }
        }
        else if (world.getName().toLowerCase().contains("redstone")){
            if(!player.hasPermission("oinkutils.redstone.build")) {
                BukkitTask quickTimer = new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage("" + ChatColor.BLUE + ChatColor.ITALIC + "You've logged into the redstone world, so we set your gamemode to spectator.");
                    }
                }.runTaskLater(Main.getInstance(), 20L);
            }
        }
    }

}
