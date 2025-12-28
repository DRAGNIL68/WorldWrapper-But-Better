package com.teknoserval.worldwrapper;

import java.util.ArrayList;
import java.util.List;

import io.papermc.paper.entity.TeleportFlag;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;


public class EventListener implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {

		Player player = event.getPlayer();
		if (WrapperUtil.needsWrapping(player)) {

			Location loc = event.getTo();
			Location newLoc = WrapperUtil.wrapper(loc);

			newLoc.getChunk().load();

			List<Entity> entities = player.getPassengers();
			Entity vehicle = player.getVehicle();


			// as of 1.21.11 TeleportFlag.EntityState.RETAIN_PASSENGERS is now standard behavior and can be removed if this plugin is ever updated to 1.21.11
			player.teleportAsync(newLoc, PlayerTeleportEvent.TeleportCause.PLUGIN,
					TeleportFlag.Relative.VELOCITY_X,
					TeleportFlag.Relative.VELOCITY_Y,
					TeleportFlag.Relative.VELOCITY_Z,
					TeleportFlag.Relative.VELOCITY_ROTATION
			).thenAccept(success -> { // loads chunks asynchronously and teleports the entity
				// this code is ran when the teleport completes
				// the Future is completed on the main thread, so it is safe to use the API here

				if (success) {


					if (vehicle != null){
						vehicle.teleportAsync(newLoc);

					}

					new BukkitRunnable() {
						@Override
						public void run() {
							if (vehicle != null){
								vehicle.addPassenger(player);

							}


							for (Entity entity1 : entities){
								player.addPassenger(entity1);
							}

							cancel();
						}
					}.runTaskTimer(WorldWrapper.getInstance(), 1, 1);





				}
			});
		}
	}


}
