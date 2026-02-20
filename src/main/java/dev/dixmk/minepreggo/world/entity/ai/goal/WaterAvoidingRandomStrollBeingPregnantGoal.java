package dev.dixmk.minepreggo.world.entity.ai.goal;

import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;

public class WaterAvoidingRandomStrollBeingPregnantGoal<E extends PreggoMob & ITamablePregnantPreggoMob> extends WaterAvoidingRandomStrollGoal {

	protected final E preggoMob;
	
	public WaterAvoidingRandomStrollBeingPregnantGoal(E preggoMob, double speedModifier) {
		super(preggoMob, speedModifier);
		this.preggoMob = preggoMob;
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
