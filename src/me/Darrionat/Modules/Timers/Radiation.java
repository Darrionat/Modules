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

public class Radiation {
	private Main plugin;

	public Radiation(Main plugin) {
		this.plugin = plugin;
	}

	Maps maps = new Maps();
	List<String> biomeBypass = maps.getBiomeBypassList();

	public void startEndTimer() {
		int time = plugin.getConfig().getInt("BiomeTimers.Radiation.End");
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					String biomeName = p.getLocation().getBlock().getBiome().name();
					if (biomeBypass.contains(p.getName())) {
						return;
					}
					if (!biomeName.contains("END")) {
						return;
					}
					ConfigurationSection config = plugin.getConfig();
					List<String> moduleNames = config.getStringList("ModuleNames");
					String radiationProt = Utils.chat(moduleNames.get(9));
					if (ModuleInformation.getModule(p, radiationProt, plugin) == null) {
						p.damage(1);
						continue;
					}
				}
			}
		}, 0L, time * 20L);
	}

}
