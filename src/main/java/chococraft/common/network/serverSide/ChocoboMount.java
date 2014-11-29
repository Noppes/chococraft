package chococraft.common.network.serverSide;

import chococraft.common.entities.EntityAnimalChocobo;
import chococraft.common.entities.EntityChocoboRideable;
import chococraft.common.network.PacketHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by clienthax on 22/10/2014.
 */
public class ChocoboMount implements IMessage {

	public int entityID;
	public String playerName;//TODO uuid convert
	public int dimensionId;

	public ChocoboMount() {}

	public ChocoboMount(EntityChocoboRideable chocobo) {
		this.entityID = chocobo.getEntityId();
		this.playerName = FMLClientHandler.instance().getClient().thePlayer.getDisplayName().getUnformattedText();
		this.dimensionId = chocobo.worldObj.provider.getDimensionId();
	}

	@Override
	public void toBytes(ByteBuf buffer) {
		buffer.writeInt(this.entityID);
		ByteBufUtils.writeUTF8String(buffer, this.playerName);
		buffer.writeInt(this.dimensionId);
	}

	@Override
	public void fromBytes(ByteBuf buffer) {
		this.entityID = buffer.readInt();
		this.playerName = ByteBufUtils.readUTF8String(buffer);
		this.dimensionId = buffer.readInt();
	}

	public static class Handler implements IMessageHandler<ChocoboMount, IMessage> {

		@Override
		public IMessage onMessage(ChocoboMount message, MessageContext ctx) {

			EntityAnimalChocobo chocobo = PacketHelper.getChocoboByID(message.entityID, message.dimensionId);
			if(chocobo != null && chocobo instanceof EntityChocoboRideable)
			{
				EntityChocoboRideable chocoboRideable = (EntityChocoboRideable)chocobo;
				if(message.playerName.isEmpty())
				{
					chocoboRideable.riddenByEntity = null;
					chocoboRideable.setJumping(false);
					chocoboRideable.setStepHeight(false);
					chocoboRideable.setLandSpeedFactor(false);
					chocoboRideable.setJumpHigh(false);
				}
				else
				{
					EntityPlayer rider = PacketHelper.getPlayer(message.playerName, message.dimensionId);
					rider.setSprinting(false);
					rider.mountEntity(chocoboRideable);
					chocoboRideable.setStepHeight(true);
					chocoboRideable.setLandSpeedFactor(true);
					chocoboRideable.setJumpHigh(true);
				}
			}
			return null;
		}
	}
}
