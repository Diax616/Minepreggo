package dev.dixmk.minepreggo.world.entity.player;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.server.level.ServerPlayer;

public class PlayerPregnancySystemP6 extends PlayerPregnancySystemP5 {

	public PlayerPregnancySystemP6(@NonNull ServerPlayer player) {
		super(player);
	}
	
	@Override
	protected void initPregnancyTimers() {
		totalTicksOfCraving = MinepreggoModConfig.SERVER.getTotalTicksOfCravingP6();
		totalTicksOfMilking = MinepreggoModConfig.SERVER.getTotalTicksOfMilkingP6();
		totalTicksOfBellyRubs = MinepreggoModConfig.SERVER.getTotalTicksOfBellyRubsP6();
		totalTicksOfHorny = MinepreggoModConfig.SERVER.getTotalTicksOfHornyP6();
		totalTicksOfPreBirth = PregnancySystemHelper.TOTAL_TICKS_PREBIRTH_P6;
		totalTicksOfBirth = PregnancySystemHelper.TOTAL_TICKS_BIRTH_P6;
		totalTicksOfFetalMovement = PregnancySystemHelper.TOTAL_TICKS_KICKING_P6;
		fetalMovementProb = PregnancySystemHelper.HIGH_PREGNANCY_PAIN_PROBABILITY;
		pregnancyExhaustion = 1.04f;
	}
}
