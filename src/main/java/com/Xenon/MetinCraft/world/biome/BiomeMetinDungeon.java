package com.Xenon.MetinCraft.world.biome;

import com.Xenon.MetinCraft.util.Reference;

import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEndDecorator;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class BiomeMetinDungeon extends Biome 
{

	public BiomeMetinDungeon() 
	{
		super(new BiomeProperties("metindungeon").setBaseHeight(0F).setHeightVariation(0F).setTemperature(.5F).setWaterColor(0x8B0000));
		
		this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySpider.class, 9, 3, 6));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityCaveSpider.class, 9, 3, 6));
        
        this.topBlock = Blocks.GRASS.getDefaultState();
		this.fillerBlock = Blocks.DIRT.getDefaultState();
		
		this.setRegistryName(Reference.MOD_ID, "metindungeon");
		
	}
	
}
