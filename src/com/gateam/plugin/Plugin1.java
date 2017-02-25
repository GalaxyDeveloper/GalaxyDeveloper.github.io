package com.gateam.plugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin1 extends JavaPlugin {

	public static String PLUGIN_NAME = "Minequest Addon";

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
}
