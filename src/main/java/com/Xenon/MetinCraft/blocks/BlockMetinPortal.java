package com.Xenon.MetinCraft.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.Xenon.MetinCraft.Main;
import com.Xenon.MetinCraft.init.MetinBlocks;
import com.Xenon.MetinCraft.init.MetinDimensions;
import com.Xenon.MetinCraft.init.MetinItems;
import com.Xenon.MetinCraft.util.IHasModel;
import com.Xenon.MetinCraft.util.Reference;
import com.Xenon.MetinCraft.world.MetinTeleporter;
import com.Xenon.MetinCraft.world.gen.WorldGenStructure;
import com.google.common.cache.LoadingCache;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.EnumFacing.AxisDirection;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMetinPortal extends BlockBreakable
{

	public static final PropertyEnum<EnumFacing.Axis> AXIS = PropertyEnum.<EnumFacing.Axis>create("axis", EnumFacing.Axis.class,EnumFacing.Axis.X,EnumFacing.Axis.Z);
	protected static final AxisAlignedBB X_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D);
    protected static final AxisAlignedBB Z_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D);
    protected static final AxisAlignedBB Y_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);
	
	public BlockMetinPortal(String name, Material material) 
	{
		super(Material.PORTAL, false);	
		setUnlocalizedName(name);
		setRegistryName(name);
		this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS,  EnumFacing.Axis.X));
		this.setTickRandomly(true);
		this.setHardness(-1.0f);
		
		MetinBlocks.BLOCKS.add(this);
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState blockState, IBlockAccess source, BlockPos pos)
    {
        switch ((EnumFacing.Axis)blockState.getValue(AXIS))
        {
            case X:
                return X_AABB;
            case Y:
            default:
                return Y_AABB;
            case Z:
                return Z_AABB;
        }
    }
	
	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

	public static int getMetaForAxis(EnumFacing.Axis axis)
	{
		if (axis == EnumFacing.Axis.X)
        {
            return 1;
        }
        else
        {
            return axis == EnumFacing.Axis.Z ? 2 : 0;
        } 
	}
	
	public boolean isFullCube(IBlockState blockState)
    {
        return false;
    }
	
	public boolean trySpawnPortal(World world, BlockPos pos)
	{
		Size size = new Size(world, pos, EnumFacing.Axis.X);
		
		if(size.isValid() && size.portalBlockCount == 0)
		{
			size.placePortalBlock();
			return true;
		}
		else
		{
			Size size1 = new Size(world, pos, EnumFacing.Axis.Z);
			if(size1.isValid() && size1.portalBlockCount == 0)
			{
				size1.placePortalBlock();
				return true;
			}
			return false;
		}
		
	}
	
	@Override
	public void neighborChanged(IBlockState blockState, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        Axis axis = (Axis) blockState.getValue(AXIS);
        
        if(axis == Axis.X) 
        {
        	Size size = new Size(worldIn, pos, EnumFacing.Axis.X);
        	
        	if(!size.isValid() || size.portalBlockCount < size.width * size.height)
    		{
    			worldIn.setBlockToAir(pos);
    		}
        }
        else if(axis == Axis.Z) 
    	{
    		Size size = new Size(worldIn, pos, EnumFacing.Axis.Z);
        	
        	if(!size.isValid() || size.portalBlockCount < size.width * size.height)
        	{
    			worldIn.setBlockToAir(pos);
    		}
    	}
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        pos = pos.offset(side);
        Axis axis = null;

        if (blockState.getBlock() == this)
        {
            axis = (Axis)blockState.getValue(AXIS);

            if (axis == null)
            {
                return false;
            }

            if (axis == Axis.Z && side != EnumFacing.EAST && side != EnumFacing.WEST)
            {
                return false;
            }

            if (axis == Axis.X && side != EnumFacing.SOUTH && side != EnumFacing.NORTH)
            {
                return false;
            }
        }

        boolean flag = blockAccess.getBlockState(pos.west()).getBlock() == this && blockAccess.getBlockState(pos.west(2)).getBlock() != this;
        boolean flag1 = blockAccess.getBlockState(pos.east()).getBlock() == this && blockAccess.getBlockState(pos.east(2)).getBlock() != this;
        boolean flag2 = blockAccess.getBlockState(pos.north()).getBlock() == this && blockAccess.getBlockState(pos.north(2)).getBlock() != this;
        boolean flag3 = blockAccess.getBlockState(pos.south()).getBlock() == this && blockAccess.getBlockState(pos.south(2)).getBlock() != this;
        boolean flag4 = flag || flag1 || axis == Axis.X;
        boolean flag5 = flag2 || flag3 || axis == Axis.Z;

        if (flag4 && side == EnumFacing.WEST)
        {
            return true;
        }
        else if (flag4 && side == EnumFacing.EAST)
        {
            return true;
        }
        else if (flag5 && side == EnumFacing.NORTH)
        {
            return true;
        }
        else
        {
            return flag5 && side == EnumFacing.SOUTH;
        }
    }
	
	@Override
	public int quantityDropped(Random random)
    {
        return 0;
    }
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState blockState, Entity entityIn)
    {
        if (!entityIn.isRiding() && !entityIn.isBeingRidden() && entityIn.isNonBoss() && entityIn instanceof EntityPlayerMP)
        {
            EntityPlayerMP player = (EntityPlayerMP)entityIn;
            
            if(player.timeUntilPortal > 0)
            {
            	player.timeUntilPortal = 10;
            }
            else if(player.dimension != MetinDimensions.SPIDERSDUNGEON.getId())
            {
            	player.timeUntilPortal = 10;
            	player.mcServer.getPlayerList().transferPlayerToDimension(player, MetinDimensions.SPIDERSDUNGEON.getId(), new MetinTeleporter(player.mcServer.getWorld(MetinDimensions.SPIDERSDUNGEON.getId())));
            	if(player.dimension == MetinDimensions.SPIDERSDUNGEON.getId())
            	{
            		player.setSpawnChunk(new BlockPos(0, 64 , 0), true, 420);
            		player.setSpawnPoint(new BlockPos(0, 64 , 0), true);
            		player.setPositionAndUpdate(0, 64, 0);
            	}
            } 
            else
            {
            	player.timeUntilPortal = 10;
            	player.mcServer.getPlayerList().transferPlayerToDimension(player, 0, new MetinTeleporter(player.mcServer.getWorld(0)));
            }
        }
    }
	
	@Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState blockState)
    {
        return ItemStack.EMPTY;
    }

	@Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(AXIS, (meta & 3) == 2 ? EnumFacing.Axis.Z : EnumFacing.Axis.X);
    }

	@Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		if (rand.nextInt(100) == 0)
		{
			worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
		}

		for (int i = 0; i < 4; ++i)
		{
			double d0 = (double)((float)pos.getX() + rand.nextFloat());
			double d1 = (double)((float)pos.getY() + rand.nextFloat());
			double d2 = (double)((float)pos.getZ() + rand.nextFloat());
			double d3 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
			double d4 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
			double d5 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
			int j = rand.nextInt(2) * 2 - 1;

			if (worldIn.getBlockState(pos.west()).getBlock() != this && worldIn.getBlockState(pos.east()).getBlock() != this)
			{
				d0 = (double)pos.getX() + 0.5D + 0.25D * (double)j;
				d3 = (double)(rand.nextFloat() * 2.0F * (float)j);
			}
			else
			{
				d2 = (double)pos.getZ() + 0.5D + 0.25D * (double)j;
				d5 = (double)(rand.nextFloat() * 2.0F * (float)j);
			}

			worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
		}
	}

	@Override
    public int getMetaFromState(IBlockState blockState)
    {
        return getMetaForAxis((Axis)blockState.getValue(AXIS));
    }
    
	@Override
	public IBlockState withRotation(IBlockState blockState, Rotation rot)
    {
        switch (rot)
        {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:

                switch ((EnumFacing.Axis)blockState.getValue(AXIS))
                {
                    case X:
                        return blockState.withProperty(AXIS, EnumFacing.Axis.Z);
                    case Z:
                        return blockState.withProperty(AXIS, EnumFacing.Axis.X);
                    default:
                        return blockState;
                }

            default:
                return blockState;
        }
    }
	
	@Override
	 protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {AXIS});
    }
	
	public BlockPattern.PatternHelper createPatternHelper(World worldIn, BlockPos pos)
    {
        Axis axis = Axis.Z;
        Size size = new Size(worldIn, pos, Axis.X);
        LoadingCache<BlockPos, BlockWorldState> loadingcache = BlockPattern.createLoadingCache(worldIn, true);

        if (!size.isValid())
        {
            axis = Axis.X;
            size = new Size(worldIn, pos, Axis.Z);
        }

        if (!size.isValid())
        {
            return new BlockPattern.PatternHelper(pos, EnumFacing.NORTH, EnumFacing.UP, loadingcache, 1, 1, 1);
        }
        else
        {
            int[] aint = new int[AxisDirection.values().length];
            EnumFacing enumfacing = size.rightDir.rotateYCCW();
            BlockPos blockpos = size.bottomLeft.up(size.getHeight() - 1);

            for (AxisDirection axisdirection : AxisDirection.values())
            {
                BlockPattern.PatternHelper patternhelper = new BlockPattern.PatternHelper(enumfacing.getAxisDirection() == axisdirection ? blockpos : blockpos.offset(size.rightDir, size.getWidth() - 1), EnumFacing.getFacingFromAxis(axisdirection, axis), EnumFacing.UP, loadingcache, size.getWidth(), size.getHeight(), 1);

                for (int i = 0; i < size.getWidth(); ++i)
                {
                    for (int j = 0; j < size.getHeight(); ++j)
                    {
                        BlockWorldState blockworldstate = patternhelper.translateOffset(i, j, 1);

                        if (blockworldstate.getBlockState() != null && blockworldstate.getBlockState().getMaterial() != Material.AIR)
                        {
                            ++aint[axisdirection.ordinal()];
                        }
                    }
                }
            }

            AxisDirection axisdirection1 = AxisDirection.POSITIVE;

            for (AxisDirection axisdirection2 : AxisDirection.values())
            {
                if (aint[axisdirection2.ordinal()] < aint[axisdirection1.ordinal()])
                {
                    axisdirection1 = axisdirection2;
                }
            }

            return new BlockPattern.PatternHelper(enumfacing.getAxisDirection() == axisdirection1 ? blockpos : blockpos.offset(size.rightDir, size.getWidth() - 1), EnumFacing.getFacingFromAxis(axisdirection1, axis), EnumFacing.UP, loadingcache, size.getWidth(), size.getHeight(), 1);
        }
    }
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState blockState, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }
	
	public static class Size
	{
		private final World world;
		private final Axis axis;
		private final EnumFacing rightDir, leftDir;
		private int portalBlockCount, height, width;
		private BlockPos bottomLeft;
		
		public Size(World world, BlockPos pos, Axis axis)
		{
			this.world = world;
			this.axis = axis;
			
			if(axis == Axis.X)
			{
				this.leftDir = EnumFacing.EAST;
				this.rightDir = EnumFacing.WEST;
			}
			else
			{
				this.leftDir = EnumFacing.NORTH;
				this.rightDir = EnumFacing.SOUTH;
			}
			
			for(BlockPos bp = pos; pos.getY() > bp.getY() - 21 && pos.getY() > 0 && this.isEmptyBlock(world.getBlockState(pos.down()).getBlock()); pos = pos.down())
			{
				;
			}
			
			int i = this.getDistanceUntilEdge(pos, this.leftDir) - 1;
			
			if(i >= 0)
			{
				this.bottomLeft = pos.offset(leftDir, i);
				this.width = this.getDistanceUntilEdge(bottomLeft, rightDir);
				
				if(this.width < 2 || this.width > 21)
				{
					this.bottomLeft = null;
					this.width = 0;
				}
			}
			
			if(this.bottomLeft != null)
				this.height = this.calculatePortalHeight();
		}
		
		protected int getDistanceUntilEdge(BlockPos pos, EnumFacing facing)
        {
            int i;

            for (i = 0; i < 22; ++i)
            {
                BlockPos bp = pos.offset(facing, i);

                if (!this.isEmptyBlock(this.world.getBlockState(bp).getBlock()) || this.world.getBlockState(bp.down()).getBlock() != Blocks.NETHER_BRICK)
                {
                    break;
                }
            }

            Block block = this.world.getBlockState(pos.offset(facing, i)).getBlock();
            return block == Blocks.NETHER_BRICK ? i : 0;
        }
		
		public int getHeight()
		{
			return height;
		}
		
		public int getWidth()
		{
			return width;
		}
		
		protected int calculatePortalHeight()
		{
			label56:
				
			for(this.height = 0; this.height < 21; ++this.height) 
			{
				for(int i = 0;i < this.width; ++i)
				{
					BlockPos pos = this.bottomLeft.offset(this.rightDir,i).up(this.height);
					Block block = this.world.getBlockState(pos).getBlock();
					if(!this.isEmptyBlock(block)) break label56;
					
					if(block == MetinBlocks.PORTAL_BLOCK) ++this.portalBlockCount;
					
					if(i == 0) 
					{
						block = this.world.getBlockState(pos.offset(leftDir)).getBlock();
						if(block != Blocks.NETHER_BRICK) break label56;
					}
					else if(i == this.width-1)
					{
						block = this.world.getBlockState(pos.offset(rightDir)).getBlock();
						if(block != Blocks.NETHER_BRICK) break label56;
					}
						
				}
			}
		
			for(int j = 0; j< this.width;++j)
			{
				if(this.world.getBlockState(this.bottomLeft.offset(this.rightDir, j).up(this.height)).getBlock() != Blocks.NETHER_BRICK)
				{
					this.height = 0;
					break;
				}
			}
			
			if(this.height <= 21 && this.height >=2) 
			{
				return this.height;
			}
			else
			{
				this.bottomLeft = null;
				return this.width = this.height = 0;
			}
		}
		
		protected boolean isEmptyBlock(Block block)
		{
			return block.getDefaultState().getMaterial() == Material.AIR || block == Blocks.FIRE || block == MetinBlocks.PORTAL_BLOCK;
		}
		
		public boolean isValid()
		{
			return this.bottomLeft != null && this.width >=2 && this.width <=21 && this.height >=2 && this.height <=21;
		}
		
		public void placePortalBlock()
		{
			for(int i = 0; i < this.width; ++i)
			{
				BlockPos pos = this.bottomLeft.offset(this.rightDir, i);
				
				for(int j = 0; j < this.height; ++j)
					this.world.setBlockState(pos.up(j), MetinBlocks.PORTAL_BLOCK.getDefaultState().withProperty(BlockMetinPortal.AXIS, this.axis), 2);
			}
		}	
	}
}
	
	
