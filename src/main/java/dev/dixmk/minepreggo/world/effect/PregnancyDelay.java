package dev.dixmk.minepreggo.world.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class PregnancyDelay extends MobEffect {

	public PregnancyDelay() {
		super(MobEffectCategory.HARMFUL, -39220);
	}
	
	@Override
	public boolean isInstantenous() {
		return true;
	}
	
	/*
	@Override
	public void applyInstantenousEffect(@Nullable Entity p_19462_, @Nullable Entity p_19463_, LivingEntity p_19464_, int p_19465_, double p_19466_) { 
			
		if (p_19464_.hasEffect(MinepreggoModMobEffects.ETERNAL_PREGNANCY.get())) return;
		
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
		
		if (p_19464_ instanceof PreggoP1 p) {		
		
			if (f != Integer.MAX_VALUE ) {
				final var oldDaysByStage = p.getDaysByStage();
				final var newDaysByStage = oldDaysByStage + f;
				final var totalDaysPassed = Math.max(0, p.getCurrentStage() - 1) * oldDaysByStage + p.getDaysPassed();
				final var newTotalDays = p.getMaxStage() * newDaysByStage;			
				p.setDaysByStage(newDaysByStage);
				p.setDaysToBirth(newTotalDays - totalDaysPassed);		
			}
			else {
				p.setDaysByStage(f);
				p.setDaysToBirth(f);
				p.setDaysPassed(Integer.MIN_VALUE);
				
				if (!p_19464_.level().isClientSide()) {
					p_19464_.addEffect(new MobEffectInstance(MinepreggoModMobEffects.ETERNAL_PREGNANCY.get(), -1, 0));
				}
			}				
 		}
		else if (p_19464_ instanceof Player player
				&& player.getCapability(MinepreggoModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MinepreggoModVariables.PlayerVariables()).isPlayerPregnant) {							
						
			if (f != Integer.MAX_VALUE) {
				final var maxStage = (int) player.getCapability(MinepreggoModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MinepreggoModVariables.PlayerVariables()).playerMaxStage;
				final var currentStage = (int) player.getCapability(MinepreggoModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MinepreggoModVariables.PlayerVariables()).playerCurrentStage;
				final var oldDaysByStage = (int) player.getCapability(MinepreggoModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MinepreggoModVariables.PlayerVariables()).playerDaysByStage;
				final var newDaysByStage = oldDaysByStage + f;
				final var daysPassed = (int) player.getCapability(MinepreggoModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MinepreggoModVariables.PlayerVariables()).playerDaysPassed;
				final var newTotalDays = maxStage * newDaysByStage;
		
				final var totalDaysPassed = Math.max(0, currentStage - 1) * oldDaysByStage + daysPassed;
				
				player.getCapability(MinepreggoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {					
					capability.playerDaysByStage = newDaysByStage;
					capability.playerDaysToGiveBirth = (newTotalDays - totalDaysPassed);
					capability.syncPlayerVariables(player);
				});		
			}
		
			else {
				player.getCapability(MinepreggoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {					
					capability.playerDaysByStage = Double.POSITIVE_INFINITY;
					capability.playerDaysToGiveBirth = Double.POSITIVE_INFINITY;
					capability.playerDaysPassed = Double.NEGATIVE_INFINITY;
					capability.syncPlayerVariables(player);
				});		

				if (!player.level().isClientSide()) {
					player.addEffect(new MobEffectInstance(MinepreggoModMobEffects.ETERNAL_PREGNANCY.get(), -1, 0));
				}
			}
			
		}
	}
	*/
}
