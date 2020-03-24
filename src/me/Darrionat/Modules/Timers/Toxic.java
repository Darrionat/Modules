package me.Darrionat.Modules.Timers;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import me.Darrionat.Modules.Main;
import me.Darrionat.Modules.Utils.Maps;
import me.Darrionat.Modules.Utils.ModuleInformation;
import me.Darrionat.Modules.Utils.Utils;

public class Toxic {
	private Main plugin;

	public Toxic(Main plugin) {
		this.plugin = plugin;
	}

	Maps maps = new Maps();
	List<String> biomeBypass = maps.getBiomeBypassList();

	public void startSwampTimer() {
		int time = plugin.getConfig().getInt("BiomeTimers.Toxic.Swamp");
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					String biomeName = p.getLocation().getBlock().getBiome().name();
					if (biomeBypass.contains(p.getName())) {
						return;
					}
					if (!biomeName.contains("SWAMP")) {
						return;
					}
					ConfigurationSection config = plugin.getConfig();
					List<String> moduleNames = config.getStringList("ModuleNames");
					String toxicProt = Utils.chat(moduleNames.get(8));
					if (ModuleInformation.getModule(p, toxicProt, plugin) == null) {
						p.damage(1);
						continue;
					}
				}
			}
		}, 0L, time * 20L);
	}

	public void startMushroomTimer() {
		int time = plugin.getConfig().getInt("BiomeTimers.Toxic.Mushroom");
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					String biomeName = p.getLocation().getBlock().getBiome().name();
					if (biomeBypass.contains(p.getName())) {
						return;
					}
					if (!biomeName.contains("MUSHROOM")) {
						return;
					}
					ConfigurationSection config = plugin.getConfig();
					List<String> moduleNames = config.getStringList("ModuleNames");
					String toxicProt = Utils.chat(moduleNames.get(8));
					if (ModuleInformation.getModule(p, toxicProt, plugin) == null) {
						p.damage(1);
						continue;
					}
				}
			}
		}, 0L, time * 20L);
	}

}
