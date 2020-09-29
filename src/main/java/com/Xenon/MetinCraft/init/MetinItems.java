package com.Xenon.MetinCraft.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.Xenon.MetinCraft.items.ItemBase;
import com.Xenon.MetinCraft.items.ItemPassageTicket;
import com.Xenon.MetinCraft.items.tools.BlackLeafDirk;
import com.Xenon.MetinCraft.items.tools.FullMoonSword;
import com.Xenon.MetinCraft.items.tools.RedIronBlade;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class MetinItems 
{
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//Metarials
	public static final ToolMaterial MATERIAL_L30 = EnumHelper.addToolMaterial("material_l30", 3, 1561, 8.0F, 1.5F, 10);
	
	//Items
	public static final Item FULL_MOON_INGOT = new ItemBase("full_moon_ingot");
	public static final Item PASSAGE_TICKET = new ItemPassageTicket("passage_ticket");
	
	//Tools
	public static final ItemSword FULL_MOON_SWORD = new FullMoonSword("full_moon_sword", MATERIAL_L30);
	public static final ItemSword BLACK_LEAF_DIRK = new BlackLeafDirk("black_leaf_dirk", MATERIAL_L30);
	public static final ItemSword RED_IRON_BLADE = new RedIronBlade("red_iron_blade", MATERIAL_L30);
}
