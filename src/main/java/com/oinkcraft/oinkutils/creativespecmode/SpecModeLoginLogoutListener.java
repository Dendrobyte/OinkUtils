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
                player.sendMessage("" + ChatColor.BLUE + ChatColor.ITALIC + "Teleported to spawn after logging into Advanced Builder world to avoid spectator abuse.");
            }
        }
        else if (world.getName().toLowerCase().contains("redstone")){
            if(!player.hasPermission("oinkutils.redstone.build")) {
                player.sendMessage("" + ChatColor.BLUE + ChatColor.ITALIC + "Teleported to spawn after logging into Redstone world to avoid spectator abuse.");
            }
        }
    }

}
