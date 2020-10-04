package com.Xenon.MetinCraft.items.tools.lv1;

import com.Xenon.MetinCraft.Main;
import com.Xenon.MetinCraft.init.MetinItems;
import com.Xenon.MetinCraft.util.IHasModel;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.world.World;

public class Lv1Bow extends ItemBow implements IHasModel
{
	public Lv1Bow(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.COMBAT);
		MetinItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
