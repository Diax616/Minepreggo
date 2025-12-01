package dev.dixmk.minepreggo.world.entity.ai.goal;

import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancySystemHandler;

public class PregnantPreggoMobFollowOwnerGoal<T extends PreggoMob & ITamablePreggoMob<?> & IPregnancySystemHandler> extends PreggoMobFollowOwnerGoal<T> {
	
	public PregnantPreggoMobFollowOwnerGoal(T p_25294_, double p_25295_, float p_25296_, float p_25297_, boolean p_25298_) {
		super(p_25294_, p_25295_, p_25296_, p_25297_, p_25298_);	
	}
	
	@Override
	public boolean canUse() {	
		return super.canUse() 
		&& !preggoMob.isIncapacitated();

	}

	@Override
	public boolean canContinueToUse() {
		return super.canContinueToUse()
		&& !preggoMob.isIncapacitated();
	}
}
