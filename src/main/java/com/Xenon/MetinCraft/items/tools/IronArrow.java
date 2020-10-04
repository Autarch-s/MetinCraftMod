package com.Xenon.MetinCraft.items.tools;

import com.Xenon.MetinCraft.Main;
import com.Xenon.MetinCraft.entities.ironArrow.EntityIronArrow;
import com.Xenon.MetinCraft.init.MetinItems;
import com.Xenon.MetinCraft.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class IronArrow extends ItemArrow implements IHasModel
{
	private double damage;
	private ItemStack dropItem = ItemStack.EMPTY;
	
	public IronArrow(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.COMBAT);
		MetinItems.ITEMS.add(this);
		
		this.damage = 3.0D;
		this.dropItem = new ItemStack(this);
	}
	
	@Override
	public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter)
	{
		EntityIronArrow entityIronArrow = new EntityIronArrow(worldIn, this.dropItem.copy(), shooter);
		entityIronArrow.setDamage(this.damage);
		return entityIronArrow;
	}
	
	public double getDamage () 
	{

        return this.damage;
    }

    public IronArrow setDamage (double damageIn) 
    {
        this.damage = damageIn;
        return this;
    }
    
	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
