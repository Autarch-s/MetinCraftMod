package com.Xenon.MetinCraft.items.tools.lv30;

import com.Xenon.MetinCraft.Main;
import com.Xenon.MetinCraft.init.MetinItems;
import com.Xenon.MetinCraft.util.IHasModel;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.math.MathHelper;

public class RedIronBlade extends ItemSword implements IHasModel
{
	private double attackDamage;
	private double attackSpeed;
	
	public RedIronBlade(String name, ToolMaterial material)
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.COMBAT);
		MetinItems.ITEMS.add(this);
		
		this.attackDamage = 5.5;
		this.attackSpeed = 1.2;
	}
	
	//Knockback without enchantment
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		stack.damageItem(1, attacker);

		double d = attacker.posX - target.posX;
                double d1;
                for(d1 = attacker.posZ - target.posZ; d * d + d1 * d1 < 0.0001D; d1 = (Math.random() - Math.random()) * 0.01D)
{
                    d = (Math.random() - Math.random()) * 0.01D;
                }

		target.isAirBorne = true;
		float f = MathHelper.sqrt(d * d + d1 * d1);
		float f1 = 0.500F;
		target.motionX /= 2D;
		target.motionY /= 2D;
		target.motionZ /= 2D;
		target.motionX -= (d / (double)f) * (double)f1;
		target.motionY += 0.40000000596046448D;
		target.motionZ -= (d1 / (double)f) * (double)f1;
        	if(target.motionY > 0.40000000596046448D) {
        		target.motionY = 0.40000000596046448D;
        	}
        	return true;
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
