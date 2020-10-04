package com.Xenon.MetinCraft.entities.bestialArcher;

import com.Xenon.MetinCraft.util.Reference;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerElytra;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderBestialArcher extends RenderBiped<AbstractSkeleton>
{
	private static final ResourceLocation BESTIAL_ARCHER_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entities/bestialArcher/bestial_archer.png");
	
	public RenderBestialArcher(RenderManager renderManagerIn) 
	{
		super(renderManagerIn, new ModelBestialArcher(), 0.2f);
        this.addLayer(new LayerHeldItem(this));
        this.addLayer(new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
                this.modelLeggings = new ModelBestialArcher(0.5F, true);
                this.modelArmor = new ModelBestialArcher(1.0F, true);
            }
        });
	}
	
	@Override
	protected ResourceLocation getEntityTexture(AbstractSkeleton entity) 
	{
		return BESTIAL_ARCHER_TEXTURE;
	}
}
