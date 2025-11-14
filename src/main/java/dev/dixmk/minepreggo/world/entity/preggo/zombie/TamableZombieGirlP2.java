package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP2;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobPregnancySystemP2;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantPreggoMobSystemP1;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableZombieGirlP2 extends AbstractTamablePregnantZombieGirl<PregnantPreggoMobSystemP1<TamableZombieGirlP2>, PreggoMobPregnancySystemP2<TamableZombieGirlP2>> implements IPregnancyP2<TamableZombieGirlP2> {
	
	public TamableZombieGirlP2(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P2.get(), world);
	}

	public TamableZombieGirlP2(EntityType<TamableZombieGirlP2> type, Level world) {
		super(type, world, PregnancyStage.P2);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}
	
	@Override
	protected PregnantPreggoMobSystemP1<TamableZombieGirlP2> createPreggoMobSystem() {
		return new PregnantPreggoMobSystemP1<>(this, MinepreggoModConfig.getTotalTicksOfHungryP2());
	}
	
	@Override
	protected PreggoMobPregnancySystemP2<TamableZombieGirlP2> createPregnancySystem() {
		return new PreggoMobPregnancySystemP2<>(this) {
			@Override
			protected void advanceToNextPregnancyPhase() {
				if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
					var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P3.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);		
					PreggoMobHelper.copyDataToAdvanceToNextPregnancyPhase(pregnantEntity, zombieGirl);			
				}
			}
			
			@Override
			protected void initPostMiscarriage() {
				TamableZombieGirlP0.onPostMiscarriage(pregnantEntity);
			}
		};
	}

	public static AttributeSupplier.Builder createAttributes() {
		return getBasicAttributes(0.23);
	}

	@Override
	public PreggoMobPregnancySystemP2<TamableZombieGirlP2> getPregnancySystemP2() {
		return pregnancySystem;
	}
}
