package me.Darrionat.Modules.Utils;

import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;

import me.Darrionat.Modules.Main;
import me.Darrionat.Modules.Files.FileManager;

public class ShootFireball {

	private Main plugin;

	public ShootFireball(Main plugin) {
		this.plugin = plugin;
	}

	static HashMap<String, Long> cooldowns = new HashMap<String, Long>();

	public void shootFireball(Player p, int lvl) {
		FileManager fileManager = new FileManager(plugin);
		FileConfiguration prices = fileManager.getDataConfig("prices");
		int cooldownTime = prices.getInt("Fireball.Cooldown"); // Get number of seconds from wherever you want
		if (cooldowns.containsKey(p.getName())) {
			long secondsLeft = ((cooldowns.get(p.getName()) / 1000) + cooldownTime)
					- (System.currentTimeMillis() / 1000);
			if (secondsLeft > 0) {
				p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.Cooldown").replace("%secondsLeft%",
						String.valueOf(secondsLeft))));
				return;
			}
		}
		cooldowns.put(p.getName(), System.currentTimeMillis());
		Fireball f = p.launchProjectile(Fireball.class);
		f.setIsIncendiary(true);
		f.setYield((float) (lvl * 0.5));
	}

}
