package dev.dixmk.minepreggo.world.entity.preggo;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;

import java.util.Optional;
import java.util.OptionalInt;

import javax.annotation.Nonnegative;

import dev.dixmk.minepreggo.init.MinepreggoModEntityDataSerializers;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancy;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancyData;

public class SyncedPostPregnancyData<E extends PreggoMob> extends PostPregnancyData {

    public static class DataAccessor<E extends PreggoMob> {
        private final EntityDataAccessor<Optional<PostPregnancy>> dataPostPregnancy;
        private final EntityDataAccessor<OptionalInt> dataLactation;
        
        public DataAccessor(Class<E> entityClass) {
            this.dataPostPregnancy = SynchedEntityData.defineId(entityClass, MinepreggoModEntityDataSerializers.OPTIONAL_POST_PREGNANCY);
            this.dataLactation = SynchedEntityData.defineId(entityClass, EntityDataSerializers.OPTIONAL_UNSIGNED_INT);
        }   
        
        public void defineSynchedData(E preggoMob) {
			SynchedEntityData entityData = preggoMob.getEntityData();
			entityData.define(dataPostPregnancy, Optional.empty());
			entityData.define(dataLactation, OptionalInt.empty());
		}
        
        public EntityDataAccessor<Optional<PostPregnancy>> getDataPostPregnancy() {
			return dataPostPregnancy;
		}

		public EntityDataAccessor<OptionalInt> getDataLactation() {
			return dataLactation;
		}
    }
    
    private final DataAccessor<E> dataAccessor;
    private final E preggoMob;
    
    public SyncedPostPregnancyData(PostPregnancy postPregnancy, SyncedPostPregnancyData.DataAccessor<E> dataAccesor, E preggoMob) {
    	super(postPregnancy);
    	this.dataAccessor = dataAccesor;
        this.preggoMob = preggoMob;
        this.preggoMob.getEntityData().set(this.dataAccessor.dataPostPregnancy, Optional.of(postPregnancy));
    }

    public SyncedPostPregnancyData(PostPregnancyData postPregnancyData, SyncedPostPregnancyData.DataAccessor<E> dataAccesor, E preggoMob) {
    	super(postPregnancyData);
		this.dataAccessor = dataAccesor;
		this.preggoMob = preggoMob;
		this.preggoMob.getEntityData().set(this.dataAccessor.dataPostPregnancy, Optional.of(super.getPostPregnancy()));
		this.preggoMob.getEntityData().set(this.dataAccessor.dataLactation, OptionalInt.of(super.getPostPartumLactation()));
	}
    
	@Override
	public int getPostPartumLactation() {
		return this.preggoMob.getEntityData().get(this.dataAccessor.dataLactation).orElse(super.getPostPartumLactation());
	}
	
	@Override
	public PostPregnancy getPostPregnancy() {
		return this.preggoMob.getEntityData().get(this.dataAccessor.dataPostPregnancy).orElse(super.getPostPregnancy());
	}
    
    @Override
    public void setPostPartumLactation(int lactationLevel) {
    	super.setPostPartumLactation(lactationLevel);
    	this.preggoMob.getEntityData().set(this.dataAccessor.dataLactation, OptionalInt.of(super.getPostPartumLactation()));
	}
    
    @Override
    public void incrementPostPartumLactation(@Nonnegative int amount) {
    	super.incrementPostPartumLactation(amount);
    	this.preggoMob.getEntityData().set(this.dataAccessor.dataLactation, OptionalInt.of(super.getPostPartumLactation()));
    }
    
    @Override
    public void incrementPostPartumLactation() {
    	super.incrementPostPartumLactation();
		this.preggoMob.getEntityData().set(this.dataAccessor.dataLactation, OptionalInt.of(super.getPostPartumLactation()));
	}
    
    @Override
    public void decrementPostPartumLactation(@Nonnegative int amount) {
    	super.decrementPostPartumLactation(amount);
    	this.preggoMob.getEntityData().set(this.dataAccessor.dataLactation, OptionalInt.of(super.getPostPartumLactation()));
    }
    
    @Override
    public void decrementPostPartumLactation() {
    	super.decrementPostPartumLactation();
    	this.preggoMob.getEntityData().set(this.dataAccessor.dataLactation, OptionalInt.of(super.getPostPartumLactation()));
    }
    
    public void resetSyncedData() {
		this.preggoMob.getEntityData().set(this.dataAccessor.dataPostPregnancy, Optional.empty());
		this.preggoMob.getEntityData().set(this.dataAccessor.dataLactation, OptionalInt.empty());
    }
}