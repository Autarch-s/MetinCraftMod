package com.Xenon.MetinCraft.items;

import java.util.Set;

import com.Xenon.MetinCraft.Main;
import com.Xenon.MetinCraft.blocks.BlockMetinPortal;
import com.Xenon.MetinCraft.init.MetinBlocks;
import com.Xenon.MetinCraft.init.MetinItems;
import com.Xenon.MetinCraft.util.IHasModel;
import com.google.common.collect.Sets;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemPassageTicket extends Item implements IHasModel
{
    
	public ItemPassageTicket(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.MISC);
		this.setMaxDamage(1);
		this.maxStackSize = 1;
		
		MetinItems.ITEMS.add(this);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		int x = pos.getX(), y = pos.getY(), z = pos.getZ();
		int side = facing.getIndex();
		ItemStack itemstack = player.getHeldItem(hand);
		
		switch(side) {
		case 0:
		default:
			y--;
			break;
		case 1:
			y++;
			break;
		case 2:
			z--;
			break;
		case 3:
			z++;
			break;
		case 4:
			x--;
			break;
		case 5:
			x++;
			break;
		}
		
		if(!player.canPlayerEdit(new BlockPos(x, y, z), facing, player.getHeldItem(hand))) 
		{
			return EnumActionResult.FAIL;
		}
		
		IBlockState location = worldIn.getBlockState(new BlockPos(x, y, z));
		if(location == Blocks.AIR.getDefaultState()) 
		{
			((BlockMetinPortal) MetinBlocks.PORTAL_BLOCK).trySpawnPortal(worldIn, new BlockPos(x, y, z));
		}
		
		itemstack.damageItem(1, player);
		
		return EnumActionResult.SUCCESS;
	}

	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
		
	}
}
