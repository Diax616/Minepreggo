package dev.dixmk.minepreggo.world.effect;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.server.ServerParticleHelper;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMob;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.core.particles.ParticleTypes;
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
		if (p_19464_.level().isClientSide) {
			return;
		}
		
		if (p_19464_ instanceof ITamablePregnantPreggoMob p) {		
			p.getPregnancyData().setPregnancyHealth(PregnancySystemHelper.MAX_PREGNANCY_HEALTH);
			ServerParticleHelper.spawnParticlesAroundSelf(p_19464_, ParticleTypes.HAPPY_VILLAGER, 12);
		}
		
		else if (p_19464_ instanceof ServerPlayer player) {										
			player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 			
				cap.getFemaleData().ifPresent(femaleData -> {
					if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
						femaleData.getPregnancyData().setPregnancyHealth(PregnancySystemHelper.MAX_PREGNANCY_HEALTH);
						ServerParticleHelper.spawnParticlesAroundSelf(p_19464_, ParticleTypes.HAPPY_VILLAGER, 12);
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
