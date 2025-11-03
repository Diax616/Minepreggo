package dev.dixmk.minepreggo.world.entity.ai.goal;

import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;

public class PreggoMobFollowOwnerGoal<T extends PreggoMob & ITamablePreggoMob> extends FollowOwnerGoal {
	
	protected final T preggoMob;
	
	public PreggoMobFollowOwnerGoal(T p_25294_, double p_25295_, float p_25296_, float p_25297_,
			boolean p_25298_) {
		super(p_25294_, p_25295_, p_25296_, p_25297_, p_25298_);
		this.preggoMob = p_25294_;
	}

	@Override
	public void tick() {
		this.tamable.getLookControl().setLookAt(this.owner, 10.0F, this.tamable.getMaxHeadXRot());
		if (--this.timeToRecalcPath <= 0) {
			this.timeToRecalcPath = this.adjustedTickDelay(10);
			this.navigation.moveTo(this.owner, this.speedModifier);
		}		
		
		if (this.tamable.getTarget() != null
				&& this.tamable.distanceToSqr(this.owner) > 169D) {
			this.tamable.setTarget(null);
			this.navigation.stop();
		}		
	}

	@Override
	public boolean canUse() {
		return super.canUse() 
		&& !preggoMob.isWaiting()
		&& !preggoMob.isSavage()
		&& !PreggoMobHelper.hasValidTarget(preggoMob);
	}

	@Override
	public boolean canContinueToUse() {
		return super.canContinueToUse()
		&& !PreggoMobHelper.isTargetStillValid(preggoMob);
	}
}
