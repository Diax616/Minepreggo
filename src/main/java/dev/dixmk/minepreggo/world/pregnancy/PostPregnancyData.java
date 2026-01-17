package dev.dixmk.minepreggo.world.pregnancy;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;

public class PostPregnancyData implements IPostPregnancyDataHolder {
	private static final String NBT_KEY = "NBTPostPregnancyData";
	
	private final PostPregnancy postPregnancy;
	private int postPregnancyTimer = 0;	
	private int postPartumlactation = 0;
	private int postPartumLactationTimer = 0;

	public PostPregnancyData(PostPregnancy postPregnancy) {
		this.postPregnancy = postPregnancy;
	}

	public PostPregnancyData(PostPregnancyData other) {
		this.postPregnancy = other.postPregnancy;
		this.postPregnancyTimer = other.postPregnancyTimer;
		this.postPartumlactation = other.postPartumlactation;
		this.postPartumLactationTimer = other.postPartumLactationTimer;
	}
	
	@Override
	public PostPregnancy getPostPregnancy() {
		return postPregnancy;
	}

	@Override
	public int getPostPregnancyTimer() {
		return postPregnancyTimer;
	}

	@Override
	public void setPostPregnancyTimer(int postPregnancyTimer) {
		this.postPregnancyTimer = Math.max(0, postPregnancyTimer);
	}
	
	@Override
	public int getPostPartumLactation() {
		return postPartumlactation;
	}

	@Override
	public void setPostPartumLactation(int postPartumlactation) {
		this.postPartumlactation = Mth.clamp(postPartumlactation, 0, PregnancySystemHelper.MAX_MILKING_LEVEL);
	}
	
	@Override
	public void incrementPostPartumLactation() {
		incrementPostPartumLactation(1);
	}
	
	@Override
	public void decrementPostPartumLactation() {
		decrementPostPartumLactation(1);
	}

	@Override
    public void incrementPostPartumLactation(@Nonnegative int amount) {
        setPostPartumLactation(this.postPartumlactation + Math.abs(amount));
    }
    
	@Override
    public void decrementPostPartumLactation(@Nonnegative int amount) {
        setPostPartumLactation(this.postPartumlactation - Math.abs(amount));
    }
	
	@Override
	public int getPostPartumLactationTimer() {
		return postPartumLactationTimer;
	}

	@Override
	public void setPostPartumLactationTimer(int postPartumLactationTimer) {
		this.postPartumLactationTimer = Math.max(0, postPartumLactationTimer);
	}

	@Override
    public CompoundTag toNBT() {
		CompoundTag wrapper = new CompoundTag();			
		CompoundTag nbt = new CompoundTag();
		nbt.putString(PostPregnancy.NBT_KEY, postPregnancy.name());
		nbt.putInt("DataPostPregnancyTimer", postPregnancyTimer);
		nbt.putInt("DataPostPartumlactation", postPartumlactation);
		nbt.putInt("DataPostPartumLactationTimer", postPartumLactationTimer);
		wrapper.put(NBT_KEY, nbt);
		return wrapper;
	}
	
    @CheckForNull
	public static PostPregnancyData fromNBT(CompoundTag nbt) {
		if (nbt.contains(NBT_KEY)) {
			CompoundTag data = nbt.getCompound(NBT_KEY);
			var pos = new PostPregnancyData(PostPregnancy.valueOf(data.getString(PostPregnancy.NBT_KEY)));
			pos.postPregnancyTimer = data.getInt("DataPostPregnancyTimer");
			pos.postPartumlactation = data.getInt("DataPostPartumlactation");
			pos.postPartumLactationTimer = data.getInt("DataPostPartumLactationTimer");
			return pos;		
		}
		return null;
	}

    @Override
    public void incrementPostPregnancyTimer() {
        this.postPregnancyTimer++;
    }
    
    @Override
    public void resetPostPregnancyTimer() {
        this.postPregnancyTimer = 0;
    }

    @Override
    public void incrementPostPartumLactationTimer() {
        this.postPartumLactationTimer++;
    }
    
    @Override
    public void resetPostPartumLactationTimer() {
        this.postPartumLactationTimer = 0;
    }
}