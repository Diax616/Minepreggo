package dev.dixmk.minepreggo.world.pregnancy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.CheckForNull;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableSetMultimap;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.RandomSource;

public class BabyData {
	public final Gender gender;
	public final Species typeOfSpecies;
	public final Creature typeOfCreature;
	public final UUID motherId;
	public final Optional<UUID> fatherId;
	
	private BabyData(Gender gender, Species typeOfSpecies, Creature typeOfCreature, UUID motherId, @Nullable UUID fatherId) {
		this.gender = gender;
		this.typeOfSpecies = typeOfSpecies;
		this.typeOfCreature = typeOfCreature;
		this.motherId = motherId;
		this.fatherId = Optional.ofNullable(fatherId);
	}
	
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeEnum(this.gender);
		buffer.writeEnum(this.typeOfSpecies);
		buffer.writeEnum(this.typeOfCreature);
		buffer.writeUUID(this.motherId);
		buffer.writeBoolean(this.fatherId.isPresent());
		this.fatherId.ifPresent(buffer::writeUUID);
	}
		

	public static BabyData decode(FriendlyByteBuf buffer) {
		Gender gender = buffer.readEnum(Gender.class);
		Species typeOfSpecies = buffer.readEnum(Species.class);;
		Creature typeOfCreature = buffer.readEnum(Creature.class);
		UUID motherId = buffer.readUUID();
		UUID fatherId = null;
		if (buffer.readBoolean()) {
			fatherId = buffer.readUUID();
		}
		return new BabyData(
				gender,
				typeOfSpecies,
				typeOfCreature,
				motherId,
				fatherId);
	}
	
	public void serializeNBT(@NonNull Tag tag) {
		CompoundTag nbt = (CompoundTag) tag;
		nbt.putString(Gender.NBT_KEY, gender.name());
		nbt.putString(Species.NBT_KEY, typeOfSpecies.name());
		nbt.putString(Creature.NBT_KEY, typeOfCreature.name());
		nbt.putUUID("motheruuid", motherId);
		fatherId.ifPresent(id -> nbt.putUUID("fatheruuid", id));
	}
	
	public static BabyData deserializeNBT(@NonNull Tag tag) {
		CompoundTag nbt = (CompoundTag) tag;
		Gender gender = Gender.valueOf(nbt.getString(Gender.NBT_KEY));
		Species typeOfSpecies = Species.valueOf(nbt.getString(Species.NBT_KEY));
		Creature typeOfCreature = Creature.valueOf(nbt.getString(Creature.NBT_KEY));
		UUID motherId = nbt.getUUID("motheruuid");
		UUID fatherId = nbt.contains("fatheruuid") ? nbt.getUUID("fatheruuid") : null;	
		return new BabyData(
				gender,
				typeOfSpecies,
				typeOfCreature,
				motherId,
				fatherId);
	}
	
    private static final ImmutableSetMultimap<Species, Creature> VALID_COMBINATIONS =
            ImmutableSetMultimap.<Species, Creature>builder()
                .put(Species.HUMAN, Creature.HUMANOID)
                .put(Species.CREEPER, Creature.MONSTER)
                .put(Species.CREEPER, Creature.HUMANOID)
                .put(Species.ZOMBIE, Creature.HUMANOID)
                .put(Species.ENDER, Creature.HUMANOID)
                .put(Species.ENDER, Creature.MONSTER)
                .put(Species.VILLAGER, Creature.HUMANOID)
                .build();
     

    public static boolean isValid(Species species, Creature creature) {
    	return VALID_COMBINATIONS.containsEntry(species, creature);
    }
    
    public static @NonNull BabyData create(@NonNull ImmutableTriple<UUID, Species, Creature> mother, @NonNull ImmutableTriple<Optional<UUID>, Species, Creature> father, RandomSource random) {
    	
    	Gender gender = random.nextBoolean() ? Gender.FEMALE : Gender.MALE;
    	ImmutablePair<Species, Creature> pair;
 	
		pair = getValidSpeciesAndCreatureFromParents(
				ImmutablePair.of(mother.getMiddle(), mother.getRight()),
				ImmutablePair.of(father.getMiddle(), father.getRight()),
				random);  	  		
		
		if (pair != null) {
			return new BabyData(gender, pair.getLeft(), pair.getRight(), mother.getLeft(), father.getLeft().orElse(null));
		}
		else {
			MinepreggoMod.LOGGER.warn("Could not determine valid species/creature combination from parents: mother species {} ", mother.getMiddle().name());
			return new BabyData(gender, mother.getMiddle(), mother.getRight(), mother.getLeft(), father.getLeft().orElse(null));
		}
    }

    @CheckForNull
    public static ImmutablePair<Species, Creature> getValidSpeciesAndCreatureFromParents(ImmutablePair<Species, Creature> mother, ImmutablePair<Species, Creature> father, RandomSource random) {
        var motherSpecies = mother.getLeft();
        var motherCreature = mother.getRight();
        var fatherSpecies = father.getLeft();
        var fatherCreature = father.getRight();
  
        if (motherSpecies == null || motherCreature == null ||
            fatherSpecies == null || fatherCreature == null) {
            return null;
        }
        
        List<ImmutablePair<Species, Creature>> candidates = new ArrayList<>();

        // Inheritance rules
        if (motherSpecies == Species.HUMAN) {
            // Baby can be either species
            candidates.add(ImmutablePair.of(motherSpecies, motherCreature));
            candidates.add(ImmutablePair.of(fatherSpecies, fatherCreature));
        } else {
            // Baby is mother's species
            candidates.add(ImmutablePair.of(motherSpecies, motherCreature));
            // Unless father is HUMAN -> then HUMAN is also allowed
            if (fatherSpecies == Species.HUMAN) {
                candidates.add(ImmutablePair.of(fatherSpecies, fatherCreature));
            }
        }

        // Filter only valid combinations
        var validCandidates = candidates.stream()
            .filter(b -> VALID_COMBINATIONS.containsEntry(b.getLeft(), b.getRight()))
            .distinct()
            .toList();

        if (validCandidates.isEmpty()) {
            return null;
        }
               
        return validCandidates.get(random.nextInt(0, validCandidates.size()));
    }
    
    @Override
	public String toString() {
    	return "Baby [ gender = " + gender + 
				", species = " + typeOfSpecies + 
				", creature = " + typeOfCreature + 
				", motherId = " + Boolean.toString(motherId != null) + 
				", fatherId = " + Boolean.toString(fatherId.isPresent()) + " ]";
    }
}