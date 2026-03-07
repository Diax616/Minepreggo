package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.world.pregnancy.IBreedable;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;

public abstract class FertilitySystem<E extends PreggoMob & ITamablePreggoMob<?>> {
	protected final E preggoMob;
	
	protected FertilitySystem(@Nonnull E preggoMob) {
		this.preggoMob = preggoMob;	
	}
	
	protected void evaluateFertilityTimer() {
		var breedableData = preggoMob.getGenderedData();
        if (breedableData.getFertilityRate() < IBreedable.MAX_FERTILITY_RATE) {     	
        	if (breedableData.getFertilityRateTimer() >= PregnancySystemHelper.TOTAL_TICKS_FERTILITY_RATE) {
        		breedableData.resetFertilityRateTimer();  	
        		breedableData.incrementFertilityRate(0.075F);
        	}
        	else {
        		breedableData.incrementFertilityRateTimer();
            } 
        } 
	}
	
	public final void onServerTick() {		
		if (preggoMob.level().isClientSide) {
			return;
		}		
		evaluateFertilitySystem();	
	}
	
	protected void evaluateFertilitySystem() {
		evaluateFertilityTimer();
	}
}
