package dev.dixmk.minepreggo.world.entity.ai.goal;

import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;

public class PregnantPreggoMobFollowOwnerGoal<T extends PreggoMob & ITamablePregnantPreggoMob> extends PreggoMobFollowOwnerGoal<T> {
	
	public PregnantPreggoMobFollowOwnerGoal(T p_25294_, double p_25295_, float p_25296_, float p_25297_, boolean p_25298_) {
		super(p_25294_, p_25295_, p_25296_, p_25297_, p_25298_);	
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
