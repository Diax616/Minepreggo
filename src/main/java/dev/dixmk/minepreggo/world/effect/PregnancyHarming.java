package dev.dixmk.minepreggo.world.effect;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMob;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class PregnancyHarming extends MobEffect {
	public PregnancyHarming() {
		super(MobEffectCategory.HARMFUL, -3407821);
	}
	
	@Override
	public void applyInstantenousEffect(@Nullable Entity p_19462_, @Nullable Entity p_19463_, LivingEntity p_19464_, int p_19465_, double p_19466_) { 
		if (p_19464_.level().isClientSide) {
			return;
		}
			
		if (p_19464_ instanceof ITamablePregnantPreggoMob p) {		
			p.getPregnancyData().reducePregnancyHealth(30);
			LivingEntityHelper.playSoundNearTo(p_19464_, MinepreggoModSounds.getRandomStomachGrowls(p_19464_.getRandom()));
			p_19464_.hurt(new DamageSource(p_19464_.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MAGIC)), 1);
		}
		
		else if (p_19464_ instanceof ServerPlayer player) {										
			player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 			
				cap.getFemaleData().ifPresent(femaleData -> {
					if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
						femaleData.getPregnancyData().reducePregnancyHealth(30);
						LivingEntityHelper.playSoundNearTo(p_19464_, MinepreggoModSounds.getRandomStomachGrowls(p_19464_.getRandom()));
						p_19464_.hurt(new DamageSource(p_19464_.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MAGIC)), 1);
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
