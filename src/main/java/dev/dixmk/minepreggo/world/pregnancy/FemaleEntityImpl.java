package dev.dixmk.minepreggo.world.pregnancy;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nonnegative;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
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
		return this.postPregnancy.orElse(null);
	}

	@Override
	public boolean tryActivatePostPregnancyPhase(@NonNull PostPregnancy postPregnancy) {
		if (pregnant) {
			this.postPregnancy = Optional.of(postPregnancy);
			this.pregnant = false;
			this.prePregnancyData = Optional.empty();
			return true;
		}
		return false;
	}

	@Override
	public boolean tryRemovePostPregnancyPhase() {
		if (getPostPregnancyPhase() != null) {
			this.postPregnancy = Optional.empty();
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
	public CompoundTag serializeNBT() {
		CompoundTag nbt = super.serializeNBT();
		nbt.putInt("DataPregnancyInitializerTimer", pregnancyInitializerTimer);
		nbt.putInt("DataPostPregnancyTimer", postPregnancyTimer);
		nbt.putBoolean("DataPregnant", pregnant);
		postPregnancy.ifPresent(post -> nbt.putString(PostPregnancy.NBT_KEY, post.name()));
		prePregnancyData.ifPresent(pre -> nbt.put("DataPrePregnancyData", pre.toNBT()));
		return nbt;
	}
	
	@Override
	public void deserializeNBT(CompoundTag nbt) {
		super.deserializeNBT(nbt);
		pregnancyInitializerTimer = nbt.getInt("DataPregnancyInitializerTimer");
		pregnant = nbt.getBoolean("DataPregnant");
		postPregnancyTimer = nbt.getInt("DataPostPregnancyTimer");
		
	    if (nbt.contains(PostPregnancy.NBT_KEY, Tag.TAG_STRING)) {
	        postPregnancy = Optional.of(PostPregnancy.valueOf(nbt.getString(PostPregnancy.NBT_KEY)));
	    }

	    if (nbt.contains("DataPrePregnancyData", Tag.TAG_COMPOUND)) {
		    prePregnancyData = Optional.ofNullable(PrePregnancyData.fromNBT(nbt.getCompound("DataPrePregnancyData")));
		    if (prePregnancyData.isEmpty()) {
		    	MinepreggoMod.LOGGER.error("PrePregnancyData is not present");
		    }
	    }
	}
}
