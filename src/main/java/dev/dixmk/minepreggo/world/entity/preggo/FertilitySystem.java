package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.world.pregnancy.AbstractBreedableEntity;
import dev.dixmk.minepreggo.world.pregnancy.IBreedable;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.util.RandomSource;

public abstract class FertilitySystem<E extends PreggoMob & ITamablePreggoMob<?>> {

	protected final RandomSource randomSource;	
	protected final E preggoMob;
	private final AbstractBreedableEntity abstractBreedableEntity;
	
	protected FertilitySystem(@Nonnull E preggoMob) {
		this.preggoMob = preggoMob;	
		this.randomSource = preggoMob.getRandom();
		this.abstractBreedableEntity = preggoMob.getGenderedData();
	}
	
	protected void evaluateFertilityTimer() {			    	
        if (abstractBreedableEntity.getFertilityRateTimer() >= PregnancySystemHelper.TOTAL_TICKS_FERTILITY_RATE
        		&& abstractBreedableEntity.getFertilityRate() < IBreedable.MAX_FERTILITY_RATE) {
        	abstractBreedableEntity.resetFertilityRateTimer();
        	abstractBreedableEntity.incrementFertilityRate(0.075F);
        } else {
        	abstractBreedableEntity.incrementFertilityRateTimer();
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
