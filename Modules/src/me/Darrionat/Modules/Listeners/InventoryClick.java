package me.Darrionat.Modules.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.Darrionat.Modules.Main;
import me.Darrionat.Modules.GUI.ModuleShop;

public class InventoryClick implements Listener {
	private Main plugin;

	public InventoryClick(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		String title = e.getView().getTitle();
		String promotionsGUI = ModuleShop.inventory_name;

		Player p = (Player) e.getWhoClicked();
		int slot = e.getSlot();
		ItemStack item = e.getCurrentItem();
		Inventory inv = e.getInventory();

		if (title.equals(promotionsGUI)) {
			if (item == null) {
				return;
			}
			e.setCancelled(true);
			ModuleShop.clicked(p, slot + 1, item, inv, plugin);

		}
	}

}
