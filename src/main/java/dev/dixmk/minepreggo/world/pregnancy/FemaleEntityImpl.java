package dev.dixmk.minepreggo.world.pregnancy;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nonnegative;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;


public class FemaleEntityImpl extends AbstractBreedableEntity implements IFemaleEntity {

	public FemaleEntityImpl() {
		super(Gender.FEMALE);
	}

	protected int pregnancyInitializerTimer = 0;
	protected int postPregnancyTimer = 0;
	protected boolean pregnant = false;
	protected Optional<PostPregnancy> postPregnancy = Optional.empty();
	protected Optional<PrePregnancyData> prePregnancyData = Optional.empty(); 
	
	
	@Override
	public boolean canGetPregnant() {
		return this.gender == Gender.FEMALE;
	}

	@Override
	public boolean isPregnant() {
		return this.pregnant;
	}
	
	@Override
	public int getPregnancyInitializerTimer() {
		return this.pregnancyInitializerTimer;
	}

	@Override
	public void setPregnancyInitializerTimer(int ticks) {
		this.pregnancyInitializerTimer = Math.max(0, ticks);
		
	}

	@Override
	public void incrementPregnancyInitializerTimer() {
		++this.pregnancyInitializerTimer;
		
	}
	
	@Override
	public boolean tryImpregnate(@Nonnegative int fertilizedEggs, @NonNull ImmutableTriple<Optional<UUID>, Species, Creature> father) {
		if (!this.pregnant) {
			this.pregnant = true;
			this.prePregnancyData = Optional.of(new PrePregnancyData(fertilizedEggs, father.getMiddle(), father.getRight(), father.getLeft().orElse(null)));
			return true;
		}
		return false;
	}

	@Override
	public @Nullable PostPregnancy getPostPregnancyPhase() {
		if (this.postPregnancy.isPresent()) {
			return this.postPregnancy.get();
		}
		return null;
	}

	@Override
	public boolean tryActivatePostPregnancyPhase(@NonNull PostPregnancy postPregnancy) {
		if (pregnant) {
			this.postPregnancy = Optional.of(postPregnancy);
			this.pregnant = false;
			return true;
		}
		return false;
	}

	@Override
	public int getPostPregnancyTimer() {
		return postPregnancyTimer;
	}

	@Override
	public void setPostPregnancyTimer(int ticks) {
		this.postPregnancyTimer = Math.max(0, ticks);	
	}

	@Override
	public void incrementPostPregnancyTimer() {
		++this.postPregnancyTimer;	
	}
	
	@Override
	public boolean tryCancelPregnancy() {
		if (this.isPregnant()) {
			this.pregnant = false;
			return true;
		}
		return false;
	}

	@Override
	public @Nullable UUID getFather() {
		if (this.prePregnancyData.isPresent()) {
			return this.prePregnancyData.get().fatherId();
		}
		return null;
	}
	
	@Override
	public Optional<PrePregnancyData> getPrePregnancyData() {
		return this.prePregnancyData;
	}

	@Override
	public boolean hasNaturalPregnancy() {
		return this.getFather() != null;
	}
	
	@Override
	public void serializeNBT(@NonNull Tag tag) {
		super.serializeNBT(tag);
		CompoundTag nbt = (CompoundTag) tag;
		nbt.putInt("DataPregnancyInitializerTimer", pregnancyInitializerTimer);
		nbt.putInt("DataPostPregnancyTimer", postPregnancyTimer);
		nbt.putBoolean("DataPregnant", pregnant);
		postPregnancy.ifPresent(post -> nbt.putString(PostPregnancy.NBT_KEY, post.name()));
		prePregnancyData.ifPresent(pre -> pre.serializeNBT(tag, PrePregnancyData.NBT_KEY));
	}
	
	@Override
	public void deserializeNBT(@NonNull Tag tag) {
		super.deserializeNBT(tag);
		CompoundTag nbt = (CompoundTag) tag;
		pregnancyInitializerTimer = nbt.getInt("DataPregnancyInitializerTimer");
		pregnant = nbt.getBoolean("DataPregnant");
		postPregnancyTimer = nbt.getInt("DataPostPregnancyTimer");
		
	    if (nbt.contains(PostPregnancy.NBT_KEY, Tag.TAG_STRING)) {
	        String name = nbt.getString(PostPregnancy.NBT_KEY);
	        postPregnancy = Optional.of(PostPregnancy.valueOf(name));
	    }
	    
	    prePregnancyData = Optional.ofNullable(PrePregnancyData.deserializeNBT(tag, PrePregnancyData.NBT_KEY));
	}
}
