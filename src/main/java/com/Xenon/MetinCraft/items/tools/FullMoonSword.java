package com.Xenon.MetinCraft.items.tools;

import java.util.Random;

import com.Xenon.MetinCraft.Main;
import com.Xenon.MetinCraft.init.MetinItems;
import com.Xenon.MetinCraft.util.IHasModel;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class FullMoonSword extends ItemSword implements IHasModel
{
	//private float damageVsEntity;
	//private static float baseDamage;
	//private boolean damageWasSet;
	//private Random rand = new Random(System.currentTimeMillis());
	
	public FullMoonSword(String name, ToolMaterial material)
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.COMBAT);
		MetinItems.ITEMS.add(this);
		//this.baseDamage = material.getAttackDamage();
		//this.damageWasSet = false;
	}

	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	/*@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int i, boolean bool) 
	{
		if(!this.damageWasSet)
		{this.damageWasSet = true;
		super.onUpdate(stack, world, entity, i, bool);
		this.damageVsEntity = (float)(rand.nextInt(10) + this.baseDamage);
		super.onUpdate(stack, world, entity, i, bool);}
	}
	
	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = HashMultimap.create();

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)(damageVsEntity), 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0));
        }

        return multimap;
    }*/
}
