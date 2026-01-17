package dev.dixmk.minepreggo.world.entity.preggo;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;

public class PregnantPreggoMobSystemP2 
	<E extends PreggoMob & ITamablePregnantPreggoMob> extends PregnantPreggoMobSystemP1<E> {

	public PregnantPreggoMobSystemP2(E preggoMob, int totalTicksOfHungry, int totalTicksOfSexualAppetitve) {
		super(preggoMob, totalTicksOfHungry, totalTicksOfSexualAppetitve);
	}

	@Override
	public boolean canOwnerAccessGUI(Player source) {
		return super.canOwnerAccessGUI(source) && source.getMainHandItem().getItem() != Items.GLASS_BOTTLE;
	}
}
