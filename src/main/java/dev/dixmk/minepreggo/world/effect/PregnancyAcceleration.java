package dev.dixmk.minepreggo.world.effect;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class PregnancyAcceleration extends MobEffect {
	
	public PregnancyAcceleration() {
		super(MobEffectCategory.HARMFUL, -52429);
	}

	@Override
	public void applyInstantenousEffect(@Nullable Entity p_19462_, @Nullable Entity p_19463_, LivingEntity p_19464_, int p_19465_, double p_19466_) { 
			
		if (p_19464_.hasEffect(MinepreggoModMobEffects.ETERNAL_PREGNANCY.get())) return;

		final var days = 6;
				
		if (p_19464_ instanceof IPregnancySystemHandler p) {	
			final var daysBirth = Mth.clamp(p.getDaysByStage() - p.getDaysPassed(), 0, days);		
			p.setDaysToGiveBirth(p.getDaysToGiveBirth() - daysBirth);	
			p.setDaysPassed(Math.min(p.getDaysByStage(), p.getDaysPassed() + days));
		
		}
		else if (p_19464_ instanceof ServerPlayer serverPlayer && PlayerHelper.isFemaleAndPregnant(serverPlayer)) {	
			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_SYSTEM).ifPresent(cap -> {							
				final var daysPassed = cap.getDaysPassed();
				final var daysByStage = cap.getDaysByStage();
				final var daysBirth = Mth.clamp(daysByStage - daysPassed, 0, days);
				final var newDaysPassed = Math.min(daysByStage, daysPassed + days);					
				cap.setDaysPassed(newDaysPassed);
				cap.setDaysToGiveBirth(cap.getDaysToGiveBirth() - daysBirth);
			});
		
		}
	}
	
	@Override
	public boolean isInstantenous() {
		return true;
	}
}
