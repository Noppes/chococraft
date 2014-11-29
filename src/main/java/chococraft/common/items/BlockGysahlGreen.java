// <copyright file="BlockGysahlGreen.java">
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
// <summary>Block class for Gysahl Greens</summary>

package chococraft.common.items;

import chococraft.common.config.ChocoCraftCreativeTabs;
import chococraft.common.config.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFurnace;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.List;


public class BlockGysahlGreen extends BlockBush
{
	public BlockGysahlGreen()
    {
		setTickRandomly(true);
        float f = 0.5F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
		this.disableStats();
		setStepSound(Block.soundTypeGrass);
		setHardness(0.0F);
		setUnlocalizedName("gysahlGreenBlock");
		this.setCreativeTab(ChocoCraftCreativeTabs.tabChococraft);
//		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(this), 0, new ModelResourceLocation(Constants.TCC_MODID + ":" + Constants.KEY_GY_GREEN, "inventory"));
    }

}
