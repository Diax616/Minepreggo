package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.server.ServerCinematicManager;
import dev.dixmk.minepreggo.utils.ServerParticleUtil;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.pregnancy.Gender;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

public record ResponseSexRequestP2PC2SPacket(int sourcePlayerId, int targetPlayerId, boolean accept) {

	public static ResponseSexRequestP2PC2SPacket decode(FriendlyByteBuf buffer) {	
		return new ResponseSexRequestP2PC2SPacket(
				buffer.readVarInt(),
				buffer.readVarInt(),
				buffer.readBoolean());
	}
	
	public static void encode(ResponseSexRequestP2PC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.sourcePlayerId);
		buffer.writeVarInt(message.targetPlayerId);
		buffer.writeBoolean(message.accept);
	}
	
	public static void handler(ResponseSexRequestP2PC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			if (context.getDirection().getReceptionSide().isServer()) {									
				var sender = context.getSender();
				var level = sender.level();
				final ServerPlayer source = level.getEntity(message.sourcePlayerId) instanceof ServerPlayer s ? s : null;
				final ServerPlayer target = level.getEntity(message.targetPlayerId) instanceof ServerPlayer t ? t : null;
		
				if (source != null && target != null) {					
					if (message.accept) {	
						accept(source, target);
					}
					else {
						reject(source, target);
					}
				}
				else {
					MinepreggoMod.LOGGER.warn("Could not find source or target player for ResponseSexRequestP2PC2SPacket: source id {}, target id {}", message.sourcePlayerId, message.targetPlayerId);
				}
			}
		});
		context.setPacketHandled(true);
	}
	
	private static void accept(ServerPlayer source, ServerPlayer target) {
		Runnable task = () -> {
			ServerParticleUtil.spawnRandomlyFromServer(source, ParticleTypes.HEART);
			ServerParticleUtil.spawnRandomlyFromServer(target, ParticleTypes.HEART);
		};
			
		Runnable end = () -> {
			source.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(sourceCap ->
				target.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(targetCap -> {
					if (sourceCap.getGender() == Gender.FEMALE) {
						if (!PlayerHelper.tryStartPregnancyBySex(source, target)) {
							MinepreggoMod.LOGGER.warn("Failed to impregnate female player {} by player {}", source.getUUID(), target.getUUID());
						}
						PlayerHelper.removeHorny(source);
					}
					else if (targetCap.getGender() == Gender.FEMALE) {
						if (!PlayerHelper.tryStartPregnancyBySex(target, source)) {
							MinepreggoMod.LOGGER.warn("Failed to impregnate female player {} by player {}", target.getUUID(), source.getUUID());
						}
						PlayerHelper.removeHorny(target);
					}
					
					sourceCap.getBreedableData().ifPresent(breedableData -> {
						breedableData.setFertilityRate(0);
						breedableData.reduceSexualAppetite(5);
					});
						
					targetCap.getBreedableData().ifPresent(breedableData -> {
						breedableData.setFertilityRate(0);
						breedableData.reduceSexualAppetite(5);
					});			
				})
			);
		};
		
		MinepreggoModPacketHandler.queueServerWork(20, task);		
		MinepreggoModPacketHandler.queueServerWork(40, task);
		
		ServerCinematicManager.getInstance().start(target, source, task, end);
	
		int overlayTicks = 120;
		int overlayPauseTicks = 60;
			
        MinepreggoModPacketHandler.INSTANCE.send(
                PacketDistributor.PLAYER.with(() -> source),
                new SexCinematicControlP2PS2CPacket(true)
            );	               
        MinepreggoModPacketHandler.INSTANCE.send(
                PacketDistributor.PLAYER.with(() -> target),
                new SexCinematicControlP2PS2CPacket(true)
            );	      
              
        MinepreggoModPacketHandler.INSTANCE.send(
    			PacketDistributor.PLAYER.with(() -> source),
    			new RenderSexOverlayS2CPacket(overlayTicks, overlayPauseTicks)
    		);     		
        MinepreggoModPacketHandler.INSTANCE.send(
    			PacketDistributor.PLAYER.with(() -> target),
    			new RenderSexOverlayS2CPacket(overlayTicks, overlayPauseTicks)
    		); 
        
		MinepreggoModPacketHandler.queueServerWork(overlayTicks * 2 + overlayPauseTicks, () -> {
			ServerCinematicManager.getInstance().end(source);
			ServerCinematicManager.getInstance().end(target);
			
	        MinepreggoModPacketHandler.INSTANCE.send(
	                PacketDistributor.PLAYER.with(() -> source),
	                new SexCinematicControlP2PS2CPacket(false)
	            );	               
	        MinepreggoModPacketHandler.INSTANCE.send(
	                PacketDistributor.PLAYER.with(() -> target),
	                new SexCinematicControlP2PS2CPacket(false)
	            );
		});		
	}
	
	private static void reject(ServerPlayer source, ServerPlayer target) {
		MessageHelper.sendTo(source, Component.translatable("chat.minepreggo.player.sex.request_reject", target.getName().getString()), true);
		ServerParticleUtil.spawnRandomlyFromServer(target, ParticleTypes.SMOKE);			
	}
}
