package com.gateam.plugin;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
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
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Plugin1Listener implements Listener {
	// Dialog
	private static final String SENCOUNTER1 = "I will capture a human!";
	private static final String SENCOUNTER2 = "You've asked for it.";
	private static final String SENCOUNTER3 = "Stop, human!";
	private static final String SKILL_MESSAGE = "Spare me, please!";
	private static final String SFOLLOWING1 = "Then, I will receive all the things I utterly deserve!";
	private static final String SFOLLOWING2 = "I was sent to kill you!";
	private static final String ZENCOUNTER1 = "Brains!";
	private static final String ZENCOUNTER2 = "Brains...";
	private static final String ZKILL_MESSAGE = "Brains?";
	private static final String ZFOLLOWING1 = "Brraaiinnnss!";
	private static final String SPENCOUNTER1 = "I've heard of a human coming through, Sssss...";
	private static final String SPENCOUNTER2 = "You seem suspicious, Sssss...";
	private static final String SPKILL_MESSAGE = "You slippery human! Sssss...";
	private static final String SPFOLLOWING1 = "Sssss...";

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getDamager() != null && event.getDamager().getType() == EntityType.PLAYER
				&& event.getEntity() instanceof Monster) {
			Player player = (Player) event.getDamager();
			Monster mob = (Monster) event.getEntity();
			if (mob.getHealth() - event.getDamage() <= 0) {
				Random rand = new Random();
				int r = rand.nextInt(10);
				if (r <= 4) {
					player.setMaxHealth(player.getMaxHealth() + 2);
					Bukkit.broadcastMessage("You absorbed the monster's soul...");
				}
			}
		}

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
				Bukkit.broadcastMessage(ChatColor.RED + "You feel like your going to have a bad time...");
				Random rand = new Random();
				int r = rand.nextInt(3);
				String message = null;
				if (r == 0) {
					message = SENCOUNTER1;
				} else if (r == 2) {
					message = SENCOUNTER3;
				} else {
					message = SENCOUNTER2;
				}
				entity.setCustomName(message);
				entity.setCustomNameVisible(true);
				Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin(Plugin1.PLUGIN_NAME);
				BukkitRunnable bukkitRunnable1 = new BukkitRunnable() {

					@Override
					public void run() {
						if (SENCOUNTER1.equals(entity.getCustomName())) {
							entity.setCustomName(SFOLLOWING1);
						} else if (SENCOUNTER3.equals(entity.getCustomName())) {
							entity.setCustomName(SFOLLOWING2);
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
	public void onMobKilledSkeleton(EntityDeathEvent event) {
		final Entity e = event.getEntity();
		if (e.getType() == EntityType.SKELETON) {
			e.setCustomName(SKILL_MESSAGE);
			e.setCustomNameVisible(true);
		}
	}

	// All Death Messages Fixed
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

	// Zombie Quotes
	@EventHandler
	public void zombieEncounterQuotes(EntityTargetLivingEntityEvent event) {
		if (event.getReason() == TargetReason.CLOSEST_PLAYER) {
			Entity entity = event.getEntity();
			if (entity.getType() == EntityType.ZOMBIE) {
				Bukkit.broadcastMessage(ChatColor.RED + "You hear some sort of groaning sound...");
				Random rand = new Random();
				int r = rand.nextInt(2);
				String message = null;
				if (r == 0) {
					message = ZENCOUNTER1;
				} else {
					message = ZENCOUNTER2;
				}
				entity.setCustomName(message);
				entity.setCustomNameVisible(true);
				Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin(Plugin1.PLUGIN_NAME);
				BukkitRunnable bukkitRunnable1 = new BukkitRunnable() {

					@Override
					public void run() {
						if (ZENCOUNTER1.equals(entity.getCustomName())) {
							entity.setCustomName(ZFOLLOWING1);
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

	// Zombie Last Words
	@EventHandler
	public void onMobKilledZombie(EntityDeathEvent event) {
		final Entity e = event.getEntity();
		if (e.getType() == EntityType.ZOMBIE) {
			e.setCustomName(ZKILL_MESSAGE);
			e.setCustomNameVisible(true);
		}
	}

	///////////////////////
	// Spider - RPG Edits//
	///////////////////////

	// Spider Quotes
	@EventHandler
	public void spiderEncounterQuotes(EntityTargetLivingEntityEvent event) {
		if (event.getReason() == TargetReason.CLOSEST_PLAYER) {
			Entity entity = event.getEntity();
			if (entity.getType() == EntityType.SPIDER) {
				Bukkit.broadcastMessage(ChatColor.RED + "You hear something hissing, it's getting louder...");
				Random rand = new Random();
				int r = rand.nextInt(2);
				String message = null;
				if (r == 0) {
					message = SPENCOUNTER1;
				} else {
					message = SPENCOUNTER2;
				}
				entity.setCustomName(message);
				entity.setCustomNameVisible(true);
				Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin(Plugin1.PLUGIN_NAME);
				BukkitRunnable bukkitRunnable1 = new BukkitRunnable() {

					@Override
					public void run() {
						if (SPENCOUNTER1.equals(entity.getCustomName())) {
							entity.setCustomName(SPFOLLOWING1);
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

	// Spider Last Words
	@EventHandler
	public void onMobKilledSpider(EntityDeathEvent event) {
		final Entity e = event.getEntity();
		if (e.getType() == EntityType.SPIDER) {
			e.setCustomName(SPKILL_MESSAGE);
			e.setCustomNameVisible(true);
		}
	}
}
