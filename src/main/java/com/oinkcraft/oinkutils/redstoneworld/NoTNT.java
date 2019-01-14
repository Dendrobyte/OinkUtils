package com.oinkcraft.oinkutils.redstoneworld;

import com.oinkcraft.oinkutils.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class NoTNT
        implements Listener
{
    @EventHandler
    public void explosionEvent(EntityExplodeEvent event)
    {
        FileConfiguration mainConfig = Main.getInstance().getConfig();

        Entity entity = event.getEntity();
        EntityType entityType = event.getEntityType();
        if ((entityType == EntityType.PRIMED_TNT) &&
                (entity.getLocation().getWorld().getName().equalsIgnoreCase(mainConfig.getString("redstone-world")))) {
            event.setCancelled(true);
        }
        if ((entityType == EntityType.MINECART_TNT) &&
                (entity.getLocation().getWorld().getName().equalsIgnoreCase(mainConfig.getString("redstone-world")))) {
            event.setCancelled(true);
        }
    }
}
