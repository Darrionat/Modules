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

public class Hot {
	private Main plugin;

	public Hot(Main plugin) {
		this.plugin = plugin;
	}

	Maps maps = new Maps();
	List<String> biomeBypass = maps.getBiomeBypassList();

	public void startSavannaTimer() {
		int time = plugin.getConfig().getInt("BiomeTimers.Hot.Savanna");
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					String biomeName = p.getLocation().getBlock().getBiome().name();
					if (biomeBypass.contains(p.getName())) {
						return;
					}
					if (!biomeName.contains("SAVANNA")) {
						return;
					}
					ConfigurationSection config = plugin.getConfig();
					List<String> moduleNames = config.getStringList("ModuleNames");
					String hotProt = Utils.chat(moduleNames.get(7));
					if (ModuleInformation.getModule(p, hotProt, plugin) == null) {
						p.damage(1);
						continue;
					}
				}
			}
		}, 0L, time * 20L);
	}

	public void startDesertTimer() {
		int time = plugin.getConfig().getInt("BiomeTimers.Hot.Desert");
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					String biomeName = p.getLocation().getBlock().getBiome().name();
					if (biomeBypass.contains(p.getName())) {
						return;
					}
					if (!biomeName.contains("DESERT")) {
						return;
					}
					ConfigurationSection config = plugin.getConfig();
					List<String> moduleNames = config.getStringList("ModuleNames");
					String hotProt = Utils.chat(moduleNames.get(7));
					if (ModuleInformation.getModule(p, hotProt, plugin) == null) {
						p.damage(1);
						continue;
					}
				}
			}
		}, 0L, time * 20L);
	}

	public void startMesaTimer() {
		int time = plugin.getConfig().getInt("BiomeTimers.Hot.Mesa");
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					String biomeName = p.getLocation().getBlock().getBiome().name();
					if (biomeBypass.contains(p.getName())) {
						return;
					}
					if (!biomeName.contains("MESA") && !biomeName.contains("BADLANDS")) {
						return;
					}
					ConfigurationSection config = plugin.getConfig();
					List<String> moduleNames = config.getStringList("ModuleNames");
					String hotProt = Utils.chat(moduleNames.get(7));
					if (ModuleInformation.getModule(p, hotProt, plugin) == null) {
						p.damage(1);
						continue;
					}
				}
			}
		}, 0L, time * 20L);
	}

	public void startNetherTimer() {
		int time = plugin.getConfig().getInt("BiomeTimers.Hot.Nether");
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					String biomeName = p.getLocation().getBlock().getBiome().name();
					if (biomeBypass.contains(p.getName())) {
						return;
					}
					if (!biomeName.contains("NETHER")) {
						return;
					}
					ConfigurationSection config = plugin.getConfig();
					List<String> moduleNames = config.getStringList("ModuleNames");
					String hotProt = Utils.chat(moduleNames.get(7));
					if (ModuleInformation.getModule(p, hotProt, plugin) == null) {
						p.damage(1);
						continue;
					}
				}
			}
		}, 0L, time * 20L);
	}

}
