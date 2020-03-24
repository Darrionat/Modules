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

public class Luck {
	private Main plugin;

	public Luck(Main plugin) {
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
					String luck = Utils.chat(moduleNames.get(19));
					if (ModuleInformation.getModule(p, luck, plugin) == null) {
						continue;
					}
					if (p.hasPotionEffect(PotionEffectType.LUCK)) {
						p.removePotionEffect(PotionEffectType.LUCK);
					}
					int currentLvl = ModuleInformation.getPlayerModuleLevel(p, luck, plugin);
					p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 20 * 20, currentLvl - 1));
					continue;

				}
			}
		}, 0L, time * 20L);
	}

}
