package me.Darrionat.Modules.Listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Darrionat.Modules.Main;
import me.Darrionat.Modules.Utils.ModuleInformation;
import me.Darrionat.Modules.Utils.Utils;

public class WaterBreathingModule implements Listener {
	private Main plugin;

	public WaterBreathingModule(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	static List<String> activeUsers = new ArrayList<String>();

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (p.hasPotionEffect(PotionEffectType.WATER_BREATHING) == true) {
			if (!activeUsers.contains(p.getName())) {
				activeUsers.add(p.getName());
			}
			return;
		}
		Material type = e.getTo().getBlock().getType();
		if (type != Material.WATER && type != Material.KELP && type != Material.SEAGRASS && type != Material.KELP_PLANT
				&& type != Material.TALL_SEAGRASS) {
			if (p.getRemainingAir() != p.getMaximumAir()) {
				return;
			}
			if (activeUsers.contains(p.getName())) {
				activeUsers.remove(p.getName());
			}
			return;
		}

		ConfigurationSection config = plugin.getConfig();
		List<String> moduleNames = config.getStringList("ModuleNames");
		String waterBreathing = Utils.chat(moduleNames.get(2));
		if (ModuleInformation.getModule(p, waterBreathing, plugin) == null) {
			return;
		}
		int level = ModuleInformation.getPlayerModuleLevel(p, waterBreathing, plugin);

		if (!activeUsers.contains(p.getName())) {
			activeUsers.add(p.getName());
			p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, level * 30 * 20, 0));
		}

		return;

	}

}
