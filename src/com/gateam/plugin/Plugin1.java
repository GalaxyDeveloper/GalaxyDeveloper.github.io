package com.gateam.plugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Skeleton;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin1 extends JavaPlugin {

	public static String PLUGIN_NAME = "Minequest Addon";
	public static String DARWIN_METADATA_ID = "DARWIN";
	public static String DARWIN_METADATA_VALUE = "TRUE";
	private boolean darwinSpawned = false;
	private Bat darwinBat;
	private Skeleton darwin;

	// Minequest Plugin
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new Plugin1Listener(), this);

		ItemStack item = new ItemStack(Material.COOKED_MUTTON);

		ItemMeta meta = item.getItemMeta();

		meta.setDisplayName("§fMonster Jerky");

		item.setItemMeta(meta);

		ShapedRecipe recipe = new ShapedRecipe(item);

		recipe.shape("AB", "C ");

		recipe.setIngredient('A', Material.ROTTEN_FLESH);
		recipe.setIngredient('B', Material.SUGAR);
		recipe.setIngredient('C', Material.SPIDER_EYE);

		Bukkit.addRecipe(recipe);

		ItemStack item1 = new ItemStack(Material.BEETROOT_SOUP);

		ItemMeta meta1 = item1.getItemMeta();

		meta1.setDisplayName("§4Apple Juice");

		item1.setItemMeta(meta1);

		ShapedRecipe recipe1 = new ShapedRecipe(item1);

		recipe1.shape("AB", "C ");

		recipe1.setIngredient('A', Material.APPLE);
		recipe1.setIngredient('B', Material.SUGAR);
		recipe1.setIngredient('C', Material.BOWL);

		Bukkit.addRecipe(recipe1);

	}

	@Override
	public void onDisable() {
	}
	
	public boolean isDarwinSpawned() {
		return this.darwinSpawned;
	}
	
	public void setDarwinSpawned(boolean spawned) {
		this.darwinSpawned = spawned; 
	}

	public Bat getDarwinBat() {
		return darwinBat;
	}

	public void setDarwinBat(Bat darwinBat) {
		this.darwinBat = darwinBat;
	}

	public Skeleton getDarwin() {
		return darwin;
	}

	public void setDarwin(Skeleton darwin) {
		this.darwin = darwin;
	}
	
	
	
}
