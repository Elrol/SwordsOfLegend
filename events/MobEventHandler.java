package com.github.elrol.SwordsOfLegend.events;

import com.github.elrol.SwordsOfLegend.items.Muramasa;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MobEventHandler {

	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent event) {
		if(event.getSource().getTrueSource() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.getSource().getTrueSource();
			if(player.getHeldItemMainhand().getItem() instanceof Muramasa) {
				ItemStack muramasa = player.getHeldItemMainhand();
				int souls = muramasa.getTagCompound().getInteger("souls");
				muramasa.getTagCompound().setInteger("souls", souls+1);
			}
		}
	}
	
}
