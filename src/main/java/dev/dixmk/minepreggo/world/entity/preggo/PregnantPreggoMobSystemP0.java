package dev.dixmk.minepreggo.world.entity.preggo;

import dev.dixmk.minepreggo.world.pregnancy.IPregnancySystemHandler;
import net.minecraft.world.entity.player.Player;

public class PregnantPreggoMobSystemP0 
	<E extends PreggoMob & ITamablePreggoMob<?> & IPregnancySystemHandler> extends PreggoMobSystem<E> {

	public PregnantPreggoMobSystemP0(E preggoMob, int totalTicksOfHungry) {
		super(preggoMob, totalTicksOfHungry);
	}

	@Override
	public boolean canOwnerAccessGUI(Player source) {
		return super.canOwnerAccessGUI(source) && !source.isShiftKeyDown() && !preggoMob.isIncapacitated();
	}
}
