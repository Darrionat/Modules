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

public class Cold {
	private Main plugin;

	public Cold(Main plugin) {
		this.plugin = plugin;
	}

	Maps maps = new Maps();
	List<String> biomeBypass = maps.getBiomeBypassList();

	public void startFrozenTimer() {
		int time = plugin.getConfig().getInt("BiomeTimers.Cold.Frozen");
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					String biomeName = p.getLocation().getBlock().getBiome().name();
					if (biomeBypass.contains(p.getName())) {
						return;
					}
					if (!biomeName.contains("FROZEN")) {
						return;
					}
					ConfigurationSection config = plugin.getConfig();
					List<String> moduleNames = config.getStringList("ModuleNames");
					String coldProt = Utils.chat(moduleNames.get(10));
					if (ModuleInformation.getModule(p, coldProt, plugin) == null) {
						p.damage(1);
						continue;
					}
				}
			}
		}, 0L, time * 20L);
	}

	public void startColdTimer() {
		int time = plugin.getConfig().getInt("BiomeTimers.Cold.Cold");
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					String biomeName = p.getLocation().getBlock().getBiome().name();
					if (biomeBypass.contains(p.getName())) {
						return;
					}
					if (!biomeName.contains("COLD")) {
						return;
					}
					ConfigurationSection config = plugin.getConfig();
					List<String> moduleNames = config.getStringList("ModuleNames");
					String coldProt = Utils.chat(moduleNames.get(10));
					if (ModuleInformation.getModule(p, coldProt, plugin) == null) {
						p.damage(1);
						continue;
					}
				}
			}
		}, 0L, time * 20L);
	}

	public void startTaigaTimer() {
		int time = plugin.getConfig().getInt("BiomeTimers.Cold.Taiga");
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					String biomeName = p.getLocation().getBlock().getBiome().name();
					if (biomeBypass.contains(p.getName())) {
						return;
					}
					if (!biomeName.contains("TAIGA")) {
						return;
					}
					// Other timer
					if (biomeName.contains("SNOWY_TAIGA")) {
						return;
					}
					ConfigurationSection config = plugin.getConfig();
					List<String> moduleNames = config.getStringList("ModuleNames");
					String coldProt = Utils.chat(moduleNames.get(10));
					if (ModuleInformation.getModule(p, coldProt, plugin) == null) {
						p.damage(1);
						continue;
					}
				}
			}
		}, 0L, time * 20L);
	}

	public void startSnowyTimer() {
		int time = plugin.getConfig().getInt("BiomeTimers.Cold.SnowyBiome");
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					String biomeName = p.getLocation().getBlock().getBiome().name();
					if (biomeBypass.contains(p.getName())) {
						return;
					}
					if (!biomeName.contains("SNOWY")) {
						return;
					}
					ConfigurationSection config = plugin.getConfig();
					List<String> moduleNames = config.getStringList("ModuleNames");
					String coldProt = Utils.chat(moduleNames.get(10));
					if (ModuleInformation.getModule(p, coldProt, plugin) == null) {
						p.damage(1);
						continue;
					}
				}
			}
		}, 0L, time * 20L);
	}

	public void startIcePlainsTimer() {
		int time = plugin.getConfig().getInt("BiomeTimers.Cold.Ice");
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					String biomeName = p.getLocation().getBlock().getBiome().name();
					if (biomeBypass.contains(p.getName())) {
						return;
					}
					if (!biomeName.contains("ICE")) {
						return;
					}
					ConfigurationSection config = plugin.getConfig();
					List<String> moduleNames = config.getStringList("ModuleNames");
					String coldProt = Utils.chat(moduleNames.get(10));
					if (ModuleInformation.getModule(p, coldProt, plugin) == null) {
						p.damage(1);
						continue;
					}
				}
			}
		}, 0L, time * 20L);
	}

}
