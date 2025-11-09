package dev.dixmk.minepreggo.world.effect;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class PregnancyDelay extends MobEffect {

	public PregnancyDelay() {
		super(MobEffectCategory.HARMFUL, -39220);
	}
	
	@Override
	public boolean isInstantenous() {
		return true;
	}

	@Override
	public void applyInstantenousEffect(@Nullable Entity p_19462_, @Nullable Entity p_19463_, LivingEntity p_19464_, int p_19465_, double p_19466_) { 
			
		if (p_19464_.level().isClientSide() || p_19464_.hasEffect(MinepreggoModMobEffects.ETERNAL_PREGNANCY.get())) return;
		
		var extraDays = 4;
		
		switch (p_19465_) {
		case 0: {
			break;
		}
		case 1: {
			extraDays *= 2; 
			break;
		}
		case 2: {
			extraDays *= 3; 
			break;
		}
		default:
			extraDays = Integer.MAX_VALUE;
		}
				
		final var f = extraDays;
		
		if (p_19464_ instanceof PreggoMob p && p instanceof IPregnancySystemHandler s) {		
		
			if (f != Integer.MAX_VALUE ) {
				final var oldDaysByStage = s.getDaysByStage();
				final var newDaysByStage = oldDaysByStage + f;
				final var totalDaysPassed = Math.max(0, s.getCurrentPregnancyStage().ordinal() - 1) * oldDaysByStage + s.getDaysPassed();
				final var newTotalDays = s.getLastPregnancyStage().ordinal() * newDaysByStage;			
				s.setDaysByStage(newDaysByStage);
				s.setDaysToGiveBirth(newTotalDays - totalDaysPassed);		
			}
			else {
				s.setDaysByStage(f);
				s.setDaysToGiveBirth(f);			
				p_19464_.addEffect(new MobEffectInstance(MinepreggoModMobEffects.ETERNAL_PREGNANCY.get(), -1, 0));
			}				
 		}
		else if (p_19464_ instanceof ServerPlayer serverPlayer && PlayerHelper.isFemaleAndPregnant(serverPlayer)) {													

			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_SYSTEM).ifPresent(cap -> {
				if (f != Integer.MAX_VALUE) {
					final var lastStage = cap.getLastPregnancyStage().ordinal();
					final var currentStage = cap.getCurrentPregnancyStage().ordinal();
					final var oldDaysByStage = cap.getDaysByStage();
					final var newDaysByStage = oldDaysByStage + f;
					
					final var daysPassed = cap.getDaysPassed();
					final var newTotalDays = lastStage * newDaysByStage;
					final var totalDaysPassed = Math.max(0, currentStage - 1) * oldDaysByStage + daysPassed;

					cap.setDaysByStage(newTotalDays);
					cap.setDaysToGiveBirth(newTotalDays - totalDaysPassed);
				}
			
				else {
					cap.setDaysByStage(f);
					cap.setDaysToGiveBirth(f);
					serverPlayer.addEffect(new MobEffectInstance(MinepreggoModMobEffects.ETERNAL_PREGNANCY.get(), -1, 0));
				}
			});			
		}
	}
}
