package dev.dixmk.minepreggo.network.capability;

import java.util.Optional;
import java.util.UUID;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.world.entity.preggo.PostPregnancy;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;


public class FemaleEntityImpl extends AbstractBreedableEntity implements IFemaleEntity {

	public FemaleEntityImpl() {
		super(Gender.FEMALE);
	}

	protected int pregnancyInitializerTimer = 0;
	protected int postPregnancyTimer = 0;
	protected boolean pregnant = false;
	Optional<PostPregnancy> postPregnancy = Optional.empty();
	Optional<UUID> father = Optional.empty();
	
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
	public void incrementFertilityRateTimer() {
		++this.pregnancyInitializerTimer;
	}
	
	@Override
	public boolean tryImpregnate(@Nullable UUID father) {
		if (!this.pregnant) {
			this.pregnant = true;
			this.father = Optional.ofNullable(father);
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
		if (this.father.isPresent()) {
			return this.father.get();
		}
		return null;
	}

	@Override
	public boolean hasNaturalPregnancy() {
		return this.father.isPresent();
	}
	
	@Override
	public void serializeNBT(@NonNull Tag tag) {
		super.serializeNBT(tag);
		CompoundTag nbt = (CompoundTag) tag;
		nbt.putInt("DataPregnancyInitializerTimer", pregnancyInitializerTimer);
		nbt.putInt("DataPostPregnancyTimer", postPregnancyTimer);
		nbt.putBoolean("DataPregnant", pregnant);
		
		if (father.isPresent()) {
			nbt.putUUID("DataFather", father.get());
		}
	
		postPregnancy.ifPresent(post -> nbt.putString(PostPregnancy.NBT_KEY, post.name()));
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
	    if (nbt.contains("DataFather")) {
	    	father = Optional.of(nbt.getUUID("DataFather"));
	    }
	}
}
