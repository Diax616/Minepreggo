package dev.dixmk.minepreggo.world.entity.player;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.server.level.ServerPlayer;

public class PlayerPregnancySystemP5 extends PlayerPregnancySystemP4 {

	public PlayerPregnancySystemP5(@NonNull ServerPlayer player) {
		super(player);
	}
	
	@Override
	protected void initPregnancyTimers() {
		totalTicksOfCraving = MinepreggoModConfig.getTotalTicksOfCravingP5();
		totalTicksOfMilking = MinepreggoModConfig.getTotalTicksOfMilkingP5();
		totalTicksOfBellyRubs = MinepreggoModConfig.getTotalTicksOfBellyRubsP5();
		totalTicksOfHorny = MinepreggoModConfig.getTotalTicksOfHornyP5();
		totalTicksOfPreBirth = PregnancySystemHelper.TOTAL_TICKS_PREBIRTH_P5;
		totalTicksOfBirth = PregnancySystemHelper.TOTAL_TICKS_BIRTH_P5;
	}
}
