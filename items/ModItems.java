package com.github.elrol.SwordsOfLegend.items;

import com.github.elrol.SwordsOfLegend.creativeTabs.ModCreativeTabs;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {
	
	public static Muramasa muramasa = new Muramasa().setCreativeTab(ModCreativeTabs.legendarySwordsTab);
	
	public static void register(IForgeRegistry<Item> registry) {
		registry.registerAll(muramasa);
	}
	
	public static void registerModels() {
		muramasa.registerItemModel();
	}
	
}
