package com.Xenon.MetinCraft.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.Xenon.MetinCraft.items.ItemBase;
import com.Xenon.MetinCraft.items.ItemPassageTicket;
import com.Xenon.MetinCraft.items.tools.IronArrow;
import com.Xenon.MetinCraft.items.tools.lv1.Dagger;
import com.Xenon.MetinCraft.items.tools.lv1.Fan;
import com.Xenon.MetinCraft.items.tools.lv1.Glaive;
import com.Xenon.MetinCraft.items.tools.lv1.Lv1Bow;
import com.Xenon.MetinCraft.items.tools.lv1.Sword;
import com.Xenon.MetinCraft.items.tools.lv30.AntiqueBell;
import com.Xenon.MetinCraft.items.tools.lv30.AutumnWindFan;
import com.Xenon.MetinCraft.items.tools.lv30.BlackLeafDirk;
import com.Xenon.MetinCraft.items.tools.lv30.FullMoonSword;
import com.Xenon.MetinCraft.items.tools.lv30.HornBow;
import com.Xenon.MetinCraft.items.tools.lv30.RedIronBlade;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class MetinItems 
{
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//Metarials
	public static final ToolMaterial MATERIAL_LV1 = EnumHelper.addToolMaterial("material_lv1", 2, 500, 6.0F, 2.2F, 14);
	public static final ToolMaterial MATERIAL_LV30 = EnumHelper.addToolMaterial("material_lv30", 3, 2000, 8.0F, 3.5F, 10);
	//public static final ArmorMaterial ARMOR_MATERIAL_LV1 = EnumHelper.addArmorMaterial("armor_material_lv1", Reference.MOd_ID + ":iron", 12, 
	//		new int[] {1,4,6,2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
	
	//Items
	public static final Item FULL_MOON_INGOT = new ItemBase("full_moon_ingot");
	public static final Item PASSAGE_TICKET = new ItemPassageTicket("passage_ticket");
	
	//Tools
	public static final ItemSword FULL_MOON_SWORD = new FullMoonSword("full_moon_sword", MATERIAL_LV30);
	public static final ItemSword BLACK_LEAF_DIRK = new BlackLeafDirk("black_leaf_dirk", MATERIAL_LV30);
	public static final ItemSword RED_IRON_BLADE = new RedIronBlade("red_iron_blade", MATERIAL_LV30);
	public static final ItemSword AUTUMN_WIND_FAN = new AutumnWindFan("autumn_wind_fan", MATERIAL_LV30);
	public static final ItemSword ANTIQUE_BELL = new AntiqueBell("antique_bell", MATERIAL_LV30);
	public static final ItemSword SWORD = new Sword("sword", MATERIAL_LV1);
	public static final ItemSword GLAIVE = new Glaive("glaive", MATERIAL_LV1);
	public static final ItemSword DAGGER = new Dagger("dagger", MATERIAL_LV1);
	public static final ItemSword FAN = new Fan("fan", MATERIAL_LV1);
	
	//Bows
	public static final ItemArrow IRON_ARROW = new IronArrow("iron_arrow");
	public static final ItemBow Lv1BOW = new Lv1Bow("lv1_bow");
	public static final ItemBow HORN_BOW = new HornBow("horn_bow");
}
