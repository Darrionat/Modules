package me.Darrionat.Modules.GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
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

import me.Darrionat.Modules.Utils.Maps;
import me.Darrionat.Modules.Utils.ModuleInformation;
import me.Darrionat.Modules.Utils.UpgradeModule;
import me.Darrionat.Modules.Utils.Utils;

public class ModuleShop {

	public static Inventory inv;
	public static String inventory_name;
	public static int inv_rows = 5;
	public static int size = inv_rows * 9;

	public static void initialize(JavaPlugin plugin) {
		inventory_name = Utils.chat(plugin.getConfig().getString("GUIName"));

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

		// Upgradeable
		List<String> moduleNames = config.getStringList("ModuleNames");
		String health = Utils.chat(moduleNames.get(0));
		String speed = Utils.chat(moduleNames.get(1));
		String waterBreathing = Utils.chat(moduleNames.get(2));
		String attackDmg = Utils.chat(moduleNames.get(3));

		String slowFalling = Utils.chat(moduleNames.get(4));
		String nightVision = Utils.chat(moduleNames.get(5));
		String fireResistance = Utils.chat(moduleNames.get(6));

		String hotProt = Utils.chat(moduleNames.get(7));
		String toxicProt = Utils.chat(moduleNames.get(8));
		String radiationProt = Utils.chat(moduleNames.get(9));
		String coldProt = Utils.chat(moduleNames.get(10));

		String upgradeable = strings.getString("upgradeable");
		String nonUpgradeable = strings.getString("nonUpgradeable");
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
			// Upgradeable
			if (moduleName.equalsIgnoreCase(health)) {
				Utils.createItem(inv, Material.RED_DYE, 1, 11, health, upgradeable,
						currentLvl + Utils.chat(
								strings.getString("healthDesc").replace("%playerLvl%", String.valueOf(playerLvl))),
						priceInfo);
				continue;
			}
			Maps maps = new Maps();
			HashMap<String, Material> moduleMaterialMap = maps.getModuleMaterialMap(plugin);
			String upgradeType = ModuleInformation.getUpradeType(moduleName, plugin);
			Material moduleMaterial = moduleMaterialMap.get(upgradeType);
			if (moduleName.equalsIgnoreCase(speed)) {
				int percent = playerLvl * 10;
				Utils.createItem(inv, moduleMaterial, 1, 13, moduleName, upgradeable,
						currentLvl + Utils
								.chat(strings.getString("speedDesc").replace("%percent%", String.valueOf(percent))),
						priceInfo);
				continue;
			}
			if (moduleName.equalsIgnoreCase(waterBreathing)) {
				int duration = 30 * playerLvl;
				Utils.createItem(
						inv, moduleMaterial, 1, 15, moduleName, upgradeable, currentLvl + Utils.chat(strings
								.getString("waterBreathingDesc").replace("%duration%", String.valueOf(duration))),
						priceInfo);
				continue;
			}
			if (moduleName.equalsIgnoreCase(attackDmg)) {
				List<Integer> attackLvls = new ArrayList<Integer>();
				attackLvls.add(0);
				attackLvls.add(2);
				attackLvls.add(5);
				attackLvls.add(10);
				attackLvls.add(14);
				attackLvls.add(17);
				int percent = attackLvls.get(playerLvl);
				Utils.createItem(inv, moduleMaterial, 1, 17, moduleName, upgradeable,
						currentLvl + Utils
								.chat(strings.getString("attackDmgDesc").replace("%percent%", String.valueOf(percent))),
						priceInfo);
				continue;
			}

			// Non upgradeable
			if (moduleName.equalsIgnoreCase(slowFalling)) {
				Utils.createItem(inv, moduleMaterial, 1, 21, moduleName, nonUpgradeable,
						currentLvl + Utils.chat(strings.getString("slowFallingDesc")), priceInfo);
			}
			if (moduleName.equalsIgnoreCase(nightVision)) {
				Utils.createItem(inv, moduleMaterial, 1, 23, moduleName, nonUpgradeable,
						currentLvl + Utils.chat(strings.getString("nightVisionDesc")), priceInfo);
			}
			if (moduleName.equalsIgnoreCase(fireResistance)) {
				Utils.createItem(inv, moduleMaterial, 1, 25, moduleName, nonUpgradeable,
						currentLvl + Utils.chat(strings.getString("fireResistanceDesc")), priceInfo);
			}

			// Biome Immunities
			String protector = "&7[&6Protector&7] &6Module";
			if (moduleName.equalsIgnoreCase(hotProt)) {
				Utils.createItem(inv, moduleMaterial, 1, 29, moduleName, protector,
						currentLvl + Utils.chat(strings.getString("hotProtDesc")), priceInfo);
			}
			if (moduleName.equalsIgnoreCase(toxicProt)) {
				Utils.createItem(inv, moduleMaterial, 1, 31, moduleName, protector,
						currentLvl + Utils.chat(strings.getString("toxicProtDesc")), priceInfo);
			}
			if (moduleName.equalsIgnoreCase(radiationProt)) {
				Utils.createItem(inv, moduleMaterial, 1, 33, moduleName, protector,
						currentLvl + Utils.chat(strings.getString("radiationProtDesc")), priceInfo);
			}
			if (moduleName.equalsIgnoreCase(coldProt)) {
				Utils.createItem(inv, moduleMaterial, 1, 35, moduleName, protector,
						currentLvl + Utils.chat(strings.getString("coldProtDesc")), priceInfo);
			}
		}

