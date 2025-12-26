package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.client.particle.ParticleHelper;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.server.ServerCinematicManager;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.pregnancy.Gender;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
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
				final ServerPlayer target = level.getEntity(message.sourcePlayerId) instanceof ServerPlayer s ? s : null;
				final ServerPlayer source = level.getEntity(message.targetPlayerId) instanceof ServerPlayer t ? t : null;
		
				if (source == null || target == null || !target.getUUID().equals(sender.getUUID())) return;
				
				if (message.accept) {	
					accept(source, target);
					return;
				}
				else {
					reject(target);
				}
			}
		});
		context.setPacketHandled(true);
	}
	
	private static void accept(ServerPlayer source, ServerPlayer target) {		
		source.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(sourceCap -> 
			target.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(targetCap -> {										
				Runnable impregnationTask = () -> {
			
					if (sourceCap.getGender() ==  Gender.FEMALE) {						
						PlayerHelper.tryStartPregnancyBySex(source, target);
						removeHorny(source);
					}
					else if (targetCap.getGender() ==  Gender.FEMALE) {						
						PlayerHelper.tryStartPregnancyBySex(target, source);
						removeHorny(target);
					}
						
					var breedableData = sourceCap.getBreedableData();				
					if (breedableData != null) {
						breedableData.setFertilityRate(0);
					}
					breedableData = targetCap.getBreedableData();
					if (breedableData != null) {
						breedableData.setFertilityRate(0);
					}
				};	
						
				final Runnable task = () -> {
					ParticleHelper.spawnRandomlyFromServer(source, ParticleTypes.HEART);
					ParticleHelper.spawnRandomlyFromServer(target, ParticleTypes.HEART);
				};
				
				MinepreggoModPacketHandler.queueServerWork(20, task);		
				MinepreggoModPacketHandler.queueServerWork(40, task);
				
				ServerCinematicManager.getInstance().start(target, source, task, impregnationTask);
			
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

			})
		);
	}
	
	private static void removeHorny(ServerPlayer source) {
		source.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
			cap.getFemaleData().ifPresent(femaleData -> {
				if (femaleData.isPregnant()
						&& femaleData.isPregnancySystemInitialized()
						&& femaleData.getPregnancySystem().getCurrentPregnancyStage().compareTo(PregnancyPhase.P4) >= 0) {				
					femaleData.getPregnancyEffects().setHorny(0);
					femaleData.getPregnancySystem().getPregnancySymptoms().remove(PregnancySymptom.HORNY);
					
					femaleData.getPregnancyEffects().sync(source);
					femaleData.getPregnancySystem().sync(source);
				}
			})			
		);
	}
	
	private static void reject(ServerPlayer target) {
		ParticleHelper.spawnRandomlyFromServer(target, ParticleTypes.SMOKE);			
	}
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(ResponseSexRequestP2PC2SPacket.class, ResponseSexRequestP2PC2SPacket::encode, ResponseSexRequestP2PC2SPacket::decode, ResponseSexRequestP2PC2SPacket::handler);
	}
	
}
