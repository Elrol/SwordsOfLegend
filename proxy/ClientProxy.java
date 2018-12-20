package com.github.elrol.SwordsOfLegend.proxy;

import com.github.elrol.SwordsOfLegend.config.ConfigManager;
import com.github.elrol.SwordsOfLegend.libs.ModInfo;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy{
	
	public void preInit() {
		ConfigManager.clientPreInit();
	}
	
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(ModInfo.MODID + ":" + id, "inventory"));
	}

}
