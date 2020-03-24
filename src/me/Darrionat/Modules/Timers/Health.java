package me.Darrionat.Modules.Timers;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import me.Darrionat.Modules.Main;
import me.Darrionat.Modules.Utils.ModuleInformation;
import me.Darrionat.Modules.Utils.Utils;

public class Health {
	private Main plugin;

	public Health(Main plugin) {
		this.plugin = plugin;
	}

	public void startTimer() {
		int time = plugin.getConfig().getInt("TimerLength");
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					ConfigurationSection config = plugin.getConfig();
					List<String> moduleNames = config.getStringList("ModuleNames");
					String health = Utils.chat(moduleNames.get(0));
					if (ModuleInformation.getModule(p, health, plugin) == null) {
						p.setMaxHealth(20);
						continue;
					}
					int level = ModuleInformation.getPlayerModuleLevel(p, health, plugin);
					p.setMaxHealth(20 + (level * 2));
					continue;
				}
			}
		}, 0L, time * 20);
	}

}
