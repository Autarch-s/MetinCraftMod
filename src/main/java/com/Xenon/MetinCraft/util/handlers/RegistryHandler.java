package com.Xenon.MetinCraft.util.handlers;

import com.Xenon.MetinCraft.init.MetinBlocks;
import com.Xenon.MetinCraft.init.MetinDimensions;
import com.Xenon.MetinCraft.init.MetinEntities;
import com.Xenon.MetinCraft.init.MetinItems;
import com.Xenon.MetinCraft.util.IHasModel;
import com.Xenon.MetinCraft.world.gen.WorldGenCustomStructures;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler 
{
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(MetinItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(MetinBlocks.BLOCKS.toArray(new Block[0]));
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for(Item item : MetinItems.ITEMS)
		{
			if(item instanceof IHasModel)
			{
				((IHasModel)item).registerModels();
			}
		}
		
		for(Block block : MetinBlocks.BLOCKS)
		{
			if(block instanceof IHasModel)
			{
				((IHasModel)block).registerModels();
			}
		}
	}
	
	public static void preInitRegistries()
	{
		MetinDimensions.register();
		GameRegistry.registerWorldGenerator(new WorldGenCustomStructures(), 2);
		MetinEntities.registerEntities();
		RenderHandler.registerEntityRenders();
	}
	
	public static void initRegistries()
	{
		
	}
	
	public static void postInitRegistries()
	{
		
	}
}
