package com.locydragon.lil;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author LocyDragon
 */
public class Main extends JavaPlugin {
	public static Main instance;

	/**
	 * 当插件被加载时被bukkit引用的方法.
	 */
	@Override
	public void onEnable() {
		if (Bukkit.getPluginManager().getPlugin("LocyItem") == null) {
			Bukkit.getLogger().info("LocyItem必备前置不存在，插件自动关闭.");
			Bukkit.getPluginManager().disablePlugin(this);
		}
		Bukkit.getLogger().info("LocyItem——前置附属已经加载了!");
		saveDefaultConfig();
		instance = this;
	}

	/**
	 * 重载
	 */
	public static void reloadSettings() {
		instance.saveConfig();
		instance.reloadConfig();
	}
}
