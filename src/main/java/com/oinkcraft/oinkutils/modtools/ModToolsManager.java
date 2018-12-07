package com.oinkcraft.oinkutils.modtools;

import javax.xml.stream.Location;

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
    public void sneezeAwayEntities(Location location, int radius){

    }
}
