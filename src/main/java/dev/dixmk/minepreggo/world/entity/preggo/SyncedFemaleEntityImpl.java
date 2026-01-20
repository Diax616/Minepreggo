package dev.dixmk.minepreggo.world.entity.preggo;

import java.util.Optional;
import java.util.OptionalInt;

import javax.annotation.CheckForNull;

import dev.dixmk.minepreggo.world.pregnancy.FemaleEntityImpl;
import dev.dixmk.minepreggo.world.pregnancy.IPostPregnancyData;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancy;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancyData;
import net.minecraft.nbt.CompoundTag;

public class SyncedFemaleEntityImpl<E extends PreggoMob> extends FemaleEntityImpl implements ISyncedFemaleEntity<E> {
	
	private final SyncedPostPregnancyData.DataAccessor<E> dataAccessor;
	private final E preggoMob;
	
	public SyncedFemaleEntityImpl(SyncedPostPregnancyData.DataAccessor<E> dataAccesor, E preggoMob) {
		super();
		this.dataAccessor = dataAccesor;
		this.preggoMob = preggoMob;
	}
	
    @Override
    protected IPostPregnancyData createPostPregnancyData(PostPregnancy postPregnancy) {
        return new SyncedPostPregnancyData<>(postPregnancy, this.dataAccessor, this.preggoMob);
    }
    
    @Override
    @CheckForNull
    protected IPostPregnancyData createPostPregnancyData(CompoundTag nbt, String key) {
    	PostPregnancyData data = PostPregnancyData.fromNBT(nbt.getCompound(key));  
    	if (data == null) {
    		return null;
    	}
    	return new SyncedPostPregnancyData<>(data, this.dataAccessor, this.preggoMob);
	}
    
    @Override
    public boolean tryRemovePostPregnancyPhase() {
    	boolean removed = super.tryRemovePostPregnancyPhase();    	
    	if (removed) {
    		this.preggoMob.getEntityData().set(this.dataAccessor.getDataPostPregnancy(), Optional.empty());
    		this.preggoMob.getEntityData().set(this.dataAccessor.getDataLactation(), OptionalInt.empty());
    	}
        return removed;
    }
    
    @Override
    public Optional<SyncedPostPregnancyData<E>> getSyncedPostPregnancyData() {
        Optional<IPostPregnancyData> holder = super.getPostPregnancyData();
        if (holder.isPresent() && holder.get() instanceof SyncedPostPregnancyData<?>) {
            @SuppressWarnings("unchecked")
            SyncedPostPregnancyData<E> casted = (SyncedPostPregnancyData<E>) holder.get();
            return Optional.of(casted);
        }
        return Optional.empty();
    }
    
	@Override
	public void deserializeNBT(CompoundTag nbt) {
		super.deserializeNBT(nbt);
		getPostPregnancyData().ifPresent(dataHolder -> {
			this.preggoMob.getEntityData().set(this.dataAccessor.getDataPostPregnancy(), Optional.of(dataHolder.getPostPregnancy()));	
			this.preggoMob.getEntityData().set(this.dataAccessor.getDataLactation(), OptionalInt.of(dataHolder.getPostPartumLactation()));
		});	
	}
}