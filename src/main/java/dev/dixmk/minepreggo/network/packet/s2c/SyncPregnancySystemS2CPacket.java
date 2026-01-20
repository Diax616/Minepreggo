package dev.dixmk.minepreggo.network.packet.s2c;

import java.util.UUID;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.network.capability.PlayerPregnancyDataImpl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public record SyncPregnancySystemS2CPacket(UUID targetId, PlayerPregnancyDataImpl.PlayerStateData data) {

	public static SyncPregnancySystemS2CPacket decode(FriendlyByteBuf buffer) {	
		return new SyncPregnancySystemS2CPacket(
				buffer.readUUID(),
				PlayerPregnancyDataImpl.PlayerStateData.decode(buffer));
	}
	
	public static void encode(SyncPregnancySystemS2CPacket message, FriendlyByteBuf buffer) {	
		buffer.writeUUID(message.targetId);
		message.data.encode(buffer);
	}
	
	public static void handler(SyncPregnancySystemS2CPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {			
			if (context.getDirection().getReceptionSide().isClient()) {
				final var target = Minecraft.getInstance().player.level().getPlayerByUUID(message.targetId);
				if (target != null) {
					target.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> {
						cap.getFemaleData().ifPresent(femaleData -> {
							var pregnancySystem = femaleData.getPregnancyData();
							var newPhase = message.data.currentPregnancyPhase();
							
							pregnancySystem.getPregnancySymptoms().setPregnancySymptoms(PregnancySymptom.fromBitMask(message.data.pregnancySymptoms()));
							pregnancySystem.setPregnancyPain(message.data.pregnancyPain());
							pregnancySystem.setCurrentPregnancyStage(newPhase);						
						});
					});
				}	
			}			
		});
		context.setPacketHandled(true);
	}	
}
