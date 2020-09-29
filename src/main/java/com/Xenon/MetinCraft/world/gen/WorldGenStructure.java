package com.Xenon.MetinCraft.world.gen;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.Xenon.MetinCraft.util.IStructure;
import com.Xenon.MetinCraft.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockID;


public class WorldGenStructure extends WorldGenerator implements IStructure 
{
	Random rand = new Random();
	int r;
	
	public void loadStructure(World world, BlockPos position, String structureName) 
    {
	    boolean flag = false;
	    if (!world.isRemote) 
	    {
	        WorldServer worldServer = (WorldServer) world;
	        MinecraftServer minecraftServer = world.getMinecraftServer();
	        TemplateManager templateManager = worldServer.getStructureTemplateManager();
	        ResourceLocation resLoc = new ResourceLocation(Reference.MOD_ID, structureName);
	        Template template = templateManager.get(minecraftServer, resLoc);
	        
            if (template != null) 
	        {
	            IBlockState iblockstate = world.getBlockState(position);
	            world.notifyBlockUpdate(position, iblockstate, iblockstate, 3);
	            flag = true;

                for (int i = 0; i < template.getSize().getX(); i++) 
                {
                    for (int j = 0; j < template.getSize().getZ(); j++) 
                    {
                        BlockPos down = position.add(i, -1, j);
                        Block b = world.getBlockState(down).getBlock();
                        if (!b.equals(Blocks.GRASS)) 
                        {
                            flag = false;
                        }
                    }
                }
	            
                if (flag) 
                {
					Rotation rotation = Rotation.values()[rand.nextInt(3)];
					
					PlacementSettings placementSettings = (new PlacementSettings()).setMirror(Mirror.NONE)
					    .setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk((ChunkPos) null)
					    .setReplacedBlock((Block) null).setIgnoreStructureBlock(true);
					
					template.addBlocksToWorldChunk(world, position, placementSettings);
					
					template.getDataBlocks(position, placementSettings);
					
					Map<BlockPos, String> map = template.getDataBlocks(position, placementSettings);
					
					for (Entry<BlockPos, String> entry : map.entrySet())
					{
						if ("chest".equals(entry.getValue()))
						{
							BlockPos blockpos2 = entry.getKey();
							world.setBlockState(blockpos2.up(), Blocks.AIR.getDefaultState(), 3);
							TileEntity tileentity = world.getTileEntity(blockpos2);

							if (tileentity instanceof TileEntityChest)
							{
								((TileEntityChest)tileentity).setLootTable(LootTableList.ENTITIES_WITCH, rand.nextLong());
							}
       	             	}
						
						if ("br".equals(entry.getValue()))
						{
							BlockPos blockpos2 = entry.getKey();
							world.setBlockState(blockpos2.up(), Blocks.AIR.getDefaultState(), 3);
							TileEntity tileentity = world.getTileEntity(blockpos2);
       	                 
							if (tileentity instanceof TileEntityBrewingStand)
							{
       	                	
								int i = 1 + rand.nextInt(3);
       	                	 
								for (int j = 0; j < i; j++) 
								{
									r = rand.nextInt(9);
       	                		
									ItemStack pot=null;
       	                		 
									switch(r) 
									{
										case 0:{pot = new ItemStack(Items.POTIONITEM);PotionUtils.addPotionToItemStack(pot, PotionTypes.STRONG_HEALING);}
										break;
										 
										case 1:{pot = new ItemStack(Items.POTIONITEM);PotionUtils.addPotionToItemStack(pot, PotionTypes.LONG_FIRE_RESISTANCE);}
										break;
										 
										case 2:{pot = new ItemStack(Items.POTIONITEM);PotionUtils.addPotionToItemStack(pot, PotionTypes.LONG_LEAPING);}
										break;
										 
										case 3:{pot = new ItemStack(Items.POTIONITEM);PotionUtils.addPotionToItemStack(pot, PotionTypes.STRONG_HARMING);}
										break;
										 
										 
										case 4:{pot = new ItemStack(Items.POTIONITEM);PotionUtils.addPotionToItemStack(pot, PotionTypes.STRONG_STRENGTH);}
										break;
										 
										case 5:{pot = new ItemStack(Items.POTIONITEM);PotionUtils.addPotionToItemStack(pot, PotionTypes.STRONG_SWIFTNESS);};
										 
										case 6:{pot = new ItemStack(Items.POTIONITEM);PotionUtils.addPotionToItemStack(pot, PotionTypes.LONG_WATER_BREATHING);}
										break;
										 
										case 7:{pot = new ItemStack(Items.POTIONITEM);PotionUtils.addPotionToItemStack(pot, PotionTypes.LONG_REGENERATION);}
										break;
										 
										case 8:{pot = new ItemStack(Items.POTIONITEM);PotionUtils.addPotionToItemStack(pot, PotionTypes.STRONG_POISON);}
										break;
										 
										case 9:{pot = new ItemStack(Items.POTIONITEM);PotionUtils.addPotionToItemStack(pot, PotionTypes.LONG_NIGHT_VISION);}
										break;
									}
	       	                	 
									((TileEntityBrewingStand)tileentity).setInventorySlotContents(j,pot);
								}
							}
						}
					}
                }
	        }
	    }
	}


	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) 
	{
		return false;
	}

}
