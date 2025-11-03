package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem.Result;
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
	
	private final Result evaluatePregnancyInitializerTimer() {			    	
		if (preggoMob.isPregnant()) {					
	        if (preggoMob.getPregnancyInitializerTimer() >= MinepreggoModConfig.getTicksToStartPregnancy()) {
	        	this.startPregnancy();
	        	preggoMob.discard();
	        	return Result.SUCCESS;
	        } else {
	        	preggoMob.setPregnancyInitializerTimer(preggoMob.getPregnancyInitializerTimer() + 1);
                if (randomSource.nextFloat() < 0.0001F
                		&& !preggoMob.hasEffect(MobEffects.CONFUSION)) {
                	preggoMob.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0, false, true));                 
                }        
                return Result.PROCESS;
	        }  
	    }
        return Result.NOTHING;
	}
	
	public void onTick() {
		final var level = preggoMob.level();
		
		if (level.isClientSide()) {
			return;
		}
		
		evaluatePregnancyInitializerTimer();
	}
	
	protected abstract void startPregnancy();
}
