package com.Xenon.MetinCraft.init;

import com.Xenon.MetinCraft.Main;
import com.Xenon.MetinCraft.entities.bestialArcher.EntityBestialArcher;
import com.Xenon.MetinCraft.entities.ironArrow.EntityIronArrow;
import com.Xenon.MetinCraft.util.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class MetinEntities 
{
	public static void registerEntities()
	{
		registerEntity("bestial_archer", EntityBestialArcher.class, Reference.ENTITY_BESTIAL_ARCHER, 50, 9120256, 181);
		registerArrow("iron_arrow", EntityIronArrow.class, Reference.ENTITY_IRON_ARROW);
	}
	
	private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), entity, name, id, Main.instance, range, 1, true, color1, color2);
	}
	
	private static void registerArrow(String name, Class<? extends Entity> entity, int id)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), entity, name, id, Main.instance, 64, 20, true);
	}
	
	private static void registerNonMobEntity()
	{
		
	}
}
