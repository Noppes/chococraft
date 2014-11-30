// <copyright file="WorldGenGysahls.java">
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
// <date>2012-05-09</date>
// <summary>distributing wild Gysahl greens in a newly generated chunk</summary>

package chococraft.common.worldgen;

import java.util.Random;

import chococraft.common.ModChocoCraft;
import chococraft.common.config.ChocoCraftBlocks;
import net.minecraft.block.BlockFlower;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;


public class WorldGenGysahls implements IWorldGenerator
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if(!world.isRemote && world.provider.isSurfaceWorld())
		{
			if(random.nextInt(1000) <= ModChocoCraft.gysahlWorldGenRate)
			{
				int randPosX = chunkX*16 + random.nextInt(16);
				int randPosZ = chunkZ*16 + random.nextInt(16);
				int randPosY = world.getTopSolidOrLiquidBlock(new BlockPos(randPosX, 0, randPosZ)).getY();
				new WorldGenFlowers(ChocoCraftBlocks.gysahlGreenBlock).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
			}
		}
	}
}
