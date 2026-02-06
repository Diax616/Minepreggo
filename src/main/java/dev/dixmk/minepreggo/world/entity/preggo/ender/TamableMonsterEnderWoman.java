package dev.dixmk.minepreggo.world.entity.preggo.ender;

import java.util.Optional;
import java.util.OptionalInt;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.IPostPregnancyEntity;
import dev.dixmk.minepreggo.world.entity.preggo.ISyncedFemaleEntity;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMobSystem;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem;
import dev.dixmk.minepreggo.world.entity.preggo.SyncedFemaleEntityImpl;
import dev.dixmk.minepreggo.world.entity.preggo.SyncedPostPregnancyData;
import dev.dixmk.minepreggo.world.pregnancy.IFemaleEntity;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancy;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableMonsterEnderWoman extends AbstractTamableMonsterEnderWoman implements IPostPregnancyEntity {

	private static final SyncedPostPregnancyData.DataAccessor<TamableMonsterEnderWoman> DATA_HOLDER = new SyncedPostPregnancyData.DataAccessor<>(TamableMonsterEnderWoman.class);
	
	public TamableMonsterEnderWoman(EntityType<TamableMonsterEnderWoman> p_32485_, Level p_32486_) {
		super(p_32485_, p_32486_);
	}

	public TamableMonsterEnderWoman(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN.get(), world);
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		DATA_HOLDER.defineSynchedData(this);
	}
		
	@Override
	protected ITamablePreggoMobSystem createTamableSystem() {
		return new PreggoMobSystem<>(this, MinepreggoModConfig.SERVER.getTotalTicksOfHungryP0(), PregnancySystemHelper.TOTAL_TICKS_SEXUAL_APPETITE_P0);
	}

	@Override
	protected IFemaleEntity createFemaleEntity() {
		return new SyncedFemaleEntityImpl<>(DATA_HOLDER, this);
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return MonsterEnderWomanHelper.createTamableAttributes(0.3);
	}
	
	@Override
	public Optional<PostPregnancy> getSyncedPostPregnancy() {
		return this.entityData.get(DATA_HOLDER.getDataPostPregnancy());
	}

	@Override
	public OptionalInt getSyncedPostPartumLactation() {
		return this.entityData.get(DATA_HOLDER.getDataLactation());
	}

	@Override
	public ISyncedFemaleEntity<?> getSyncedFemaleEntity() {
		return (SyncedFemaleEntityImpl<?>) this.femaleEntity;
	}
}
