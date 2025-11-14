package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP4;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobPregnancySystemP4;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantPreggoMobSystemP1;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableZombieGirlP4 extends AbstractTamablePregnantZombieGirl<PregnantPreggoMobSystemP1<TamableZombieGirlP4>, PreggoMobPregnancySystemP4<TamableZombieGirlP4>> implements IPregnancyP4<TamableZombieGirlP4> {
		
	public TamableZombieGirlP4(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P4.get(), world);
	}

	public TamableZombieGirlP4(EntityType<TamableZombieGirlP4> type, Level world) {
		super(type, world, PregnancyStage.P4);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}

	@Override
	protected PregnantPreggoMobSystemP1<TamableZombieGirlP4> createPreggoMobSystem() {
		return new PregnantPreggoMobSystemP1<>(this, MinepreggoModConfig.getTotalTicksOfHungryP4());
	}
	
	@Override
	protected PreggoMobPregnancySystemP4<TamableZombieGirlP4> createPregnancySystem() {
		return new PreggoMobPregnancySystemP4<>(this) {
			@Override
			protected void advanceToNextPregnancyPhase() {
				if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
					var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P5.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);		
					PreggoMobHelper.copyDataToAdvanceToNextPregnancyPhase(pregnantEntity, zombieGirl);			
				}
			}
			
			@Override
			protected void initPostMiscarriage() {
				TamableZombieGirlP0.onPostMiscarriage(pregnantEntity);
			}

			@Override
			protected void initPostPartum() {
				TamableZombieGirlP0.onPostPartum(pregnantEntity);
			}
		};
	}

	public static AttributeSupplier.Builder createAttributes() {
		return getBasicAttributes(0.22);
	}

	@Override
	public PreggoMobPregnancySystemP4<TamableZombieGirlP4> getPregnancySystemP4() {
		return pregnancySystem;
	}
}

