package me.Darrionat.Modules.Utils;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import me.Darrionat.Modules.Main;
import me.Darrionat.Modules.Files.FileManager;

public class ElytraJump {

	private Main plugin;

	public ElytraJump(Main plugin) {
		this.plugin = plugin;
	}

	static HashMap<String, Long> cooldowns = new HashMap<String, Long>();

	public void elytraJump(Player p, int lvl) {
		FileManager fileManager = new FileManager(plugin);
		FileConfiguration prices = fileManager.getDataConfig("prices");
		if (p.getInventory().getChestplate() == null) {
			return;
		}
		if (p.getInventory().getChestplate().getType() != Material.ELYTRA) {
			p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.WearElytra")));
			return;
		}

		int cooldownTime = prices.getInt("ElytraJump.Cooldown"); // Get number of seconds from wherever you want
		if (cooldowns.containsKey(p.getName())) {
			long secondsLeft = ((cooldowns.get(p.getName()) / 1000) + cooldownTime)
					- (System.currentTimeMillis() / 1000);
			if (secondsLeft > 0) {
				p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.Cooldown").replace("%secondsLeft%",
						String.valueOf(secondsLeft))));
				return;
			}
		}
		if (!p.isOnGround()) {
			p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.NotOnGround")));
			return;
		}
		cooldowns.put(p.getName(), System.currentTimeMillis());

		double height = lvl * 5;
		double x = (height + 16) / 18.7;
		p.setVelocity(new Vector(0, x, 0));
	}

}
