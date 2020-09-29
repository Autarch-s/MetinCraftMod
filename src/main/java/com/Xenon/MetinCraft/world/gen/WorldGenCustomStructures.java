package com.Xenon.MetinCraft.world.gen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.Xenon.MetinCraft.init.MetinBiomes;
import com.Xenon.MetinCraft.world.biome.BiomeMetinDungeon;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;


public class WorldGenCustomStructures implements IWorldGenerator
{
	public static boolean generated;
	
	public WorldGenCustomStructures()
	{
		generated = false;
	}
	
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World worldIn, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		if(!generated)
		{	
			WorldGenStructure generator = new WorldGenStructure();
			int chunkY = getGroundFromAbove(worldIn, 0, 0);
			
			generated = true;
			switch(worldIn.provider.getDimension())
			{
				case 420:
				{
					generator.loadStructure(worldIn, new BlockPos(0, chunkY , 0), "spidersDungeon0");
					generator.loadStructure(worldIn, new BlockPos(-16, chunkY , 0), "spidersDungeon1");
					generator.loadStructure(worldIn, new BlockPos(-16, chunkY , -16), "spidersDungeon2");
					generator.loadStructure(worldIn, new BlockPos(0, chunkY , -16), "spidersDungeon3");
					break;
				}
				
				case 1:
					
					break;
					
				case 0:
					
					break;
				
				case -1: 
					
					break;	
			}
		}
	}
	
	public static int getGroundFromAbove(World world, int x, int z) 
	{
		int y = 255;
		boolean foundGround = false;
		while (!foundGround && y-- > 0) 
		{
			Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
			if (block == Blocks.WATER || block == Blocks.FLOWING_WATER) 
			{
				y = -1;
				break;
			}
			foundGround = block == Blocks.GRASS || block == Blocks.SAND || block == Blocks.SNOW
					|| block == Blocks.SNOW_LAYER || block == Blocks.MYCELIUM || block == Blocks.STONE;
		}
		return y;
	}
}





