package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.world.inventory.preggo.RequestSexM2PMenu;
import dev.dixmk.minepreggo.world.pregnancy.AbstractPregnancySystem;
import dev.dixmk.minepreggo.world.pregnancy.IBreedable;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import dev.dixmk.minepreggo.world.pregnancy.SyncedSetPregnancySymptom;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;

public abstract class PreggoMobPregnancySystemP4
	<E extends PreggoMob & ITamablePregnantPreggoMob> extends PreggoMobPregnancySystemP3<E> {
	
	protected @Nonnegative int totalTicksOfHorny = MinepreggoModConfig.SERVER.getTotalTicksOfHornyP4();
	protected @Nonnegative int totalTicksOfBirth = PregnancySystemHelper.TOTAL_TICKS_BIRTH_P4;
	protected @Nonnegative int totalTicksOfPreBirth = PregnancySystemHelper.TOTAL_TICKS_PREBIRTH_P4;
	protected @Nonnegative float contractionProb = PregnancySystemHelper.HIGH_PREGNANCY_PAIN_PROBABILITY;

	private int sexRequestCooldown = 0;

	protected PreggoMobPregnancySystemP4(@Nonnull E preggoMob) {
		super(preggoMob);
		addNewValidPregnancySymptom(PregnancySymptom.HORNY);
		fetalMovementProb = PregnancySystemHelper.MEDIUM_PREGNANCY_PAIN_PROBABILITY;
	}
	
	@Override
	protected void initPregnancyTimers() {
		totalTicksOfCraving = MinepreggoModConfig.SERVER.getTotalTicksOfCravingP4();
		totalTicksOfMilking = MinepreggoModConfig.SERVER.getTotalTicksOfMilkingP4();
		totalTicksOfBellyRubs = MinepreggoModConfig.SERVER.getTotalTicksOfBellyRubsP4();
		totalTicksOfFetalMovement = PregnancySystemHelper.TOTAL_TICKS_KICKING_P4;
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
			if (pregnantEntity.getPregnancyData().getWomb().isWombOverloaded()) {
				if (pregnantEntity.isAlive()) {
					PregnancySystemHelper.tornWomb(pregnantEntity);
				}
				else {
					MinepreggoMod.LOGGER.debug("PreggoMob {} has a torn womb but is dead, skipping torn womb handling.", pregnantEntity.getDisplayName().getString());
				}
			} 
			else {
				breakWater();
			}
			return;
		}
		
		tryRequestSexToOwner();
		
		super.evaluatePregnancySystem();
	}
	
	@Override
	protected void evaluateBirth(ServerLevel serverLevel) {		
		final var pregnancyData = pregnantEntity.getPregnancyData();
		final var pain = pregnancyData.getPregnancyPain();
		if (pain == PregnancyPain.PREBIRTH) {		
			if (pregnancyData.getPregnancyPainTimer() >= totalTicksOfPreBirth) {
				tryHurt();
				pregnancyData.setPregnancyPain(PregnancyPain.BIRTH);
				pregnantEntity.getTamableData().setBodyState(PreggoMobBody.NAKED);
				pregnancyData.resetPregnancyPainTimer();   		
				PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.CHEST);
				PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.LEGS);
				PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.MAINHAND);
				PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.OFFHAND);
	        	MessageHelper.sendTo(MessageHelper.asServerPlayer((Player) pregnantEntity.getOwner()), Component.translatable("chat.minepreggo.preggo_mob.birth.message.pre", pregnantEntity.getSimpleName()));
			}	
			else {
				pregnancyData.incrementPregnancyPainTimer();
	    		AbstractPregnancySystem.spawnParticulesForWaterBreaking(serverLevel, pregnantEntity);
			}
		}
		else if (pain == PregnancyPain.BIRTH) {
			if (pregnancyData.getPregnancyPainTimer() >= totalTicksOfBirth) {			
				
	        	final var aliveBabiesItemStacks = PregnancySystemHelper.getAliveBabies(pregnancyData.getWomb());   	
	       		
	        	MinepreggoMod.LOGGER.debug("PreggoMob {} is giving birth to {} babies.", pregnantEntity.getDisplayName().getString(), aliveBabiesItemStacks.size());
	        	
	        	if (aliveBabiesItemStacks.isEmpty()) {
					MinepreggoMod.LOGGER.error("Failed to get baby item for pregnancy system {} birth.", pregnancyData.getCurrentPregnancyPhase());
				}
	        	
	        	// TODO: Babies itemstacks are only removed if player's hands are empty. It should handle stacking unless itemstack is a baby item.
	        	aliveBabiesItemStacks.removeIf(baby -> {
	        		if (pregnantEntity.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
	        			PreggoMobHelper.replaceAndDropItemstackInHand(pregnantEntity, InteractionHand.MAIN_HAND, baby);
	            		return true;
	        		}
	        		else if (pregnantEntity.getItemInHand(InteractionHand.OFF_HAND).isEmpty()) {
	        			PreggoMobHelper.replaceAndDropItemstackInHand(pregnantEntity, InteractionHand.OFF_HAND, baby);
	            		return true;
	        		}
	        		return false;
	        	});
	        	
	        	if (!aliveBabiesItemStacks.isEmpty()) {	              	
		        	aliveBabiesItemStacks.forEach(baby -> PreggoMobHelper.storeItemInSpecificRangeOrDrop(pregnantEntity, baby.copy(), ITamablePreggoMob.FOOD_INVENTORY_SLOT + 1, pregnantEntity.getInventorySize() - 1)); 						
	        	}
	        	        	
	        	MessageHelper.sendTo(MessageHelper.asServerPlayer((Player) pregnantEntity.getOwner()), Component.translatable("chat.minepreggo.preggo_mob.birth.message.post", pregnantEntity.getSimpleName()));
				initPostPartum();	
	        	pregnantEntity.discard();   	
				MinepreggoMod.LOGGER.debug("PreggoMob {} has given birth.", pregnantEntity.getDisplayName().getString());	
			}	
			else {
				pregnancyData.incrementPregnancyPainTimer();
				tryHurtByCooldown();
			}
		}
	}
	
	@Override
	protected void startLabor() {
		tryHurt();
		final var pregnancyData = pregnantEntity.getPregnancyData();
		pregnancyData.setPregnancyPain(PregnancyPain.PREBIRTH);
		pregnancyData.resetPregnancyPainTimer();
		pregnantEntity.setCanPickUpLoot(false);
		pregnantEntity.setBreakBlocks(false);
		MinepreggoMod.LOGGER.debug("PreggoMob {} has started labor.", pregnantEntity.getDisplayName().getString());	
    	MessageHelper.sendTo(MessageHelper.asServerPlayer((Player) pregnantEntity.getOwner()), Component.translatable("chat.minepreggo.preggo_mob.birth.message.warning", pregnantEntity.getSimpleName()));
	}
	
	@Override
	protected void evaluateWaterBreaking(ServerLevel serverLevel) {
		final var pregnancyData = pregnantEntity.getPregnancyData();
		if (pregnancyData.getPregnancyPainTimer() >= PregnancySystemHelper.TOTAL_TICKS_WATER_BREAKING) {
			startLabor();
		}
		else {
			pregnancyData.incrementPregnancyPainTimer();
    		AbstractPregnancySystem.spawnParticulesForWaterBreaking(serverLevel, pregnantEntity);
		}
	}
	
	@Override
	protected void breakWater() {
		tryHurt();
		final var pregnancyData = pregnantEntity.getPregnancyData();
		pregnancyData.resetPregnancyPainTimer();
		pregnancyData.setPregnancyPain(PregnancyPain.WATER_BREAKING);
		MinepreggoMod.LOGGER.debug("PreggoMob {} water has broken.", pregnantEntity.getDisplayName().getString());
    	MessageHelper.sendTo(MessageHelper.asServerPlayer((Player) pregnantEntity.getOwner()), Component.translatable("chat.minepreggo.preggo_mob.birth.message.init", pregnantEntity.getSimpleName()));
	}
	
	@Override
	public boolean canBeAngry() {
		return super.canBeAngry() || pregnantEntity.getPregnancyData().getHorny() >= PregnancySystemHelper.MAX_HORNY_LEVEL;	
	}
	
	@Override
	protected boolean hasToGiveBirth() {
		final var pregnancyData = pregnantEntity.getPregnancyData();
		return pregnancyData.getLastPregnancyStage() == pregnancyData.getCurrentPregnancyPhase();
	}
	
	@Override
	protected boolean isInLabor() {
		final var pregnancyData = pregnantEntity.getPregnancyData();
		final var pain = pregnancyData.getPregnancyPain();
		return pain == PregnancyPain.PREBIRTH || pain == PregnancyPain.BIRTH;
 	}
	
	@Override
	protected boolean isWaterBroken() {
		return pregnantEntity.getPregnancyData().getPregnancyPain() == PregnancyPain.WATER_BREAKING;
 	}
		
	@Override
	protected boolean tryInitPregnancySymptom() {
		if (super.tryInitPregnancySymptom()) {
			return true;
		}
		final var pregnancyData = pregnantEntity.getPregnancyData();
		SyncedSetPregnancySymptom pregnancySymptoms = pregnancyData.getSyncedPregnancySymptoms();	
		if (pregnancyData.getHorny() >= PregnancySystemHelper.ACTIVATE_HORNY_SYMPTOM
				&& !pregnancySymptoms.containsPregnancySymptom(PregnancySymptom.HORNY)) {
			pregnancySymptoms.addPregnancySymptom(PregnancySymptom.HORNY);
	    	pregnantEntity.getGenderedData().setSexualAppetite(IBreedable.MAX_SEXUAL_APPETIVE);
	    	return true;		
		}
		return false;
	}
	
	@Override
	protected boolean tryInitRandomPregnancyPain() {
		if (super.tryInitRandomPregnancyPain()) {
			return true;
		}			
		final var pregnancyData = pregnantEntity.getPregnancyData();
		if (hasToGiveBirth()
				&& !pregnantEntity.hasEffect(MinepreggoModMobEffects.ETERNAL_PREGNANCY.get())
				&& randomSource.nextFloat() < contractionProb) {
			pregnancyData.setPregnancyPain(PregnancyPain.CONTRACTION);
			pregnancyData.resetPregnancyPainTimer();
			PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.CHEST);					
			return true;
		}     
	    return false;
	}
	
	@Override
	protected void evaluatePregnancyPains() {
		super.evaluatePregnancyPains();
		final var pregnancyData = pregnantEntity.getPregnancyData();
		if (pregnancyData.getPregnancyPain() == PregnancyPain.CONTRACTION) {
			if (pregnancyData.getPregnancyPainTimer() >= totalTicksOfFetalMovement) {
				pregnancyData.clearPregnancyPain();	
				pregnancyData.resetPregnancyPainTimer();
			}
			else {
				pregnancyData.incrementPregnancyPainTimer();
				tryHurtByCooldown();
			}
		}
	}
	
	protected void evaluateHornyTimer() {
		final var pregnancyData = pregnantEntity.getPregnancyData();
		if (pregnancyData.getHorny() < PregnancySystemHelper.MAX_HORNY_LEVEL) {
	        if (pregnancyData.getHornyTimer() >= totalTicksOfHorny) {
	        	pregnancyData.incrementHorny();
	        	pregnancyData.resetHornyTimer();
	        }
	        else {
	        	pregnancyData.incrementHornyTimer();
	        }
		}	
	}
	
	// TODO: PreggoMob can only request sex if she is pregnant and horny enough. But She can't request if she's not pregnant. It should allow even if not pregnant.
	// It ignores if their owner (Player) is female or male
	protected boolean tryRequestSexToOwner() {	
		final var pregnancyData = pregnantEntity.getPregnancyData();
		if (!pregnancyData.getSyncedPregnancySymptoms().containsPregnancySymptom(PregnancySymptom.HORNY)) {
			return false;
		}
		
		if (sexRequestCooldown < 3600) {
			++sexRequestCooldown;
			return false;
		}
		
		if (pregnantEntity.getOwner() instanceof ServerPlayer serverPlayer && pregnantEntity.distanceToSqr(serverPlayer) < 25D && !pregnantEntity.isAggressive()) {
			sexRequestCooldown = 0;			
			RequestSexM2PMenu.create(serverPlayer, this.pregnantEntity);
			return true;
		}
		
		return false;
	}
}
