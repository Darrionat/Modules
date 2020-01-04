package me.Darrionat.Modules.Timers;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import me.Darrionat.Modules.Main;
import me.Darrionat.Modules.Utils.ModuleInformation;
import me.Darrionat.Modules.Utils.Utils;

public class NightVision {
	private Main plugin;

	public NightVision(Main plugin) {
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
					String nightVision = Utils.chat(moduleNames.get(5));
					if (ModuleInformation.getModule(p, nightVision, plugin) != null) {
						if (p.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
							p.removePotionEffect(PotionEffectType.NIGHT_VISION);
						}
						p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20 * 20, 0));
						continue;
					}
				}
			}
		}, 0L, time * 20L);
	}

}
