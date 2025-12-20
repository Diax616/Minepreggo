package dev.dixmk.minepreggo.network.packet;

import java.util.UUID;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.network.capability.FemalePlayerImpl;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancy;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancyData;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record SyncFemalePlayerDataS2CPacket(UUID source, FemalePlayerImpl.ClientData data) {

	public static SyncFemalePlayerDataS2CPacket decode(FriendlyByteBuf buffer) {		
		
		UUID source = buffer.readUUID();
		boolean pregnant = buffer.readBoolean();
		PostPregnancyData postPregnancy = null;
		
		if (buffer.readBoolean()) {
			postPregnancy = new PostPregnancyData(buffer.readEnum(PostPregnancy.class));
		}
		
		float fertility = buffer.readFloat();
			
		return new SyncFemalePlayerDataS2CPacket(
				source,
				new FemalePlayerImpl.ClientData(pregnant, postPregnancy, fertility));
	}
	
	public static void encode(SyncFemalePlayerDataS2CPacket message, FriendlyByteBuf buffer) {
		buffer.writeUUID(message.source);
		buffer.writeBoolean(message.data.pregnant());
		buffer.writeBoolean(message.data.postPregnancy() != null);
		
		if (message.data.postPregnancy() != null) {
			var post = message.data.postPregnancy();
			buffer.writeEnum(post.getPostPregnancy());
			buffer.writeInt(post.getPostPartumLactation());
		}
		
		buffer.writeFloat(message.data.fertility());
	}
	
	public static void handler(SyncFemalePlayerDataS2CPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {			
			if (context.getDirection().getReceptionSide().isClient()) {	
				final Player target = Minecraft.getInstance().player.level().getPlayerByUUID(message.source);
				if (target == null) return;
				target.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
					cap.getFemaleData().ifPresent(femaleData -> femaleData.updateFromServer(message.data))
				);			
			}			
		});
		context.setPacketHandled(true);
	}
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(SyncFemalePlayerDataS2CPacket.class, SyncFemalePlayerDataS2CPacket::encode, SyncFemalePlayerDataS2CPacket::decode, SyncFemalePlayerDataS2CPacket::handler);
	}
}
