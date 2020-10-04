package com.Xenon.MetinCraft.util.handlers;

import com.Xenon.MetinCraft.entities.bestialArcher.EntityBestialArcher;
import com.Xenon.MetinCraft.entities.bestialArcher.RenderBestialArcher;
import com.Xenon.MetinCraft.entities.ironArrow.EntityIronArrow;
import com.Xenon.MetinCraft.entities.ironArrow.RenderIronArrow;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler 
{
	public static void registerEntityRenders()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityBestialArcher.class, new IRenderFactory<EntityBestialArcher>() 
		{
			@Override
			public Render<? super EntityBestialArcher> createRenderFor(RenderManager renderManagerIn)
			{
				return new RenderBestialArcher(renderManagerIn);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityIronArrow.class, new IRenderFactory<EntityIronArrow>() 
		{
			@Override
			public Render<? super EntityIronArrow> createRenderFor(RenderManager renderManagerIn)
			{
				return new RenderIronArrow(renderManagerIn);
			}
		});
	}
}
