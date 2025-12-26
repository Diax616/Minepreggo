package dev.dixmk.minepreggo.world.entity.player;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.server.level.ServerPlayer;

public class PlayerPregnancySystemP8 extends PlayerPregnancySystemP6 {

	public PlayerPregnancySystemP8(@NonNull ServerPlayer player) {
		super(player);
	}
	
	@Override
	protected void initPregnancyTimers() {
		totalTicksOfCraving = MinepreggoModConfig.getTotalTicksOfCravingP8();
		totalTicksOfMilking = MinepreggoModConfig.getTotalTicksOfMilkingP8();
		totalTicksOfBellyRubs = MinepreggoModConfig.getTotalTicksOfBellyRubsP8();
		totalTicksOfHorny = MinepreggoModConfig.getTotalTicksOfHornyP8();
		totalTicksOfPreBirth = PregnancySystemHelper.TOTAL_TICKS_PREBIRTH_P8;
		totalTicksOfBirth = PregnancySystemHelper.TOTAL_TICKS_BIRTH_P8;
		totalTicksOfFetalMovement = PregnancySystemHelper.TOTAL_TICKS_KICKING_P8;
		pregnancyExhaustion = 1.1f;
	}
}
