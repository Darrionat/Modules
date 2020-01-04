package me.Darrionat.Modules.Listeners;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import me.Darrionat.Modules.Main;
import me.Darrionat.Modules.Utils.Utils;

public class PreventProjectileModuleThrow implements Listener {
	private Main plugin;

	public PreventProjectileModuleThrow(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {

		if (e.getMaterial() == null) {

		}
		if (e.getMaterial() != Material.ENDER_PEARL && e.getMaterial() != Material.SNOWBALL) {
			return;
		}
		List<String> moduleNameList = plugin.getConfig().getStringList("ModuleNames");
		if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat(moduleNameList.get(9)))) {
			e.setCancelled(true);
			return;
		}
		if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat(moduleNameList.get(10)))) {
			e.setCancelled(true);
		}

	}

}
