package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.server.ServerCinematicManager;
import dev.dixmk.minepreggo.utils.ServerParticleUtil;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobFace;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.world.pregnancy.FemaleEntityImpl;
import dev.dixmk.minepreggo.world.pregnancy.IFemaleEntity;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancyEffectsHandler;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancySystemHandler;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

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
                		
                if (level.getEntity(message.mobId) instanceof PreggoMob preggoMob) {      		  
                	if (preggoMob instanceof AbstractTamableCreeperGirl<?> creeperGirl) {
                		init(source, creeperGirl);
                	}
                	else if (preggoMob instanceof AbstractTamableZombieGirl<?> zombieGirl) {
                		init(source, zombieGirl);
                	}
                } 
            }			
		});
		context.setPacketHandled(true);
    }
    
    private static<E extends PreggoMob & ITamablePreggoMob<FemaleEntityImpl> & IFemaleEntity> void init(ServerPlayer source, E preggoMob) {   	
    	if (!PreggoMobHelper.canOwnerActivateSexEvent(source, preggoMob)) {
			return;
		}

		ServerCinematicManager.getInstance().start(
				source,
				preggoMob,
				() -> {
					ServerParticleUtil.spawnRandomlyFromServer(preggoMob, ParticleTypes.HEART);
					preggoMob.setFaceState(PreggoMobFace.BLUSHED);  
				}, () -> {
					preggoMob.cleanFaceState();
					if (!(preggoMob instanceof IPregnancySystemHandler)) {
						PreggoMobHelper.initPregnancyBySex(preggoMob, source);
					}	
					else if (preggoMob instanceof IPregnancySystemHandler pregnancyHandler
							&& preggoMob instanceof IPregnancyEffectsHandler effectsHandler
							&& pregnancyHandler.getCurrentPregnancyStage().compareTo(PregnancyPhase.P4) >= 0) {
						pregnancyHandler.removePregnancySymptom(PregnancySymptom.HORNY);
						effectsHandler.setHorny(0);		
						effectsHandler.setHornyTimer(0);
					}
					
					preggoMob.reduceSexualAppetite(5);
					source.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> cap.getBreedableData().ifPresent(breedableData -> breedableData.reduceSexualAppetite(5)));					
					PlayerHelper.removeHorny(source);
				});
		
		preggoMob.setCinematicOwner(source);
		preggoMob.setCinematicEndTime(source.level().getGameTime() + 120 * 2 + 60);
  
        MinepreggoModPacketHandler.INSTANCE.send(
            PacketDistributor.PLAYER.with(() -> source),
            new SexCinematicControlP2MS2CPacket(true, preggoMob.getId())
        );	               
		MinepreggoModPacketHandler.INSTANCE.send(
			PacketDistributor.PLAYER.with(() -> source),
			new RenderSexOverlayS2CPacket(120, 60));  
    }
}
