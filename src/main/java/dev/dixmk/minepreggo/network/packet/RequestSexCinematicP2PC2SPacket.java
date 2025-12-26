package dev.dixmk.minepreggo.network.packet;

import java.util.UUID;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.client.particle.ParticleHelper;
import dev.dixmk.minepreggo.server.ServerCinematicManager;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record RequestSexCinematicP2PC2SPacket(UUID targetPlayerUUID) {
	
	public static RequestSexCinematicP2PC2SPacket decode(FriendlyByteBuf buffer) {	
		return new RequestSexCinematicP2PC2SPacket(
				buffer.readUUID());
		}
		
	public static void encode(RequestSexCinematicP2PC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeUUID(message.targetPlayerUUID);
	}
	 	 
    public static void handler(RequestSexCinematicP2PC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {					
            if (context.getDirection().getReceptionSide().isServer()) {
                var source = context.getSender();
                var level = source.level();
                		
                if (level.getPlayerByUUID(message.targetPlayerUUID) instanceof ServerPlayer target
                		&& source.distanceToSqr(target) <= 32
                		&& !ServerCinematicManager.getInstance().isInCinematic(target)) {
                		      		
                		ServerCinematicManager.getInstance().start(
                				source,
                				target,
                				() -> ParticleHelper.spawnRandomlyFromServer(target, ParticleTypes.HEART),
                				null);
         
                	 	final int totalOverlayTicks = 120;
                	 	final int totalPauseTicks = 60;
                		
                        sendControlPacket(source, true);
                        sendControlPacket(target, true);
                        
                        sendOverlayPacket(source, totalOverlayTicks, totalPauseTicks); 
                        sendOverlayPacket(target, totalOverlayTicks, totalPauseTicks);
    					                    
    					MinepreggoModPacketHandler.queueServerWork(totalOverlayTicks + totalPauseTicks, () -> {
    	                    if (ServerCinematicManager.getInstance().isInCinematic(source) || ServerCinematicManager.getInstance().isInCinematic(target)) {        		
    	                    		ServerCinematicManager.getInstance().end(source);	                   		
    	                            sendControlPacket(source, false);
    	                            sendControlPacket(target, false);	
    	                        }
    					});
                } 
            }			
		});
		context.setPacketHandled(true);
    }
    
    private static void sendControlPacket(ServerPlayer player, boolean start) {
    	MinepreggoModPacketHandler.INSTANCE.send(
            PacketDistributor.PLAYER.with(() -> player),
            new SexCinematicControlP2PS2CPacket(start)
        );
    }
    
    private static void sendOverlayPacket(ServerPlayer player, int totalOverlayTicks, int totalPauseTicks) {
		MinepreggoModPacketHandler.INSTANCE.send(
				PacketDistributor.PLAYER.with(() -> player),
				new RenderSexOverlayS2CPacket(totalOverlayTicks, totalPauseTicks));   
    }
    
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(RequestSexCinematicP2PC2SPacket.class, RequestSexCinematicP2PC2SPacket::encode, RequestSexCinematicP2PC2SPacket::decode, RequestSexCinematicP2PC2SPacket::handler);
	}
}
