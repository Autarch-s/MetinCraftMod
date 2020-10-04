package com.Xenon.MetinCraft.items.tools.lv30;

import com.Xenon.MetinCraft.Main;
import com.Xenon.MetinCraft.init.MetinItems;
import com.Xenon.MetinCraft.util.IHasModel;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class BlackLeafDirk extends ItemSword implements IHasModel
{
	private double attackDamage;
	private double attackSpeed;
	
	private boolean equipped;
	
	public BlackLeafDirk(String name, ToolMaterial material)
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.COMBAT);
		this.equipped = false;
		MetinItems.ITEMS.add(this);
		
		this.attackDamage = 4.5;
		this.attackSpeed = 2;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) 
	{
		EntityPlayer player=(EntityPlayer)entity;
		
		if(isSelected)
		{
			if(!player.hasItemInSlot(EntityEquipmentSlot.OFFHAND))
			{
				player.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, stack);
			}

			this.equipped = true;
		}
		else if(player.getHeldItemOffhand() == stack && player.getHeldItemMainhand() != stack)
		{
			if(equipped)
			{
				player.inventory.offHandInventory.clear();
				equipped = false;
			}
		}
		player.inventoryContainer.detectAndSendChanges();
		
		super.onUpdate(stack, world, entity, itemSlot, isSelected);
	}
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = HashMultimap.create();

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)(attackDamage), 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double)(attackSpeed), 0));
        }

        return multimap;
    }
}
