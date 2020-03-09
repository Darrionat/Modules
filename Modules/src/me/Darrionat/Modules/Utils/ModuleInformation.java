package me.Darrionat.Modules.Utils;

import java.util.HashMap;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import me.Darrionat.Modules.Main;
import me.Darrionat.Modules.Files.FileManager;
import net.md_5.bungee.api.ChatColor;

public class ModuleInformation {

	public static String getUpradeType(String moduleName, JavaPlugin plugin) {
		List<String> moduleNames = plugin.getConfig().getStringList("ModuleNames");
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

		HashMap<String, String> typeMap = new HashMap<String, String>();
		typeMap.put(health, "Health");
		typeMap.put(speed, "Speed");
		typeMap.put(waterBreathing, "WaterBreathing");
		typeMap.put(attackDmg, "AttackDmg");

		typeMap.put(slowFalling, "SlowFalling");
		typeMap.put(nightVision, "NightVision");
		typeMap.put(fireResistance, "FireResistance");

		typeMap.put(hotProt, "HotProt");
		typeMap.put(toxicProt, "ToxicProt");
		typeMap.put(radiationProt, "RadiationProt");
		typeMap.put(coldProt, "ColdProt");

		typeMap.put(spiderProt, "SpiderProt");
		typeMap.put(zombieProt, "ZombieProt");
		typeMap.put(skeletonProt, "SkeletonProt");
		typeMap.put(endermanProt, "EndermanProt");
		typeMap.put(phantomProt, "PhantomProt");
		typeMap.put(creeperProt, "CreeperProt");
		typeMap.put(pigmanProt, "PigmanProt");

		typeMap.put(hungerSlow, "HungerSlow");
		typeMap.put(luck, "Luck");
		typeMap.put(fireball, "Fireball");
		typeMap.put(elytraJump, "ElytraJump");

		String upgradeType = typeMap.get(moduleName);
		return upgradeType;
	}

	public static ItemStack getModule(Player p, String moduleName, JavaPlugin plugin) {
		for (ItemStack item : p.getInventory()) {
			if (item == null || item.getItemMeta() == null) {
				continue;
			}
			if (item.getItemMeta().getDisplayName() == null) {
				continue;
			}
			if (!item.getItemMeta().getDisplayName().equalsIgnoreCase(moduleName)) {
				continue;
			}

			// Bug: If a player changes their name to another player's past name on the
			// server.
			FileManager fileManager = new FileManager((Main) plugin);
			FileConfiguration playerdata = fileManager.getDataConfig("playerdata");
			String uuid = p.getUniqueId().toString();
			ConfigurationSection uuidSection = playerdata.getConfigurationSection(uuid);
			List<String> nameList = uuidSection.getStringList("Names");
			String itemPlayerName = ChatColor.stripColor(item.getItemMeta().getLore().get(1));
			if (!nameList.contains(itemPlayerName)) {
				return null;
			}

			return item;

		}
		return null;
	}

	public static int getPlayerModuleLevel(Player p, String moduleName, JavaPlugin plugin) {
		for (ItemStack item : p.getInventory()) {
			if (item == null || item.getItemMeta() == null) {
				continue;
			}
			if (item.getItemMeta().getDisplayName() == null) {
				continue;
			}
			if (!item.getItemMeta().getDisplayName().equalsIgnoreCase(moduleName)) {
				continue;
			}
			ConfigurationSection strings = plugin.getConfig().getConfigurationSection("Strings");
			int playerLevel = Integer.parseInt(
					(item.getItemMeta().getLore().get(0).replace(Utils.chat(strings.getString("level")), "")));
			return playerLevel;

		}

		return 0;
	}

	public static int getModulePrice(Player p, int playerLevel, JavaPlugin plugin, String moduleName) {
		FileManager fileManager = new FileManager((Main) plugin);
		FileConfiguration prices = fileManager.getDataConfig("prices");
		String upgradeType = getUpradeType(moduleName, plugin);

		int nextLvl = playerLevel + 1;
		int cost = prices.getInt(upgradeType + "." + String.valueOf(nextLvl));
		return cost;
	}

	public static int getModuleMaxLevel(String moduleName, JavaPlugin plugin) {
		String upgradeType = getUpradeType(moduleName, plugin);
		FileManager fileManager = new FileManager((Main) plugin);
		FileConfiguration prices = fileManager.getDataConfig("prices");
		int maxLevel = prices.getInt(upgradeType + ".MaxLvl");
		return maxLevel;
	}

}
