// <copyright file="BlockGysahlStem.java">
// Copyright (c) 2012 All Right Reserved, http://chococraft.arno-saxena.de/
//
// THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY 
// KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
// PARTICULAR PURPOSE.
//
// </copyright>
// <author>Arno Saxena</author>
// <email>al-s@gmx.de</email>
// <date>2012-10-25</date>
// <summary>Block class for Gysahl Stems</summary>

package chococraft.common.items;

import chococraft.common.ModChocoCraft;
import chococraft.common.config.ChocoCraftBlocks;
import chococraft.common.config.ChocoCraftCreativeTabs;
import chococraft.common.config.ChocoCraftItems;
import chococraft.common.config.Constants;
import chococraft.common.items.helper.ISimpleTextureHelper;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;


public class BlockGysahlStem extends BlockBush implements ISimpleTextureHelper
{
	static private final int MAX_STAGE = 4;
	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, MAX_STAGE);
	private ModelResourceLocation defaultModelLocation;
	
	public BlockGysahlStem()
    {
        this.setTickRandomly(true);
        float f = 0.5F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
		this.setCreativeTab(ChocoCraftCreativeTabs.tabChococraft);
        this.disableStats();
        this.setStepSound(soundTypeGrass);
		setHardness(0f);
		setUnlocalizedName("gysahlStemBlock");
		defaultModelLocation = new ModelResourceLocation(Constants.TCC_MODID + ":" + "gyshal_stem01", "inventory");
		this.setDefaultState(this.blockState.getBaseState().withProperty(STAGE, Integer.valueOf(0)));
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{//eah..
		return this.getDefaultState().withProperty(STAGE, Integer.valueOf(meta));
	}
	public int getMetaFromState(IBlockState state)
	{
		return ((Integer)state.getValue(STAGE)).intValue();
	}


	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] {STAGE});
	}

    @Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(worldIn, pos, state, rand);

		if(worldIn.getLightFromNeighbors(pos) >= 9) {
			int gysahlStage = ((Integer)state.getValue(STAGE)).intValue();
			if( gysahlStage < MAX_STAGE) {
				float growthRate = getGrowthRate(worldIn, pos.getX(), pos.getX(), pos.getZ());
				if(rand.nextInt((int)(25F / growthRate) + 1) == 0) {
					gysahlStage++;
					worldIn.setBlockState(pos, state.cycleProperty(STAGE), 2);
				}
			}
		}
    }
	
	private float getGrowthRate(World theWorld, int xPos, int yPos, int zPos)
    {
        float growRate = 1.0F;
        Block i = theWorld.getBlockState(new BlockPos(xPos, yPos, zPos - 1)).getBlock();
		Block j = theWorld.getBlockState(new BlockPos(xPos, yPos, zPos + 1)).getBlock();
		Block k = theWorld.getBlockState(new BlockPos(xPos - 1, yPos, zPos)).getBlock();
		Block l = theWorld.getBlockState(new BlockPos(xPos + 1, yPos, zPos)).getBlock();
		Block i1 = theWorld.getBlockState(new BlockPos(xPos - 1, yPos, zPos - 1)).getBlock();
		Block j1 = theWorld.getBlockState(new BlockPos(xPos + 1, yPos, zPos - 1)).getBlock();
		Block k1 = theWorld.getBlockState(new BlockPos(xPos + 1, yPos, zPos + 1)).getBlock();
		Block l1 = theWorld.getBlockState(new BlockPos(xPos - 1, yPos, zPos + 1)).getBlock();

        boolean samePlantLeftOrRight = k.equals(this) || l.equals(this);
        boolean samePlantFrontOrBack = i.equals(this) || j.equals(this);
        boolean samePlantAnyCorner = i1.equals(this) || j1.equals(this) || k1.equals(this) || l1.equals(this);

        for (int xTmp = xPos - 1; xTmp <= xPos + 1; xTmp++)
        {
            for (int zTmp = zPos - 1; zTmp <= zPos + 1; zTmp++)
            {
                Block baseBlockId = theWorld.getBlockState(new BlockPos(xTmp, yPos - 1, zTmp)).getBlock();
                float tmpGrowRate = 0.0F;

                if (this.canThisPlantGrowOnThisBlock(baseBlockId))
                {
                    tmpGrowRate = 1.0F;
//TODO 1.8
/*                    if (theWorld.getBlockMetadata(xTmp, yPos - 1, zTmp) > 0)
                    {
                        tmpGrowRate = 3F;
                    }
*/                }

                if (xTmp != xPos || zTmp != zPos)
                {
                    tmpGrowRate /= 4F;
                }

                growRate += tmpGrowRate;
            }
        }

        if (samePlantAnyCorner || samePlantLeftOrRight && samePlantFrontOrBack)
        {
            growRate /= 2.0F;
        }

        return growRate;
    }


	protected boolean canThisPlantGrowOnThisBlock(Block block)
    {
        return block.equals(Blocks.farmland);
    }

    @Override
    public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float chance, int fortune)
    {
    	super.dropBlockAsItemWithChance(world, pos, getDefaultState(), chance, 0);
    	if (!world.isRemote)
    	{
    		int j1 = 3 + fortune;
    		for (int k1 = 0; k1 < j1; k1++)
    		{
				if(world.rand.nextInt(15) > 1)
				{
					continue;
				}
				float f1 = 0.7F;
    			float f2 = world.rand.nextFloat() * f1 + (1.0F - f1) * 0.5F;
    			float f3 = world.rand.nextFloat() * f1 + (1.0F - f1) * 0.5F;
    			float f4 = world.rand.nextFloat() * f1 + (1.0F - f1) * 0.5F;
    			EntityItem entityitem = new EntityItem(world, (float)pos.getX() + f2, (float)pos.getY() + f3, (float)pos.getZ() + f4, new ItemStack(ChocoCraftItems.gysahlSeedsItem));
    			entityitem.setPickupDelay(10);
    			world.spawnEntityInWorld(entityitem);
    		}
    	}
    }

    @Override
    public Item getItemDropped(IBlockState state, Random random, int fortune)
    {
		int gysahlStage = ((Integer)state.getValue(STAGE)).intValue();
		if(gysahlStage == 4)
		{
			if(random.nextInt(1000) > ModChocoCraft.gysahlGreenMutationRate)
			{
				return Item.getItemFromBlock(ChocoCraftBlocks.gysahlGreenBlock);
			}
			else
			{
				if(random.nextInt(1000) > ModChocoCraft.gysahlLoveMutationRate)
				{
					return ChocoCraftItems.gysahlLoverlyItem;
				}
				else
				{
					return ChocoCraftItems.gysahlGoldenItem;
				}
			}
		}
		else
		{
			return null;
		}
    }


	//Called from a event , does not need override
	public boolean onBonemealUse(World theWorld, BlockPos position)
	{
		int stage = ((Integer)theWorld.getBlockState(position).getValue(STAGE)).intValue();
		if(stage < MAX_STAGE)
		{//TODO is this right 1.8 stuff?
			IBlockState state = theWorld.getBlockState(position).withProperty(STAGE, MAX_STAGE);
			theWorld.setBlockState(position, state);
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public ModelResourceLocation getDefaultModelLocation() {
		return defaultModelLocation;
	}
}
