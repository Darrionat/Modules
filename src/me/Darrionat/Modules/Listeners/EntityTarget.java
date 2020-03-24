package me.Darrionat.Modules.Listeners;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

import me.Darrionat.Modules.Main;
import me.Darrionat.Modules.Utils.ModuleInformation;
import me.Darrionat.Modules.Utils.Utils;

public class EntityTarget implements Listener {
	private Main plugin;

	public EntityTarget(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onTarget(EntityTargetEvent e) {
		Entity entity = e.getEntity();
		if (!(e.getTarget() instanceof Player)) {
			return;
		}
		Player target = (Player) e.getTarget();

		FileConfiguration config = plugin.getConfig();
		List<String> moduleNames = config.getStringList("ModuleNames");
		String spiderProt = Utils.chat(moduleNames.get(11));
		String zombieProt = Utils.chat(moduleNames.get(12));
		String skeletonProt = Utils.chat(moduleNames.get(13));
		String endermanProt = Utils.chat(moduleNames.get(14));
		String phantomProt = Utils.chat(moduleNames.get(15));
		String creeperProt = Utils.chat(moduleNames.get(16));
		String pigmanProt = Utils.chat(moduleNames.get(17));

		if (ModuleInformation.getModule(target, spiderProt, plugin) != null) {
			if (entity instanceof Spider) {
				e.setCancelled(true);
				return;
			}
		}
		if (ModuleInformation.getModule(target, zombieProt, plugin) != null) {
			if (entity instanceof Zombie || entity instanceof Giant) {
				e.setCancelled(true);
				return;
			}
		}
		if (ModuleInformation.getModule(target, skeletonProt, plugin) != null) {
			if (entity instanceof Skeleton) {
				e.setCancelled(true);
				return;
			}
		}
		if (ModuleInformation.getModule(target, endermanProt, plugin) != null) {
			if (entity instanceof Enderman) {
				e.setCancelled(true);
				return;
			}
		}
		if (ModuleInformation.getModule(target, phantomProt, plugin) != null) {
			if (entity instanceof Phantom) {
				e.setCancelled(true);
				return;
			}
		}
		if (ModuleInformation.getModule(target, creeperProt, plugin) != null) {
			if (entity instanceof Creeper) {
				e.setCancelled(true);
				return;
			}
		}
		if (ModuleInformation.getModule(target, pigmanProt, plugin) != null) {
			if (entity instanceof PigZombie) {
				e.setCancelled(true);
				return;
			}
		}
	}

}
