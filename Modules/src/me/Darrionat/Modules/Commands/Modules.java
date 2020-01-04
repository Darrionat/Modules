package me.Darrionat.Modules.Commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.Darrionat.Modules.Main;
import me.Darrionat.Modules.Files.FileManager;
import me.Darrionat.Modules.GUI.ModuleShop;
import me.Darrionat.Modules.Utils.Maps;
import me.Darrionat.Modules.Utils.Utils;

public class Modules implements CommandExecutor {

	private Main plugin;

	public Modules(Main plugin) {
		this.plugin = plugin;

		plugin.getCommand("moduli").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		FileConfiguration config = plugin.getConfig();
		if (!(sender instanceof Player)) {
			sender.sendMessage(Utils.chat(config.getString("messages.OnlyPlayers")));
			return true;
		}
		Player p = (Player) sender;
		if (args.length != 0) {
			// Bypass
			if (args[0].equalsIgnoreCase("bypassbiomes")) {
				String adminPerm = "modules.admin";
				if (!p.hasPermission(adminPerm)) {
					p.sendMessage(Utils.chat(config.getString("messages.NoPermission").replace("%perm%", adminPerm)));
					return true;
				}
				Maps maps = new Maps();
				List<String> biomeBypass = maps.getBiomeBypassList();
				if (biomeBypass.contains(p.getName())) {
					biomeBypass.remove(p.getName());
					p.sendMessage(Utils.chat(config.getString("messages.NoLongerBypassing")));
					return true;
				}
				p.sendMessage(Utils.chat(config.getString("messages.Bypassing")));
				biomeBypass.add(p.getName());
				return true;
			}
			if (args[0].equalsIgnoreCase("help")) {
				sender.sendMessage(Utils.chat("&a&lMODULES - USAGE"));
				sender.sendMessage(Utils.chat("  &7/" + label + " bypassbiomes"));
				sender.sendMessage(Utils.chat("  &7/" + label + " help"));
				sender.sendMessage(Utils.chat("  &7/" + label + " reload"));
				return true;
			}
			if (args[0].equalsIgnoreCase("reload")) {
				Bukkit.getScheduler().cancelTasks(plugin);
				plugin.reloadConfig();
				FileManager fileManager = new FileManager(plugin);
				fileManager.reloadDataFile("playerdata");
				fileManager.reloadDataFile("prices");
				plugin.startAllTimers();
				return true;
			}
			// If the player doesn't type any of the correct arguments
			sender.sendMessage(Utils.chat("&a&lMODULES v" + plugin.getDescription().getVersion()));
			sender.sendMessage(Utils.chat("  &7Author: Darrionat"));
			sender.sendMessage(Utils.chat("  &7Support: https://discord.gg/xNKrH5Z"));
			sender.sendMessage(Utils.chat("  &7/" + label + " help - &oFor additional information"));

		}
		String shopPerm = "modules.shop";
		if (!p.hasPermission(shopPerm)) {
			p.sendMessage(Utils.chat(config.getString("messages.NoPermission").replace("%perm%", shopPerm)));
			return true;
		}

		Utils.setupPlayer(p, plugin);
		p.openInventory(ModuleShop.GUI(p, plugin));
		return true;

	}

}