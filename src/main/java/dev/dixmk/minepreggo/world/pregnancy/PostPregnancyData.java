package dev.dixmk.minepreggo.world.pregnancy;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;

public class PostPregnancyData {
	private static final String NBT_KEY = "NBTPostPregnancyData";
	
	private final PostPregnancy postPregnancy;
	private int postPregnancyTimer = 0;	
	private int postPartumlactation = 0;
	private int postPartumLactationTimer = 0;

	public PostPregnancyData(PostPregnancy postPregnancy) {
		this.postPregnancy = postPregnancy;
	}

	public PostPregnancy getPostPregnancy() {
		return postPregnancy;
	}

	public int getPostPregnancyTimer() {
		return postPregnancyTimer;
	}

	public void setPostPregnancyTimer(int postPregnancyTimer) {
		this.postPregnancyTimer = Math.max(0, postPregnancyTimer);
	}
	
	public int getPostPartumLactation() {
		return postPartumlactation;
	}

	public void setPostPartumlactation(int postPartumlactation) {
		this.postPartumlactation = Mth.clamp(postPartumlactation, 0, PregnancySystemHelper.MAX_MILKING_LEVEL);
	}

	public int getPostPartumLactationTimer() {
		return postPartumLactationTimer;
	}

	public void setPostPartumLactationTimer(int postPartumLactationTimer) {
		this.postPartumLactationTimer = Math.max(0, postPartumLactationTimer);
	}

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

    public void incrementPostPregnancyTimer() {
        this.postPregnancyTimer++;
    }
    public void resetPostPregnancyTimer() {
        this.postPregnancyTimer = 0;
    }

    public void incrementPostPartumLactation(@Nonnegative int amount) {
        setPostPartumlactation(this.postPartumlactation + Math.abs(amount));
    }
    
    public void decrementPostPartumlactation(@Nonnegative int amount) {
        setPostPartumlactation(this.postPartumlactation - Math.abs(amount));
    }
    
    public void resetPostPartumlactation() {
        this.postPartumlactation = 0;
    }

    public void incrementPostPartumLactationTimer() {
        this.postPartumLactationTimer++;
    }
    public void resetPostPartumLactationTimer() {
        this.postPartumLactationTimer = 0;
    }
}