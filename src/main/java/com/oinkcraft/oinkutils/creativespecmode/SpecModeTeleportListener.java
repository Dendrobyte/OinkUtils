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
    public void onAdvBuilderWorldPlayerTeleport(PlayerTeleportEvent event){
        Player player = event.getPlayer();
        World to = event.getTo().getWorld();
        World from = event.getFrom().getWorld();
        // First, cancel if the world is the same
        if(to.equals(from)) return;

        // Second, cancel if they are both advbuilder, advbuilderterrain, or redstone
        if(from.getName().toLowerCase().contains(advBuilderString) || from.getName().toLowerCase().contains("redstone")){
            if(to.getName().toLowerCase().contains(advBuilderString) || to.getName().toLowerCase().contains("redstone")) {
                return;
            }
        }

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
        else if (to.getName().toLowerCase().contains("redstone")){
            if(!player.hasPermission("oinkutils.redstone.build")) {
                player.sendMessage("" + ChatColor.BLUE + ChatColor.ITALIC + "Welcome to the Redstone world. Enjoy the builds!");
                BukkitTask quickTimer = new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.setGameMode(GameMode.SPECTATOR);
                    }
                }.runTaskLater(Main.getInstance(), 20L);
            }
        }

        else {
            String spawnWorldName = Main.getInstance().getConfig().getString("spawn-world");
            if(bothAdvBuilderWorlds(from, to)) return;
            if(!to.getName().equalsIgnoreCase(spawnWorldName)){
                event.setCancelled(true);
                player.teleport(Bukkit.getServer().getWorld(spawnWorldName).getSpawnLocation());
                player.sendMessage("" + ChatColor.GRAY + "You have been sent to the default spawn world as to avoid abuse of spectator mode teleportation.");
            }
        }
    }

    private boolean bothAdvBuilderWorlds(World from, World to){
        return from.getName().contains(advBuilderString) && to.getName().contains(advBuilderString);
    }

}
