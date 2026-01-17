package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.IPreggoMobPregnancySystem;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMobSystem;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobPregnancySystemP5;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantPreggoMobSystemP2;
import dev.dixmk.minepreggo.world.pregnancy.FemaleEntityImpl;
import dev.dixmk.minepreggo.world.pregnancy.IFemaleEntity;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableZombieGirlP5 extends AbstractTamablePregnantZombieGirl {
		
	public TamableZombieGirlP5(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P5.get(), world);
	}

	public TamableZombieGirlP5(EntityType<TamableZombieGirlP5> type, Level world) {
		super(type, world, PregnancyPhase.P5);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}

	@Override
	protected ITamablePreggoMobSystem createTamableSystem() {
		return new PregnantPreggoMobSystemP2<>(this, MinepreggoModConfig.getTotalTicksOfHungryP5(), PregnancySystemHelper.TOTAL_TICKS_SEXUAL_APPETITE_P5);
	}
	
	@Override
	protected IFemaleEntity createFemaleEntity() {
		return new FemaleEntityImpl();
	}
	
	@Override
	protected IPreggoMobPregnancySystem createPregnancySystem() {
		return new PreggoMobPregnancySystemP5<>(this) {
			@Override
			protected void advanceToNextPregnancyPhase() {
				if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
					var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P6.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);		
					PreggoMobHelper.transferAllData(pregnantEntity, zombieGirl);
				}
			}
			
			@Override
			protected void initPostMiscarriage() {
				TamableZombieGirl.onPostMiscarriage(pregnantEntity);
			}

			@Override
			protected void initPostPartum() {
				TamableZombieGirl.onPostPartum(pregnantEntity);
			}
		};
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return getBasicAttributes(0.215);
	}
}

