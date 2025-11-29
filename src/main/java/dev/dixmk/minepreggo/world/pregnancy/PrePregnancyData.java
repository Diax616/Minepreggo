package dev.dixmk.minepreggo.world.pregnancy;

import java.util.UUID;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

public record PrePregnancyData(@Nonnegative int fertilizedEggs, Species typeOfSpeciesOfFather, Creature typeOfCreatureOfFather, @Nullable UUID fatherId) {		
	
	public static final String NBT_KEY = "PrePregnancyData";
	
	public void serializeNBT(@NonNull Tag tag, String id) {
		CompoundTag nbt = (CompoundTag) tag;			
		CompoundTag data = new CompoundTag();
		
		data.putInt("DataFertilizedEggs", fertilizedEggs);		
		if (fatherId != null) {
			data.putUUID("DataFatherId", fatherId);
		}
		if (typeOfSpeciesOfFather != null) {
			data.putString(Species.NBT_KEY, typeOfSpeciesOfFather.name());
		}
		if (typeOfCreatureOfFather != null) {
			data.putString(Creature.NBT_KEY, typeOfCreatureOfFather.name());
		}			
		nbt.put(id, data);
	}

	@CheckForNull
	public static PrePregnancyData deserializeNBT(@NonNull Tag tag, String id) {
		CompoundTag nbt = (CompoundTag) tag;			
		if (nbt.contains(id, Tag.TAG_COMPOUND)) {			
			CompoundTag data = nbt.getCompound(id);		
			int fertilizedEggs = data.getInt("DataFertilizedEggs");
			UUID fatherId = data.contains("DataFatherId") ? data.getUUID("DataFather") : null;
			Species typeOfSpeciesOfFather = data.contains(Species.NBT_KEY, Tag.TAG_STRING) ? Species.valueOf(data.getString(Species.NBT_KEY)) : null;
			Creature typeOfCreatureOfFather = data.contains(Creature.NBT_KEY, Tag.TAG_STRING) ? Creature.valueOf(data.getString(Creature.NBT_KEY)) : null;
		    return new PrePregnancyData(fertilizedEggs, typeOfSpeciesOfFather, typeOfCreatureOfFather, fatherId);
		}	
		return null;
	}
}
