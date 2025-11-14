package dev.dixmk.minepreggo.world.entity.preggo;

import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;

public class PregnantPreggoMobSystemP2 
	<E extends PreggoMob & ITamablePreggoMob & IPregnancySystemHandler> extends PregnantPreggoMobSystemP1<E> {

	public PregnantPreggoMobSystemP2(E preggoMob, int totalTicksOfHungry) {
		super(preggoMob, totalTicksOfHungry);
	}

	@Override
	public boolean canOwnerAccessGUI(Player source) {
		return super.canOwnerAccessGUI(source) && source.getMainHandItem().getItem() != Items.GLASS_BOTTLE;
	}
}