		// Close Menu and About Sign
		Utils.createItem(inv, Material.RED_STAINED_GLASS_PANE, 1, 41, Utils.chat(config.getString("CloseMenu")));
		Utils.createItem(inv, Material.ARROW, 1, 42, Utils.chat(strings.getString("nextPage")));

		/*
		 * if (config.getBoolean("InformationBookInGUI")) { Utils.createItem(inv,
		 * Material.OAK_SIGN, 1, 42, Utils.chat(config.getString("Information")), ""); }
		 */
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
		String health = Utils.chat(moduleNames.get(0));
		String speed = Utils.chat(moduleNames.get(1));
		String waterBreathing = Utils.chat(moduleNames.get(2));
		String attackDmg = Utils.chat(moduleNames.get(3));

		String slowFalling = Utils.chat(moduleNames.get(4));
		String nightVision = Utils.chat(moduleNames.get(5));
		String fireResistance = Utils.chat(moduleNames.get(6));

		String hotProt = Utils.chat(moduleNames.get(7));
		String toxicProt = Utils.chat(moduleNames.get(8));
		String radiationProt = Utils.chat(moduleNames.get(9));
		String coldProt = Utils.chat(moduleNames.get(10));

		// Slot check so the player isn't clicking it in their inventory
		if (compareNames(clicked, health) && slot == 11) {
			UpgradeModule.upgrade(p, plugin, "Health", health);
		}
		if (compareNames(clicked, speed) && slot == 13) {
			UpgradeModule.upgrade(p, plugin, "Speed", speed);
			return;
		}
		if (compareNames(clicked, waterBreathing) && slot == 15) {
			UpgradeModule.upgrade(p, plugin, "WaterBreathing", waterBreathing);
			return;
		}
		if (compareNames(clicked, attackDmg) && slot == 17) {
			UpgradeModule.upgrade(p, plugin, "AttackDmg", attackDmg);
			return;
		}
		// Non-upgradeable
		if (compareNames(clicked, slowFalling) && slot == 21) {
			UpgradeModule.upgrade(p, plugin, "SlowFalling", slowFalling);
			return;
		}
		if (compareNames(clicked, nightVision) && slot == 23) {
			UpgradeModule.upgrade(p, plugin, "NightVision", nightVision);
			return;
		}
		if (compareNames(clicked, fireResistance) && slot == 25) {
			UpgradeModule.upgrade(p, plugin, "FireResistance", fireResistance);
			return;
		}
		// Biome Protectors
		if (compareNames(clicked, hotProt) && slot == 29) {
			UpgradeModule.upgrade(p, plugin, "HotProt", hotProt);
			return;
		}
		if (compareNames(clicked, toxicProt) && slot == 31) {
			UpgradeModule.upgrade(p, plugin, "ToxicProt", toxicProt);
			return;
		}
		if (compareNames(clicked, radiationProt) && slot == 33) {
			UpgradeModule.upgrade(p, plugin, "RadiationProt", radiationProt);
			return;
		}
		if (compareNames(clicked, coldProt) && slot == 35) {
			UpgradeModule.upgrade(p, plugin, "ColdProt", coldProt);
			return;
		}

		String closeMenu = Utils.chat(config.getString("CloseMenu"));
		String nextPage = Utils.chat(config.getString("Strings.nextPage"));
		// String information = Utils.chat(config.getString("Information"));

		if (compareNames(clicked, closeMenu)) {
			p.closeInventory();
			return;
		}
		if (compareNames(clicked, nextPage)) {
			p.openInventory(ModuleShopPage2.GUI(p, plugin));
			return;
		}
		/*
		 * if (compareNames(clicked, information)) { p.closeInventory(); ItemStack book
		 * = new ItemStack(Material.WRITTEN_BOOK); BookMeta bookmeta = (BookMeta)
		 * book.getItemMeta(); // Each page is a max of 256 characters. String nl =
		 * "\n"; bookmeta.addPage("&a&lMODULES"); bookmeta.addPage("");
		 * book.setItemMeta(bookmeta); p.openBook(book); return; }
		 */
	}

	public static boolean compareNames(ItemStack item, String s) {
		if (item.getItemMeta().getDisplayName().equalsIgnoreCase(s)) {
			return true;
		}
		return false;
	}

}
