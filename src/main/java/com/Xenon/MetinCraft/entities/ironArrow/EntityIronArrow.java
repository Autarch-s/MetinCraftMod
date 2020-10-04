package com.Xenon.MetinCraft.entities.ironArrow;

import com.Xenon.MetinCraft.init.MetinItems;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityIronArrow extends EntityTippedArrow
{
	private ItemStack arrowStack;
	
	public EntityIronArrow(World worldIn) 
	{
		super(worldIn);
	}
	
	public EntityIronArrow(World worldIn, double x, double y, double z) 
	{
		super(worldIn, x, y, z);
	}

	public EntityIronArrow(World worldIn,ItemStack stack, EntityLivingBase shooter) 
	{
		super(worldIn, shooter);
		 this.arrowStack = stack;
	}
	
	@Override
	protected ItemStack getArrowStack() 
	{
		return new ItemStack(MetinItems.IRON_ARROW);
	}

	@Override
	protected void arrowHit(EntityLivingBase living)
	{
		super.arrowHit(living);
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
	}
}
