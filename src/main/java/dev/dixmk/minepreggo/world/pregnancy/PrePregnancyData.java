package dev.dixmk.minepreggo.world.pregnancy;

import java.util.UUID;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;

import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

public record PrePregnancyData(PregnancyType pregnancyType, @Nonnegative int fertilizedEggs, Species typeOfSpeciesOfFather, Creature typeOfCreatureOfFather, @Nullable UUID fatherId) {		
	
	private static final String NBT_KEY = "NBTPrePregnancyData";
	
    public CompoundTag toNBT() {
		CompoundTag wrapper = new CompoundTag();			
		CompoundTag nbt = new CompoundTag();
		
		nbt.putInt("DataFertilizedEggs", fertilizedEggs);		
		if (fatherId != null) {
			nbt.putUUID("DataFatherId", fatherId);
		}
		nbt.putString(Species.NBT_KEY, typeOfSpeciesOfFather.name());
		nbt.putString(Creature.NBT_KEY, typeOfCreatureOfFather.name());	
		nbt.putString(PregnancyType.NBT_KEY, pregnancyType.name());
		wrapper.put(NBT_KEY, nbt);
		return wrapper;
    }
	
    @CheckForNull
	public static PrePregnancyData fromNBT(CompoundTag nbt) {		
		if (nbt.contains(NBT_KEY, Tag.TAG_COMPOUND)) {			
			CompoundTag data = nbt.getCompound(NBT_KEY);		
			int fertilizedEggs = data.getInt("DataFertilizedEggs");
			UUID fatherId = data.contains("DataFatherId") ? data.getUUID("DataFatherId") : null;
			Species typeOfSpeciesOfFather = Species.valueOf(data.getString(Species.NBT_KEY));
			Creature typeOfCreatureOfFather = Creature.valueOf(data.getString(Creature.NBT_KEY));
		    PregnancyType pregnancyType = PregnancyType.valueOf(data.getString(PregnancyType.NBT_KEY));
			return new PrePregnancyData(pregnancyType, fertilizedEggs, typeOfSpeciesOfFather, typeOfCreatureOfFather, fatherId);
		}	
		return null;
	}
}
