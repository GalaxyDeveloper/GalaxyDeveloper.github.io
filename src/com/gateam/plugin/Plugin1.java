package com.gateam.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Plugin1 extends JavaPlugin {

	public static String PLUGIN_NAME = "Minequest Addon";

	// Minequest Plugin
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new Plugin1Listener(), this);
	}

	@Override
	public void onDisable() {
	}
}
