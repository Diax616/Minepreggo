package dev.dixmk.minepreggo.world.entity.preggo;

import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.init.MinepreggoModEntityDataSerializers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;

public class TamablePreggoMobDataImpl<E extends PreggoMob> implements ITamablePreggoMobData {
	
    public static class DataAccessor<E extends PreggoMob> {
    	private final EntityDataAccessor<Integer> dataHungry;
    	private final EntityDataAccessor<Boolean> dataSavage;
    	private final EntityDataAccessor<Boolean> dataWaiting;	
    	private final EntityDataAccessor<Boolean> dataAngry;	
    	private final EntityDataAccessor<Optional<PreggoMobFace>> dataFace;
    	private final EntityDataAccessor<Optional<PreggoMobBody>> dataBody;
    	 	
    	public DataAccessor(Class<E> entityClass) {
            this.dataHungry = SynchedEntityData.defineId(entityClass, EntityDataSerializers.INT);
            this.dataSavage = SynchedEntityData.defineId(entityClass, EntityDataSerializers.BOOLEAN);
            this.dataWaiting = SynchedEntityData.defineId(entityClass, EntityDataSerializers.BOOLEAN);
            this.dataAngry = SynchedEntityData.defineId(entityClass, EntityDataSerializers.BOOLEAN);
            this.dataFace = SynchedEntityData.defineId(entityClass, MinepreggoModEntityDataSerializers.OPTIONAL_PREGGO_MOB_FACE);
            this.dataBody = SynchedEntityData.defineId(entityClass, MinepreggoModEntityDataSerializers.OPTIONAL_PREGGO_MOB_BODY);
    	}
    	
    	public void defineSynchedData(E preggomob) {
    		SynchedEntityData entityData = preggomob.getEntityData();
        	entityData.define(dataHungry, 12);		
        	entityData.define(dataSavage, true);
        	entityData.define(dataWaiting, false);
        	entityData.define(dataAngry, false);
        	entityData.define(dataFace, Optional.empty());
        	entityData.define(dataBody, Optional.empty());
        }
    }
	  
	private final DataAccessor<E> dataAccessor;
	private final E preggomob;
	
	private boolean panic = false;
	private int hungryTimer = 0;
	
	public TamablePreggoMobDataImpl(DataAccessor<E> dataAccessors, E preggomob) {	
		this.dataAccessor = dataAccessors;
		this.preggomob = preggomob;
	}	

	@Override
	public int getFullness() {
	    return this.preggomob.getEntityData().get(this.dataAccessor.dataHungry);
	}

	@Override
	public void setFullness(int hungry) {
		this.preggomob.getEntityData().set(this.dataAccessor.dataHungry, Mth.clamp(hungry, 0, ITamablePreggoMob.MAX_FULLNESS));
	}

	@Override
	public int getHungryTimer() {
	    return this.hungryTimer;
	}

	@Override
	public void setHungryTimer(int ticks) {
	    this.hungryTimer = ticks;
	}
	
	@Override
	public void incrementFullness(int amount) {
		this.setFullness(this.preggomob.getEntityData().get(this.dataAccessor.dataHungry) + amount);
	}
	
	@Override
	public void decrementFullness(int amount) {
		this.setFullness(this.preggomob.getEntityData().get(this.dataAccessor.dataHungry) - amount);		
	}

	@Override
	public void incrementHungryTimer() {
		++this.hungryTimer;
	}

	@Override
	public void resetHungryTimer() {
		this.hungryTimer = 0;	
	}

	@Override
	public boolean isWaiting() {
	    return this.preggomob.getEntityData().get(dataAccessor.dataWaiting);
	}

	@Override
	public void setWaiting(boolean waiting) {
		this.preggomob.getEntityData().set(dataAccessor.dataWaiting, waiting);
	}

	@Override
	public boolean isSavage() {
	    return this.preggomob.getEntityData().get(dataAccessor.dataSavage);
	}

