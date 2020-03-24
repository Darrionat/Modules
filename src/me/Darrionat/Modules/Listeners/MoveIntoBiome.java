package me.Darrionat.Modules.Listeners;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.Darrionat.Modules.Main;
import me.Darrionat.Modules.Utils.ActionBar;
import me.Darrionat.Modules.Utils.Maps;
import me.Darrionat.Modules.Utils.ModuleInformation;
import me.Darrionat.Modules.Utils.Utils;

public class MoveIntoBiome implements Listener {
	private Main plugin;

	public MoveIntoBiome(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		String biomeName = p.getLocation().getBlock().getBiome().name();
		ConfigurationSection config = plugin.getConfig();
		List<String> moduleNames = config.getStringList("ModuleNames");

		Maps maps = new Maps();
		List<String> biomeBypass = maps.getBiomeBypassList();
		if (biomeBypass.contains(p.getName())) {
			return;
		}
		// Hot
		if (biomeName.contains("SAVANNA") || biomeName.contains("DESERT") || biomeName.contains("MESA")
				|| biomeName.contains("NETHER") || biomeName.contains("BADLANDS")) {
			String hotProt = Utils.chat(moduleNames.get(7));
			if (ModuleInformation.getModule(p, hotProt, plugin) == null) {
				if (config.getBoolean("SendWarningMessages") == false) {
					return;
				}
				ActionBar.sendActionBar(p, Utils.chat(config.getString("messages.HotBiome")));
				return;
			}
		}

		// Toxic
		if (biomeName.contains("SWAMP") || biomeName.contains("MUSHROOM")) {
			String toxicProt = Utils.chat(moduleNames.get(8));
			if (ModuleInformation.getModule(p, toxicProt, plugin) == null) {
				if (config.getBoolean("SendWarningMessages") == false) {
					return;
				}
				ActionBar.sendActionBar(p, Utils.chat(config.getString("messages.ToxicBiome")));
				return;
			}
		}

		// Radiation
		if (biomeName.contains("END")) {
			String radiationProt = Utils.chat(moduleNames.get(9));
			if (ModuleInformation.getModule(p, radiationProt, plugin) == null) {
				if (config.getBoolean("SendWarningMessages") == false) {
					return;
				}
				ActionBar.sendActionBar(p, Utils.chat(config.getString("messages.RadiationBiome")));
				return;
			}
		}

		// Cold
		if (biomeName.contains("FROZEN") || biomeName.contains("COLD") || biomeName.contains("TAIGA")
				|| biomeName.contains("SNOWY") || biomeName.contains("ICE")) {
			String coldProt = Utils.chat(moduleNames.get(10));
			if (ModuleInformation.getModule(p, coldProt, plugin) == null) {
				if (config.getBoolean("SendWarningMessages") == false) {
					return;
				}
				ActionBar.sendActionBar(p, Utils.chat(config.getString("messages.ColdBiome")));
				return;
			}
		}
	}

}
