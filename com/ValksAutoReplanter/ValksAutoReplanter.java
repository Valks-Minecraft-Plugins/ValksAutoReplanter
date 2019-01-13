package com.ValksAutoReplanter;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.ValksAutoReplanter.planter.Planter;

public class ValksAutoReplanter extends JavaPlugin {
	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new Planter(), this);
	}
}
