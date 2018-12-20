package com.github.elrol.SwordsOfLegend.items;

import com.github.elrol.SwordsOfLegend.SwordsOfLegend;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item{
	
	protected String name;
	
	public ItemBase(String name) {
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
	}
	
	public void registerItemModel() {
		SwordsOfLegend.proxy.registerItemRenderer(this, 0, name);
	}
	
	public ItemBase setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

}
