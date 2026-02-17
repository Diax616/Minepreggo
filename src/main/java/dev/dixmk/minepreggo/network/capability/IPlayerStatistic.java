package dev.dixmk.minepreggo.network.capability;

import java.util.Set;

import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.item.checkup.PrenatalCheckups.PrenatalCheckup;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IPlayerStatistic extends INBTSerializable<CompoundTag>{
	Set<Species> getSuccessfulBirths();
	Set<Species> getEntitiesGottenPregnant();
	Set<PrenatalCheckup> getPrenatalCheckupsDoneMyself();
	boolean addSuccessfulBirth(Species species);
	boolean addEntityGottenPregnant(Species species);
	boolean addPrenatalCheckupDoneMyself(PrenatalCheckup checkup);
}
