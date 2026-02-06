package dev.dixmk.minepreggo.world.entity.preggo.creeper;

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

public class TamableMonsterCreeperGirl extends AbstractTamableMonsterCreeperGirl implements IPostPregnancyEntity {

	private static final SyncedPostPregnancyData.DataAccessor<TamableMonsterCreeperGirl> DATA_HOLDER = new SyncedPostPregnancyData.DataAccessor<>(TamableMonsterCreeperGirl.class);
	
	public TamableMonsterCreeperGirl(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL.get(), world);
	}

	public TamableMonsterCreeperGirl(EntityType<TamableMonsterCreeperGirl> type, Level world) {
		super(type, world);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}
	
	@Override
	protected ITamablePreggoMobSystem createTamablePreggoMobSystem() {
		return new PreggoMobSystem<>(this, MinepreggoModConfig.SERVER.getTotalTicksOfHungryP0(), PregnancySystemHelper.TOTAL_TICKS_SEXUAL_APPETITE_P0);
	}

	@Override
	protected IFemaleEntity createFemaleEntityData() {
		return new SyncedFemaleEntityImpl<>(DATA_HOLDER, this);
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		DATA_HOLDER.defineSynchedData(this);
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return MonsterCreeperHelper.createBasicAttributes(0.26);
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
		return (SyncedFemaleEntityImpl<?>) this.femaleEntityData;
	}
}
