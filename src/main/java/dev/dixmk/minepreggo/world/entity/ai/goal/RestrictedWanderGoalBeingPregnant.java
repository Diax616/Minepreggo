package dev.dixmk.minepreggo.world.entity.ai.goal;

import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;

public class RestrictedWanderGoalBeingPregnant<E extends PreggoMob & ITamablePregnantPreggoMob> extends RestrictedWanderGoal<E> {

	public RestrictedWanderGoalBeingPregnant(E mob, double speedModifier, double maxRadius) {
		super(mob, speedModifier, maxRadius);
	}
	
	@Override
	public boolean canUse() {
		return super.canUse() 
		&& !preggoMob.getPregnancyData().isIncapacitated();	
	}
	
	@Override
	public boolean canContinueToUse() {
		return super.canContinueToUse() 
		&& !preggoMob.getPregnancyData().isIncapacitated();	
	}
}
