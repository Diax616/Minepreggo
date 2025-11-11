package dev.dixmk.minepreggo.world.entity.preggo;

import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;
import net.minecraft.world.entity.player.Player;

public class PregnantP1PreggoMobSystem
	<E extends PreggoMob & ITamablePreggoMob & IPregnancySystemHandler> extends PreggoMobSystem<E> {

	public PregnantP1PreggoMobSystem(E preggoMob, int totalTicksOfHungry) {
		super(preggoMob, totalTicksOfHungry);
	}

	@Override
	public boolean canOwnerAccessGUI(Player source) {
		return super.canOwnerAccessGUI(source) && !source.isShiftKeyDown() && !preggoMob.isIncapacitated();
	}
	
	@Override
	public boolean canFeedHerself() {
		return super.canFeedHerself() && !preggoMob.isIncapacitated();
	}
}
