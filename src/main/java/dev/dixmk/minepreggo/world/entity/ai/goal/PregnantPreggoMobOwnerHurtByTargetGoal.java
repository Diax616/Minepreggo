package dev.dixmk.minepreggo.world.entity.ai.goal;

import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;

public class PregnantPreggoMobOwnerHurtByTargetGoal<T extends PreggoMob & ITamablePregnantPreggoMob> extends OwnerHurtByTargetGoal {

	protected final T preggoMob;
	
	public PregnantPreggoMobOwnerHurtByTargetGoal(T p_26107_) {
		super(p_26107_);
		this.preggoMob = p_26107_;
	}
	
	@Override
	public boolean canUse() {
		return super.canUse() 
		&& !preggoMob.getPregnancyData().isIncapacitated()
		&& !preggoMob.getTamableData().isWaiting()
		&& !preggoMob.getTamableData().isSavage();
	}
	@Override
	public boolean canContinueToUse() {
		return super.canContinueToUse() 
		&& !preggoMob.getPregnancyData().isIncapacitated();
	}
}
