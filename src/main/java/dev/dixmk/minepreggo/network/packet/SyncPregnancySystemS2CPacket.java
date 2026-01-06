package dev.dixmk.minepreggo.network.packet;

import java.util.UUID;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.client.jiggle.JigglePhysicsManager;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.network.capability.PlayerPregnancySystemImpl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public record SyncPregnancySystemS2CPacket(UUID targetId, PlayerPregnancySystemImpl.ClientData data) {

	public static SyncPregnancySystemS2CPacket decode(FriendlyByteBuf buffer) {	
		return new SyncPregnancySystemS2CPacket(
				buffer.readUUID(),
				PlayerPregnancySystemImpl.ClientData.decode(buffer));
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
							var pregnancySystem = femaleData.getPregnancySystem();
							var previousPhase = pregnancySystem.getCurrentPregnancyStage();
							var newPhase = message.data.currentPregnancyPhase();
							
							pregnancySystem.setPregnancySymptoms(PregnancySymptom.fromBitMask(message.data.pregnancySymptoms()));
							pregnancySystem.setPregnancyPain(message.data.pregnancyPain());
							pregnancySystem.setCurrentPregnancyStage(newPhase);
							
							
							if (previousPhase != newPhase) {
								JigglePhysicsManager.getInstance().updateForPhase(message.targetId, newPhase, cap.getSkinType());
							}
						});
					});
				}	
			}			
		});
		context.setPacketHandled(true);
	}	
}
