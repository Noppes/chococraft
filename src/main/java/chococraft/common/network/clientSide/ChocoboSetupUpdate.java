package chococraft.common.network.clientSide;

import chococraft.common.ModChocoCraft;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.server.MinecraftServer;

/**
 * Created by clienthax on 22/10/2014.
 */
public class ChocoboSetupUpdate implements IMessage {//TODO - is this even needed..

	public boolean dedicatedServer;
	public boolean hungerEnabled;

	public ChocoboSetupUpdate() {
		this.dedicatedServer = false;
		if(MinecraftServer.getServer() != null)
			this.dedicatedServer = MinecraftServer.getServer().isDedicatedServer();
		this.hungerEnabled = ModChocoCraft.hungerEnabled;
	}


	@Override
	public void toBytes(ByteBuf buffer) {
		buffer.writeBoolean(this.dedicatedServer);
		buffer.writeBoolean(this.hungerEnabled);
	}

	@Override
	public void fromBytes(ByteBuf buffer) {
		this.dedicatedServer = buffer.readBoolean();
		this.hungerEnabled = buffer.readBoolean();
	}

	public static class Handler implements IMessageHandler<ChocoboSetupUpdate, IMessage> {

		@Override
		public IMessage onMessage(ChocoboSetupUpdate message, MessageContext ctx) {
			ModChocoCraft.isRemoteClient = message.dedicatedServer;
			ModChocoCraft.hungerEnabled = message.hungerEnabled;
			return null;
		}
	}
}
