package com.github.elrol.SwordsOfLegend.items;

import java.util.List;

import javax.annotation.Nullable;

import com.github.elrol.SwordsOfLegend.SwordsOfLegend;
import com.github.elrol.SwordsOfLegend.config.ConfigManager;
import com.github.elrol.SwordsOfLegend.materials.ToolMaterials;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class Muramasa extends ItemSword{

	protected String name = "muramasa";
	//protected double souls;
	
	public Muramasa() {
		super(ToolMaterials.muramasa);
		setUnlocalizedName(name);
		setRegistryName(name);
	}
	
	public boolean isEnchantable(ItemStack stack) {
		return stack.getItem() instanceof Muramasa;
	}

	public void registerItemModel() {
		SwordsOfLegend.proxy.registerItemRenderer(this, 0, name);
	}

	@Override
	public Muramasa setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
	@Override
	 public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack){
	        Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();
        if (slot == EntityEquipmentSlot.MAINHAND){
        	multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)ToolMaterials.muramasa.getAttackDamage() + getAttackMod(stack), 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -3.5D + getSpeedMod(stack), 0));
        }

        return multimap;
    }
	
	@Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5) {
		//Update nbt data
		if(!itemStack.hasTagCompound() && !itemStack.getTagCompound().hasKey("souls")) {
			itemStack.setTagCompound(new NBTTagCompound());
		    itemStack.getTagCompound().setInteger("souls", 0);
		}
		
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn){
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey("souls")) {
			tooltip.add("Souls: " + stack.getTagCompound().getInteger("souls"));
		}
	}
	
	private double getAttackMod(ItemStack stack) {
		if(stack == null || !stack.hasTagCompound() || !stack.getTagCompound().hasKey("souls")) {
			return 0D;
		}
		int souls = stack.getTagCompound().getInteger("souls");
		if(souls > 0) {
			if((double)(souls / ConfigManager.soulsPerDamage + super.getAttackDamage()) >= ConfigManager.maxSoulDamage) {
				return (double)ConfigManager.maxSoulDamage;
			} else {
				return (double)(souls / (double)ConfigManager.soulsPerDamage);
			}
		} else {
			return 0D;
		}
	}
	
	private double getSpeedMod(ItemStack stack) {
		if(stack == null || !stack.hasTagCompound() || !stack.getTagCompound().hasKey("souls")) {
			return 0D;
		}
		int souls = stack.getTagCompound().getInteger("souls");
		if(souls > 0) {
			if((double)(souls / ConfigManager.soulsPerSpeed) - 3.5D >= ConfigManager.maxSoulSpeed) {
				return (double)ConfigManager.maxSoulSpeed;
			} else {
				return (double)(souls / (double)ConfigManager.soulsPerSpeed);
			}
		} else {
			return 0D;
		}
	}

}
