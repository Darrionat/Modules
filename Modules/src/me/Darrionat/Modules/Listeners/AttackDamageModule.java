package me.Darrionat.Modules.Listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.Darrionat.Modules.Main;
import me.Darrionat.Modules.Utils.ModuleInformation;
import me.Darrionat.Modules.Utils.Utils;

public class AttackDamageModule implements Listener {
	private Main plugin;

	public AttackDamageModule(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	static List<String> activeUsers = new ArrayList<String>();

	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getDamager();
		ConfigurationSection config = plugin.getConfig();
		List<String> moduleNames = config.getStringList("ModuleNames");
		String attackDmg = Utils.chat(moduleNames.get(3));
		// Don't have the module
		if (ModuleInformation.getModule(p, attackDmg, plugin) == null) {
			return;
		}
		// When the player has the module
		int level = ModuleInformation.getPlayerModuleLevel(p, attackDmg, plugin);
		double percent = 1 + (level * 0.1);
		e.setDamage(e.getDamage() * percent);
		return;
	}

}
