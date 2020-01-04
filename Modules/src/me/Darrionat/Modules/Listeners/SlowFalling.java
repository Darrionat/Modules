package me.Darrionat.Modules.Listeners;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import me.Darrionat.Modules.Main;
import me.Darrionat.Modules.Utils.ModuleInformation;
import me.Darrionat.Modules.Utils.Utils;

public class SlowFalling implements Listener {
	private Main plugin;

	public SlowFalling(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		ConfigurationSection config = plugin.getConfig();
		List<String> moduleNames = config.getStringList("ModuleNames");

		String slowFalling = Utils.chat(moduleNames.get(4));

		if (ModuleInformation.getModule(p, slowFalling, plugin) != null) {
			Vector velocity = p.getVelocity();
			if (velocity.getY() < -0.6) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 1 * 20, 0));
				return;
			}
		}

	}

}
