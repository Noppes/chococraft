package chococraft.common.config;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by clienthax on 24/10/2014.
 */
public class ChocoCraftCreativeTabs {

	public static final CreativeTabs tabChococraft = new CreativeTabs(CreativeTabs.getNextID(), "ChocoCraft"){
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {return ChocoCraftItems.chocoboFeatherItem;}
	};

}
