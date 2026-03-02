package dev.dixmk.minepreggo.world.entity.preggo.ender;

import java.util.Optional;
import java.util.OptionalInt;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.EntityHelper;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.entity.preggo.FemaleFertilitySystem;
import dev.dixmk.minepreggo.world.entity.preggo.IPostPregnancyEntity;
import dev.dixmk.minepreggo.world.entity.preggo.ISyncedFemaleEntity;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMobSystem;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem;
import dev.dixmk.minepreggo.world.entity.preggo.SyncedFemaleEntityImpl;
import dev.dixmk.minepreggo.world.entity.preggo.SyncedPostPregnancyData;
import dev.dixmk.minepreggo.world.pregnancy.IFemaleEntity;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancy;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableMonsterEnderWoman extends AbstractTamableMonsterEnderWoman implements IPostPregnancyEntity {

	private static final SyncedPostPregnancyData.DataAccessor<TamableMonsterEnderWoman> DATA_HOLDER = new SyncedPostPregnancyData.DataAccessor<>(TamableMonsterEnderWoman.class);
	
	private final FemaleFertilitySystem<TamableMonsterEnderWoman> fertilitySystem = new FemaleFertilitySystem<>(this) {
		@Override
		protected void startPregnancy() {		
			if (preggoMob.level() instanceof ServerLevel serverLevel && !serverLevel.isClientSide) {
				TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP0 enderWoman = MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P0.get().spawn(serverLevel, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), MobSpawnType.CONVERSION);		
				LivingEntityHelper.copyRotation(preggoMob, enderWoman);
				PreggoMobHelper.copyOwner(preggoMob, enderWoman);
				LivingEntityHelper.copyHealth(preggoMob, enderWoman);
				EntityHelper.copyName(preggoMob, enderWoman);
				PreggoMobHelper.copyTamableData(preggoMob, enderWoman);				
				PreggoMobHelper.transferInventory(preggoMob, enderWoman);					
				LivingEntityHelper.transferAttackTarget(preggoMob, enderWoman);
				LivingEntityHelper.copyMobEffects(preggoMob, enderWoman);
				enderWoman.setCarriedBlock(preggoMob.getCarriedBlock());
				syncBlockToInventory(enderWoman);
				PreggoMobHelper.initPregnancy(enderWoman);				
			}			
		}

		@Override
		protected void afterPostPregnancy() {
			PregnancySystemHelper.removePostPregnancyNeft(preggoMob);			
		}
	};
	public TamableMonsterEnderWoman(EntityType<TamableMonsterEnderWoman> entityType, Level level) {
		super(entityType, level);
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
   	public void aiStep() {
      super.aiStep();
      fertilitySystem.onServerTick();  
	}
	
	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {				
		var retval = fertilitySystem.onRightClick(sourceentity);			
		if (retval == InteractionResult.SUCCESS || retval == InteractionResult.CONSUME) {
			return retval;
		}			
		return super.mobInteract(sourceentity, hand);
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
	
	public static AttributeSupplier.Builder createAttributes() {
		return MonsterEnderWomanHelper.createTamableAttributes(0.3);
	}
	
	public static<E extends AbstractTamablePregnantEnderWoman> void onPostPartum(E source) {
		if (source.level() instanceof ServerLevel serverLevel) {
			TamableMonsterEnderWoman enderWoman = MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN.get().spawn(serverLevel, BlockPos.containing(source.getX(), source.getY(), source.getZ()), MobSpawnType.CONVERSION);	
			LivingEntityHelper.copyRotation(source, enderWoman);
			PreggoMobHelper.copyOwner(source, enderWoman);
			LivingEntityHelper.copyHealth(source, enderWoman);
			EntityHelper.copyName(source, enderWoman);
			PreggoMobHelper.copyTamableData(source, enderWoman);	
			LivingEntityHelper.copyMobEffects(source, enderWoman);
			PreggoMobHelper.transferInventory(source, enderWoman);
			LivingEntityHelper.transferAttackTarget(source, enderWoman);	
			enderWoman.getTamableData().setBodyState(null);
			enderWoman.setCarriedBlock(source.getCarriedBlock());
			syncBlockToInventory(enderWoman);
			
			if (!enderWoman.getGenderedData().tryActivatePostPregnancyPhase(PostPregnancy.PARTUM)) {
				source.discard();
				enderWoman.discard();
				throw new IllegalStateException("Failed to activate PostPregnancy.PARTUM phase on TamableMonsterEnderWoman after giving birth");
			}
			
			PregnancySystemHelper.applyPostPregnancyNerf(enderWoman);
		}
	}
	
	public static<E extends AbstractTamablePregnantEnderWoman> void onPostMiscarriage(E source) {
		if (source.level() instanceof ServerLevel serverLevel) {
			TamableMonsterEnderWoman enderWoman = MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN.get().spawn(serverLevel, BlockPos.containing(source.getX(), source.getY(), source.getZ()), MobSpawnType.CONVERSION);	
			LivingEntityHelper.copyRotation(source, enderWoman);
			PreggoMobHelper.copyOwner(source, enderWoman);
			LivingEntityHelper.copyHealth(source, enderWoman);
			EntityHelper.copyName(source, enderWoman);
			PreggoMobHelper.copyTamableData(source, enderWoman);	
			LivingEntityHelper.copyMobEffects(source, enderWoman);
			PreggoMobHelper.transferInventory(source, enderWoman);
			LivingEntityHelper.transferAttackTarget(source, enderWoman);
			enderWoman.setCarriedBlock(source.getCarriedBlock());
			syncBlockToInventory(enderWoman);
			
			if (!enderWoman.getGenderedData().tryActivatePostPregnancyPhase(PostPregnancy.MISCARRIAGE)) {
				source.discard();
				enderWoman.discard();
                throw new IllegalStateException("Failed to activate PostPregnancy.MISCARRIAGE phase on TamableMonsterEnderWoman after miscarriage");
			}
			
			PregnancySystemHelper.applyPostPregnancyNerf(enderWoman);
		}
	}
}
