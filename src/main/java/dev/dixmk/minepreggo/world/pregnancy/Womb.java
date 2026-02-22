package dev.dixmk.minepreggo.world.pregnancy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Stream;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;

public class Womb {
	
	private List<BabyData> babies = new ArrayList<>(PregnancySystemHelper.MAX_NUMBER_OF_BABIES);
	private static final String NBT_KEY = "DataWomb";
	
	private Womb() {}

    public Womb (@NonNull ImmutableTriple<UUID, Species, Creature> mother, @NonNull ImmutableTriple<Optional<UUID>, Species, Creature> father, RandomSource random, @Nonnegative int count) {		
    	if (count < 1) {
    		MinepreggoMod.LOGGER.warn("Attempted to create a Womb with count less than 1. Count: {}. Defaulting to 1.", count);
    	}
    	int tempCount = Mth.clamp(count, 1, Womb.getMaxNumOfBabies());	
    	for (int i = 0; i < tempCount; i++) {
    		addBaby(BabyData.create(mother, father, random));
		}
    }
    
	public Womb(UUID mother, ImmutableTriple<Optional<@Nullable UUID>, Species, Creature> father, RandomSource random, int count) {
    	if (count < 1) {
    		MinepreggoMod.LOGGER.warn("Attempted to create a Womb with count less than 1. Count: {}. Defaulting to 1.", count);
    	}
		int tempCount = Mth.clamp(count, 1, Womb.getMaxNumOfBabies());  	
    	for (int i = 0; i < tempCount; i++) {
    		addBaby(BabyData.create(mother, father, random));
		}
	}

	public boolean addBaby(@NonNull BabyData babyData) {
		return babies.add(babyData);
	}
	
	public boolean removeBaby() {	
		if (!babies.isEmpty()) {
			Collections.shuffle(babies);
			return babies.remove(0) != null;
		}
		return false;
	}

	public int getNumOfBabies() {	
		return babies.size();
	}
	
	public int calculateNumOfBabiesBySpecies(Species species) {
		return (int) babies.stream().filter(baby -> baby.typeOfSpecies == species).count();
	}
	
	public Stream<BabyData> stream() {
		return babies.stream();
	}
	
	public boolean isEmpty() {
		return babies.isEmpty();
	}
	
	public void forEach(Consumer<BabyData> consumer) {
		babies.forEach(consumer);
	}
	
	/**
	 * Duplicates a random baby in the womb.
	 * If the womb is already at maximum capacity, the duplicated baby is added to the discarded, extraBabies list indicate that the womb is overloaded.
	 * 
	 * @see #isWombOverloaded
	 * */
	public boolean duplicateRandomBaby(RandomSource random) {
		if (this.babies.isEmpty()) {
	    	MinepreggoMod.LOGGER.warn("Attempted to duplicate a baby in the womb, but there are no babies to duplicate.");
	        return false;
	    }	
	    int idx = random.nextInt(this.babies.size());
	    BabyData original = this.babies.get(idx);
	    return this.babies.add(BabyData.duplicate(original));
	}
	
	public static int getMaxNumOfBabies() {
		return PregnancySystemHelper.MAX_NUMBER_OF_BABIES;
	}
	
	/**
	 * Indicates whether the womb has exceeded its maximum capacity of babies.
	 * If true, in a some point in the pregnancy process, the mother should die due to overloading.
	 * */
	public boolean isWombOverloaded() {
		return this.babies.size() > PregnancySystemHelper.MAX_NUMBER_OF_BABIES;
	}
 
    public CompoundTag toNBT() {
    	CompoundTag wrapper = new CompoundTag();
    	CompoundTag data = new CompoundTag();
		ListTag listTag = new ListTag();
		babies.forEach(baby -> listTag.add(baby.toNBT()));	
		data.put("Babies", listTag);
		wrapper.put(NBT_KEY, data);
        return wrapper;
    }
	
    @Override
    public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Womb{");
		sb.append("numOfBabies=").append(getNumOfBabies()).append(", ");
		sb.append("babies=[");
		babies.forEach(baby -> sb.append(baby.toString()).append(", "));
		sb.append("]}");
		return sb.toString();
	}
   
	public static Womb empty() {
		return new Womb();
	}
	
	public static Womb of(@NonNull BabyData... babies) {
		var womb = empty();
		for (final var baby : babies) {
			womb.addBaby(baby);
		}	
		return womb;
	}

	@CheckForNull
	public static Womb fromNBT(CompoundTag nbt) {		
		if (nbt.contains(NBT_KEY, Tag.TAG_COMPOUND)) {
			CompoundTag dataTag = nbt.getCompound(NBT_KEY);
			ListTag list = dataTag.getList("Babies", Tag.TAG_COMPOUND);
			Womb womb = new Womb();	
			
	        for (var tag : list) {
	        	final var baby = BabyData.fromNBT((CompoundTag) tag);     	
	        	if (baby != null) {
	            	womb.addBaby(baby);
	        	}
	        	else {
	        		MinepreggoMod.LOGGER.warn("Failed to read BabyData from NBT tag in Womb: {}", tag);
	        	}
	        }	        
	        if (womb.isEmpty()) {
	        	MinepreggoMod.LOGGER.warn("Womb read from NBT is empty.");
	        }
	        
	        return womb;
		}		
		return null;
	}
}