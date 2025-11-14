package dev.dixmk.minepreggo.world.entity.player;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemHelper;
import net.minecraft.server.level.ServerPlayer;

public class PlayerPregnancySystemP8 extends PlayerPregnancySystemP6 {

	public PlayerPregnancySystemP8(@NonNull ServerPlayer player) {
		super(player);
		totalTicksOfCraving = MinepreggoModConfig.getTotalTicksOfCravingP8();
		totalTicksOfMilking = MinepreggoModConfig.getTotalTicksOfMilkingP8();
		totalTicksOfBellyRubs = MinepreggoModConfig.getTotalTicksOfBellyRubsP8();
		totalTicksOfHorny = MinepreggoModConfig.getTotalTicksOfHornyP8();
		totalTicksOfPreBirth = PregnancySystemHelper.TOTAL_TICKS_PREBIRTH_P8;
		totalTicksOfBirth = PregnancySystemHelper.TOTAL_TICKS_BIRTH_P8;
	}
}
