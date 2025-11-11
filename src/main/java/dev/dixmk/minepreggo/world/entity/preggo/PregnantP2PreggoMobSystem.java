package dev.dixmk.minepreggo.world.entity.preggo;

import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;

public class PregnantP2PreggoMobSystem 
	<E extends PreggoMob & ITamablePreggoMob & IPregnancySystemHandler> extends PregnantP1PreggoMobSystem<E> {

	public PregnantP2PreggoMobSystem(E preggoMob, int totalTicksOfHungry) {
		super(preggoMob, totalTicksOfHungry);
	}

	@Override
	public boolean canOwnerAccessGUI(Player source) {
		return super.canOwnerAccessGUI(source) && source.getMainHandItem().getItem() != Items.GLASS_BOTTLE;
	}
}
