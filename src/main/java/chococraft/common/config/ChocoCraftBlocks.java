package chococraft.common.config;

import chococraft.common.items.BlockGysahlGreen;
import chococraft.common.items.BlockGysahlStem;
import chococraft.common.items.BlockStraw;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by clienthax on 23/10/2014.
 */
public class ChocoCraftBlocks {

	public static Block gysahlStemBlock;
	public static Block gysahlGreenBlock;
	public static Block strawBlock;

	public static void registerBlocks() {

		gysahlStemBlock = new BlockGysahlStem();
		GameRegistry.registerBlock(gysahlStemBlock, "gysahlStemBlock");

		gysahlGreenBlock = new BlockGysahlGreen();
		GameRegistry.registerBlock(gysahlGreenBlock, "gysahlGreenBlock");

		strawBlock = new BlockStraw();
		GameRegistry.registerBlock(strawBlock, "strawBlock");
	}
}
