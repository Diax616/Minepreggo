package dev.dixmk.minepreggo.world.effect;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.pregnancy.IBreedable;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancySystemHandler;
import dev.dixmk.minepreggo.world.pregnancy.MapPregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class BabyDuplication extends MobEffect {

	public BabyDuplication() {
		super(MobEffectCategory.HARMFUL, -39220);
	}
	
	@Override
	public boolean isInstantenous() {
		return true;
	}
	
    @Override
    public void applyInstantenousEffect(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity target, int amplifier, double effectiveness) {     
        if (target.level().isClientSide) {
            return;
        }
            
        if (target instanceof IPregnancySystemHandler handler) {
        	apply(handler, getExtraBabiesByAmplifier(amplifier), target.getRandom());
        }            
        else if (target instanceof ServerPlayer player) {
            player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap ->
                cap.getFemaleData().ifPresent(femaleData -> {
                    if (femaleData.isPregnant() && femaleData.isPregnancySystemInitialized()) {
                    	var pregnancySystem = femaleData.getPregnancySystem();
                    	var oldPhase = pregnancySystem.getCurrentPregnancyStage(); 	
                    	if (apply(pregnancySystem, getExtraBabiesByAmplifier(amplifier), player.getRandom())) {
                    		var newPhase = pregnancySystem.getCurrentPregnancyStage();
                    		if (oldPhase != newPhase) {
                        		player.removeEffect(PlayerHelper.getPregnancyEffects(oldPhase));
                        		player.addEffect(new MobEffectInstance(PlayerHelper.getPregnancyEffects(newPhase), -1, 0, false, false, true));	
                        		pregnancySystem.sync(player);
                    		}  		
                    	}
                    }
                })
            );
        }
    }
    
    
    private static boolean apply(IPregnancySystemHandler handler, int numOfTargetBabies, RandomSource random) {
        var womb = handler.getWomb();
        var numOfBabies = womb.getNumOfBabies();
        if (numOfBabies < IBreedable.MAX_NUMBER_OF_BABIES) {
            // Duplicate babies
            int extraBabies = Math.min(numOfTargetBabies, IBreedable.MAX_NUMBER_OF_BABIES - numOfBabies);
            for (int i = 0; i < extraBabies; ++i) {
                womb.duplicateRandomBaby(random);
            }

            // --- Calculate total days elapsed from old map ---
            MapPregnancyPhase oldMap = handler.getMapPregnancyPhase();
            PregnancyPhase oldCurrentPhase = handler.getCurrentPregnancyStage();
            int oldDaysPassed = handler.getDaysPassed();
            int totalDaysElapsed = 0;
            for (PregnancyPhase phase : PregnancyPhase.values()) {
                if (phase == oldCurrentPhase) {
                    totalDaysElapsed += oldDaysPassed;
                    break;
                }
                if (oldMap.containsPregnancyPhase(phase)) {
                    totalDaysElapsed += oldMap.getDaysByPregnancyPhase(phase);
                }
            }

            // --- Update pregnancy phase and map ---
            int updatedNumOfBabies = womb.getNumOfBabies();
            PregnancyPhase newLastPregnancyPhase = IBreedable.calculateMaxPregnancyPhaseByTotalNumOfBabies(updatedNumOfBabies);
            PregnancyPhase currentLastPhase = handler.getLastPregnancyStage();
            if (newLastPregnancyPhase != currentLastPhase) {
            	handler.setLastPregnancyStage(newLastPregnancyPhase);
                int totalDays = handler.getTotalDaysOfPregnancy();
                MapPregnancyPhase newMap = new MapPregnancyPhase(totalDays, newLastPregnancyPhase);
                handler.setMapPregnancyPhase(newMap);
                handler.setDaysToGiveBirth(totalDays);

                // --- Recalculate current phase and daysPassed based on totalDaysElapsed ---
                int daysCount = 0;
                PregnancyPhase newCurrentPhase = null;
                int newDaysPassed = 0;
                for (PregnancyPhase phase : PregnancyPhase.values()) {
                    if (newMap.containsPregnancyPhase(phase)) {
                        int phaseDays = newMap.getDaysByPregnancyPhase(phase);
                        if (totalDaysElapsed < daysCount + phaseDays) {
                            newCurrentPhase = phase;
                            newDaysPassed = totalDaysElapsed - daysCount;
                            break;
                        }
                        daysCount += phaseDays;
                    }
                }
                if (newCurrentPhase != null) {
                	handler.setCurrentPregnancyStage(newCurrentPhase);
                    handler.setDaysPassed(newDaysPassed);
                } else {
                    // If not found, set to last phase and max days
                	handler.setCurrentPregnancyStage(newLastPregnancyPhase);
                	handler.setDaysPassed(newMap.getDaysByPregnancyPhase(newLastPregnancyPhase));
                }
            }
            
            return true;
        } 
        
        return false;
    }
    
	private static int getExtraBabiesByAmplifier(int amplifier) {
        return Math.min(amplifier + 1, 4) + 1;
	}
}