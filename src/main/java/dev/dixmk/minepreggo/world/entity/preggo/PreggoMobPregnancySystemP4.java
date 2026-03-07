package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoMobEffects;
import dev.dixmk.minepreggo.init.MinepreggoSounds;
import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.world.entity.EntityHelper;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.inventory.preggo.RequestSexM2PMenu;
import dev.dixmk.minepreggo.world.item.BabyItem;
import dev.dixmk.minepreggo.world.pregnancy.BabyData;
import dev.dixmk.minepreggo.world.pregnancy.BirthData;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPainInstance;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import dev.dixmk.minepreggo.world.pregnancy.Womb;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

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
		final var instance = pregnancyData.getPregnancyPain();
		if (instance == null) {
			MinepreggoMod.LOGGER.warn("Pregnancy pain instance is null for player {} during birth evaluation.", pregnantEntity.getSimpleNameOrCustom());
			return;
		}
		
		if (instance.getPain() == PregnancyPain.PREBIRTH) {		
			if (instance.isExpired()) {
				tryHurt();
				int totalTicksForBirth = pregnancyData.getWomb().stream()
						.mapToInt(PregnancySystemHelper::calculateBirthDuration)
						.sum() + 200;
				pregnancyData.setPregnancyPain(new PregnancyPainInstance(PregnancyPain.BIRTH, totalTicksForBirth, 0));
				pregnantEntity.getTamableData().setBodyState(PreggoMobBody.NAKED);
				PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, InventorySlot.CHEST);
				PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, InventorySlot.LEGS);
				PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, InventorySlot.MAINHAND);
				PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, InventorySlot.OFFHAND);
				Womb womb = pregnancyData.getWomb();
				womb.refreshOriginalNumOfBabies();
				BabyData babyData = womb.removeBaby();
				pregnancyData.setBirthData(new BirthData(babyData, PregnancySystemHelper.calculateBirthDuration(babyData)));	
	        	MessageHelper.sendTo(MessageHelper.asServerPlayer((Player) pregnantEntity.getOwner()), Component.translatable("chat.minepreggo.preggo_mob.birth.message.pre", pregnantEntity.getSimpleNameOrCustom()));
			}	
			else {
				instance.tick();
				spawnParticulesForWaterBreaking(serverLevel);
			}
		}
		else if (instance.getPain() == PregnancyPain.BIRTH) {
			if (instance.isExpired()) {	
				MinepreggoMod.LOGGER.debug("PreggoMob {} has given birth.", pregnantEntity.getDisplayName().getString());
	        	MessageHelper.sendTo(MessageHelper.asServerPlayer((Player) pregnantEntity.getOwner()), Component.translatable("chat.minepreggo.preggo_mob.birth.message.post", pregnantEntity.getSimpleNameOrCustom()));
				initPostPartum();	
	        	pregnantEntity.discard();   		
			}	
			else {
				instance.tick();
				BirthData birthData = pregnancyData.getBirthData();
				if (birthData != null) { 
					birthData.tick();				
					if (birthData.isBirthComplete()) {
						BabyData babyData = birthData.getBabyData();
						Item babyItem = PregnancySystemHelper.getAliveBabyItem(babyData.typeOfSpecies, babyData.typeOfCreature);
						if (babyItem != null) {		
							tryHurt();
							LivingEntityHelper.playSoundNearTo(pregnantEntity, MinepreggoSounds.SPLASH.get(), 0.3f);
							ItemStack babyItemStack = BabyItem.createBabyItemStack(babyData.motherId, babyData.fatherId.orElse(null), babyItem);
							EntityHelper.spawnItemOn(pregnantEntity, babyItemStack);			
							BabyData nextBabyData = pregnancyData.getWomb().removeBaby();
							if (nextBabyData != null) {
								pregnancyData.setBirthData(new BirthData(nextBabyData, PregnancySystemHelper.calculateBirthDuration(nextBabyData)));
							}
							else {
								pregnancyData.setBirthData(null);
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	protected void startLabor() {
		tryHurt();
		final var pregnancyData = pregnantEntity.getPregnancyData();
		pregnancyData.setPregnancyPain(new PregnancyPainInstance(PregnancyPain.PREBIRTH, totalTicksOfPreBirth, 0));
		pregnantEntity.setCanPickUpLoot(false);
		pregnantEntity.setBreakBlocks(false);
		MinepreggoMod.LOGGER.debug("PreggoMob {} has started labor.", pregnantEntity.getDisplayName().getString());	
    	MessageHelper.sendTo(MessageHelper.asServerPlayer((Player) pregnantEntity.getOwner()), Component.translatable("chat.minepreggo.preggo_mob.birth.message.warning", pregnantEntity.getSimpleNameOrCustom()));
	}
	
	@Override
	protected void evaluateWaterBreaking(ServerLevel serverLevel) {
		final var pregnancyData = pregnantEntity.getPregnancyData();
		final var instance = pregnancyData.getPregnancyPain();
		if (instance == null) {
			MinepreggoMod.LOGGER.warn("Pregnancy pain instance is null for pregggoMob {} during water breaking evaluation.", pregnantEntity.getSimpleNameOrCustom());
			return;
		}
		
		if (instance.isExpired()) {
			startLabor();
		}
		else {
			instance.tick();
			spawnParticulesForWaterBreaking(serverLevel);
		}
	}
	
	@Override
	protected void breakWater() {
		tryHurt();
		final var pregnancyData = pregnantEntity.getPregnancyData();
		pregnancyData.setPregnancyPain(new PregnancyPainInstance(PregnancyPain.WATER_BREAKING, PregnancySystemHelper.TOTAL_TICKS_WATER_BREAKING, 0));
		MinepreggoMod.LOGGER.debug("PreggoMob {} water has broken.", pregnantEntity.getDisplayName().getString());
    	MessageHelper.sendTo(MessageHelper.asServerPlayer((Player) pregnantEntity.getOwner()), Component.translatable("chat.minepreggo.preggo_mob.birth.message.init", pregnantEntity.getSimpleNameOrCustom()));
	}
	
	@Override
	protected boolean hasToGiveBirth() {
		final var pregnancyData = pregnantEntity.getPregnancyData();
		return pregnancyData.getLastPregnancyStage() == pregnancyData.getCurrentPregnancyPhase();
	}
	
	@Override
	protected boolean isInLabor() {
		final var instance = pregnantEntity.getPregnancyData().getPregnancyPain();
		return instance != null && (instance.getPain() == PregnancyPain.PREBIRTH || instance.getPain() == PregnancyPain.BIRTH);
 	}
	
	@Override
	protected boolean isWaterBroken() {
		final var instance = pregnantEntity.getPregnancyData().getPregnancyPain();
		return instance != null && instance.getPain() == PregnancyPain.WATER_BREAKING;
 	}
		
	@Override
	protected boolean tryInitPregnancySymptom() {
		if (super.tryInitPregnancySymptom()) {
			return true;
		}
		final var pregnancyData = pregnantEntity.getPregnancyData();
		if (pregnancyData.getHorny() >= PregnancySystemHelper.ACTIVATE_HORNY_SYMPTOM
				&& !pregnancyData.getPregnancySymptoms().contains(PregnancySymptom.HORNY)) {
			initPregnancySymptom(PregnancySymptom.HORNY);
	    	return true;		
		}
		return false;
	}
	
	@Override
	protected boolean tryInitRandomPregnancyPain() {
		if (super.tryInitRandomPregnancyPain()) {
			return true;
		}			
		if (hasToGiveBirth()
				&& !pregnantEntity.hasEffect(MinepreggoMobEffects.ETERNAL_PREGNANCY.get())
				&& pregnantEntity.getRandom().nextFloat() < contractionProb) {
			initCommonPregnancyPain(PregnancyPain.CONTRACTION, totalTicksOfFetalMovement, 0);				
			return true;
		}     
	    return false;
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
		if (!pregnancyData.getPregnancySymptoms().contains(PregnancySymptom.HORNY)) {
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
