package com.teknoserval.worldwrapper;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldWrapper extends JavaPlugin {

	@Override
	public void onEnable() {

		
		getServer().getPluginManager().registerEvents(new EventListener(), this);


		saveDefaultConfig();
		
	}
	
	@Override
	public void onDisable() {
		
	}

	public static WorldWrapper getInstance() {
		return getPlugin(WorldWrapper.class);
	}


	
	

}
