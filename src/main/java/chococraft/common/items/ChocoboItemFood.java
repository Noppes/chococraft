package chococraft.common.items;

import chococraft.common.config.ChocoCraftCreativeTabs;
import chococraft.common.items.helper.ISimpleTextureHelper;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.ItemFood;
import chococraft.common.config.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ChocoboItemFood extends ItemFood implements ISimpleTextureHelper
{
	ModelResourceLocation modelResourceLocation = null;

	public ChocoboItemFood(int healAmt, boolean wolfsFavorite)
	{
		super(healAmt, wolfsFavorite);
		this.maxStackSize = 64;
		this.setCreativeTab(ChocoCraftCreativeTabs.tabChococraft);
	}

	public void setDefaultModelLocation() {
		modelResourceLocation = new ModelResourceLocation(Constants.TCC_MODID + ":" + this.getUnlocalizedName().substring(5), "inventory");
	}

	@Override
	public ModelResourceLocation getDefaultModelLocation() {
		return modelResourceLocation;
	}
}
