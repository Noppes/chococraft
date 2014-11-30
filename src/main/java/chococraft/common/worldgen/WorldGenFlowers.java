package chococraft.common.worldgen;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenFlowers extends WorldGenerator
{
	private Block blockToGenerate;
	private static final String __OBFID = "CL_00000410";

	public WorldGenFlowers(Block p_i45452_1_)
	{
		this.blockToGenerate = p_i45452_1_;
	}
	public boolean generate(World world, Random random, BlockPos pos)
	{
		for (int l = 0; l < 64; ++l)
		{
			pos = pos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));

			if (world.isAirBlock(pos) && (!world.provider.getHasNoSky() || pos.getY() < 255) && this.blockToGenerate.canPlaceBlockAt(world,pos))//.canBlockStay(world, pos))
			{
				world.setBlockState(pos, this.blockToGenerate.getDefaultState());

			}
		}

		return true;
	}
}