	@Override
	public void setSavage(boolean savage) {
		this.preggomob.getEntityData().set(dataAccessor.dataSavage, savage);
	}
	
	@Override
	public boolean isAngry() {
		return this.preggomob.getEntityData().get(dataAccessor.dataAngry);
	}

	@Override
	public void setAngry(boolean angry) {
		this.preggomob.getEntityData().set(dataAccessor.dataAngry, angry);
	}

	@Override
	public boolean isPanic() {
		return panic;
	}

	@Override
	public void setPanic(boolean panic) {
		this.panic = panic;		
	}
	
	@Override
	public boolean canBePanicking() {
		return this.preggomob.getEntityData().get(dataAccessor.dataWaiting) && !this.panic;
	}

	@Override
	public @Nullable PreggoMobFace getFaceState() {
		return this.preggomob.getEntityData().get(dataAccessor.dataFace).orElse(null);
	}

	@Override
	public void setFaceState(@Nullable PreggoMobFace state) {
		this.preggomob.getEntityData().set(dataAccessor.dataFace, Optional.ofNullable(state));	
	}

	@Override
	public void cleanFaceState() {
		this.preggomob.getEntityData().set(dataAccessor.dataFace, Optional.empty());	
	}

	@Override
	public @Nullable PreggoMobBody getBodyState() {
		return this.preggomob.getEntityData().get(dataAccessor.dataBody).orElse(null);
	}

	@Override
	public void setBodyState(@Nullable PreggoMobBody state) {
		this.preggomob.getEntityData().set(dataAccessor.dataBody, Optional.ofNullable(state));	
	}

	@Override
	public void cleanBodyState() {
		this.preggomob.getEntityData().set(dataAccessor.dataBody, Optional.empty());	
	}

	@Override
	public CompoundTag serializeNBT() {
		CompoundTag compound = new CompoundTag();
		compound.putInt("DataHungry", this.preggomob.getEntityData().get(dataAccessor.dataHungry));
		compound.putInt("DataHungryTimer", hungryTimer);
		compound.putBoolean("DataSavage", this.preggomob.getEntityData().get(dataAccessor.dataSavage));
		compound.putBoolean("DataWaiting", this.preggomob.getEntityData().get(dataAccessor.dataWaiting));
		compound.putBoolean("DataAngry", this.preggomob.getEntityData().get(dataAccessor.dataAngry));
		compound.putBoolean("DataPanic", this.panic);	
		final var face = getFaceState();
		if (face != null) {
			compound.putString(PreggoMobFace.NBT_KEY, face.name());
		}
		final var body = getBodyState();
		if (body != null) {
			compound.putString(PreggoMobBody.NBT_KEY, body.name());
		}
		return compound;
	}

	@Override
	public void deserializeNBT(CompoundTag compound) {
		this.preggomob.getEntityData().set(dataAccessor.dataHungry, compound.getInt("DataHungry"));
		this.hungryTimer = compound.getInt("DataHungryTimer");		
		this.preggomob.getEntityData().set(dataAccessor.dataSavage, compound.getBoolean("DataSavage"));		
		this.preggomob.getEntityData().set(dataAccessor.dataWaiting, compound.getBoolean("DataWaiting"));		
		this.preggomob.getEntityData().set(dataAccessor.dataAngry, compound.getBoolean("DataAngry"));	
		this.panic = compound.getBoolean("DataPanic");
		
		if (compound.contains(PreggoMobFace.NBT_KEY, Tag.TAG_STRING)) {
			setFaceState(PreggoMobFace.valueOf(compound.getString(PreggoMobFace.NBT_KEY)));
		}
		if (compound.contains(PreggoMobBody.NBT_KEY, Tag.TAG_STRING)) {
			setBodyState(PreggoMobBody.valueOf(compound.getString(PreggoMobBody.NBT_KEY)));
		}
	}
}
