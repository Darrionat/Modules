package me.Darrionat.Modules.Utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import me.Darrionat.Modules.Main;
import me.Darrionat.Modules.Files.FileManager;

public class Utils {

	public static String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

	public static void setupPlayer(Player p, JavaPlugin plugin) {
		FileManager fileManager = new FileManager((Main) plugin);
		FileConfiguration playerdata = fileManager.getDataConfig("playerdata");
		String uuid = p.getUniqueId().toString();

		if (playerdata.getConfigurationSection(uuid) == null) {
			playerdata.createSection(uuid);
			List<String> nameList = new ArrayList<String>();
			nameList.add(p.getName());
			playerdata.getConfigurationSection(uuid).set("Names", nameList);
			fileManager.saveConfigFile("playerdata", playerdata);
			return;
		}
		// Update player's username
		ConfigurationSection uuidSection = playerdata.getConfigurationSection(uuid);
		List<String> nameList = uuidSection.getStringList("Names");
		if (nameList.contains(p.getName())) {
			return;
		}
		nameList.add(p.getName());
		fileManager.saveConfigFile("playerdata", playerdata);
		return;
	}

	public static void updateModulePlayerName(Player p, JavaPlugin plugin) {
		FileConfiguration config = plugin.getConfig();
		for (String s : config.getStringList("ModuleNames")) {
			String moduleName = Utils.chat(s);
			if (ModuleInformation.getModule(p, moduleName, plugin) == null) {
				continue;
			}
			// PLayer has the module
			ItemStack module = ModuleInformation.getModule(p, moduleName, plugin);
			String modulePlayerName = ChatColor.stripColor(module.getItemMeta().getLore().get(1));
			if (modulePlayerName.equalsIgnoreCase(p.getName())) {
				continue;
			}
			List<String> moduleLore = module.getItemMeta().getLore();
			moduleLore.set(1, Utils.chat("&e" + p.getName()));
		}
	}

	/*
	 * public static String getUuid(String name) { String url =
	 * "https://api.mojang.com/users/profiles/minecraft/" + name; try {
	 * 
	 * @SuppressWarnings("deprecation") String UUIDJson =
	 * org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils.toString(new
	 * URL(url)); if (UUIDJson.isEmpty()) return "invalid name"; JSONObject
	 * UUIDObject = (JSONObject) JSONValue.parseWithException(UUIDJson); return
	 * UUIDObject.get("id").toString(); } catch (IOException | ParseException e) {
	 * e.printStackTrace(); }
	 * 
	 * return "error"; }
	 */

	public static ItemStack createItem(Inventory inv, Material material, int amount, int invSlot, String displayName,
			String... loreString) {
		ItemStack item;
		List<String> lore = new ArrayList<String>();

		item = new ItemStack(material, amount);

		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Utils.chat(displayName));
		for (String s : loreString) {
			lore.add(Utils.chat(s));
		}
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(invSlot - 1, item);
		return item;

	}

}