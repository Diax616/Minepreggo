package dev.dixmk.minepreggo.network.capability;

import java.util.EnumSet;
import java.util.Set;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.item.checkup.PrenatalCheckups.PrenatalCheckup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

public class PlayerStatisticImpl implements IPlayerStatistic {
	private final Set<Species> successfulBirths = EnumSet.noneOf(Species.class);
	private final Set<Species> entitiesGottenPregnant = EnumSet.noneOf(Species.class);
	private final Set<PrenatalCheckup> prenatalCheckupsDoneMyself = EnumSet.noneOf(PrenatalCheckup.class);
	
	@Override
	public Set<Species> getSuccessfulBirths() {
		return this.successfulBirths;
	}

	@Override
	public Set<Species> getEntitiesGottenPregnant() {
		return this.entitiesGottenPregnant;
	}

	@Override
	public boolean addSuccessfulBirth(Species species) {
		return this.successfulBirths.add(species);
	}

	@Override
	public boolean addEntityGottenPregnant(Species species) {
		return this.entitiesGottenPregnant.add(species);
	}

	@Override
	public Set<PrenatalCheckup> getPrenatalCheckupsDoneMyself() {
		return this.prenatalCheckupsDoneMyself;
	}

	@Override
	public boolean addPrenatalCheckupDoneMyself(PrenatalCheckup checkup) {
		return this.prenatalCheckupsDoneMyself.add(checkup);
	}
	
	@Override
	public CompoundTag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		if (!successfulBirths.isEmpty()) {
			CompoundTag birthsTag = new CompoundTag();
			for (Species species : successfulBirths) {
				birthsTag.putBoolean(species.name(), true);
			}
			nbt.put("SuccessfulBirths", birthsTag);
		}
		if (!entitiesGottenPregnant.isEmpty()) {
			CompoundTag pregnantTag = new CompoundTag();
			for (Species species : entitiesGottenPregnant) {
				pregnantTag.putBoolean(species.name(), true);
			}
			nbt.put("EntitiesGottenPregnant", pregnantTag);
		}
		if (!prenatalCheckupsDoneMyself.isEmpty()) {
			CompoundTag checkupsMyselfTag = new CompoundTag();
			for (PrenatalCheckup checkup : prenatalCheckupsDoneMyself) {
				checkupsMyselfTag.putBoolean(checkup.name(), true);
			}
			nbt.put("PrenatalCheckupsDoneMyself", checkupsMyselfTag);
		}

		return nbt;
	}
	
	@Override
	public void deserializeNBT(CompoundTag nbt) {
	    if (nbt.contains("SuccessfulBirths", Tag.TAG_COMPOUND)) {
	    	CompoundTag birthsTag = nbt.getCompound("SuccessfulBirths");
	    	successfulBirths.clear();
	    	for (String key : birthsTag.getAllKeys()) {
	    		try {
	    			Species species = Species.valueOf(key);
	    			if (birthsTag.getBoolean(key)) {
	    				successfulBirths.add(species);
	    			}
	    		} catch (IllegalArgumentException e) {
	    			MinepreggoMod.LOGGER.error("Unknown species in SuccessfulBirths: {}", e.getMessage());
	    		}
	    	}
	    }
	    if (nbt.contains("EntitiesGottenPregnant", Tag.TAG_COMPOUND)) {
	    	CompoundTag pregnantTag = nbt.getCompound("EntitiesGottenPregnant");
	    	entitiesGottenPregnant.clear();
	    	for (String key : pregnantTag.getAllKeys()) {
	    		try {
	    			Species species = Species.valueOf(key);
	    			if (pregnantTag.getBoolean(key)) {
	    				entitiesGottenPregnant.add(species);
	    			}
	    		} catch (IllegalArgumentException e) {
	    			MinepreggoMod.LOGGER.error("Unknown species in EntitiesGottenPregnant: {}", e.getMessage());
	    		}
	    	}
	    }
	    if (nbt.contains("PrenatalCheckupsDoneMyself", Tag.TAG_COMPOUND)) {
	    	CompoundTag checkupsMyselfTag = nbt.getCompound("PrenatalCheckupsDoneMyself");
	    	prenatalCheckupsDoneMyself.clear();
	    	for (String key : checkupsMyselfTag.getAllKeys()) {
	    		try {
	    			PrenatalCheckup checkup = PrenatalCheckup.valueOf(key);
	    			if (checkupsMyselfTag.getBoolean(key)) {
	    				prenatalCheckupsDoneMyself.add(checkup);
	    			}
	    		} catch (IllegalArgumentException e) {
	    			MinepreggoMod.LOGGER.error("Unknown prenatal checkup in PrenatalCheckupsDoneMyself: {}", e.getMessage());
	    		}
	    	}
	    }

	}
}
