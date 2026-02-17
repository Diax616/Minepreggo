package dev.dixmk.minepreggo.world.effect;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class Infertility extends MobEffect {

	public Infertility() {
		super(MobEffectCategory.HARMFUL, -10053376);
	}

	@Override
	public void applyInstantenousEffect(@Nullable Entity p_19462_, @Nullable Entity p_19463_, LivingEntity p_19464_, int p_19465_, double p_19466_) { 		
		if (p_19464_.level().isClientSide) {
			return;
		}
		
		if (p_19464_ instanceof ITamablePreggoMob<?> p) {	
			var genderedData = p.getGenderedData();
			genderedData.setFertilityRate(Math.max(0, genderedData.getFertilityRate() - 0.3f));
			genderedData.resetFertilityRateTimer();
		}		
		else if (p_19464_ instanceof ServerPlayer player) {										
			player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 				
				cap.getBreedableData().ifPresent(breedableData -> {
					breedableData.setFertilityRate(Math.max(0, breedableData.getFertilityRate() - 0.3f));
					breedableData.resetFertilityRateTimer();
				})
			);			
		}
	}
	
	@Override
	public boolean isInstantenous() {
		return true;
	}
}
