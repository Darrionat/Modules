package me.Darrionat.Modules.GUI;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import me.Darrionat.Modules.Main;
import me.Darrionat.Modules.Files.FileManager;
import me.Darrionat.Modules.Utils.Maps;
import me.Darrionat.Modules.Utils.ModuleInformation;
import me.Darrionat.Modules.Utils.UpgradeModule;
import me.Darrionat.Modules.Utils.Utils;

public class ModuleShopPage2 {

	public static Inventory inv;
	public static String inventory_name;
	public static int inv_rows = 5;
	public static int size = inv_rows * 9;

	public static void initialize(JavaPlugin plugin) {
		inventory_name = Utils.chat(plugin.getConfig().getString("GUINamePg2"));

		inv = Bukkit.createInventory(null, size);
	}

	public static Inventory GUI(Player p, JavaPlugin plugin) {
		Inventory toReturn = Bukkit.createInventory(null, size, inventory_name);
		// Background glass
		for (int i = 1; i <= size; i++) {
			Utils.createItem(inv, Material.BLACK_STAINED_GLASS_PANE, 1, i, " ");
		}
		FileConfiguration config = plugin.getConfig();
		ConfigurationSection strings = config.getConfigurationSection("Strings");
		List<String> moduleNames = config.getStringList("ModuleNames");

		// String coldProt = Utils.chat(moduleNames.get(10));
		String spiderProt = Utils.chat(moduleNames.get(11));
		String zombieProt = Utils.chat(moduleNames.get(12));
		String skeletonProt = Utils.chat(moduleNames.get(13));
		String endermanProt = Utils.chat(moduleNames.get(14));
		String phantomProt = Utils.chat(moduleNames.get(15));
		String creeperProt = Utils.chat(moduleNames.get(16));
		String pigmanProt = Utils.chat(moduleNames.get(17));

		String hungerSlow = Utils.chat(moduleNames.get(18));
		String luck = Utils.chat(moduleNames.get(19));
		String fireball = Utils.chat(moduleNames.get(20));
		String elytraJump = Utils.chat(moduleNames.get(21));

		String upgradeable = strings.getString("upgradeable");
		// String nonUpgradeable = strings.getString("nonUpgradeable");
		String mobProtector = strings.getString("mobProtector");

		for (String moduleName : moduleNames) {
			moduleName = Utils.chat(moduleName);
			int playerLvl = ModuleInformation.getPlayerModuleLevel(p, moduleName, plugin);
			int price = ModuleInformation.getModulePrice(p, playerLvl, plugin, moduleName);

			String priceInfo = "";
			if (playerLvl >= ModuleInformation.getModuleMaxLevel(moduleName, plugin)) {
				priceInfo = Utils.chat(strings.getString("MaxedModule"));
			} else {
				priceInfo = Utils.chat(strings.getString("priceInfo").replace("%price%", String.valueOf(price)));
			}

			String currentLvl = Utils
					.chat(strings.getString("currentLevel").replace("%playerLvl%", String.valueOf(playerLvl))) + " ";
			// Non upgradeable
			Maps maps = new Maps();
			HashMap<String, Material> moduleMaterialMap = maps.getModuleMaterialMap(plugin);
			String upgradeType = ModuleInformation.getUpradeType(moduleName, plugin);
			Material moduleMaterial = moduleMaterialMap.get(upgradeType);
			if (moduleName.equalsIgnoreCase(spiderProt)) {
				Utils.createItem(inv, moduleMaterial, 1, 11, moduleName, mobProtector,
						currentLvl + Utils.chat(strings.getString("spiderProtDesc")), priceInfo);
			}
			if (moduleName.equalsIgnoreCase(zombieProt)) {
				Utils.createItem(inv, moduleMaterial, 1, 13, moduleName, mobProtector,
						currentLvl + Utils.chat(strings.getString("zombieProtDesc")), priceInfo);
			}
			if (moduleName.equalsIgnoreCase(skeletonProt)) {
				Utils.createItem(inv, moduleMaterial, 1, 15, moduleName, mobProtector,
						currentLvl + Utils.chat(strings.getString("skeletonProtDesc")), priceInfo);
			}
			if (moduleName.equalsIgnoreCase(endermanProt)) {
				Utils.createItem(inv, moduleMaterial, 1, 17, moduleName, mobProtector,
						currentLvl + Utils.chat(strings.getString("endermanProtDesc")), priceInfo);
			}
			if (moduleName.equalsIgnoreCase(phantomProt)) {
				Utils.createItem(inv, moduleMaterial, 1, 21, moduleName, mobProtector,
						currentLvl + Utils.chat(strings.getString("phantomProtDesc")), priceInfo);
			}
			if (moduleName.equalsIgnoreCase(creeperProt)) {
				Utils.createItem(inv, moduleMaterial, 1, 23, moduleName, mobProtector,
						currentLvl + Utils.chat(strings.getString("creeperProtDesc")), priceInfo);
			}
			if (moduleName.equalsIgnoreCase(pigmanProt)) {
				Utils.createItem(inv, moduleMaterial, 1, 25, moduleName, mobProtector,
						currentLvl + Utils.chat(strings.getString("pigmanProtDesc")), priceInfo);
			}
			if (moduleName.equalsIgnoreCase(hungerSlow)) {
				FileManager fileManager = new FileManager((Main) plugin);
				FileConfiguration prices = fileManager.getDataConfig("prices");
				int percent = playerLvl * prices.getInt("HungerSlow.UpgradePercent");
				Utils.createItem(
						inv, moduleMaterial, 1, 29, moduleName, upgradeable, currentLvl + Utils.chat(strings
								.getString("hungerSlowDesc").replace("percent", String.valueOf(percent * playerLvl))),
						priceInfo);
			}
			if (moduleName.equalsIgnoreCase(luck)) {
				Utils.createItem(inv, moduleMaterial, 1, 31, moduleName, upgradeable,
						currentLvl + Utils.chat(strings.getString("luckDesc")), priceInfo);
			}
			if (moduleName.equalsIgnoreCase(fireball)) {
				Utils.createItem(inv, moduleMaterial, 1, 33, moduleName, upgradeable,
						currentLvl + Utils.chat(strings.getString("fireballDesc")), priceInfo);
			}
			if (moduleName.equalsIgnoreCase(elytraJump)) {
				Utils.createItem(inv, moduleMaterial, 1, 35, moduleName, upgradeable,
						currentLvl + Utils.chat(
								strings.getString("elytraJumpDesc").replace("%height%", String.valueOf(5 * playerLvl))),
						priceInfo);
			}
		}
		Utils.createItem(inv, Material.RED_STAINED_GLASS_PANE, 1, 41, Utils.chat(config.getString("CloseMenu")));
		Utils.createItem(inv, Material.ARROW, 1, 40, Utils.chat(strings.getString("goBackPage")));

		toReturn.setContents(inv.getContents());
		for (int i = 10; i <= 34; i++) {
			ItemStack item = toReturn.getItem(i);
			if (item.getType() == Material.BLACK_STAINED_GLASS_PANE) {
				continue;
			}
			ItemMeta meta = item.getItemMeta();
			if (!meta.getLore().get(2).equalsIgnoreCase(Utils.chat(strings.getString("MaxedModule")))) {
				continue;
			}
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			item.setItemMeta(meta);
			toReturn.getItem(i).addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
		}
		return toReturn;
	}

