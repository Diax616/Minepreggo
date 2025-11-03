package dev.dixmk.minepreggo.world.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class PregnancyAcceleration extends MobEffect {

	
	public PregnancyAcceleration() {
		super(MobEffectCategory.HARMFUL, -52429);
	}

	/*
	@Override
	public void applyInstantenousEffect(@Nullable Entity p_19462_, @Nullable Entity p_19463_, LivingEntity p_19464_, int p_19465_, double p_19466_) { 
			
		if (p_19464_.hasEffect(MinepreggoModMobEffects.ETERNAL_PREGNANCY.get())) return;

		final var days = 6;
				
		if (p_19464_ instanceof PreggoP1 p) {	
		
			final var daysBirth = Math.min(Math.max(0, p.getDaysByStage() - p.getDaysPassed()), days);		
			p.setDaysToBirth(p.getDaysToBirth() - daysBirth);	
			p.setDaysPassed(Math.min(p.getDaysByStage(), p.getDaysPassed() + days));
		
		}
		else if (p_19464_ instanceof Player player
				&& player.getCapability(MinepreggoModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MinepreggoModVariables.PlayerVariables()).isPlayerPregnant) {							
			
			final var daysPassed = (int) player.getCapability(MinepreggoModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MinepreggoModVariables.PlayerVariables()).playerDaysPassed;
			final var daysByStage = (int) player.getCapability(MinepreggoModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MinepreggoModVariables.PlayerVariables()).playerDaysByStage;
			
			final double daysBirth = Math.min(Math.max(0, daysByStage - daysPassed), days);
			final double playerDaysPassed = Math.min(daysByStage, daysPassed + days);
			
			player.getCapability(MinepreggoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.playerDaysPassed = playerDaysPassed;
				capability.playerDaysToGiveBirth = player.getCapability(MinepreggoModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MinepreggoModVariables.PlayerVariables()).playerDaysToGiveBirth - daysBirth;
				capability.syncPlayerVariables(player);
			});			
		}
	}
	*/
	
	@Override
	public boolean isInstantenous() {
		return true;
	}
}
