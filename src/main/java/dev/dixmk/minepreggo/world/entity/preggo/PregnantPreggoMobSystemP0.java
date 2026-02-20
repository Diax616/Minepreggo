package dev.dixmk.minepreggo.world.entity.preggo;

import dev.dixmk.minepreggo.world.entity.ai.goal.WaterAvoidingRandomStrollBeingPregnantGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;

public class PregnantPreggoMobSystemP0 
	<E extends PreggoMob & ITamablePregnantPreggoMob> extends PreggoMobSystem<E> {

	protected PregnantPreggoMobSystemP0(E preggoMob, int totalTicksOfHungry, int totalTicksOfSexualAppetitve, float angerProbability) {
		super(preggoMob, totalTicksOfHungry, totalTicksOfSexualAppetitve, angerProbability);
	}
	
	public PregnantPreggoMobSystemP0(E preggoMob, int totalTicksOfHungry, int totalTicksOfSexualAppetitve) {
		super(preggoMob, totalTicksOfHungry, totalTicksOfSexualAppetitve);
	}

	@Override
	protected WaterAvoidingRandomStrollGoal createWanderGoal() {
		return new WaterAvoidingRandomStrollBeingPregnantGoal<>(preggoMob, 1.0D);
	}
	
	@Override
	public boolean canOwnerAccessGUI(Player source) {
		return super.canOwnerAccessGUI(source) && !source.isShiftKeyDown() && !preggoMob.getPregnancyData().isIncapacitated();
	}
}
