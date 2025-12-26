package dev.dixmk.minepreggo.world.entity.player;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.server.level.ServerPlayer;

public class PlayerPregnancySystemP7 extends PlayerPregnancySystemP6 {

	public PlayerPregnancySystemP7(@NonNull ServerPlayer player) {
		super(player);
	}

	@Override
	protected void initPregnancyTimers() {
		totalTicksOfCraving = MinepreggoModConfig.getTotalTicksOfCravingP7();
		totalTicksOfMilking = MinepreggoModConfig.getTotalTicksOfMilkingP7();
		totalTicksOfBellyRubs = MinepreggoModConfig.getTotalTicksOfBellyRubsP7();
		totalTicksOfHorny = MinepreggoModConfig.getTotalTicksOfHornyP7();
		totalTicksOfPreBirth = PregnancySystemHelper.TOTAL_TICKS_PREBIRTH_P7;
		totalTicksOfBirth = PregnancySystemHelper.TOTAL_TICKS_BIRTH_P7;
		totalTicksOfFetalMovement = PregnancySystemHelper.TOTAL_TICKS_KICKING_P7;
		pregnancyExhaustion = 1.1f;
	}
}
