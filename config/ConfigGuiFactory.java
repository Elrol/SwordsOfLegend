package com.github.elrol.SwordsOfLegend.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.elrol.SwordsOfLegend.libs.ModInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.DummyConfigElement.DummyCategoryElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.GuiConfigEntries.CategoryEntry;
import net.minecraftforge.fml.client.config.IConfigElement;

public class ConfigGuiFactory implements IModGuiFactory{

	@Override
	public void initialize(Minecraft minecraftInstance) {
	}

	@Override
	public boolean hasConfigGui() {
		return true;
	}

	public Class<? extends GuiScreen> mainConfigGuiClass(){
		return ConfigGui.class;}
	
	@Override
	public GuiScreen createConfigGui(GuiScreen parentScreen) {
		return new ConfigGui(parentScreen);
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}
	
	public static class ConfigGui extends GuiConfig {
		public ConfigGui(GuiScreen parentScreen) {
			super(parentScreen, getConfigElements(), ModInfo.MODID, false, false, I18n.format("gui.config.main_title"));	
		}

		private static List<IConfigElement> getConfigElements() {
			List<IConfigElement> list = new ArrayList<IConfigElement>();
			list.add(new DummyCategoryElement(I18n.format("gui.config.muramasa"), "gui.config.muramasa", CategoryEntryMuramasa.class));
			return list;
		}
	}
		
	public static class CategoryEntryMuramasa extends CategoryEntry{

		public CategoryEntryMuramasa(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement configElement) {
			super(owningScreen, owningEntryList, configElement);
		}
		
		@Override
		protected GuiScreen buildChildScreen() {
			Configuration config = ConfigManager.getConfig();
			ConfigElement categoryMuramasa = new ConfigElement(config.getCategory(ConfigManager.CATEGORY_MURAMASA));
			List<IConfigElement> propertiesOnScreen = categoryMuramasa.getChildElements();
			String windowTitle = I18n.format("gui.config.muramasa");
			return new GuiConfig(owningScreen, propertiesOnScreen, owningScreen.modID, this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart, this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart, windowTitle);
		}
	}

}
