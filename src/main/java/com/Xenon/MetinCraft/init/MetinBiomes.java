package com.Xenon.MetinCraft.init;

import com.Xenon.MetinCraft.util.Reference;
import com.Xenon.MetinCraft.world.biome.BiomeMetinDungeon;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(Reference.MOD_ID)
public class MetinBiomes 
{
	public static final Biome METINDUNGEON = new BiomeMetinDungeon();
	
	@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
	public static class Register
	{
		@SubscribeEvent
		public static void registerBiomes(final RegistryEvent.Register<Biome> event)
		{
			final Biome[] biomes={ METINDUNGEON };
			
			event.getRegistry().registerAll(biomes);
			
			spawnBiomes();
		}
		
		private static void spawnBiomes()
		{
			BiomeManager.addBiome(BiomeType.COOL, new BiomeManager.BiomeEntry(METINDUNGEON, 1000));
			BiomeManager.addSpawnBiome(METINDUNGEON);
		}
	}

}
