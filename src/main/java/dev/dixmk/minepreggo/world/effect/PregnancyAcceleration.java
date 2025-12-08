package dev.dixmk.minepreggo.world.effect;

import javax.annotation.CheckForNull;
import javax.annotation.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancySystemHandler;
import dev.dixmk.minepreggo.world.pregnancy.MapPregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.network.capability.PlayerPregnancySystemImpl;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class PregnancyAcceleration extends MobEffect {
	
	public PregnancyAcceleration() {
		super(MobEffectCategory.HARMFUL, -52429);
	}

	@Override
	public void applyInstantenousEffect(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity target, int amplifier, double effectiveness) { 			
		if (target.level().isClientSide || target.hasEffect(MinepreggoModMobEffects.ETERNAL_PREGNANCY.get())) return;

		updatePregnancyDays(target, getDaysByAmplifier(target.getRandom(), amplifier));
	}

	// TODO: Extra days is not proporsional to total days of pregnancy, should be reworked later 
	private static int getDaysByAmplifier(RandomSource random, int amplifier) {
		switch (amplifier) {
		case 0: return random.nextInt(1, 6);
		case 1: return random.nextInt(5, 11);
		case 2: return random.nextInt(10, 16);
		case 3: return random.nextInt(15, 21);
		default: return random.nextInt(20, 26);
		}
	}

	@CheckForNull
	private static PregnancyPhase getNextPhase(PregnancyPhase current) {
        PregnancyPhase[] phases = PregnancyPhase.values();
        int idx = current.ordinal();
        if (idx + 1 < phases.length) {
            return phases[idx + 1];
        }
        return null;
    }

	/**
	 * Upgrades pregnancy days for the target entity, consuming days across multiple stages if needed.
	 * Only modifies MapPregnancyPhase. Does NOT modify current pregnancy stage, that is managed by AbstractPlayerPregnancy and AbstractPregnancySystem.
	 */
	private static void updatePregnancyDays(LivingEntity target, int days) {
        if (target instanceof IPregnancySystemHandler p) {
            MapPregnancyPhase map = p.getMapPregnancyPhase();
            PregnancyPhase current = p.getCurrentPregnancyStage();
            int daysLeft = days;
            PregnancyPhase phase = current;
            while (daysLeft > 0 && phase != null && map.containsPregnancyPhase(phase)) {
                int phaseDays = map.getDaysByPregnancyPhase(phase);
                int reduce = Math.min(daysLeft, phaseDays);
                map.modifyDaysByPregnancyPhase(phase, Math.max(0, phaseDays - reduce));
                daysLeft -= reduce;
                // If days left, move to next phase, but DO NOT modify current pregnancy stage
                if (daysLeft > 0) {
                    phase = getNextPhase(phase);
                } else {
                    break;
                }
            }
            p.setMapPregnancyPhase(map);
        } else if (target instanceof ServerPlayer serverPlayer) {
            serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap ->
                cap.getFemaleData().ifPresent(femaleData -> {
                    if (femaleData.isPregnant()) {
                        PlayerPregnancySystemImpl pregnancySystem = femaleData.getPregnancySystem();
                        MapPregnancyPhase map = pregnancySystem.getMapPregnancyPhase();
                        int daysLeft = days;
                        PregnancyPhase phase = pregnancySystem.getCurrentPregnancyStage();
                        
                        MinepreggoMod.LOGGER.debug("Accelerating pregnancy for player {} by {} days", serverPlayer.getScoreboardName(), days);
                        
                        while (daysLeft > 0 && phase != null && map.containsPregnancyPhase(phase)) {
                            int phaseDays = map.getDaysByPregnancyPhase(phase);
                            int reduce = Math.min(daysLeft, phaseDays);
                            map.modifyDaysByPregnancyPhase(phase, Math.max(0, phaseDays - reduce));
                            daysLeft -= reduce;
                            // If days left, move to next phase, but DO NOT modify current pregnancy stage
                            if (daysLeft > 0) {
                                phase = getNextPhase(phase);
                            } else {
                                break;
                            }
                        }
                        
                        MinepreggoMod.LOGGER.debug("Pregnancy acceleration complete for player {}. New map: {}", serverPlayer.getScoreboardName(), map);
                        
                        pregnancySystem.setMapPregnancyPhase(map);
                    }
                })
            );
        }
    }
	
	@Override
	public boolean isInstantenous() {
		return true;
	}
}
