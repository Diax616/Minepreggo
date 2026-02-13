package dev.dixmk.minepreggo.world.entity.preggo;

import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;

public class PregnantPreggoMobSystemP2 
	<E extends PreggoMob & ITamablePregnantPreggoMob> extends PregnantPreggoMobSystemP1<E> {

	protected PregnantPreggoMobSystemP2(E preggoMob, int totalTicksOfHungry, int totalTicksOfSexualAppetitve, float angerProbability) {
		super(preggoMob, totalTicksOfHungry, totalTicksOfSexualAppetitve, angerProbability);
	}
	
	public PregnantPreggoMobSystemP2(E preggoMob, int totalTicksOfHungry, int totalTicksOfSexualAppetitve) {
		super(preggoMob, totalTicksOfHungry, totalTicksOfSexualAppetitve, PregnancySystemHelper.MEDIUM_ANGER_PROBABILITY);
	}

	@Override
	public boolean canOwnerAccessGUI(Player source) {
		return super.canOwnerAccessGUI(source) && source.getMainHandItem().getItem() != Items.GLASS_BOTTLE;
	}
	
	@Override
	public boolean canBeAngry() {
		return super.canBeAngry() || preggoMob.getPregnancyData().getMilking() >= PregnancySystemHelper.MAX_MILKING_LEVEL;
	}
}
