package dev.dixmk.minepreggo.network.packet.c2s;

import java.util.Set;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.ai.goal.GoalHelper;
import dev.dixmk.minepreggo.world.entity.ai.goal.RestrictedWanderGoal;
import dev.dixmk.minepreggo.world.entity.ai.goal.ReturnToHomeGoal;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.MovementState;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamableEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamablePregnantEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamableZombieGirl;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public record UpdatePreggoMobWaitC2SPacket(int preggoMobId, MovementState movementState) {

	public static UpdatePreggoMobWaitC2SPacket decode(FriendlyByteBuf buffer) {	
		return new UpdatePreggoMobWaitC2SPacket(
				buffer.readVarInt(),
				buffer.readEnum(MovementState.class));
	}
	
	public static void encode(UpdatePreggoMobWaitC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.preggoMobId);
		buffer.writeEnum(message.movementState);
	}

	public static void handler(UpdatePreggoMobWaitC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
            if (context.getDirection().getReceptionSide().isServer()) {
    			var serverPlayer = context.getSender();			
    			var level = serverPlayer.level();			
    			if (!(level.getEntity(message.preggoMobId) instanceof PreggoMob preggoMob)) {	
    				return;
    			}
    			
				if (preggoMob instanceof AbstractTamableZombieGirl zombieGirl) {			
					if (zombieGirl instanceof AbstractTamablePregnantZombieGirl pregnantZombieGirl) {
						applyMovementStateBeingPregnant(pregnantZombieGirl, message.movementState);
					}
					else {
						applyMovementState(zombieGirl, message.movementState);
					}
				}
				else if (preggoMob instanceof AbstractTamableCreeperGirl creeperGirl) {
					if (creeperGirl instanceof AbstractTamablePregnantCreeperGirl pregnantCreeperGirl) {
						applyMovementStateBeingPregnant(pregnantCreeperGirl, message.movementState);
					}
					else {
						applyMovementState(creeperGirl, message.movementState);
					}
				}
				else if (preggoMob instanceof AbstractTamableEnderWoman enderWoman) {
					if (enderWoman instanceof AbstractTamablePregnantEnderWoman pregnantEnderWoman) {
						applyMovementStateBeingPregnant(pregnantEnderWoman, message.movementState);
					}
					else {
						applyMovementState(enderWoman, message.movementState);
					}
				}
				else {
					MinepreggoMod.LOGGER.warn("Received UpdatePreggoMobWaitC2SPacket for entity that is not a known tamable preggo mob. Class {}", preggoMob.getClass().getName());
				}
            }
		});
		context.setPacketHandled(true);
	}
	
	private static<E extends PreggoMob & ITamablePreggoMob<?>> void applyMovementState(E preggoMob, MovementState movementState) {
		preggoMob.getTamableData().setMovementState(movementState);
		if (movementState == MovementState.WANDERING) {
			PreggoMobHelper.addWanderingGoals(preggoMob, 7, 4);
		}
		else {
			GoalHelper.removeGoalByClass(preggoMob.goalSelector, Set.of(RestrictedWanderGoal.class, ReturnToHomeGoal.class));
		}
		MinepreggoMod.LOGGER.debug("Set movement state of {} with id {} to {}", preggoMob.getClass().getSimpleName(), preggoMob.getId(), preggoMob.getTamableData().getMovementState());
	}
	
	private static<E extends PreggoMob & ITamablePregnantPreggoMob> void applyMovementStateBeingPregnant(E preggoMob, MovementState movementState) {
		preggoMob.getTamableData().setMovementState(movementState);
		if (movementState == MovementState.WANDERING) {
			PreggoMobHelper.addWanderingGoalsBeingPregnant(preggoMob, 7, 4);
		}
		else {
			GoalHelper.removeGoalByClass(preggoMob.goalSelector, Set.of(RestrictedWanderGoal.class, ReturnToHomeGoal.class));
		}
		MinepreggoMod.LOGGER.debug("Set movement state of {} with id {} to {}", preggoMob.getClass().getSimpleName(), preggoMob.getId(), preggoMob.getTamableData().getMovementState());
	}
}
