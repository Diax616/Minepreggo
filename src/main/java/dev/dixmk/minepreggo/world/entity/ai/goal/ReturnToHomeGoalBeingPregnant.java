package dev.dixmk.minepreggo.world.entity.ai.goal;

import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;

public class ReturnToHomeGoalBeingPregnant<E extends PreggoMob & ITamablePregnantPreggoMob> extends ReturnToHomeGoal<E> {
	
	public ReturnToHomeGoalBeingPregnant(E preggomob, double speedModifier, double maxRadius) {
		super(preggomob, speedModifier, maxRadius);
	}
	
	@Override
	public boolean canUse() {
		return super.canUse() 
		&& !mob.getPregnancyData().isIncapacitated();	
	}
	
	@Override
	public boolean canContinueToUse() {
		return super.canContinueToUse() 
		&& !mob.getPregnancyData().isIncapacitated();	
	}
}
