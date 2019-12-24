package com.oinkcraft.oinkutils.portals;

import com.oinkcraft.oinkutils.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * OinkUtils created/started by mobki (Mobkinz78/Dendrobyte)
 * Please do not use or edit without permission!
 * If you have any questions, reach out to me on Twitter: @Mobkinz78
 * §
 */
public class NetherPortalListener implements Listener {

    @EventHandler
    public void onPlayerUseNetherPortal(PlayerTeleportEvent event){
        Player player = event.getPlayer();
        if(!player.getWorld().getName().equalsIgnoreCase(Main.getInstance().getConfig().getString("creative-world"))) return;
        if(event.getCause() == PlayerTeleportEvent.TeleportCause.END_PORTAL || event.getCause() == PlayerTeleportEvent.TeleportCause.NETHER_PORTAL){
            player.sendMessage("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + ">> " + ChatColor.GRAY + "Portals are disabled in creative.");
            event.setCancelled(true);
        }
    }

}
