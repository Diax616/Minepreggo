package dev.dixmk.minepreggo.world.pregnancy;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public abstract class FertilitySystem<E extends PreggoMob & ITamablePreggoMob & IFemaleEntity> {

	protected final RandomSource randomSource;	
	protected final E preggoMob;
	protected int discomfortTick = 0;
	
	
	protected FertilitySystem(@Nonnull E preggoMob) {
		this.preggoMob = preggoMob;	
		this.randomSource = preggoMob.getRandom();
	}
	
	protected void evaluatePregnancyInitializerTimer() {			    	
        if (preggoMob.getPregnancyInitializerTimer() >= MinepreggoModConfig.getTicksToStartPregnancy()) {
        	startPregnancy();
        	preggoMob.discard();
        } else {
        	preggoMob.incrementPregnancyInitializerTimer();
        } 
	}

	protected boolean tryStartRandomDiscomfort() {
        if (randomSource.nextFloat() < 0.001F && !preggoMob.hasEffect(MobEffects.CONFUSION)) {
        	preggoMob.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0, false, true));                 
        	return true;
        }    
        return false;
	}
	
	protected boolean isExperiencingDiscomfort() {
		return preggoMob.hasEffect(MobEffects.CONFUSION);
	}
	
	public void onServerTick() {		
		if (preggoMob.level().isClientSide) {
			return;
		}
		
		if (preggoMob.isPregnant()) {			
			if (!isExperiencingDiscomfort()) {			
				if (discomfortTick > 40) {
					tryStartRandomDiscomfort();
					discomfortTick = 0;
				}
				else {
					++discomfortTick;
				}
			}	
			
			evaluatePregnancyInitializerTimer();
		}
	}
	
	protected abstract void startPregnancy();
}
