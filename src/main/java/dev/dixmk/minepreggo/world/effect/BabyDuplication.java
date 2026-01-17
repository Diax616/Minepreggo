package dev.dixmk.minepreggo.world.effect;

import javax.annotation.CheckForNull;
import javax.annotation.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import dev.dixmk.minepreggo.world.pregnancy.IBreedable;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancyData;
import dev.dixmk.minepreggo.world.pregnancy.MapPregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;

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
            
        if (target instanceof ITamablePregnantPreggoMob tamablePregnantPreggoMob) {
        	var pregnancyData = tamablePregnantPreggoMob.getPregnancyData();
        	PregnancySystemHelper.playSoundNearTo(target, MinepreggoModSounds.getRandomStomachGrowls(target.getRandom()));         	
        	var newPhase = apply(pregnancyData, getExtraBabiesByAmplifier(amplifier), target.getRandom());
        	if (newPhase != null && newPhase != pregnancyData.getCurrentPregnancyStage()) {    		
        		if (target instanceof AbstractTamablePregnantZombieGirl zombieGirl && target.level() instanceof ServerLevel serverLevel) {
        			var nextPhase = AbstractTamablePregnantZombieGirl.getEntityType(newPhase);   			
        			var newZombieGirl = (AbstractTamablePregnantZombieGirl) nextPhase.spawn(serverLevel, BlockPos.containing(target.getX(), target.getY(), target.getZ()), MobSpawnType.CONVERSION);
        			if (newZombieGirl != null) {
        				PreggoMobHelper.transferAllData(zombieGirl, newZombieGirl);
        				zombieGirl.discard();
        			}
        			else {
        				MinepreggoMod.LOGGER.warn("Failed to duplicate babies for zombie girl entity conversion.");
        			}
        		}
        		else if (target instanceof AbstractTamablePregnantHumanoidCreeperGirl creeperGirl && target.level() instanceof ServerLevel serverLevel) {
        			var nextPhase = AbstractTamablePregnantHumanoidCreeperGirl.getEntityType(newPhase);   			
        			var newCreeperGirl = (AbstractTamablePregnantHumanoidCreeperGirl) nextPhase.spawn(serverLevel, BlockPos.containing(target.getX(), target.getY(), target.getZ()), MobSpawnType.CONVERSION);
        			if (newCreeperGirl != null) {
        				PreggoMobHelper.transferAllData(creeperGirl, newCreeperGirl);
        				creeperGirl.discard();
        			}
        			else {
        				MinepreggoMod.LOGGER.warn("Failed to duplicate babies for creeper girl entity conversion.");
        			}
        		}
        	} 	
        }  
        else if (target instanceof ServerPlayer player) {
            player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap ->
                cap.getFemaleData().ifPresent(femaleData -> {
                    if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
                    	var pregnancySystem = femaleData.getPregnancyData();
                    	var oldPhase = pregnancySystem.getCurrentPregnancyStage(); 	
                    	var newPhase = apply(pregnancySystem, getExtraBabiesByAmplifier(amplifier), player.getRandom());
                    	if (newPhase != null && newPhase != oldPhase) {
                        	PregnancySystemHelper.playSoundNearTo(target, MinepreggoModSounds.getRandomStomachGrowls(target.getRandom()));
                    		player.removeEffect(PlayerHelper.getPregnancyEffects(oldPhase));
                    		player.addEffect(new MobEffectInstance(PlayerHelper.getPregnancyEffects(newPhase), -1, 0, false, false, true));	
                    		pregnancySystem.syncState(player);
                    	}
                    }
                })
            );
        }
    }
    
    @CheckForNull
    private static PregnancyPhase apply(IPregnancyData handler, int numOfTargetBabies, RandomSource random) {
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
            PregnancyPhase temp = currentLastPhase;
            
            if (newLastPregnancyPhase != currentLastPhase) {
            	handler.setLastPregnancyStage(newLastPregnancyPhase);
                int totalDays = handler.getTotalDaysOfPregnancy();
                MapPregnancyPhase newMap = new MapPregnancyPhase(totalDays, newLastPregnancyPhase);
                handler.setMapPregnancyPhase(newMap);

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
                    temp = newCurrentPhase;
                } else {
                    // If not found, set to last phase and max days
                	handler.setCurrentPregnancyStage(newLastPregnancyPhase);    	
                	handler.setDaysPassed(newMap.getDaysByPregnancyPhase(newLastPregnancyPhase));
                	temp = newLastPregnancyPhase;
                }
            }
            
            return temp;
        } 
        
        return null;
    }
    
	private static int getExtraBabiesByAmplifier(int amplifier) {
        return Math.min(amplifier + 1, 4) + 1;
	}
}