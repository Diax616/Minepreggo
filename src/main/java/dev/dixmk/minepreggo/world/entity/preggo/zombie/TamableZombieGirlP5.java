package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP5;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobPregnancySystemP5;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantPreggoMobSystemP1;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableZombieGirlP5 extends AbstractTamablePregnantZombieGirl<PregnantPreggoMobSystemP1<TamableZombieGirlP5>, PreggoMobPregnancySystemP5<TamableZombieGirlP5>> implements IPregnancyP5<TamableZombieGirlP5> {
		
	public TamableZombieGirlP5(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P5.get(), world);
	}

	public TamableZombieGirlP5(EntityType<TamableZombieGirlP5> type, Level world) {
		super(type, world, PregnancyStage.P5);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}

	@Override
	protected PregnantPreggoMobSystemP1<TamableZombieGirlP5> createPreggoMobSystem() {
		return new PregnantPreggoMobSystemP1<>(this, MinepreggoModConfig.getTotalTicksOfHungryP5());
	}
	
	@Override
	protected PreggoMobPregnancySystemP5<TamableZombieGirlP5> createPregnancySystem() {
		return new PreggoMobPregnancySystemP5<>(this) {
			@Override
			protected void advanceToNextPregnancyPhase() {
				if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
					var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P6.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);		
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
		return getBasicAttributes(0.215);
	}

	@Override
	public PreggoMobPregnancySystemP5<TamableZombieGirlP5> getPregnancySystemP5() {
		return pregnancySystem;
	}
}

