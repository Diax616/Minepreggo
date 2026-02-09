package dev.dixmk.minepreggo.world.entity.preggo;

import dev.dixmk.minepreggo.world.pregnancy.IPregnancySystem;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;

public interface IPreggoMobPregnancySystem extends IPregnancySystem {

	InteractionResult onRightClick(Player source);
		
	void evaluateOnSuccessfulHurt(DamageSource damagesource);
}
