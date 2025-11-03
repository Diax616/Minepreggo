package dev.dixmk.minepreggo.world.entity.ai.goal;

import dev.dixmk.minepreggo.world.entity.preggo.IPregnancySystem;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;

public class PregnantPreggoMobOwnerHurtTargetGoal<T extends PreggoMob & ITamablePreggoMob & IPregnancySystem> extends OwnerHurtTargetGoal {
	private final T preggoMob;
	
	public PregnantPreggoMobOwnerHurtTargetGoal(T p_26107_) {
		super(p_26107_);
		this.preggoMob = p_26107_;
	}

	@Override
	public boolean canUse() {
		return super.canUse() 
		&& !preggoMob.isIncapacitated()
		&& !preggoMob.isWaiting()
		&& !preggoMob.isSavage();
	}

	@Override
	public boolean canContinueToUse() {
		return super.canContinueToUse()
		&& !preggoMob.isIncapacitated();	
	}
}
