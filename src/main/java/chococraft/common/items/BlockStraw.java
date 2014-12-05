package chococraft.common.items;

import chococraft.common.config.ChocoCraftCreativeTabs;
import chococraft.common.config.ChocoCraftItems;
import chococraft.common.config.Constants;
import chococraft.common.items.helper.ISimpleTextureHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;



public class BlockStraw extends Block implements ISimpleTextureHelper
{
	ModelResourceLocation modelResourceLocation = null;

    public BlockStraw()
    {
        super(Material.circuits);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
		this.setCreativeTab(ChocoCraftCreativeTabs.tabChococraft);
		setStepSound(Block.soundTypeGrass);
		setHardness(0.0F);
		setUnlocalizedName("strawBlock");
		modelResourceLocation = new ModelResourceLocation(Constants.TCC_MODID + ":" + this.getUnlocalizedName().substring(5), "inventory");
    }

	@Override
	public ModelResourceLocation getDefaultModelLocation() {
		return modelResourceLocation;
	}

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
  /*  @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
*/
    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
	/*
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
    }

    @Override
    public void onNeighborBlockChange(World world, int posX, int posY, int posZ, Block par5)
    {
        this.canStrawStay(world, posX, posY, posZ);
    }
    */
    private boolean canStrawStay(World world, BlockPos pos)
    {
        if (!this.canPlaceBlockAt(world, pos))
        {
//            world.setBlock(pos, this, 0, 2);
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        Block block = worldIn.getBlockState(pos.add(0, -1, 0)).getBlock();
        return block != null && (block.isOpaqueCube()) && block.getMaterial().blocksMovement();
    }

    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    @Override
    public void harvestBlock(World worldIn, EntityPlayer playerIn, BlockPos pos, IBlockState state, TileEntity te)
    {

        super.harvestBlock(worldIn, playerIn, pos, state, te);
		worldIn.setBlockToAir(pos);
        //world.setBlock(posX, posY, posZ, this.blockID, 0, 2);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }
    
    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random)
    {
		return 1;
//no bloody idea -.-'
//        return (meta & 7) + 1;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (worldIn.getLightFor(EnumSkyBlock.BLOCK, pos) > 11)
        {
//            world.setBlock(posX, posY, posZ, this, 0, 2);
        }
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    @SideOnly(Side.CLIENT)
    @Override
	public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
    {
        return super.shouldSideBeRendered(worldIn, pos, side);
    }
}
