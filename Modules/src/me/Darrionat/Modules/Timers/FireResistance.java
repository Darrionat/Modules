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

public class FireResistance {
	private Main plugin;

	public FireResistance(Main plugin) {
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
					String fireRes = Utils.chat(moduleNames.get(6));
					if (ModuleInformation.getModule(p, fireRes, plugin) != null) {
						if (p.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) {
							p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
						}
						p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20 * 20, 0));
						continue;
					}
				}
			}
		}, 0L, time * 20);
	}

}
