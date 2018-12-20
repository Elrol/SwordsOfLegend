package com.github.elrol.SwordsOfLegend.creativeTabs;

import com.github.elrol.SwordsOfLegend.items.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class LegendarySwordsTab extends CreativeTabs {

	public LegendarySwordsTab() {
		super("tabLegendarySwords");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.muramasa);
	}

}
