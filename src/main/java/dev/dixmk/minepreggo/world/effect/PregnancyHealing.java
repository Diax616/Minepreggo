package dev.dixmk.minepreggo.world.effect;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemConstants;
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
		if (p_19464_ instanceof PreggoMob t && t instanceof IPregnancySystemHandler p) {		
			p.setPregnancyHealth(PregnancySystemConstants.MAX_PREGNANCY_HEALTH);
			t.heal(2F);
		}
		
		else if (p_19464_ instanceof ServerPlayer player && PlayerHelper.isFemaleAndPregnant(player)) {										
			player.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_SYSTEM).ifPresent(cap -> {
				cap.setPregnancyHealth(PregnancySystemConstants.MAX_PREGNANCY_HEALTH);
				player.heal(2F);
			});			
		}
	}
	

	@Override
	public boolean isInstantenous() {
		return true;
	}
}
