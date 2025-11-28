package dev.dixmk.minepreggo.world.entity.player;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemHelper;
import net.minecraft.server.level.ServerPlayer;

public class PlayerPregnancySystemP6 extends PlayerPregnancySystemP5 {

	public PlayerPregnancySystemP6(@NonNull ServerPlayer player) {
		super(player);
	}
	
	@Override
	protected void initPregnancySymptomsTimers() {
		totalTicksOfCraving = MinepreggoModConfig.getTotalTicksOfCravingP6();
		totalTicksOfMilking = MinepreggoModConfig.getTotalTicksOfMilkingP6();
		totalTicksOfBellyRubs = MinepreggoModConfig.getTotalTicksOfBellyRubsP6();
		totalTicksOfHorny = MinepreggoModConfig.getTotalTicksOfHornyP6();
		totalTicksOfPreBirth = PregnancySystemHelper.TOTAL_TICKS_PREBIRTH_P6;
		totalTicksOfBirth = PregnancySystemHelper.TOTAL_TICKS_BIRTH_P6;
	}
}
