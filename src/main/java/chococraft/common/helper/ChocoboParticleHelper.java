package chococraft.common.helper;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;

public class ChocoboParticleHelper
{
	// "bubble", "suspended", "depthsuspend", "townaura", "crit", "magicCrit",
	// "smoke", "mobSpell", "mobSpellAmbient", "spell", "instantSpell", "witchMagic", "note", "portal", 
	// "enchantmenttable", "explode", "flame", "lava", "footstep", "splash",
	// "largesmoke", "cloud", "reddust", "snowballpoof", "dripWater", "dripLava",
	// "snowshovel", "slime", "heart", "angryVillager", "happyVillager"
    public static void showParticleAroundEntityFx(EnumParticleTypes particleType, Entity entity)
    {
    	if(entity.worldObj.isRemote)
    	{
    		Random rand = new Random();

    		double partPosX = (entity.posX + (double)(rand.nextFloat() * entity.width * 2.0F)) - (double)entity.width;
    		double partPosY = entity.posY + 0.5D + (double)(rand.nextFloat() * entity.height);
    		double partPosZ = (entity.posZ + (double)(rand.nextFloat() * entity.width * 2.0F)) - (double)entity.width;
    		double partVelX = rand.nextGaussian() * 0.02D;
    		double partVelY = rand.nextGaussian() * 0.02D;
    		double partVelZ = rand.nextGaussian() * 0.02D;
    		entity.worldObj.spawnParticle(particleType, partPosX, partPosY, partPosZ, partVelX, partVelY, partVelZ);
    	}
    }
    
    public static void showParticleAroundEntityFx(EnumParticleTypes particleType, Entity entity, int amount)
    {
    	for(int i = 0; i < amount; i++)
    	{
    		showParticleAroundEntityFx(particleType, entity);
    	}
    }
    
    public static void showParticleAroundEntityFxDebugger(EnumParticleTypes particleType, Entity entity)
    {
    	showParticleAroundEntityFx(particleType, entity);
    } 
}
