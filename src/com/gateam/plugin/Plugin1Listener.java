package com.gateam.plugin;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Plugin1Listener implements Listener {
	private static final String ENCOUNTER1 = "I will capture a human!";
	private static final String ENCOUNTER2 = "You've asked for it.";
	private static final String KILL_MESSAGE = "Spare me, please!";
	private static final String FOLLOWING1 = "Then, I will receive all the things I utterly deserve!";

	// Player Join Message
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Bukkit.broadcastMessage("This Server Uses Minequest v.1.0");
	}

	/////////////////////////
	// Skeleton - RPG Edits//
	/////////////////////////

	// Skeleton Quotes
	@EventHandler
	public void skeletonEncounterQuotes(EntityTargetLivingEntityEvent event) {
		if (event.getReason() == TargetReason.CLOSEST_PLAYER) {
			Entity entity = event.getEntity();
			if (entity.getType() == EntityType.SKELETON) {
				Random rand = new Random();
				int r = rand.nextInt(2);
				String message = null;
				if (r == 0) {
					message = ENCOUNTER1;
				} else {
					message = ENCOUNTER2;
				}
				entity.setCustomName(message);
				entity.setCustomNameVisible(true);
				Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin(Plugin1.PLUGIN_NAME);
				BukkitRunnable bukkitRunnable1 = new BukkitRunnable() {

					@Override
					public void run() {
						if (ENCOUNTER1.equals(entity.getCustomName())) {
							entity.setCustomName(FOLLOWING1);
						} else {
							entity.setCustomName(null);
							entity.setCustomNameVisible(false);
						}
					}
				};
				bukkitRunnable1.runTaskLater(plugin, 140L);
			}
		}
	}

	// Skeleton Setup
	@EventHandler
	public void skeletonMobChanges(EntitySpawnEvent event) {
		if (event.getEntityType() == EntityType.SKELETON) {
			Skeleton s = (Skeleton) event.getEntity();
			s.getEquipment().setItemInMainHand(null);
		}
	}

	// Skeleton Last Words
	@EventHandler
	public void onMobKilled(EntityDeathEvent event) {
		final Entity e = event.getEntity();
		if (e.getType() == EntityType.SKELETON) {
			e.setCustomName(KILL_MESSAGE);
			e.setCustomNameVisible(true);
		}
	}

	// Skeleton Death Message Bug Fixed
	@EventHandler
	public void onKill(PlayerDeathEvent e) {
		String killed = e.getEntity().getName();
		EntityDamageEvent ede = e.getEntity().getLastDamageCause();
		if (ede instanceof EntityDamageByEntityEvent) {
			EntityDamageByEntityEvent edbee = (EntityDamageByEntityEvent) ede;
			Entity attacker = edbee.getDamager();
			if (attacker.getType() == EntityType.SKELETON) {
				e.setDeathMessage(ChatColor.WHITE + killed + " has been slain by Skeleton");
			}
			e.setDeathMessage(ChatColor.WHITE + killed + " has been slain by " + attacker.getType());
		}
	}

	///////////////////////
	// Zombie - RPG Edits//
	///////////////////////

	// @EventHandler
	// public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
	// if (event.getEntity().getType() == EntityType.PLAYER) {
	// Player attacked = (Player) event.getEntity();
	// if (attacked.getHealth() - event.getDamage() <= 0) {
	// Entity damager = event.getDamager();
	// Bukkit.broadcastMessage("damanged killed");
	// if (damager != null) {
	// damager.setCustomName("dam");
	// damager.setCustomNameVisible(false);
	// Bukkit.broadcastMessage("not null");
	// }
	// }
	// }
	// }
}
