package com.teknoserval.worldwrapper;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class WrapperUtil {
    
    
    private WrapperUtil() {}


    public static boolean needsWrapping(Entity entity) {

        boolean needWrap = false;
        Location loc = entity.getLocation();

        if (loc.getX() > ConfigHandler.getInstance().getConfig().getInt("worldEdgeEast") || loc.getX() < ConfigHandler.getInstance().getConfig().getInt("worldEdgeWest")
                || loc.getZ() > ConfigHandler.getInstance().getConfig().getInt("worldEdgeSouth") || loc.getZ() < ConfigHandler.getInstance().getConfig().getInt("worldEdgeNorth")) {

            needWrap = true;

        }

        return needWrap;

    }

    public static Location wrapper(Location loc) {

        if (ConfigHandler.getInstance().getConfig().getBoolean("wrapNorthSouth")) {
            if (loc.getZ() > ConfigHandler.getInstance().getConfig().getInt("worldEdgeSouth")) {

                loc.setZ(ConfigHandler.getInstance().getConfig().getInt("worldEdgeNorth") + 2);

            } else if (loc.getZ() < ConfigHandler.getInstance().getConfig().getInt("worldEdgeNorth")) {

                loc.setZ(ConfigHandler.getInstance().getConfig().getInt("worldEdgeSouth") - 2);

            }
        }

        if (ConfigHandler.getInstance().getConfig().getBoolean("wrapEastWest")) {
            if (loc.getX() > ConfigHandler.getInstance().getConfig().getInt("worldEdgeEast")) {

                loc.setX(ConfigHandler.getInstance().getConfig().getInt("worldEdgeWest") + 2);

            } else if (loc.getX() < ConfigHandler.getInstance().getConfig().getInt("worldEdgeWest")) {

                loc.setX(ConfigHandler.getInstance().getConfig().getInt("worldEdgeEast") - 2);

            }
        }

        loc.setY(loc.getWorld().getHighestBlockYAt(loc) + 2);

        return loc;

    }
    
}
