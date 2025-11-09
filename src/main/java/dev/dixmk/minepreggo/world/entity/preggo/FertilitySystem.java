package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public abstract class FertilitySystem<E extends PreggoMob & ITamablePreggoMob & IBreedable> {

	protected final RandomSource randomSource;	
	protected final E preggoMob;
	
	protected FertilitySystem(@Nonnull E preggoMob) {
		this.preggoMob = preggoMob;	
		this.randomSource = preggoMob.getRandom();
	}
	
	protected void evaluatePregnancyInitializerTimer() {			    	
        if (preggoMob.getPregnancyInitializerTimer() >= MinepreggoModConfig.getTicksToStartPregnancy()) {
        	startPregnancy();
        	preggoMob.discard();
        } else {
        	preggoMob.setPregnancyInitializerTimer(preggoMob.getPregnancyInitializerTimer() + 1);
    
        } 
	}

	protected boolean tryStartRandomDiscomfort() {
        if (randomSource.nextFloat() < 0.0001F && !preggoMob.hasEffect(MobEffects.CONFUSION)) {
        	preggoMob.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0, false, true));                 
        	return true;
        }    
        return false;
	}
	
	protected boolean isExperiencingDiscomfort() {
		return preggoMob.hasEffect(MobEffects.CONFUSION);
	}
	
	public void onServerTick() {
		final var level = preggoMob.level();
		
		if (level.isClientSide()) {
			return;
		}
		
		if (preggoMob.isPregnant()) {
			evaluatePregnancyInitializerTimer();
			
			if (!isExperiencingDiscomfort()) {
				tryStartRandomDiscomfort();
			}		
		}
	}
	
	protected abstract void startPregnancy();
}
