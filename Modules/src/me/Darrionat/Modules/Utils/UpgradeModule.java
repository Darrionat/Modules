package me.Darrionat.Modules.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import me.Darrionat.Modules.Main;
import me.Darrionat.Modules.Files.FileManager;
import net.milkbowl.vault.economy.EconomyResponse;

public class UpgradeModule {

	public static void upgrade(Player p, JavaPlugin plugin, String upgradeType, String moduleName) {
		FileConfiguration config = plugin.getConfig();
		FileManager fileManager = new FileManager((Main) plugin);
		FileConfiguration prices = fileManager.getDataConfig("prices");
		ConfigurationSection strings = plugin.getConfig().getConfigurationSection("Strings");

		// If the module isn't in the inventory. Purchsae new one
		if (ModuleInformation.getModule(p, moduleName, plugin) == null) {
			int cost = prices.getInt(upgradeType + "." + String.valueOf(1));
			@SuppressWarnings("deprecation")
			EconomyResponse sell = Main.econ.withdrawPlayer(p.getName(), cost);
			if (!sell.transactionSuccess()) {
				p.sendMessage(Utils.chat(config.getString("messages.NotEnoughMoney")));
				return;
			}
			Maps maps = new Maps();
			HashMap<String, Material> moduleMaterialMap = maps.getModuleMaterialMap(plugin);

			ItemStack module = new ItemStack(moduleMaterialMap.get(upgradeType), 1);
			ItemMeta meta = module.getItemMeta();
			ArrayList<String> lore = new ArrayList<String>();
			meta.setDisplayName(moduleName);
			lore.add(Utils.chat(strings.getString("level") + "1"));
			lore.add(Utils.chat(strings.getString("playerName").replace("%player%", p.getName())));
			meta.setLore(lore);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			module.setItemMeta(meta);
			module.addUnsafeEnchantment(Enchantment.MENDING, 1);
			if (p.getInventory().firstEmpty() == -1) {
				p.getWorld().dropItem(p.getLocation(), module);
			} else {
				p.getInventory().addItem(module);
			}
			p.sendMessage(Utils.chat(config.getString("messages.BuyModule").replace("%price%", String.valueOf(cost))
					.replace("%type%", moduleName)));
			p.closeInventory();
			return;
		}

		// When they do have a module, upgrade it

		ItemStack item = ModuleInformation.getModule(p, moduleName, plugin);
		String itemPlayerName = ChatColor.stripColor(item.getItemMeta().getLore().get(1));
		FileConfiguration playerdata = fileManager.getDataConfig("playerdata");

		String uuid = p.getUniqueId().toString();
		if (!playerdata.getConfigurationSection(uuid).getStringList("Names").contains(itemPlayerName)) {
			p.sendMessage(Utils.chat(config.getString("messages.NotYourModule")));
			return;
		}
		int playerLevel = ModuleInformation.getPlayerModuleLevel(p, moduleName, plugin);
		int maxLevel = prices.getInt(upgradeType + ".MaxLvl");
		if (playerLevel >= maxLevel) {
			p.sendMessage(Utils.chat(config.getString("messages.MaxUpgrade")));
			p.closeInventory();
			return;
		}

		int nextLvl = playerLevel + 1;
		int cost = prices.getInt(upgradeType + "." + String.valueOf(nextLvl));
		@SuppressWarnings("deprecation")
		EconomyResponse sell = Main.econ.withdrawPlayer(p.getName(), cost);
		if (!sell.transactionSuccess()) {
			p.sendMessage(Utils.chat(config.getString("messages.NotEnoughMoney")));
			return;
		}

		ItemMeta meta = item.getItemMeta();
		List<String> lore = meta.getLore();
		lore.set(0, Utils.chat(strings.getString("level") + nextLvl));
		meta.setLore(lore);
		item.setItemMeta(meta);
		fileManager.saveConfigFile("playerdata", playerdata);

		p.sendMessage(Utils.chat(config.getString("messages.UpgradeModule").replace("%price%", String.valueOf(cost))
				.replace("%level%", String.valueOf(nextLvl)).replace("%type%", moduleName)));
		p.closeInventory();
		return;

	}

}
