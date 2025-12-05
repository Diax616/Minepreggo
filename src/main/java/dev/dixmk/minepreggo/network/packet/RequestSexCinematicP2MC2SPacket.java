package dev.dixmk.minepreggo.network.packet;

import java.util.Optional;
import java.util.function.Supplier;

import org.apache.commons.lang3.tuple.ImmutableTriple;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.client.particle.ParticleHelper;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.server.ServerCinematicManager;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobFace;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.FemaleEntityImpl;
import dev.dixmk.minepreggo.world.pregnancy.IBreedable;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record RequestSexCinematicP2MC2SPacket(int mobId) {
	
	public static RequestSexCinematicP2MC2SPacket decode(FriendlyByteBuf buffer) {	
		return new RequestSexCinematicP2MC2SPacket(
				buffer.readVarInt());
		}
		
	public static void encode(RequestSexCinematicP2MC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.mobId);
	}
	 	 
    public static void handler(RequestSexCinematicP2MC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {					
            if (context.getDirection().getReceptionSide().isServer()) {
                var source = context.getSender();
                var level = source.level();
                		
                if (level.getEntity(message.mobId) instanceof PreggoMob preggoMob    
                		&& preggoMob instanceof ITamablePreggoMob<?> tamedPreggoMob) {
                		  
                		if (!PreggoMobHelper.canOwnerActivateSexEvent(source, preggoMob)) {
                			return;
                		}
 		
                		ServerCinematicManager.getInstance().start(
                				source,
                				preggoMob,
                				() -> {
                					ParticleHelper.spawnRandomlyFromServer(preggoMob, ParticleTypes.HEART);
                					tamedPreggoMob.setFaceState(PreggoMobFace.BLUSHED);  
                				}, () -> {
                					tamedPreggoMob.cleanFaceState();
                					impregnate(source, tamedPreggoMob);
                				});
                		
                        tamedPreggoMob.setCinematicOwner(source);
                        tamedPreggoMob.setCinematicEndTime(source.level().getGameTime() + 120 * 2 + 60);
                  
                        MinepreggoModPacketHandler.INSTANCE.send(
                            PacketDistributor.PLAYER.with(() -> source),
                            new SexCinematicControlP2MS2CPacket(true, message.mobId)
                        );	               
    					MinepreggoModPacketHandler.INSTANCE.send(
    						PacketDistributor.PLAYER.with(() -> source),
    						new RenderSexOverlayS2CPacket(120, 60));           		
                } 
            }			
		});
		context.setPacketHandled(true);
    }
    
    private static void impregnate(ServerPlayer player, ITamablePreggoMob<?> tamablePreggoMob) {
    	player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> {	
    		// Player is male and preggomob is female
    		cap.getMaleData().ifPresent(maleData -> {
    			if (tamablePreggoMob.getGenderedData() instanceof FemaleEntityImpl femaleData) {
    				final var numOfBabies = IBreedable.calculateNumOfBabiesByFertility(maleData.getFertilityRate(), femaleData.getFertilityRate());
    				
    				if (numOfBabies > 0) {
    					boolean result = femaleData.tryImpregnate(numOfBabies, ImmutableTriple.of(Optional.of(player.getUUID()), Species.HUMAN, Creature.HUMANOID));    					
    					
    					if (!result) {
    						MinepreggoMod.LOGGER.debug("Impregnation failed during pregnancy application phase");
						}
    				}
    				else {
						MinepreggoMod.LOGGER.debug("Impregnation failed during pregnancy attempt phase, player fertility rate: {}, preggo mob fertility rate: {}", maleData.getFertilityRate(), femaleData.getFertilityRate());
    				}
    			}
    		}); 		
    				
    		// Player is female and preggomob is male
    	});
    }
    
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(RequestSexCinematicP2MC2SPacket.class, RequestSexCinematicP2MC2SPacket::encode, RequestSexCinematicP2MC2SPacket::decode, RequestSexCinematicP2MC2SPacket::handler);
	}
}
