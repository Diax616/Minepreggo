package dev.dixmk.minepreggo.world.effect;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancySystemHandler;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class PregnancyHealing extends MobEffect {
	
	public PregnancyHealing() {
		super(MobEffectCategory.BENEFICIAL, -3407821);
	}
	
	@Override
	public void applyInstantenousEffect(@Nullable Entity p_19462_, @Nullable Entity p_19463_, LivingEntity p_19464_, int p_19465_, double p_19466_) { 
		if (p_19464_ instanceof IPregnancySystemHandler p) {		
			p.setPregnancyHealth(PregnancySystemHelper.MAX_PREGNANCY_HEALTH);
		}
		
		else if (p_19464_ instanceof ServerPlayer player) {										
			player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 			
				cap.getFemaleData().ifPresent(femaleData -> {
					if (!femaleData.isPregnant()) {
						return;
					}	
					femaleData.getPregnancySystem().setPregnancyHealth(PregnancySystemHelper.MAX_PREGNANCY_HEALTH);
				})
			);			
		}
	}
	

	@Override
	public boolean isInstantenous() {
		return true;
	}
}
