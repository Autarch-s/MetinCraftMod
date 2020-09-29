package com.Xenon.MetinCraft.init;

import com.Xenon.MetinCraft.world.dimension.WorldProviderMetin;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class MetinDimensions 
{
	public static final DimensionType SPIDERSDUNGEON= DimensionType.register("SpidersDungeon", "_spidersdungeon", 420, WorldProviderMetin.class, false);

	public static void register()
	{
		DimensionManager.registerDimension(420, SPIDERSDUNGEON);
	}
}
