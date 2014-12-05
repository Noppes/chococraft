package chococraft.common.items;

import chococraft.common.config.ChocoCraftCreativeTabs;
import chococraft.common.config.Constants;
import chococraft.common.items.helper.ISimpleTextureHelper;
import net.minecraft.block.Block;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSeeds;

public class ItemGysahlSeeds extends ItemSeeds implements ISimpleTextureHelper
{
	ModelResourceLocation modelResourceLocation = null;

	public ItemGysahlSeeds(Block block, Block soilBlock)
	{
		super(block, soilBlock);
		this.setCreativeTab(ChocoCraftCreativeTabs.tabChococraft);
		this.setUnlocalizedName(Constants.KEY_GY_SEEDS);
	}

	public void setDefaultModelLocation() {
		modelResourceLocation = new ModelResourceLocation(Constants.TCC_MODID + ":" + this.getUnlocalizedName().substring(5), "inventory");
	}

	@Override
	public ModelResourceLocation getDefaultModelLocation() {
		return modelResourceLocation;
	}
}
