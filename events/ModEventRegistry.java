package com.github.elrol.SwordsOfLegend.events;

import net.minecraftforge.common.MinecraftForge;

public class ModEventRegistry {
	
	public static void registerEvents() {
		MinecraftForge.EVENT_BUS.register(new MobEventHandler());
	}

}
