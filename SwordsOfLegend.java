package com.github.elrol.SwordsOfLegend;

import org.apache.logging.log4j.Logger;

import com.github.elrol.SwordsOfLegend.config.ConfigManager;
import com.github.elrol.SwordsOfLegend.events.ModEventRegistry;
import com.github.elrol.SwordsOfLegend.items.ModItems;
import com.github.elrol.SwordsOfLegend.libs.ModInfo;
import com.github.elrol.SwordsOfLegend.proxy.CommonProxy;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = ModInfo.MODID, name = ModInfo.MODNAME, version = ModInfo.MODVERSION, guiFactory = ModInfo.GUIFACTORY)
public class SwordsOfLegend
{
	@Mod.Instance(ModInfo.MODID)
	public static SwordsOfLegend instnace;
	
	@SidedProxy(serverSide = "com.github.elrol.SwordsOfLegend.proxy.CommonProxy", clientSide = "com.github.elrol.SwordsOfLegend.proxy.ClientProxy")
	public static CommonProxy proxy;
	
    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        logger.debug(ModInfo.MODNAME + " is loading!");
        ConfigManager.preInit();
        ModEventRegistry.registerEvents();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }
    
    @Mod.EventBusSubscriber
    public static class RegistrationHandler {
    	
    	@SubscribeEvent
    	public static void registerItems(RegistryEvent.Register<Item> event) {
    		ModItems.register(event.getRegistry());
    	}
    	
    	@SubscribeEvent
    	public static void registerItems(ModelRegistryEvent event) {
    		ModItems.registerModels();
    	}
    	
    }
}
