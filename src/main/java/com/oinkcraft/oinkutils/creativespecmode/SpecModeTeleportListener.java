package com.oinkcraft.oinkutils.creativespecmode;

import com.oinkcraft.oinkutils.Main;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
        Location to = event.getTo();

        // We only check the world they are arriving in because PerWorldInv / Multiverse should manage them leaving
        if(to.getWorld().getName().toLowerCase().contains(advBuilderString)){
            Player player = event.getPlayer();
            if(!player.hasPermission("oinkutils.advbuilder.build")){
                player.sendMessage("" + ChatColor.BLUE + ChatColor.ITALIC + "Welcome to the Advanced Builder world! Enjoy looking at the builds :)");
                BukkitTask quickTimer = new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.setGameMode(GameMode.SPECTATOR);
                    }
                }.runTaskLater(Main.getInstance(), 20L);
            }
        }
    }

}
