package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP1;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobPregnancySystemP1;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantPreggoMobSystemP1;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableZombieGirlP1 extends AbstractTamablePregnantZombieGirl<PregnantPreggoMobSystemP1<TamableZombieGirlP1>, PreggoMobPregnancySystemP1<TamableZombieGirlP1>> implements IPregnancyP1<TamableZombieGirlP1> {
	
	public TamableZombieGirlP1(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P1.get(), world);
	}

	public TamableZombieGirlP1(EntityType<TamableZombieGirlP1> type, Level world) {
		super(type, world, PregnancyPhase.P1);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}
	
	@Override
	protected PregnantPreggoMobSystemP1<TamableZombieGirlP1> createPreggoMobSystem() {
		return new PregnantPreggoMobSystemP1<>(this, MinepreggoModConfig.getTotalTicksOfHungryP1());
	}
	
	@Override
	protected PreggoMobPregnancySystemP1<TamableZombieGirlP1> createPregnancySystem() {
		return new PreggoMobPregnancySystemP1<>(this) {
			@Override
			protected void advanceToNextPregnancyPhase() {
				if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
					var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P2.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);		
					PreggoMobHelper.transferPregnancyData(pregnantEntity, zombieGirl);			
				}
			}
			
			@Override
			protected void initPostMiscarriage() {
				TamableZombieGirl.onPostMiscarriage(pregnantEntity);
			}
		};
	}

	public static AttributeSupplier.Builder createAttributes() {
		return getBasicAttributes(0.235);
	}

	@Override
	public PreggoMobPregnancySystemP1<TamableZombieGirlP1> getPregnancySystemP1() {
		return pregnancySystem;
	}
}
