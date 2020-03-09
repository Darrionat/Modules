package me.Darrionat.Modules.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public class Maps {

	static HashMap<String, Material> moduleMaterial = new HashMap<String, Material>();

	public HashMap<String, Material> getModuleMaterialMap(JavaPlugin plugin) {
		List<String> materialList = plugin.getConfig().getStringList("MaterialList");
		moduleMaterial.put("Health", Material.getMaterial(materialList.get(0)));
		moduleMaterial.put("Speed", Material.getMaterial(materialList.get(1)));
		moduleMaterial.put("WaterBreathing", Material.getMaterial(materialList.get(2)));
		moduleMaterial.put("AttackDmg", Material.getMaterial(materialList.get(3)));
		moduleMaterial.put("SlowFalling", Material.getMaterial(materialList.get(4)));
		moduleMaterial.put("NightVision", Material.getMaterial(materialList.get(5)));
		moduleMaterial.put("FireResistance", Material.getMaterial(materialList.get(6)));

		moduleMaterial.put("HotProt", Material.getMaterial(materialList.get(7)));
		moduleMaterial.put("ToxicProt", Material.getMaterial(materialList.get(8)));
		moduleMaterial.put("RadiationProt", Material.getMaterial(materialList.get(9)));
		moduleMaterial.put("ColdProt", Material.getMaterial(materialList.get(10)));

		moduleMaterial.put("SpiderProt", Material.getMaterial(materialList.get(11)));
		moduleMaterial.put("ZombieProt", Material.getMaterial(materialList.get(12)));
		moduleMaterial.put("SkeletonProt", Material.getMaterial(materialList.get(13)));
		moduleMaterial.put("EndermanProt", Material.getMaterial(materialList.get(14)));
		moduleMaterial.put("PhantomProt", Material.getMaterial(materialList.get(15)));
		moduleMaterial.put("CreeperProt", Material.getMaterial(materialList.get(16)));
		moduleMaterial.put("PigmanProt", Material.getMaterial(materialList.get(17)));

		moduleMaterial.put("HungerSlow", Material.getMaterial(materialList.get(18)));
		moduleMaterial.put("Luck", Material.getMaterial(materialList.get(19)));
		moduleMaterial.put("Fireball", Material.getMaterial(materialList.get(20)));
		moduleMaterial.put("ElytraJump", Material.getMaterial(materialList.get(21)));
		return moduleMaterial;
	}

	static List<String> biomeBypass = new ArrayList<String>();

	public List<String> getBiomeBypassList() {
		return biomeBypass;
	}
}
