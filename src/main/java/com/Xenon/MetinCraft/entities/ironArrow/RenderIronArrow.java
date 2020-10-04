package com.Xenon.MetinCraft.entities.ironArrow;

import com.Xenon.MetinCraft.util.Reference;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderIronArrow extends RenderArrow<EntityIronArrow>
{

	public RenderIronArrow(RenderManager renderManagerIn) 
	{
		super(renderManagerIn);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityIronArrow entity) 
	{
		return new ResourceLocation(Reference.MOD_ID + ":textures/entities/arrows/iron_arrow.png");
	}

}
