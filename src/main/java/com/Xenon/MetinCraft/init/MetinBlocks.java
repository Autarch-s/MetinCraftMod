package com.Xenon.MetinCraft.init;

import java.util.ArrayList;
import java.util.List;

import com.Xenon.MetinCraft.blocks.BlockMetinPortal;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class MetinBlocks 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block PORTAL_BLOCK = new BlockMetinPortal("metin_portal", Material.PORTAL);
	
	
}
