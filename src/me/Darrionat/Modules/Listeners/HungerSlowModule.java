package me.Darrionat.Modules.Listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import me.Darrionat.Modules.Main;
import me.Darrionat.Modules.Files.FileManager;
import me.Darrionat.Modules.Utils.ModuleInformation;
import me.Darrionat.Modules.Utils.Utils;

public class HungerSlowModule implements Listener {
	private Main plugin;

	public HungerSlowModule(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	static List<String> activeUsers = new ArrayList<String>();

	@EventHandler
	public void onFoodChange(FoodLevelChangeEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		if (e.getItem() != null) {
			return;
		}
		Player p = (Player) e.getEntity();
		ConfigurationSection config = plugin.getConfig();
		List<String> moduleNames = config.getStringList("ModuleNames");
		String hungerSlow = Utils.chat(moduleNames.get(18));
		// Don't have the module
		if (ModuleInformation.getModule(p, hungerSlow, plugin) == null) {
			return;
		}
		FileManager fileManager = new FileManager(plugin);
		FileConfiguration prices = fileManager.getDataConfig("prices");
		int playerLvl = ModuleInformation.getPlayerModuleLevel(p, hungerSlow, plugin);
		int min = 1;
		int max = 100;
		Random r = new Random();
		int i = r.nextInt(max - min + 1) + min;
		int percent = playerLvl * prices.getInt("HungerSlow.UpgradePercent");
		if (i > percent) {
			return;
		}
		e.setCancelled(true);
		return;

	}

}
