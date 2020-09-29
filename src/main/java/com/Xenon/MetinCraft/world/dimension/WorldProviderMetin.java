package com.Xenon.MetinCraft.world.dimension;

import com.Xenon.MetinCraft.init.MetinBiomes;
import com.Xenon.MetinCraft.init.MetinDimensions;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.gen.ChunkGeneratorEnd;
import net.minecraft.world.gen.ChunkGeneratorFlat;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;

public class WorldProviderMetin extends WorldProvider {

	@Override
	protected void init()
	{
		this.biomeProvider = new BiomeProviderSingle(MetinBiomes.METINDUNGEON);
		this.hasSkyLight=false;
	}
	
	@Override
	public DimensionType getDimensionType() 
	{
		return MetinDimensions.SPIDERSDUNGEON;
	}
	
	@Override
	public boolean isSurfaceWorld()
	{
		return false;
	}

	@Override
    public int getAverageGroundLevel() 
	{
        return 63;
    }

	@Override
	public boolean canCoordinateBeSpawn(int x, int z)
	{
		return super.canCoordinateBeSpawn(x, z);
	}
	
	@Override
	public boolean canRespawnHere()
	{
		return true;
	}
	
	@Override
	public boolean doesXZShowFog(int x, int z)
    {
        return false;
    }
	
	@Override
	public WorldBorder createWorldBorder()
	{
		return new WorldBorder();
	}
	
	@Override
	public IChunkGenerator createChunkGenerator()
	{
		return new ChunkGeneratorMetin(this.world, this.world.getSeed(), "");
	}
	
	
}
