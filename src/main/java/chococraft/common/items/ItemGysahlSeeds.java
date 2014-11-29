package chococraft.common.items;

import chococraft.common.config.ChocoCraftCreativeTabs;
import chococraft.common.config.Constants;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSeeds;

public class ItemGysahlSeeds extends ItemSeeds
{
	public ItemGysahlSeeds(Block block, Block soilBlock)
	{
		super(block, soilBlock);
		this.setCreativeTab(ChocoCraftCreativeTabs.tabChococraft);
		this.setUnlocalizedName(Constants.KEY_GY_SEEDS);
	}
	/*
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon(Constants.TCC_MODID + ":" + Constants.KEY_GY_SEEDS);
	}*/
}
