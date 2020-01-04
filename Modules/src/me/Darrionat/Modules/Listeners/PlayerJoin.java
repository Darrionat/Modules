package me.Darrionat.Modules.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.Darrionat.Modules.Main;
import me.Darrionat.Modules.Utils.Utils;

public class PlayerJoin implements Listener {
	private Main plugin;

	public PlayerJoin(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		Utils.setupPlayer(p, plugin);
		Utils.updateModulePlayerName(p, plugin);
	}

}
