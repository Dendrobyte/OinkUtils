package com.oinkcraft.oinkutils.creativespecmode;

import com.oinkcraft.oinkutils.Main;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
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
    String redstoneString = Main.getInstance().getConfig().getString("redstone-world");

    @EventHandler
    public void onAdvBuilderWorldPlayerTeleport(PlayerTeleportEvent event){
        Player player = event.getPlayer();
        World to = event.getTo().getWorld();
        World from = event.getFrom().getWorld();
        // First, cancel if the world is the same
        if(to.getName().equalsIgnoreCase(from.getName())) return;

        // Second, cancel if they are both advbuilder, advbuilderterrain, or redstone
        if(from.getName().toLowerCase().contains(advBuilderString) || from.getName().toLowerCase().contains(redstoneString)){
            if(to.getName().toLowerCase().contains(advBuilderString) || to.getName().toLowerCase().contains(redstoneString)) {
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
                        if (player.getWorld().getName().toLowerCase().contains(advBuilderString)){
                            player.setGameMode(GameMode.SPECTATOR);
                        }
                    }
                }.runTaskLater(Main.getInstance(), 20L);
            }
        }
        if (to.getName().toLowerCase().contains(redstoneString)){
            if(!player.hasPermission("oinkutils.redstone.build")) {
                player.sendMessage("" + ChatColor.BLUE + ChatColor.ITALIC + "Welcome to the Redstone world. Enjoy the builds!");
                BukkitTask quickTimer = new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (player.getWorld().getName().toLowerCase().contains(redstoneString)){
                            player.setGameMode(GameMode.SPECTATOR);
                        }
                    }
                }.runTaskLater(Main.getInstance(), 20L);
            }
        }
        else {
            String spawnWorldName = Main.getInstance().getConfig().getString("spawn-world");
            if(bothAdvBuilderWorlds(from, to)) return;
            if(from.getName().contains(advBuilderString) || from.getName().contains(redstoneString)){
                if(!to.getName().equalsIgnoreCase(spawnWorldName)) {
                    event.setCancelled(true);
                    player.teleport(Bukkit.getServer().getWorld(spawnWorldName).getSpawnLocation());
                    player.sendMessage("" + ChatColor.GRAY + "You have been sent to the default spawn world as to avoid abuse of spectator mode teleportation.");
                }
            }
        }
    }

    private boolean bothAdvBuilderWorlds(World from, World to){
        return from.getName().contains(advBuilderString) && to.getName().contains(advBuilderString);
    }

    @EventHandler
    public void onPlayerJoinPrivateWorld(PlayerJoinEvent event){
        Player player = event.getPlayer();
        World world = player.getWorld();

        // If they are in advbuilder or redstone and they don't have the necessary permission, bring them to spectator immediately
        if(world.getName().contains(advBuilderString)){
            if(!player.hasPermission("oinkutils.advbuilder.build")) {
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage("" + ChatColor.GRAY + "You have been set to spectator mode as you have logged into one of the advanced builder worlds.");
            }
        }
        if(world.getName().contains(redstoneString)){
            if(!player.hasPermission("oinkutils.redstone.build")) {
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage("" + ChatColor.GRAY + "You have been set to spectator mode as you have logged into the redstone rank world.");
            }
        }
    }

}
