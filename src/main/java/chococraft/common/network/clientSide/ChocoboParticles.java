package chococraft.common.network.clientSide;

import chococraft.common.entities.EntityAnimalChocobo;
import chococraft.common.helper.ChocoboParticleHelper;
import chococraft.common.network.PacketHelper;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

/**
 * Created by clienthax on 22/10/2014.
 */
public class ChocoboParticles implements IMessage {

	public int entityID;
	public int particleID;
	public int particleAmount;
	public int dimensionId;

	public ChocoboParticles() {}

	public ChocoboParticles(EntityAnimalChocobo chocobo, EnumParticleTypes particleType, int particleAmount) {
		this.entityID = chocobo.getEntityId();
		this.particleID = particleType.ordinal();
		this.particleAmount = particleAmount;
		this.dimensionId = chocobo.worldObj.provider.getDimensionId();
	}

	@Override
	public void toBytes(ByteBuf buffer) {
		buffer.writeInt(this.entityID);
		buffer.writeInt(this.particleID);
		buffer.writeInt(particleAmount);
		buffer.writeInt(this.dimensionId);
	}

	@Override
	public void fromBytes(ByteBuf buffer) {
		this.entityID = buffer.readInt();
		this.particleID = buffer.readInt();
		this.particleAmount = buffer.readInt();
		this.dimensionId = buffer.readInt();
	}

	public static class Handler implements IMessageHandler<ChocoboParticles, IMessage> {

		@Override
		public IMessage onMessage(ChocoboParticles message, MessageContext ctx) {
			EntityAnimalChocobo chocobo = PacketHelper.getChocoboByID(message.entityID, message.dimensionId);
			if(chocobo != null)
			{
				ChocoboParticleHelper.showParticleAroundEntityFx(EnumParticleTypes.values()[message.particleID], chocobo, message.particleAmount);
			}
			return null;
		}
	}
}
