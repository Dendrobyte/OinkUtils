package com.oinkcraft.oinkutils.creativespecmode;

import com.oinkcraft.oinkutils.Main;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

/**
 * OinkUtils created/started by markb (Mobkinz78/Dendrobyte)
 * Please do not use or edit without permission!
 * If you have any questions, reach out to me on Twitter: @Mobkinz78
 * §
 */
public class SpecModeTeleportListener implements Listener {

    String advBuilderString = Main.getInstance().getConfig().getString("advbuilder-world");

    @EventHandler
    public void onAdvBuilderWorldPlayerTeleport(PlayerChangedWorldEvent event){
        Player player = event.getPlayer();
        World to = player.getWorld();
        World from = event.getFrom();
        // We only check the world they are arriving in because PerWorldInv / Multiverse should manage them leaving
        if(to.getName().toLowerCase().contains(advBuilderString)){
            if(!player.hasPermission("oinkutils.advbuilder.build")){
                player.sendMessage("" + ChatColor.BLUE + ChatColor.ITALIC + "Welcome to the Advanced Builder world. Enjoy the builds!");
                BukkitTask quickTimer = new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.setGameMode(GameMode.SPECTATOR);
                    }
                }.runTaskLater(Main.getInstance(), 20L);
            }
        }

        if(from.getName().toLowerCase().contains(advBuilderString)){
            String spawnWorldName = Main.getInstance().getConfig().getString("spawn-world");
            if(!to.getName().equalsIgnoreCase(spawnWorldName)){
                player.teleport(Bukkit.getServer().getWorld(spawnWorldName).getSpawnLocation());
                player.sendMessage("" + ChatColor.GRAY + "You have been sent to the default spawn world as to avoid abuse of spectator mode teleportation.");
            }
        }
    }

}
