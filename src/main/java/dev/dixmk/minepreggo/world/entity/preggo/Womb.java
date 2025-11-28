package dev.dixmk.minepreggo.world.entity.preggo;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Stream;

import javax.annotation.Nonnegative;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.common.utils.FixedSizeList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.RandomSource;

public class Womb {
	FixedSizeList<BabyData> babies = new FixedSizeList<>(IBreedable.MAX_NUMBER_OF_BABIES);
	
	private Womb() {}
	
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
	
	public Stream<BabyData> stream() {
		return babies.stream();
	}
	
	public boolean isEmpty() {
		return babies.isEmpty();
	}
	
	public void forEach(Consumer<BabyData> consumer) {
		babies.forEach(consumer);
	}
	
	
	public static int getMaxNumOfBabies() {
		return IBreedable.MAX_NUMBER_OF_BABIES;
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
	
    public static @NonNull Womb create(@NonNull ImmutableTriple<UUID, Species, Creature> mother, @NonNull ImmutableTriple<Optional<UUID>, Species, Creature> father, RandomSource random, @Nonnegative int count) {		
    	Womb womb = Womb.empty();  	
    	int tempCount = Math.min(count, Womb.getMaxNumOfBabies());   	
    	for (int i = 0; i < tempCount; i++) {
    		womb.addBaby(BabyData.create(mother, father, random));
		}
		return womb;
    }
    
    public static @NonNull Womb create(@NonNull ImmutableTriple<UUID, Species, Creature> mother, RandomSource random, @Nonnegative int count) {		
    	return create(mother, ImmutableTriple.of(Optional.empty(), mother.getMiddle(), mother.getRight()) , random, count);
    }
    
	public static ListTag serializeNBT(Womb womb) {
		ListTag listTag = new ListTag();
		womb.forEach(baby -> {
			CompoundTag tag = new CompoundTag();
			baby.serializeNBT(tag);
			listTag.add(tag);
		});	
        return listTag;
	}
	
	public static void deserializeNBT(ListTag list, Womb womb) {
        for (var tag : list) {
        	womb.addBaby(BabyData.deserializeNBT(tag));
        }
	}
}
