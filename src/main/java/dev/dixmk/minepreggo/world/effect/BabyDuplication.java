package dev.dixmk.minepreggo.world.effect;

import java.util.ArrayList;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.entity.preggo.IHostilPregnantPreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantMonsterCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.HumanoidCreeperHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterCreeperHelper;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamablePregnantMonsterEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.ender.MonsterEnderWomanHelper;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancyData;
import dev.dixmk.minepreggo.world.pregnancy.MapPregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import dev.dixmk.minepreggo.world.pregnancy.Womb;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
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
        	var random = target.getRandom();
        	var newPhase = apply(pregnancyData, getExtraBabiesByAmplifier(amplifier), random);
        	LivingEntityHelper.playSoundNearTo(target, MinepreggoModSounds.getRandomStomachGrowls(target.getRandom()));         	

        	if (newPhase != pregnancyData.getCurrentPregnancyPhase()) {    		
        		MinepreggoMod.LOGGER.debug("BabyDuplication Effect: Entity {} advanced from phase {} to {}", target.getDisplayName().getString(), pregnancyData.getCurrentPregnancyPhase(), newPhase);
        		
        		if (target instanceof AbstractTamablePregnantZombieGirl zombieGirl && target.level() instanceof ServerLevel serverLevel) {
        			var nextPhase = AbstractTamablePregnantZombieGirl.getEntityType(newPhase);   			
        			var newZombieGirl = (AbstractTamablePregnantZombieGirl) nextPhase.spawn(serverLevel, BlockPos.containing(target.getX(), target.getY(), target.getZ()), MobSpawnType.CONVERSION);
    				PreggoMobHelper.copyAllData(zombieGirl, newZombieGirl);
    				zombieGirl.discard();
        		}
        		else if (target instanceof AbstractTamablePregnantHumanoidCreeperGirl creeperGirl && target.level() instanceof ServerLevel serverLevel) {
        			var nextPhase = HumanoidCreeperHelper.getEntityType(newPhase);   			
        			var newCreeperGirl = (AbstractTamablePregnantHumanoidCreeperGirl) nextPhase.spawn(serverLevel, BlockPos.containing(target.getX(), target.getY(), target.getZ()), MobSpawnType.CONVERSION);
    				PreggoMobHelper.copyAllData(creeperGirl, newCreeperGirl);
    				creeperGirl.discard();
        		}
        		else if (target instanceof AbstractTamablePregnantMonsterCreeperGirl creeperGirl && target.level() instanceof ServerLevel serverLevel) {
        			var nextPhase = MonsterCreeperHelper.getEntityType(newPhase);   			
        			var newCreeperGirl = (AbstractTamablePregnantMonsterCreeperGirl) nextPhase.spawn(serverLevel, BlockPos.containing(target.getX(), target.getY(), target.getZ()), MobSpawnType.CONVERSION);
    				PreggoMobHelper.copyAllData(creeperGirl, newCreeperGirl);
    				creeperGirl.discard();
        		}
        		else if (target instanceof AbstractTamablePregnantMonsterEnderWoman enderWoman && target.level() instanceof ServerLevel serverLevel) {
        			var nextPhase = MonsterEnderWomanHelper.getEntityType(newPhase);   			
        			var newEnderWoman = (AbstractTamablePregnantMonsterEnderWoman) nextPhase.spawn(serverLevel, BlockPos.containing(target.getX(), target.getY(), target.getZ()), MobSpawnType.CONVERSION);
    				PreggoMobHelper.copyAllData(enderWoman, newEnderWoman);
    				enderWoman.discard();
        		}
        	} 	
        }  
        else if (target instanceof ServerPlayer player) {
            player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap ->
                cap.getFemaleData().ifPresent(femaleData -> {
                    if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
                    	var pregnancySystem = femaleData.getPregnancyData();
                    	var random = player.getRandom();
                    	var oldPhase = pregnancySystem.getCurrentPregnancyPhase(); 	
                    	var newPhase = apply(pregnancySystem, getExtraBabiesByAmplifier(amplifier), random);
                    	LivingEntityHelper.playSoundNearTo(target, MinepreggoModSounds.getRandomStomachGrowls(target.getRandom()));
        	
                    	if (newPhase != oldPhase) {
                    		player.removeEffect(PlayerHelper.getPregnancyEffects(oldPhase));
                    		player.addEffect(new MobEffectInstance(PlayerHelper.getPregnancyEffects(newPhase), -1, 0, false, false, true));		
							PlayerHelper.updateJigglePhysics(player, cap.getSkinType(), newPhase);       
                    		pregnancySystem.syncState(player);
                    	}
                    }
                })
            );
        }
        else if (target instanceof IHostilPregnantPreggoMob monsterPregnantPreggoMob) {
        	monsterPregnantPreggoMob.getPregnancyData().incrementNumOfBabies(getExtraBabiesByAmplifier(amplifier));
        	PregnancySystemHelper.tornWomb(target);
        }
		else {
			target.hurt(new DamageSource(target.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)), 1);
		}
    }
    
    
    private static PregnancyPhase apply(IPregnancyData handler, int numOfTargetBabies, RandomSource random) {
        Womb womb = handler.getWomb();
        int newNumOfBabies = womb.getNumOfBabies() + numOfTargetBabies;
        
        MinepreggoMod.LOGGER.debug("Applying BabyDuplication: Current babies = {}, Target extra babies = {}, New total babies = {}", womb.getNumOfBabies(), numOfTargetBabies, newNumOfBabies);
                
        for (int i = 0; i < numOfTargetBabies; ++i) {
            womb.duplicateRandomBaby(random);
        }       
        
        MapPregnancyPhase currentMap = handler.getMapPregnancyPhase();
        PregnancyPhase oldCurrentPhase = handler.getCurrentPregnancyPhase();
        
        int oldDaysPassed = handler.getDaysPassed();
        var originalMap = currentMap.getOriginalMap();

        int totalDaysElapsed = 0;
        for (PregnancyPhase phase : PregnancyPhase.values()) {
            if (phase == oldCurrentPhase) {
                totalDaysElapsed += oldDaysPassed;
                break;
            }
            if (currentMap.containsPregnancyPhase(phase)) {
            	int consumed = currentMap.getDaysByPregnancyPhase(phase);
            	
            	// PregnancyAcceleration effect may have consumed days (days by phase = 0), so refer to original map to calculate elapsed days
            	if (consumed == 0) {
            		var originalDays = originalMap.get(phase);
            		if (originalDays != null) {
						consumed = originalDays;
					}
            	}
            	
                totalDaysElapsed += consumed;
            }
        }
           
        // --- Update pregnancy phase and map ---
        int updatedNumOfBabies = womb.getNumOfBabies();
        PregnancyPhase newLastPregnancyPhase = PregnancySystemHelper.calculateMaxPregnancyPhaseByTotalNumOfBabies(updatedNumOfBabies);
        PregnancyPhase temp;
                    
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
            handler.setCurrentPregnancyPhase(newCurrentPhase);
            handler.setDaysPassed(newDaysPassed);
            temp = newCurrentPhase;
        } else {
            // If not found, set to last phase and max days
            handler.setCurrentPregnancyPhase(newLastPregnancyPhase);    
            handler.setDaysPassed(newMap.getDaysByPregnancyPhase(newLastPregnancyPhase));
            temp = newLastPregnancyPhase;
        }
           
        if (womb.isWombOverloaded()) {
        	if (oldCurrentPhase == PregnancyPhase.P8) {
        		MinepreggoMod.LOGGER.debug("Womb overloaded after BabyDuplication but already at final phase P8, no further advancement.");
        		newMap.modifyDaysByPregnancyPhase(oldCurrentPhase, 0);
        		handler.setMapPregnancyPhase(newMap);
        		return temp;
        	}
            MinepreggoMod.LOGGER.debug("Womb overloaded after BabyDuplication: Total babies = {}, Advancing pregnancy accordingly", updatedNumOfBabies);
            
            int totalConsumed = distributeOverloadDaysProportionally(handler, newMap, oldCurrentPhase, numOfTargetBabies);

            PregnancyPhase finalPhase = calculateCurrentPhaseAfterOverload(handler, newMap, totalDaysElapsed + totalConsumed);
            handler.setCurrentPregnancyPhase(finalPhase);
            
            MinepreggoMod.LOGGER.debug("After overload advancement: moved to phase {} (total consumed: {} days)", finalPhase, totalConsumed);
            
            return finalPhase;
        }
        
        return temp; 
    }
    
    private static int distributeOverloadDaysProportionally(IPregnancyData handler, MapPregnancyPhase map, 
                                                              PregnancyPhase startPhase, int babiesAdded) {
        int baseDaysPerBaby = getBaseDayPerBaby(startPhase);
        int totalDaysToDistribute = babiesAdded * baseDaysPerBaby;
        
        double phaseMultiplier = getPhaseMultiplier(startPhase);
        totalDaysToDistribute = (int) (totalDaysToDistribute * phaseMultiplier);
        
        MinepreggoMod.LOGGER.debug("Distributing {} days from phase {} (multiplier: {})", 
            totalDaysToDistribute, startPhase, phaseMultiplier);
        
        var remainingPhases = new ArrayList<PregnancyPhase>();
        boolean collecting = false;
        for (PregnancyPhase phase : PregnancyPhase.values()) {
            if (phase == startPhase) {
                collecting = true;
            }
            if (collecting && map.containsPregnancyPhase(phase)) {
                remainingPhases.add(phase);
            }
        }
        
        if (remainingPhases.isEmpty()) {
            return 0;
        }
        
        double[] weights = new double[remainingPhases.size()];
        double totalWeight = 0;
        
        for (int i = 0; i < remainingPhases.size(); i++) {
            weights[i] = Math.pow(0.6, i);
            totalWeight += weights[i];
        }
        
        int daysDistributed = 0;
        for (int i = 0; i < remainingPhases.size(); i++) {
            PregnancyPhase phase = remainingPhases.get(i);
            int currentDays = map.getDaysByPregnancyPhase(phase);
            int daysToConsume;
            if (i == remainingPhases.size() - 1) {
                daysToConsume = totalDaysToDistribute - daysDistributed;
            } else {
                daysToConsume = (int) ((weights[i] / totalWeight) * totalDaysToDistribute);
            }
            
            daysToConsume = Math.min(daysToConsume, currentDays);
            
            if (daysToConsume > 0) {
                int newDays = currentDays - daysToConsume;
                map.modifyDaysByPregnancyPhase(phase, newDays);
                daysDistributed += daysToConsume;
                
                MinepreggoMod.LOGGER.debug("  Phase {}: consumed {} days (weight: {}, had: {}, remaining: {})", 
                    phase, daysToConsume, weights[i], currentDays, newDays);
            }
        }
        
        handler.setMapPregnancyPhase(map);
        return daysDistributed;
    }
    
    private static double getPhaseMultiplier(PregnancyPhase phase) {
        return switch (phase) {
            case P0 -> 0.75;
            case P1 -> 0.85;   
            case P2 -> 1.15;  
            case P3 -> 1.3;
            case P4 -> 1.5; 
            case P5 -> 1.65;
            case P6 -> 1.8;
            case P7 -> 2.0;
            case P8 -> 2.5;      
        };
    }
    
    private static int getBaseDayPerBaby(PregnancyPhase phase) {
    	return switch (phase) {
			case P0, P1 -> 1;
			case P2, P3, P4, P5 -> 2;
			case P6, P7, P8 -> 3;
		};
	}
    
    private static PregnancyPhase calculateCurrentPhaseAfterOverload(IPregnancyData handler, MapPregnancyPhase map, int totalElapsedDays) {
        int daysCount = 0;
        
        for (PregnancyPhase phase : PregnancyPhase.values()) {
            if (map.containsPregnancyPhase(phase)) {
                int phaseDays = map.getDaysByPregnancyPhase(phase);
                
                if (totalElapsedDays < daysCount + phaseDays) {   
                    int daysIntoPhase = totalElapsedDays - daysCount;
                    handler.setDaysPassed(daysIntoPhase);
                    return phase;
                }
                
                daysCount += phaseDays;
            }
        }
        
        PregnancyPhase lastPhase = handler.getLastPregnancyStage();
        handler.setDaysPassed(map.getDaysByPregnancyPhase(lastPhase));
        return lastPhase;
    }
	
	private static int getExtraBabiesByAmplifier(int amplifier) {     
		return switch (Math.max(amplifier, 0)) {
			case 0 -> 1;
			case 1 -> 2;
			case 2 -> 3;
			case 3 -> 4;
			default -> 5;
		};
	}
}