	public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv, JavaPlugin plugin) {
		if (clicked.getItemMeta().getDisplayName() == null) {
			return;
		}
		FileConfiguration config = plugin.getConfig();
		List<String> moduleNames = config.getStringList("ModuleNames");
		String spiderProt = Utils.chat(moduleNames.get(11));
		String zombieProt = Utils.chat(moduleNames.get(12));
		String skeletonProt = Utils.chat(moduleNames.get(13));
		String endermanProt = Utils.chat(moduleNames.get(14));
		String phantomProt = Utils.chat(moduleNames.get(15));
		String creeperProt = Utils.chat(moduleNames.get(16));
		String pigmanProt = Utils.chat(moduleNames.get(17));

		String hungerSlow = Utils.chat(moduleNames.get(18));
		String luck = Utils.chat(moduleNames.get(19));
		String fireball = Utils.chat(moduleNames.get(20));
		String elytraJump = Utils.chat(moduleNames.get(21));

		if (compareNames(clicked, spiderProt) && slot == 11) {
			UpgradeModule.upgrade(p, plugin, "SpiderProt", spiderProt);
		}
		if (compareNames(clicked, zombieProt) && slot == 13) {
			UpgradeModule.upgrade(p, plugin, "ZombieProt", zombieProt);
			return;
		}
		if (compareNames(clicked, skeletonProt) && slot == 15) {
			UpgradeModule.upgrade(p, plugin, "SkeletonProt", skeletonProt);
			return;
		}
		if (compareNames(clicked, endermanProt) && slot == 17) {
			UpgradeModule.upgrade(p, plugin, "EndermanProt", endermanProt);
			return;
		}
		// Non-upgradeable
		if (compareNames(clicked, phantomProt) && slot == 21) {
			UpgradeModule.upgrade(p, plugin, "PhantomProt", phantomProt);
			return;
		}
		if (compareNames(clicked, creeperProt) && slot == 23) {
			UpgradeModule.upgrade(p, plugin, "CreeperProt", creeperProt);
			return;
		}
		if (compareNames(clicked, pigmanProt) && slot == 25) {
			UpgradeModule.upgrade(p, plugin, "PigmanProt", pigmanProt);
			return;
		}
		if (compareNames(clicked, hungerSlow) && slot == 29) {
			UpgradeModule.upgrade(p, plugin, "HungerSlow", hungerSlow);
			return;
		}
		if (compareNames(clicked, luck) && slot == 31) {
			UpgradeModule.upgrade(p, plugin, "Luck", luck);
			return;
		}
		if (compareNames(clicked, fireball) && slot == 33) {
			UpgradeModule.upgrade(p, plugin, "Fireball", fireball);
			return;
		}
		if (compareNames(clicked, elytraJump) && slot == 35) {
			UpgradeModule.upgrade(p, plugin, "ElytraJump", elytraJump);
			return;
		}
		String closeMenu = Utils.chat(config.getString("CloseMenu"));
		String goBackPage = Utils.chat(config.getString("Strings.goBackPage"));

		if (compareNames(clicked, closeMenu)) {
			p.closeInventory();
			return;
		}
		if (compareNames(clicked, goBackPage)) {
			p.openInventory(ModuleShop.GUI(p, plugin));
			return;
		}
	}

	public static boolean compareNames(ItemStack item, String s) {
		if (ChatColor.stripColor(item.getItemMeta().getDisplayName()).equalsIgnoreCase(ChatColor.stripColor(s))) {
			return true;
		}
		return false;
	}

}
