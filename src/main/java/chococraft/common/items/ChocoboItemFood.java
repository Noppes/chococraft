package chococraft.common.items;

import chococraft.common.config.ChocoCraftCreativeTabs;
import net.minecraft.item.ItemFood;
import chococraft.common.config.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ChocoboItemFood extends ItemFood
{
	public ChocoboItemFood(int healAmt, boolean wolfsFavorite)
	{
		super(healAmt, wolfsFavorite);
		this.maxStackSize = 64;
		this.setCreativeTab(ChocoCraftCreativeTabs.tabChococraft);
	}
	
/*	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		String name = this.getUnlocalizedName().substring(5);
		this.itemIcon = iconRegister.registerIcon(Constants.TCC_MODID + ":" + name);
	}
*/
}
