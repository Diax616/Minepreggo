package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.world.pregnancy.IBreedable;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.util.RandomSource;

public abstract class FertilitySystem<E extends PreggoMob & ITamablePreggoMob<?>> {

	protected final RandomSource randomSource;	
	protected final E preggoMob;
	private final IBreedable breedableData;
	
	protected FertilitySystem(@Nonnull E preggoMob) {
		this.preggoMob = preggoMob;	
		this.randomSource = preggoMob.getRandom();
		this.breedableData = preggoMob.getGenderedData();
	}
	
	protected void evaluateFertilityTimer() {			    	
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
