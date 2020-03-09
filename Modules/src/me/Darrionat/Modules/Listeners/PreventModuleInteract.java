package me.Darrionat.Modules.Listeners;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import me.Darrionat.Modules.Main;
import me.Darrionat.Modules.Utils.ElytraJump;
import me.Darrionat.Modules.Utils.ModuleInformation;
import me.Darrionat.Modules.Utils.ShootFireball;
import me.Darrionat.Modules.Utils.Utils;

public class PreventModuleInteract implements Listener {
	private Main plugin;

	public PreventModuleInteract(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getItem() == null) {
			return;
		}
		if (e.getMaterial() == null) {
			return;
		}
		if (e.getItem().getItemMeta() == null) {
			return;
		}
		if (e.getItem().getItemMeta().getDisplayName() == null) {
			return;
		}
		List<String> moduleNames = plugin.getConfig().getStringList("ModuleNames");
		for (String s : moduleNames) {
			s = Utils.chat(s);
			if (!s.equals(e.getItem().getItemMeta().getDisplayName())) {
				continue;
			}
			String fireball = Utils.chat(moduleNames.get(20));
			if (Utils.chat(s).equals(fireball)) {
				int currentLvl = ModuleInformation.getPlayerModuleLevel(p, fireball, plugin);
				ShootFireball shootFireball = new ShootFireball(plugin);
				shootFireball.shootFireball(p, currentLvl);
			}
			String elytraJump = Utils.chat(moduleNames.get(21));
			if (Utils.chat(s).equals(elytraJump)) {
				int currentLvl = ModuleInformation.getPlayerModuleLevel(p, elytraJump, plugin);
				ElytraJump jumpPlayer = new ElytraJump(plugin);
				jumpPlayer.elytraJump(p, currentLvl);
			}
			e.setCancelled(true);
			return;
		}
	}

}
