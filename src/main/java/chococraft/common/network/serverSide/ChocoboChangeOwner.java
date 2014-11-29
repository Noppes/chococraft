package chococraft.common.network.serverSide;

import chococraft.common.entities.EntityAnimalChocobo;
import chococraft.common.network.PacketHelper;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

/**
 * Created by clienthax on 22/10/2014.
 */
public class ChocoboChangeOwner implements IMessage {

	public int entityID;
	public String ownerName;//update to uuid in future TODO
	public boolean isTamed;
	public int dimensionId;

	public ChocoboChangeOwner() {}

	public ChocoboChangeOwner(EntityAnimalChocobo chocobo) {
		this.entityID = chocobo.getEntityId();
		this.ownerName = chocobo.func_152113_b();
		this.isTamed = chocobo.isTamed();
		this.dimensionId = chocobo.worldObj.provider.getDimensionId();
	}

	@Override
	public void toBytes(ByteBuf buffer) {
		buffer.writeInt(this.entityID);
		ByteBufUtils.writeUTF8String(buffer, this.ownerName);
		buffer.writeBoolean(this.isTamed);
		buffer.writeInt(this.dimensionId);
	}

	@Override
	public void fromBytes(ByteBuf buffer) {
		this.entityID = buffer.readInt();
		this.ownerName = ByteBufUtils.readUTF8String(buffer);
		this.isTamed = buffer.readBoolean();
		this.dimensionId = buffer.readInt();
	}

	public static class Handler implements IMessageHandler<ChocoboChangeOwner, IMessage> {

		@Override
		public IMessage onMessage(ChocoboChangeOwner message, MessageContext ctx) {
			EntityAnimalChocobo chocobo = PacketHelper.getChocoboByID(message.entityID, message.dimensionId);
			if(chocobo != null)
			{
				if(message.isTamed)
				{//setowner
					chocobo.func_152115_b(message.ownerName);
				}
			}
			return null;
		}
	}
}
