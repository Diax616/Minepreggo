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

	protected Optional<PostPregnancyData> postPregnancyData = Optional.empty();
	protected Optional<PrePregnancyData> prePregnancyData = Optional.empty(); 
	
	// ???????????? TODO: Remove or rework this shit
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
		if (!this.pregnant && postPregnancyData.isEmpty()) {
			this.pregnant = true;
			this.prePregnancyData = Optional.of(new PrePregnancyData(fertilizedEggs, father.getMiddle(), father.getRight(), father.getLeft().orElse(null)));
			return true;
		}
		return false;
	}

	@Override
	public @Nullable PostPregnancy getPostPregnancyPhase() {
		return postPregnancyData.map(PostPregnancyData::getPostPregnancy).orElse(null);		
	}

	@Override
	public boolean tryActivatePostPregnancyPhase(@NonNull PostPregnancy postPregnancy) {
		if (pregnant) {
			this.postPregnancyData = Optional.of(new PostPregnancyData(postPregnancy));
			this.pregnant = false;
			this.prePregnancyData = Optional.empty();
			this.pregnancyInitializerTimer = 0;
			this.postPregnancyTimer = 0;
			return true;
		}
		return false;
	}

	@Override
	public boolean tryRemovePostPregnancyPhase() {
		if (this.postPregnancyData.isPresent()) {
			this.postPregnancyData = Optional.empty();
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
	public Optional<PostPregnancyData> getPostPregnancyData() {
		return this.postPregnancyData;
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
		postPregnancyData.ifPresent(post -> nbt.put("DataPostPregnancyData", post.toNBT()));
		prePregnancyData.ifPresent(pre -> nbt.put("DataPrePregnancyData", pre.toNBT()));
		return nbt;
	}
	
	@Override
	public void deserializeNBT(CompoundTag nbt) {
		super.deserializeNBT(nbt);
		pregnant = nbt.getBoolean("DataPregnant");		
		
	    if (nbt.contains("DataPostPregnancyData", Tag.TAG_COMPOUND)) {
	    	postPregnancyData = Optional.ofNullable(PostPregnancyData.fromNBT(nbt.getCompound("DataPostPregnancyData")));
		    if (postPregnancyData.isEmpty()) {
		    	throw new IllegalStateException("Failed to load PostPregnancyData from NBT");
		    }
	    }

	    if (nbt.contains("DataPrePregnancyData", Tag.TAG_COMPOUND)) {
		    prePregnancyData = Optional.ofNullable(PrePregnancyData.fromNBT(nbt.getCompound("DataPrePregnancyData")));
		    if (prePregnancyData.isEmpty()) {
		    	throw new IllegalStateException("Failed to load PrePregnancyData from NBT");
		    }
	    }
	}

	@Override
	public int getPostPartumLactation() {
		return this.getPostPregnancyData().map(PostPregnancyData::getPostPartumLactation).orElse(0);
	}

	@Override
	public void setPostPartumLactation(int amount) {
		this.postPregnancyData.ifPresent(post -> post.setPostPartumlactation(amount));
	}
}
