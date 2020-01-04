package me.Darrionat.Modules.Listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import me.Darrionat.Modules.Main;
import me.Darrionat.Modules.Utils.Utils;
import net.md_5.bungee.api.ChatColor;

public class KeepModuleOnDeath implements Listener {
	private Main plugin;

	public KeepModuleOnDeath(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	public HashMap<String, List<ItemStack>> items = new HashMap<String, List<ItemStack>>();

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		FileConfiguration config = plugin.getConfig();
		if (!config.getBoolean("keepModulesOnDeath")) {
			return;
		}
		List<ItemStack> removeModules = new ArrayList<ItemStack>();
		for (ItemStack item : e.getDrops()) {
			if (item.getItemMeta() == null) {
				continue;
			}
			if (item.getItemMeta().getDisplayName() == null) {
				continue;
			}
			String itemName = item.getItemMeta().getDisplayName();
			List<String> moduleNames = config.getStringList("ModuleNames");
			for (String moduleName : moduleNames) {
				if (!itemName.equalsIgnoreCase(Utils.chat(moduleName))) {
					continue;
				}
				System.out.println(item.getItemMeta().getDisplayName());
				removeModules.add(item);
				break;
			}
			continue;
		}
		for (ItemStack module : removeModules) {
			e.getDrops().remove(module);
		}
		items.put(e.getEntity().getName(), removeModules);
	}

	@EventHandler()
	public void onRespawn(PlayerRespawnEvent e) {
		FileConfiguration config = plugin.getConfig();
		if (!config.getBoolean("keepModulesOnDeath")) {
			return;
		}
		Player p = e.getPlayer();
		if (items.containsKey(p.getName())) {
			for (ItemStack item : items.get(p.getName())) {
				String itemPlayerName = ChatColor.stripColor(item.getItemMeta().getLore().get(1));
				if (!itemPlayerName.equalsIgnoreCase(p.getName())) {
					continue;
				}
				e.getPlayer().getInventory().addItem(item);
			}
			items.remove(p.getName());
		}
	}

}
