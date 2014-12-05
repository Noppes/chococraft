package chococraft.common.entities.ai;

import net.minecraft.entity.ai.EntityAIBase;
import chococraft.common.entities.EntityAnimalChocobo;
import net.minecraft.pathfinding.PathNavigateGround;

public class ChocoboAISwimming extends EntityAIBase
{
	private EntityAnimalChocobo chocobo;
	private float originalStepHight;
	
	public ChocoboAISwimming(EntityAnimalChocobo chocobo)
	{
        this.setMutexBits(4);
        this.chocobo = chocobo;
		((PathNavigateGround)this.chocobo.getNavigator()).func_179693_d(true);//setCanSwim
	}
	
	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	@Override
	public boolean shouldExecute()
	{
		return this.chocobo.isInWater() || this.chocobo.isInLava();
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */	
	@Override
	public void startExecuting()
	{
		this.originalStepHight = this.chocobo.stepHeight;
		if(this.chocobo.stepHeight < 1.0F)
		{
			this.chocobo.stepHeight = 1.0F;
		}
	}

	/**
	 * Resets the task
	 */
	@Override
	public void resetTask()
	{
		this.chocobo.stepHeight = this.originalStepHight;
	}

	/**
	 * Updates the task
	 */
	@Override
	public void updateTask()
	{
		if (this.chocobo.getRNG().nextFloat() < 0.8F)
		{
			this.chocobo.setJumping(true);
			this.chocobo.motionY = 0.1D;
			this.chocobo.getJumpHelper().setJumping();
		}
	}
}
