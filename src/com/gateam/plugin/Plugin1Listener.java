package com.gateam.plugin;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
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
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Plugin1Listener implements Listener {
	// Dialog
	private static final String SENCOUNTER1 = "Hey, human!";
	private static final String SENCOUNTER2 = "My leader requests that you be captured!.";
	private static final String SENCOUNTER3 = "Stop, human!";
	private static final String SENCOUNTER4 = "I will capture you!";
	private static final String SKILL_MESSAGE = "Spare me, please!";
	private static final String SKILL_MESSAGE2 = "Alas, poor me!";
	private static final String SFOLLOWING1 = "Get over here so I can capture you!";
	private static final String SFOLLOWING2 = "I was sent to kill you!";
	private static final String DARWIN1 = "I, THE AMAZING DARWIN, WILL CAPTURE A HUMAN!";
	private static final String DARWIN2 = "THEN, I WILL RECEIVE EVERYTHING I UTTERLY DESERVE!";
	private static final String DARWIN3 = "I WILL BE POPULAR, FAMOUS...";
	private static final String DARWIN4 = "THAT'S DARWIN, THE NEWEST MEMBER OF THE GUARDSMEN.";
	private static final String DARWINKILL = "ALAS, POOR DARWIN!";
	private static final String DARWINSPARE = "I, THE AMAZING DARWIN, WILL SPARE YOU!";
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
				int r = rand.nextInt(100);
				if (r <= 5) {
					player.getAttribute(Attribute.GENERIC_MAX_HEALTH)
							.setBaseValue(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() + 2);
					// player.setMaxHealth(player.getMaxHealth() + 2);
					player.sendRawMessage(ChatColor.GREEN + "You absorbed the monster soul...");
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
				Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin(Plugin1.PLUGIN_NAME);
				BukkitRunnable bukkitRunnable1 = null;
				List<MetadataValue> metadata = entity.getMetadata(Plugin1.DARWIN_METADATA_ID);
				// Darwin's Boss Battle
				if (metadata != null && !metadata.isEmpty() && Plugin1.DARWIN_METADATA_VALUE.equals(metadata.get(0).asString())) {
					((Player) event.getTarget()).sendRawMessage(ChatColor.RED + "Metalic stomping echos around you...");
					entity.setCustomName(DARWIN1);
					entity.setCustomNameVisible(true);
					bukkitRunnable1 = new BukkitRunnable() {

						@Override
						public void run() {
							if(DARWIN1.equals(entity.getCustomName())) {
								entity.setCustomName(DARWIN2);
							} else if (DARWIN2.equals(entity.getCustomName())) {
								entity.setCustomName(DARWIN3);
							} else if (DARWIN3.equals(entity.getCustomName())) {
								entity.setCustomName(DARWIN4);
							} else {
								entity.setCustomName(null);
								entity.setCustomNameVisible(false);
								this.cancel();
							}
						}
					};
					bukkitRunnable1.runTaskTimer(plugin, 140L, 100L);
				} else {
					((Player) event.getTarget()).sendRawMessage(ChatColor.RED + "You feel like your going to have a bad time...");
					Random rand = new Random();
					int r = rand.nextInt(4);
					String message = null;
					if (r == 0) {
						message = SENCOUNTER1;
					} else if (r == 2) {
						message = SENCOUNTER3;
					} else if (r == 3) {
						message = SENCOUNTER4;
					} else {
						message = SENCOUNTER2;
					}
					entity.setCustomName(message);
					entity.setCustomNameVisible(true);
					bukkitRunnable1 = new BukkitRunnable() {

						@Override
						public void run() {
							if (SENCOUNTER1.equals(entity.getCustomName())) {
								entity.setCustomName(SFOLLOWING1);
							} else if (SENCOUNTER3.equals(entity.getCustomName())) {
								entity.setCustomName(SFOLLOWING2);
							} else if (SENCOUNTER4.equals(entity.getCustomName())) {
								entity.setCustomName(SFOLLOWING1);
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
	}
	
	@SuppressWarnings("unused")
	private boolean isDarwin(Entity entity) {
		boolean result = false;
		if (entity.getType() == EntityType.SKELETON) {
			List<MetadataValue> metadata = entity.getMetadata(Plugin1.DARWIN_METADATA_ID);
			if (metadata != null && !metadata.isEmpty() && Plugin1.DARWIN_METADATA_VALUE.equals(metadata.get(0).asString())) {
				result = true;
			}
		}
		return result;
	}

	// Skeleton Setup
	@EventHandler
	public void skeletonMobChanges(EntitySpawnEvent event) {
		if (event.getEntityType() == EntityType.SKELETON) {
			Skeleton s = (Skeleton) event.getEntity();
			s.getEquipment().setItemInMainHand(null);

			Plugin1 plugin = ((Plugin1) Bukkit.getServer().getPluginManager().getPlugin(Plugin1.PLUGIN_NAME));
			if (!plugin.isDarwinSpawned()) {
				Random rand = new Random();
				int r = rand.nextInt(10);
				if (r <= 5) {
					s.setMetadata(Plugin1.DARWIN_METADATA_ID,
							new FixedMetadataValue(plugin, Plugin1.DARWIN_METADATA_VALUE));
					s.getEquipment().setItemInMainHand(new ItemStack(Material.STONE_SWORD));
					s.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
					s.getEquipment().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
					ItemStack bootItemStack = new ItemStack(Material.LEATHER_BOOTS);
					LeatherArmorMeta meta = (LeatherArmorMeta) bootItemStack.getItemMeta();
					meta.setColor(Color.BLACK);
					bootItemStack.setItemMeta(meta);
					s.getEquipment().setBoots(bootItemStack);
					s.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(s.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() + 40);
					// s.getMetadata(Plugin1.DARWIN_METADATA_ID).get(0).asString();
					plugin.setDarwinSpawned(true);
					plugin.setDarwin(s);
					World world = s.getWorld();
					Bat bat = (Bat)world.spawnEntity(s.getLocation(), EntityType.BAT);
					ArmorStand stand = (ArmorStand)world.spawnEntity(s.getEyeLocation(), EntityType.ARMOR_STAND);
					stand.setArms(true);
					stand.setItemInHand(new ItemStack(Material.BONE));
					bat.addPassenger(stand);
					stand.setVisible(false);
					bat.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
					plugin.setDarwinBat(bat);
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		Plugin1 plugin = ((Plugin1) Bukkit.getServer().getPluginManager().getPlugin(Plugin1.PLUGIN_NAME));
		if (plugin.getDarwinBat() != null) {
			Bat bat = plugin.getDarwinBat();
			bat.teleport(new Location(p.getWorld(), bat.getLocation().getX(), bat
					.getLocation().getY(), bat.getLocation().getZ(), p
					.getEyeLocation().getPitch(), p.getEyeLocation().getYaw()));
		}
	}

	// Skeleton Last Words
	@EventHandler
	public void onMobKilledSkeleton(EntityDeathEvent event) {
		final Entity e = event.getEntity();
		if (e.getType() == EntityType.SKELETON) {
			List<MetadataValue> metadata = e.getMetadata(Plugin1.DARWIN_METADATA_ID);
			if (metadata != null && !metadata.isEmpty() && Plugin1.DARWIN_METADATA_VALUE.equals(metadata.get(0).asString())) {
				e.setCustomName(DARWINKILL);
			} else {
				Random rand = new Random();
				int r = rand.nextInt(4);
				if (r == 0) {
					e.setCustomName(SKILL_MESSAGE);
				} else if (r == 2) {
					e.setCustomName(SKILL_MESSAGE2);
					e.setCustomNameVisible(true);
				}
			}
		}
	}
	
	//Darwin - Mercy
	@EventHandler
	 void darwinMercy(EntityDamageByEntityEvent event) {
		Entity e = event.getEntity();
		if (e.getType() == EntityType.SKELETON) {
			List<MetadataValue> metadata = e.getMetadata(Plugin1.DARWIN_METADATA_ID);
			if (metadata != null && !metadata.isEmpty() && Plugin1.DARWIN_METADATA_VALUE.equals(metadata.get(0).asString())) {
				if (((LivingEntity) e).getHealth() < 10) {
					e.setCustomName(DARWINSPARE);
				}
			}
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
	//Darwin Chat 
	@EventHandler
	public void onPlayerChatWithDarwin(PlayerChatEvent event) {
		Plugin1 plugin = (Plugin1)Bukkit.getServer().getPluginManager().getPlugin(Plugin1.PLUGIN_NAME);
		if (plugin.isDarwinSpawned() && event.getPlayer().equals(plugin.getDarwin().getTarget())) {
			Bukkit.broadcastMessage("darwin spawnd " + plugin.getDarwin().getTarget().getName());
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
				((Player) event.getTarget()).sendRawMessage(ChatColor.RED + "You hear some sort of groaning sound...");
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

	// Zombie Drops
	// @EventHandler
	// public void onEntityDeath(EntityDeathEvent event) {
	// Random rand = new Random();
	// int r = rand.nextInt(100);
	// String message = null;
	// if (r <= 99) {
	// int amount = 1;
	// EntityType ent = event.getEntityType();
	// byte typeID = (byte) EntityType.ZOMBIE.ordinal();
	// ItemStack stack = new ItemStack(Material.PAPER, amount, (byte) 6);
	// Entity entity = event.getEntity();
	// event.getDrops().clear();
	// event.getDrops().add(stack);
	// event.getEntity().getWorld().dropItemNaturally(entity.getLocation(),
	// stack);
	// }
	// }

	///////////////////////
	// Spider - RPG Edits//
	///////////////////////

	// Spider Quotes
	@EventHandler
	public void spiderEncounterQuotes(EntityTargetLivingEntityEvent event) {
		if (event.getReason() == TargetReason.CLOSEST_PLAYER) {
			Entity entity = event.getEntity();
			if (entity.getType() == EntityType.SPIDER) {
				((Player) event.getTarget())
						.sendRawMessage(ChatColor.RED + "You hear something hissing, it's getting louder...");
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
