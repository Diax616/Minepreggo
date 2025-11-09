package dev.dixmk.minepreggo.world.effect;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.entity.preggo.IBreedable;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class Fertility extends MobEffect {

	public Fertility() {
		super(MobEffectCategory.BENEFICIAL, -10027213);
	}

	@Override
	public void applyInstantenousEffect(@Nullable Entity p_19462_, @Nullable Entity p_19463_, LivingEntity p_19464_, int p_19465_, double p_19466_) { 
		if (p_19464_ instanceof PreggoMob t && t instanceof IBreedable p) {		
			p.incrementFertilityRate(0.1F);
			t.heal(1F);
		}
		
		else if (p_19464_ instanceof ServerPlayer player && PlayerHelper.isFemale(player)) {										
			player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> {
				cap.incrementFertilityRate(0.1F);
				player.heal(1F);
			});			
		}
	}
	

	@Override
	public boolean isInstantenous() {
		return true;
	}
}
