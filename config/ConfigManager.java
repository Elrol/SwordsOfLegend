package com.github.elrol.SwordsOfLegend.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.github.elrol.SwordsOfLegend.libs.ModInfo;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigManager {
	
	private static Configuration config = null;
	
	public static final String CATEGORY_MURAMASA = "muramasa";
	
	public static int soulsPerDamage;
	public static int soulsPerSpeed;
	public static int maxSoulDamage;
	public static double maxSoulSpeed;
	
	
	public static void preInit() {
		File configFile = new File(Loader.instance().getConfigDir(), "SwordsOfLegend/SwordsOfLegend.cfg");
		config = new Configuration(configFile);
		syncFromFiles();
	}

	public static Configuration getConfig() {
		return config;
	}

	public static void clientPreInit() {
		MinecraftForge.EVENT_BUS.register(new ConfigEventHandler());
	}
	
	public static void syncFromFiles() {
		syncConfig(true, true);
	}
	
	public static void syncFromGui() {
		syncConfig(false, true);
	}
	
	public static void syncFromFeilds() {
		syncConfig(false, false);
	}
	
	private static void syncConfig(boolean loadFromConfigFile, boolean readFieldsFromConfig) {
		if(loadFromConfigFile)
			config.load();
		
		//Muramasa Category
		List<String> propertyOrderMuramasa = new ArrayList<String>();
		
		Property propertySoulsPerDamage = config.get(CATEGORY_MURAMASA, "SoulsPerDamage", 20);
			propertySoulsPerDamage.setLanguageKey("gui.config.muramasa.souls_per_damage.name");
			propertySoulsPerDamage.setComment(I18n.format("gui.config.muramasa.souls_per_damage.comment"));
			propertySoulsPerDamage.setMinValue(1);
			propertySoulsPerDamage.setMaxValue(100000);
			propertyOrderMuramasa.add(propertySoulsPerDamage.getName());
			
		Property propertySoulsPerSpeed = config.get(CATEGORY_MURAMASA, "SoulsPerSpeed", 200);
			propertySoulsPerSpeed.setLanguageKey("gui.config.muramasa.souls_per_speed.name");
			propertySoulsPerSpeed.setComment(I18n.format("gui.config.muramasa.souls_per_speed.comment"));
			propertySoulsPerSpeed.setMinValue(1);
			propertySoulsPerSpeed.setMaxValue(100000);
			propertyOrderMuramasa.add(propertySoulsPerSpeed.getName());
			
		Property propertyMaxSoulDamage = config.get(CATEGORY_MURAMASA, "MaxSoulDamage", 100);
			propertyMaxSoulDamage.setLanguageKey("gui.config.muramasa.max_soul_damage.name");
			propertyMaxSoulDamage.setComment(I18n.format("gui.config.muramasa.max_soul_damage.comment"));
			propertyMaxSoulDamage.setMinValue(10);
			propertyMaxSoulDamage.setMaxListLength(Integer.MAX_VALUE);
			propertyOrderMuramasa.add(propertyMaxSoulDamage.getName());
		
		Property propertyMaxSoulSpeed = config.get(CATEGORY_MURAMASA, "MaxSoulSpeed", 0.5D);
			propertyMaxSoulSpeed.setLanguageKey("gui.config.muramasa.max_soul_speed.name");
			propertyMaxSoulSpeed.setComment(I18n.format("gui.config.muramasa.max_soul_speed.comment"));
			propertyMaxSoulSpeed.setMinValue(0);
			propertyMaxSoulSpeed.setMaxListLength(5);
			propertyOrderMuramasa.add(propertyMaxSoulSpeed.getName());
			
		config.setCategoryPropertyOrder(CATEGORY_MURAMASA, propertyOrderMuramasa);
		
		if(readFieldsFromConfig) {
			//Muramasa Category
			soulsPerDamage = propertySoulsPerDamage.getInt();
			soulsPerSpeed = propertySoulsPerSpeed.getInt();
			maxSoulDamage = propertyMaxSoulDamage.getInt();
			maxSoulSpeed = propertyMaxSoulSpeed.getDouble();
		}
		
		//Muramasa Category
		propertySoulsPerDamage.set(soulsPerDamage);
		propertySoulsPerSpeed.set(soulsPerSpeed);
		propertyMaxSoulDamage.set(maxSoulDamage);
		propertyMaxSoulSpeed.set(maxSoulSpeed);
		
		config.save();
		System.out.print("Saved Config");
	}
	
	public static class ConfigEventHandler {
		@SubscribeEvent(priority = EventPriority.LOWEST)
		public void onEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
			if(event.getModID().equals(ModInfo.MODID)) {
				syncFromGui();
			}
				
		}
	}
}
