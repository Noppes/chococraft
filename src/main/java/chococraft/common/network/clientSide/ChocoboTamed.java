package chococraft.common.network.clientSide;

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
public class ChocoboTamed implements IMessage {

	public int entityID;
	public boolean isTamed;
	public String ownerName;//update to uuid in future TODO
	public int dimensionId;

	public ChocoboTamed() {}

	public ChocoboTamed(EntityAnimalChocobo chocobo) {
		this.entityID = chocobo.getEntityId();
		this.isTamed = chocobo.isTamed();
		this.ownerName = chocobo.getOwner().getDisplayName().getUnformattedText();
		this.dimensionId = chocobo.worldObj.provider.getDimensionId();
	}

	@Override
	public void toBytes(ByteBuf buffer) {
		buffer.writeInt(this.entityID);
		buffer.writeBoolean(isTamed);
		ByteBufUtils.writeUTF8String(buffer, ownerName);
		buffer.writeInt(this.dimensionId);
	}

	@Override
	public void fromBytes(ByteBuf buffer) {
		this.entityID = buffer.readInt();
		this.isTamed = buffer.readBoolean();
		this.ownerName = ByteBufUtils.readUTF8String(buffer);
		this.dimensionId = buffer.readInt();
	}

	public static class Handler implements IMessageHandler<ChocoboTamed, IMessage> {

		@Override
		public IMessage onMessage(ChocoboTamed message, MessageContext ctx) {
			EntityAnimalChocobo chicobo = PacketHelper.getChocoboByID(message.entityID, message.dimensionId);
			if(chicobo != null)
			{
				chicobo.setTamed(message.isTamed);
				chicobo.setOwnerId(message.ownerName);//setowner
			}
			return null;
		}
	}
}
