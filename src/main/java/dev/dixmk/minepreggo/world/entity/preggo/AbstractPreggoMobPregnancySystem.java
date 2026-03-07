package dev.dixmk.minepreggo.world.entity.preggo;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoMobEffects;
import dev.dixmk.minepreggo.world.pregnancy.AbstractPregnancySystem;
import dev.dixmk.minepreggo.world.pregnancy.IBreedable;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPainInstance;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;

public abstract class AbstractPreggoMobPregnancySystem 
	<E extends PreggoMob & ITamablePregnantPreggoMob> extends AbstractPregnancySystem<E> implements IPreggoMobPregnancySystem {
	
	protected AbstractPreggoMobPregnancySystem(E pregnantEntity) {
		super(pregnantEntity);
	}
	
	// It has to be executed on server side
	protected void evaluatePregnancySystem() {	
		this.evaluatePregnancyTimer();
		if (canAdvanceNextPregnancyPhase() && !hasToGiveBirth()) {			
			var chestplate = pregnantEntity.getItemBySlot(EquipmentSlot.CHEST);
			var leggings = pregnantEntity.getItemBySlot(EquipmentSlot.LEGS);
			var phases = PregnancyPhase.values();
			var pregnancyData = pregnantEntity.getPregnancyData();
			var current = pregnancyData.getCurrentPregnancyPhase();
			var next = phases[Math.min(current.ordinal() + 1, phases.length - 1)];
			
			
			if (!chestplate.isEmpty()
					&& (!PregnancySystemHelper.canUseChestplate(chestplate.getItem(), next) || pregnancyData.getSyncedPregnancySymptoms().contains(PregnancySymptom.MILKING))) {
				PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, InventorySlot.CHEST);
			}
			
			if (!leggings.isEmpty()
					&& !PregnancySystemHelper.canUseLegging(leggings.getItem(), next)) {
				PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, InventorySlot.LEGS);
			}
			
			advanceToNextPregnancyPhase();
			
			pregnantEntity.discard();
			MinepreggoMod.LOGGER.debug("Pregnancy phase advanced from {} for entity {}", pregnancyData.getCurrentPregnancyPhase(), pregnantEntity.getSimpleNameOrCustom());
		}	
	}
	
	public final void onServerTick() {
		if (pregnantEntity.level().isClientSide) {			
			return;
		}	
		
		evaluatePregnancySystem();
	}
	
	protected void evaluatePregnancyTimer() {
		if (this.pregnantEntity.hasEffect(MinepreggoMobEffects.ETERNAL_PREGNANCY.get())) {
			return;
		}
		final var pregnancyData = pregnantEntity.getPregnancyData();
        if (pregnancyData.getPregnancyTimer() >= MinepreggoModConfig.SERVER.getTotalTicksByPregnancyDay()) {
        	pregnancyData.resetPregnancyTimer();
        	pregnancyData.incrementDaysPassed();
        	pregnancyData.reduceDaysToGiveBirth();
        	MinepreggoMod.LOGGER.debug("Pregnancy day advanced to {} for entity {}", pregnancyData.getDaysPassed(), pregnantEntity.getSimpleNameOrCustom());
        } else {
        	pregnancyData.incrementPregnancyTimer();
        }
	}
	
	public boolean canAdvanceNextPregnancyPhase() {
	    return pregnantEntity.getPregnancyData().getDaysPassed() >= pregnantEntity.getPregnancyData().getDaysByCurrentStage();
	}
	
	public boolean isRightClickValid(Player source) {
		return pregnantEntity.isOwnedBy(source);
	}
	
	@Override
	public boolean initCommonPregnancyPain(PregnancyPain pain, int duration, int severity) {
		if (pain.type != PregnancyPain.Type.COMMON) {
			return false;
		}
		boolean flag = false;

		if (pain == PregnancyPain.MORNING_SICKNESS) {
			pregnantEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, duration, 0, false, true));
			flag = true;			
		}
		else if (pain == PregnancyPain.FETAL_MOVEMENT) {
			pregnantEntity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, duration, 0, false, true));
			flag = true;
		}
		else if (pain == PregnancyPain.CONTRACTION) {
			flag = true;
		}
		
		if (flag) {
			pregnantEntity.getPregnancyData().setPregnancyPain(new PregnancyPainInstance(pain, duration, severity));
			MinepreggoMod.LOGGER.debug("Pregnancy pain {} applied to entity {}", pain, pregnantEntity.getSimpleNameOrCustom());
			return true;
		}
		
		return false;
	}

	@Override
	public boolean initPregnancySymptom(PregnancySymptom symptom) {
		var pregnancyData = pregnantEntity.getPregnancyData();
		var pregnancySymptoms = pregnancyData.getSyncedPregnancySymptoms();
		if (pregnancySymptoms.contains(symptom)) {
			return false;
		}
		
		boolean flag = false;
		
		if (symptom == PregnancySymptom.CRAVING) {
			pregnancyData.setTypeOfCraving(PregnancySystemHelper.getRandomCraving(pregnantEntity.getRandom()));
			flag = true;
		}
		else if (symptom == PregnancySymptom.MILKING) {
			flag = true;
		}
		else if (symptom == PregnancySymptom.BELLY_RUBS) {
			flag = true;
		}
		else if (symptom == PregnancySymptom.HORNY) {
	    	pregnantEntity.getGenderedData().setSexualAppetite(IBreedable.MAX_SEXUAL_APPETIVE);
			flag = true;
		}
		
		if (flag) {
			pregnancySymptoms.add(symptom);
			MinepreggoMod.LOGGER.debug("PreggomMob {} has developed pregnancy symptom: {}, all pregnancy symptoms: {}",
					pregnantEntity.getSimpleNameOrCustom(), PregnancySymptom.CRAVING.name(), pregnancySymptoms.getSymptoms());
			return true;
		}
		
		return false;
	}
}
