package dev.dixmk.minepreggo.world.pregnancy;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

public class FemaleEntityImpl extends AbstractBreedableEntity implements IFemaleEntity {

	public FemaleEntityImpl() {
		super(Gender.FEMALE);
	}

	protected int pregnancyInitializerTimer = 0;
	protected int discomfortCooldown = 0;
	protected boolean pregnant = false;

	protected Optional<IPostPregnancyData> postPregnancyData = Optional.empty();
	protected Optional<PrePregnancyData> prePregnancyData = Optional.empty(); 
	
    protected IPostPregnancyData createPostPregnancyData(PostPregnancy postPregnancy) {
        return new PostPregnancyData(postPregnancy);
    }
	
    @CheckForNull
    protected IPostPregnancyData createPostPregnancyData(CompoundTag nbt, String key) {
	    return PostPregnancyData.fromNBT(nbt.getCompound(key));
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
    public boolean tryActivatePostPregnancyPhase(@NonNull PostPregnancy postPregnancy) {
        if (pregnant) {
            this.postPregnancyData = Optional.of(createPostPregnancyData(postPregnancy));
            this.pregnant = false;
            this.prePregnancyData = Optional.empty();
            this.pregnancyInitializerTimer = 0;
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
	public boolean tryCancelPregnancy() {
		if (this.isPregnant()) {
			this.pregnant = false;
			return true;
		}
		return false;
	}
	
	@Override
	public Optional<PrePregnancyData> getPrePregnancyData() {
		return this.prePregnancyData;
	}

    public Optional<IPostPregnancyData> getPostPregnancyData() {
        return this.postPregnancyData;
    }
	
	public int getDiscomfortCooldown() {
		return discomfortCooldown;
	}
	
	public void incrementDiscomfortCooldown() {
		++this.discomfortCooldown;
	}
	
	public void resetDiscomfortCooldown() {
		this.discomfortCooldown = 0;
	}
	
	@Override
	public CompoundTag serializeNBT() {
		CompoundTag nbt = super.serializeNBT();
		nbt.putInt("DataPregnancyInitializerTimer", pregnancyInitializerTimer);
		nbt.putBoolean("DataPregnant", pregnant);
		postPregnancyData.ifPresent(post -> nbt.put("DataPostPregnancyData", post.toNBT()));
		prePregnancyData.ifPresent(pre -> nbt.put("DataPrePregnancyData", pre.toNBT()));
		return nbt;
	}
	
	@Override
	public void deserializeNBT(CompoundTag nbt) throws IllegalStateException {
		super.deserializeNBT(nbt);
		pregnant = nbt.getBoolean("DataPregnant");	
		pregnancyInitializerTimer = nbt.getInt("DataPregnancyInitializerTimer");

	    if (nbt.contains("DataPrePregnancyData", Tag.TAG_COMPOUND)) {
		    prePregnancyData = Optional.ofNullable(PrePregnancyData.fromNBT(nbt.getCompound("DataPrePregnancyData")));
		    if (prePregnancyData.isEmpty()) {
		    	throw new IllegalStateException("Failed to load PrePregnancyData from NBT");
		    }
	    }
	    
	    if (nbt.contains("DataPostPregnancyData", Tag.TAG_COMPOUND)) {
			postPregnancyData = Optional.ofNullable(createPostPregnancyData(nbt, "DataPostPregnancyData"));		    	
			if (postPregnancyData.isEmpty()) {
	    		throw new IllegalStateException("Failed to load PostPregnancyData from NBT");
	    	}
	    }
	}
}
