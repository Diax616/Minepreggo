package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP6;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobPregnancySystemP6;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantPreggoMobSystemP2;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableZombieGirlP6 extends AbstractTamablePregnantZombieGirl<PregnantPreggoMobSystemP2<TamableZombieGirlP6>, PreggoMobPregnancySystemP6<TamableZombieGirlP6>> implements IPregnancyP6<TamableZombieGirlP6> {
		
	public TamableZombieGirlP6(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P6.get(), world);
	}

	public TamableZombieGirlP6(EntityType<TamableZombieGirlP6> type, Level world) {
		super(type, world, PregnancyPhase.P6);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}

	@Override
	protected PregnantPreggoMobSystemP2<TamableZombieGirlP6> createPreggoMobSystem() {
		return new PregnantPreggoMobSystemP2<>(this, MinepreggoModConfig.getTotalTicksOfHungryP6());
	}
	
	@Override
	protected PreggoMobPregnancySystemP6<TamableZombieGirlP6> createPregnancySystem() {
		return new PreggoMobPregnancySystemP6<>(this) {
			@Override
			protected void advanceToNextPregnancyPhase() {
				if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
					var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P7.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);		
					PreggoMobHelper.copyDataToAdvanceToNextPregnancyPhase(pregnantEntity, zombieGirl);			
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
		return getBasicAttributes(0.20);
	}

	@Override
	public PreggoMobPregnancySystemP6<TamableZombieGirlP6> getPregnancySystemP6() {
		return pregnancySystem;
	}
}
