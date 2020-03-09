package me.Darrionat.Modules;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.Darrionat.Modules.Commands.Modules;
import me.Darrionat.Modules.Files.FileManager;
import me.Darrionat.Modules.GUI.ModuleShop;
import me.Darrionat.Modules.GUI.ModuleShopPage2;
import me.Darrionat.Modules.Listeners.AttackDamageModule;
import me.Darrionat.Modules.Listeners.EntityTarget;
import me.Darrionat.Modules.Listeners.HungerSlowModule;
import me.Darrionat.Modules.Listeners.InventoryClick;
import me.Darrionat.Modules.Listeners.KeepModuleOnDeath;
import me.Darrionat.Modules.Listeners.MoveIntoBiome;
import me.Darrionat.Modules.Listeners.PlayerJoin;
import me.Darrionat.Modules.Listeners.PreventModuleInteract;
import me.Darrionat.Modules.Listeners.SlowFalling;
import me.Darrionat.Modules.Listeners.WaterBreathingModule;
import me.Darrionat.Modules.Timers.Cold;
import me.Darrionat.Modules.Timers.FireResistance;
import me.Darrionat.Modules.Timers.Health;
import me.Darrionat.Modules.Timers.Hot;
import me.Darrionat.Modules.Timers.Luck;
import me.Darrionat.Modules.Timers.NightVision;
import me.Darrionat.Modules.Timers.Radiation;
import me.Darrionat.Modules.Timers.Speed;
import me.Darrionat.Modules.Timers.Toxic;
import me.Darrionat.Modules.Utils.ActionBar;
import me.Darrionat.Modules.Utils.ElytraJump;
import me.Darrionat.Modules.Utils.ShootFireball;
import me.Darrionat.Modules.Utils.UpgradeModule;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {
	public static Economy econ = null;

	public void onEnable() {

		// Classes
		new Modules(this);
		new PlayerJoin(this);
		new InventoryClick(this);
		new KeepModuleOnDeath(this);
		new WaterBreathingModule(this);
		new AttackDamageModule(this);
		new SlowFalling(this);
		new PreventModuleInteract(this);
		new MoveIntoBiome(this);
		new UpgradeModule();
		new EntityTarget(this);
		new HungerSlowModule(this);
		new ShootFireball(this);
		new ElytraJump(this);
		ModuleShop.initialize(this);
		ModuleShopPage2.initialize(this);

		// Api and Dependencies
		ActionBar actionbarapi = new ActionBar(this);
		actionbarapi.enableActionBar();

		// Timers
		startAllTimers();

		// Configs
		FileManager filemanager = new FileManager(this);
		filemanager.setup("playerdata");
		saveDefaultConfig();
		// Update filemanager to not include the .yml
		this.saveResource("prices.yml", false);

		// Economy
		if (!setupEconomy()) {
			getLogger().severe(
					String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
	}

	public void onDisable() {

	}

	public void startAllTimers() {
		// Timers
		Health healthTimer = new Health(this);
		healthTimer.startTimer();
		Speed speedTimer = new Speed(this);
		speedTimer.startTimer();
		NightVision nvTimer = new NightVision(this);
		nvTimer.startTimer();
		FireResistance fireResTimer = new FireResistance(this);
		fireResTimer.startTimer();
		Luck luckTimer = new Luck(this);
		luckTimer.startTimer();

		Hot hotTimer = new Hot(this);
		hotTimer.startSavannahTimer();
		hotTimer.startDesertTimer();
		hotTimer.startMesaTimer();
		hotTimer.startNetherTimer();
		Toxic toxicTimer = new Toxic(this);
		toxicTimer.startMushroomTimer();
		toxicTimer.startSwampTimer();
		Radiation radiationTimer = new Radiation(this);
		radiationTimer.startEndTimer();
		Cold coldTimer = new Cold(this);
		coldTimer.startColdTimer();
		coldTimer.startFrozenTimer();
		coldTimer.startIcePlainsTimer();
		coldTimer.startSnowyTimer();
		coldTimer.startTaigaTimer();

	}

	// Vault Setup
	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}
}
