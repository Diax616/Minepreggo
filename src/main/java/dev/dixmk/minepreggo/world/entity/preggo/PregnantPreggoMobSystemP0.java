package dev.dixmk.minepreggo.world.entity.preggo;

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
	public boolean canOwnerAccessGUI(Player source) {
		return super.canOwnerAccessGUI(source) && !source.isShiftKeyDown() && !preggoMob.getPregnancyData().isIncapacitated();
	}
}
