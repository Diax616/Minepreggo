package dev.dixmk.minepreggo.world.pregnancy;

import java.util.Collections;
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
import dev.dixmk.minepreggo.common.utils.FixedSizeList;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.RandomSource;

public class Womb {
	FixedSizeList<BabyData> babies = new FixedSizeList<>(IBreedable.MAX_NUMBER_OF_BABIES);
	
	private static final String NBT_KEY = "DataWomb";
	
	private Womb() {}

    public Womb (@NonNull ImmutableTriple<UUID, Species, Creature> mother, @NonNull ImmutableTriple<Optional<UUID>, Species, Creature> father, RandomSource random, @Nonnegative int count) {		
    	int tempCount = Math.min(count, Womb.getMaxNumOfBabies());   	
    	for (int i = 0; i < tempCount; i++) {
    		addBaby(BabyData.create(mother, father, random));
		}
    }
    
	public Womb(UUID mother, ImmutableTriple<Optional<@Nullable UUID>, Species, Creature> father, RandomSource random, int count) {
    	int tempCount = Math.min(count, Womb.getMaxNumOfBabies());   	
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
			babies.remove(0);
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
	
	public boolean duplicateRandomBaby(RandomSource random) {
	    if (this.babies.size() >= IBreedable.MAX_NUMBER_OF_BABIES || this.babies.isEmpty()) {
	        return false;
	    }

	    // Select a random baby to duplicate
	    int idx = random.nextInt(0, this.babies.size());
	    BabyData original = this.babies.get(idx);
	    // Use BabyData.duplicate to create a copy
	    BabyData duplicate = BabyData.duplicate(original);
	    return this.babies.add(duplicate);
	}
	
	public static int getMaxNumOfBabies() {
		return IBreedable.MAX_NUMBER_OF_BABIES;
	}
 
    public CompoundTag toNBT() {
    	CompoundTag wrapper = new CompoundTag();
		ListTag listTag = new ListTag();
		babies.forEach(baby -> listTag.add(baby.toNBT()));	
		wrapper.put(NBT_KEY, listTag);
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
		if (nbt.contains(NBT_KEY, Tag.TAG_LIST)) {
			ListTag list = nbt.getList(NBT_KEY, Tag.TAG_COMPOUND);
			Womb womb = new Womb();			
	        for (var tag : list) {
	        	final var baby = BabyData.fromNBT((CompoundTag) tag);     	
	        	if (baby != null) {
	            	womb.addBaby(baby);
	        	}
	        	else {
	        		MinepreggoMod.LOGGER.error("BabyData was null from nbt");
	        	}
	        }
	        return womb;
		}		
		return null;
	}
}