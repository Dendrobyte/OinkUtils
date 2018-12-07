package com.oinkcraft.oinkutils.modtools;

import org.bukkit.Location;
import org.bukkit.entity.*;

import java.util.List;

/**
 * OinkUtils created/started by Mark Bacon (Mobkinz78 or ByteKangaroo) on 12/7/2018
 * Please do not use or edit without permission! (Being on GitHub counts as permission)
 * If you have any questions, reach out to me on Twitter! @Mobkinz78
 * §
 */
public class ModToolsManager {
    private static ModToolsManager instance = new ModToolsManager();
    private String prefix = "§8[§3ModTools§8]§b ";

    private ModToolsManager() {
    }

    public static ModToolsManager getInstance() {
        return instance;
    }

    public String getPrefix(){
        return prefix;
    }

    /* The method that does the magic of the sneeze command (entity clearing) */
    // Returns the number of entities cleared
    public int sneezeAwayEntities(List<Entity> entityList){
        int clearedCounter = 0;
        for(Entity entity : entityList){
            if(!(entity instanceof HumanEntity) && !(entity instanceof ItemFrame)){
                entity.remove();
                clearedCounter++;
            }
        }
        return clearedCounter;
    }
}
