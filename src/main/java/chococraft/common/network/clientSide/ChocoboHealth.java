package chococraft.common.network.clientSide;

import chococraft.common.entities.EntityAnimalChocobo;
import chococraft.common.network.PacketHelper;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by clienthax on 22/10/2014.
 */
public class ChocoboHealth implements IMessage {

	public int entityID;
	public int health;
	public int dimensionId;

	public ChocoboHealth() {}

	public ChocoboHealth(EntityAnimalChocobo chocobo) {
		this.entityID = chocobo.getEntityId();
		this.health = (int)chocobo.getHealth();
		this.dimensionId = chocobo.worldObj.provider.getDimensionId();
	}

	@Override
	public void toBytes(ByteBuf buffer) {
		buffer.writeInt(this.entityID);
		buffer.writeInt(this.health);
		buffer.writeInt(this.dimensionId);
	}

	@Override
	public void fromBytes(ByteBuf buffer) {
		this.entityID = buffer.readInt();
		this.health = buffer.readInt();
		this.dimensionId = buffer.readInt();
	}

	public static class Handler implements IMessageHandler<ChocoboHealth, IMessage> {

		@Override
		public IMessage onMessage(ChocoboHealth message, MessageContext ctx) {
			EntityAnimalChocobo chocobo = PacketHelper.getChocoboByID(message.entityID, message.dimensionId);
			if(chocobo != null)
			{
				chocobo.setHealth(message.health);
			}
			return null;
		}
	}
}
