package dev.dixmk.minepreggo.world.entity.preggo;

import java.util.ArrayList;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.network.packet.RequestSexM2PC2SPacket;
import dev.dixmk.minepreggo.world.pregnancy.AbstractPregnancySystem;
import dev.dixmk.minepreggo.world.pregnancy.FemaleEntityImpl;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancyEffectsHandler;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancySystemHandler;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;

public abstract class PreggoMobPregnancySystemP4<E extends PreggoMob
	& ITamablePreggoMob<FemaleEntityImpl> & IPregnancySystemHandler & IPregnancyEffectsHandler> extends PreggoMobPregnancySystemP3<E> {
	
	protected @Nonnegative int totalTicksOfHorny = MinepreggoModConfig.getTotalTicksOfHornyP4();
	protected @Nonnegative int totalTicksOfBirth = PregnancySystemHelper.TOTAL_TICKS_BIRTH_P4;
	protected @Nonnegative int totalTicksOfPreBirth = PregnancySystemHelper.TOTAL_TICKS_PREBIRTH_P4;
	
	private int sexRequestCooldown = 0;
	
	protected PreggoMobPregnancySystemP4(@Nonnull E preggoMob) {
		super(preggoMob);
		addNewValidPregnancySymptom(PregnancySymptom.HORNY);
	}
	
	@Override
	protected void initPregnancySymptomsTimers() {
		totalTicksOfCraving = MinepreggoModConfig.getTotalTicksOfCravingP4();
		totalTicksOfMilking = MinepreggoModConfig.getTotalTicksOfMilkingP4();
		totalTicksOfBellyRubs = MinepreggoModConfig.getTotalTicksOfBellyRubsP4();
	}
	
	@Override
	protected void evaluatePregnancyNeeds() {	
		super.evaluatePregnancyNeeds();
		evaluateHornyTimer();
	}
	
	@Override
	protected void evaluatePregnancySystem() {
		if (isInLabor()) {		
			if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
				evaluateBirth(serverLevel);
			}		
			return;
		}	
		
		if (isWaterBroken()) {
			if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
				evaluateWaterBreaking(serverLevel);
			}
			return;
		}
		
		if (canAdvanceNextPregnancyPhase() && hasToGiveBirth()) {
			breakWater();
			return;
		}
		
		tryRequestSexToOwner();
		
		super.evaluatePregnancySystem();
	}
	
	@Override
	protected void evaluateBirth(ServerLevel serverLevel) {		
		
		final var pain = pregnantEntity.getPregnancyPain();
		
		if (pain == PregnancyPain.PREBIRTH) {		
			if (pregnantEntity.getPregnancyPainTimer() >= totalTicksOfPreBirth) {
				pregnantEntity.setPregnancyPain(PregnancyPain.BIRTH);
				pregnantEntity.setBodyState(PreggoMobBody.NAKED);
	    		pregnantEntity.resetPregnancyPainTimer();   		
				PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.CHEST);
				PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.LEGS);
				PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.MAINHAND);
				PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.OFFHAND);
	        	MessageHelper.sendTo(MessageHelper.asServerPlayer((Player) pregnantEntity.getOwner()), Component.translatable("chat.minepreggo.preggo_mob.birth.message.pre", pregnantEntity.getSimpleName()));
			}	
			else {
	    		pregnantEntity.incrementPregnancyPainTimer();
	    		AbstractPregnancySystem.spawnParticulesForWaterBreaking(serverLevel, pregnantEntity);
			}
		}
		else if (pain == PregnancyPain.BIRTH) {
			if (pregnantEntity.getPregnancyPainTimer() >= totalTicksOfBirth) {			
				
	        	final var aliveBabiesItemStacks = new ArrayList<>(PregnancySystemHelper.getAliveBabies(pregnantEntity.getWomb()));   	
	       		
	        	MinepreggoMod.LOGGER.debug("PreggoMob {} is giving birth to {} babies.", pregnantEntity.getDisplayName().getString(), aliveBabiesItemStacks.size());
	        	
	        	if (aliveBabiesItemStacks.isEmpty()) {
					MinepreggoMod.LOGGER.error("Failed to get baby item for pregnancy system {} birth.", pregnantEntity.getCurrentPregnancyStage());
				}
	        	
	        	// TODO: Babies itemstacks are only removed if player's hands are empty. It should handle stacking unless itemstack is a baby item.
	        	aliveBabiesItemStacks.removeIf(baby -> {
	        		if (pregnantEntity.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
	        			PreggoMobHelper.setItemstackInHand(pregnantEntity, InteractionHand.MAIN_HAND, baby);
	            		return true;
	        		}
	        		else if (pregnantEntity.getItemInHand(InteractionHand.OFF_HAND).isEmpty()) {
	        			PreggoMobHelper.setItemstackInHand(pregnantEntity, InteractionHand.OFF_HAND, baby);
	            		return true;
	        		}
	        		return false;
	        	});
	        	
	        	if (!aliveBabiesItemStacks.isEmpty()) {	              	
		        	aliveBabiesItemStacks.forEach(baby -> PreggoMobHelper.storeItemInSpecificRangeOrDrop(pregnantEntity, baby, ITamablePreggoMob.FOOD_INVENTORY_SLOT + 1, pregnantEntity.getInventorySize() - 1)); 						
	        	}
	        	        	
	        	MessageHelper.sendTo(MessageHelper.asServerPlayer((Player) pregnantEntity.getOwner()), Component.translatable("chat.minepreggo.preggo_mob.birth.message.post", pregnantEntity.getSimpleName()));
				initPostPartum();	
	        	pregnantEntity.discard();   	
				MinepreggoMod.LOGGER.debug("PreggoMob {} has given birth.", pregnantEntity.getDisplayName().getString());	
			}	
			else {
	    		pregnantEntity.incrementPregnancyPainTimer();
			}
		}
	}
	
	@Override
	protected void startLabor() {
		pregnantEntity.setPregnancyPain(PregnancyPain.PREBIRTH);
		pregnantEntity.resetPregnancyPainTimer();
		pregnantEntity.setPickUpItems(false);
		pregnantEntity.setBreakBlocks(false);
		MinepreggoMod.LOGGER.debug("PreggoMob {} has started labor.", pregnantEntity.getDisplayName().getString());	
    	MessageHelper.sendTo(MessageHelper.asServerPlayer((Player) pregnantEntity.getOwner()), Component.translatable("chat.minepreggo.preggo_mob.birth.message.warning", pregnantEntity.getSimpleName()));
	}
	
	@Override
	protected void evaluateWaterBreaking(ServerLevel serverLevel) {
		if (pregnantEntity.getPregnancyPainTimer() >= PregnancySystemHelper.TOTAL_TICKS_WATER_BREAKING) {
			startLabor();
		}
		else {
			pregnantEntity.incrementPregnancyPainTimer();
    		AbstractPregnancySystem.spawnParticulesForWaterBreaking(serverLevel, pregnantEntity);
		}
	}
	
	@Override
	protected void breakWater() {
		pregnantEntity.resetPregnancyPainTimer();
		pregnantEntity.setPregnancyPain(PregnancyPain.WATER_BREAKING);
		MinepreggoMod.LOGGER.debug("PreggoMob {} water has broken.", pregnantEntity.getDisplayName().getString());
    	MessageHelper.sendTo(MessageHelper.asServerPlayer((Player) pregnantEntity.getOwner()), Component.translatable("chat.minepreggo.preggo_mob.birth.message.init", pregnantEntity.getSimpleName()));
	}
	
	@Override
	public boolean canBeAngry() {
		return super.canBeAngry() || pregnantEntity.getHorny() >= 20;	
	}
	
	@Override
	protected boolean hasToGiveBirth() {
		return pregnantEntity.getLastPregnancyStage() == pregnantEntity.getCurrentPregnancyStage();
	}
	
	@Override
	protected boolean isInLabor() {
		final var pain = pregnantEntity.getPregnancyPain();
		return pain == PregnancyPain.PREBIRTH || pain == PregnancyPain.BIRTH;
 	}
	
	@Override
	protected boolean isWaterBroken() {
		return pregnantEntity.getPregnancyPain() == PregnancyPain.WATER_BREAKING;
 	}
		
	@Override
	protected boolean tryInitPregnancySymptom() {
		if (super.tryInitPregnancySymptom()) {
			return true;
		}
		if (pregnantEntity.getHorny() >= PregnancySystemHelper.ACTIVATE_HORNY_SYMPTOM) {
	    	pregnantEntity.addPregnancySymptom(PregnancySymptom.HORNY);
	    	return true;		
		}
		return false;
	}
	
	@Override
	protected boolean tryInitRandomPregnancyPain() {
	    if (randomSource.nextFloat() < PregnancySystemHelper.LOW_MORNING_SICKNESS_PROBABILITY) {
	        pregnantEntity.setPregnancyPain(PregnancyPain.MORNING_SICKNESS);
	        pregnantEntity.resetPregnancyPainTimer();
	        return true;
	    }
		else if (randomSource.nextFloat() < PregnancySystemHelper.MEDIUM_PREGNANCY_PAIN_PROBABILITY) {
			
			if (hasToGiveBirth()) {
				pregnantEntity.setPregnancyPain(PregnancyPain.CONTRACTION);
			}		
			else {
				pregnantEntity.setPregnancyPain(PregnancyPain.FETAL_MOVEMENT);
			}

			pregnantEntity.resetPregnancyPainTimer();
			PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.CHEST);					
			return true;
		}     
	    return false;
	}
	
	@Override
	protected void evaluatePregnancyPains() {
		final var pain = pregnantEntity.getPregnancyPain();
	
		if ((pain == PregnancyPain.MORNING_SICKNESS && pregnantEntity.getPregnancyPainTimer() >= PregnancySystemHelper.TOTAL_TICKS_MORNING_SICKNESS)
				|| (pain == PregnancyPain.FETAL_MOVEMENT && pregnantEntity.getPregnancyPainTimer() >= PregnancySystemHelper.TOTAL_TICKS_KICKING_P4)
				|| (pain == PregnancyPain.CONTRACTION && pregnantEntity.getPregnancyPainTimer() >= PregnancySystemHelper.TOTAL_TICKS_CONTRACTION_P4)) {
			pregnantEntity.clearPregnancyPain();
			pregnantEntity.resetPregnancyPainTimer();
		}
		else {
			pregnantEntity.incrementPregnancyPainTimer();
		}
	}
	
	protected void evaluateHornyTimer() {
		if (pregnantEntity.getHorny() < PregnancySystemHelper.MAX_HORNY_LEVEL) {
	        if (pregnantEntity.getHornyTimer() >= totalTicksOfHorny) {
	        	pregnantEntity.incrementHorny();
	        	pregnantEntity.resetHornyTimer();
	        }
	        else {
	        	pregnantEntity.incrementHornyTimer();
	        }
		}	
	}
	
	// TODO: PreggoMob can only request sex if she is pregnant and horny enough. But She can't request if she's not pregnant. It should allow even if not pregnant.
	// It ignores if their owner (Player) is female or male
	protected boolean tryRequestSexToOwner() {	
		if (!pregnantEntity.getPregnancySymptoms().contains(PregnancySymptom.HORNY)) {
			return false;
		}
		
		if (sexRequestCooldown < 4800) {
			++sexRequestCooldown;
			return false;
		}
		
		var owner = pregnantEntity.getOwner();		
		if (owner != null && pregnantEntity.distanceToSqr(owner) < 25D && !pregnantEntity.isAggressive()) {
			sexRequestCooldown = 0;
			MinepreggoModPacketHandler.INSTANCE.sendToServer(new RequestSexM2PC2SPacket(pregnantEntity.getId(), owner.getId()));
			return true;
		}
		
		return false;
	}
}
