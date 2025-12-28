package com.teknoserval.worldwrapper;

import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;

public class ConfigHandler {

	private FileConfiguration config;
	private static ConfigHandler configHandler;


	private ConfigHandler () {

		config = WorldWrapper.getInstance().getConfig();

		config.addDefault("worldEdgeNorth", -30000000);
		config.addDefault("worldEdgeSouth", 30000000);
		config.addDefault("worldEdgeEast", 30000000);
		config.addDefault("worldEdgeWest", -30000000);

		config.addDefault("wrapNorthSouth", true);
		config.addDefault("wrapEastWest", true);

		config.options().copyDefaults(true);

	}


	public static ConfigHandler getInstance(){
		if (configHandler == null) {

			configHandler = new ConfigHandler();
		}

		return configHandler;
	}

	public FileConfiguration getConfig(){return config;}

}
