package com.oinkcraft.oinkutils.submission;

import com.oinkcraft.oinkutils.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitScheduler;

public class JoinNotificationListener implements Listener {
    private String prefix = Main.getInstance().getPrefix();

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event)
    {
        final Player player = event.getPlayer();

        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
            public void run() {
                if ((player.hasPermission("mibutils.submissions.review")) || (player.isOp())) {
                    if (Main.getInstance().getSubmissions().getStringList("submissions.builder").size() > 0) {
                        player.sendMessage(prefix+ "Players waiting for Builder submission review: " + Main.getInstance().getSubmissions().getStringList("submissions.builder").toString());
                    }
                    if (Main.getInstance().getSubmissions().getStringList("submissions.redstone").size() > 0) {
                        player.sendMessage(prefix+ "Players waiting for Redstone submission review: " + Main.getInstance().getSubmissions().getStringList("submissions.redstone").toString());
                    }
                    if (Main.getInstance().getSubmissions().getStringList("submissions.parkour").size() > 0) {
                        player.sendMessage(prefix+ "Players waiting for Parkour submission review: " + Main.getInstance().getSubmissions().getStringList("submissions.parkour").toString());
                    }
                    return;
                }
            }
        }, 40L);
    }
}
