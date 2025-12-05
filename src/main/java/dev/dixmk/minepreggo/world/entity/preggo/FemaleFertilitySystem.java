package dev.dixmk.minepreggo.world.entity.preggo;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.world.pregnancy.FemaleEntityImpl;
import dev.dixmk.minepreggo.world.pregnancy.IFemaleEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public abstract class FemaleFertilitySystem<E extends PreggoMob & IFemaleEntity & ITamablePreggoMob<FemaleEntityImpl> > extends FertilitySystem<E> {

	protected int discomfortTick = 0;
	
	protected FemaleFertilitySystem(E preggoMob) {
		super(preggoMob);
	}

	@Override
	protected void evaluateFertilitySystem() {
		if (preggoMob.isPregnant()) {			
			if (!isExperiencingDiscomfort()) {			
				if (discomfortTick > 10) {
					tryStartRandomDiscomfort();
					discomfortTick = 0;
				}
				else {
					++discomfortTick;
				}
			}	
			
			evaluatePregnancyInitializerTimer();
		} 
		else {
			super.evaluateFertilitySystem();
		}
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
        	preggoMob.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0, true, true, true));                 
        	return true;
        }    
        return false;
	}
	
	protected boolean isExperiencingDiscomfort() {
		return preggoMob.hasEffect(MobEffects.CONFUSION);
	}
	
	protected abstract void startPregnancy();
	
}
