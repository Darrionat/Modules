package me.Darrionat.Modules.Timers;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import me.Darrionat.Modules.Main;
import me.Darrionat.Modules.Utils.ModuleInformation;
import me.Darrionat.Modules.Utils.Utils;

public class Speed {
	private Main plugin;

	public Speed(Main plugin) {
		this.plugin = plugin;
	}

	public void startTimer() {
		int time = plugin.getConfig().getInt("TimerLength");
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					ConfigurationSection config = plugin.getConfig();
					List<String> moduleNames = config.getStringList("ModuleNames");
					String speed = Utils.chat(moduleNames.get(1));
					if (ModuleInformation.getModule(p, speed, plugin) == null) {
						p.setWalkSpeed((float) 0.2);
						continue;
					}
					int level = ModuleInformation.getPlayerModuleLevel(p, speed, plugin);
					double percent = (1 + level * 0.10) * 0.2;
					p.setWalkSpeed((float) percent);
					continue;
				}
			}
		}, 0L, time * 20);
	}

}
