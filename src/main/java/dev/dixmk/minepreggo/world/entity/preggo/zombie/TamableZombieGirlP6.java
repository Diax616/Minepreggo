package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP6;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemP6;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantP1PreggoMobSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableZombieGirlP6 extends AbstractTamablePregnantZombieGirl<PregnantP1PreggoMobSystem<TamableZombieGirlP6>, PregnancySystemP6<TamableZombieGirlP6>> implements IPregnancyP6<TamableZombieGirlP6> {
		
	public TamableZombieGirlP6(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P6.get(), world);
	}

	public TamableZombieGirlP6(EntityType<TamableZombieGirlP6> type, Level world) {
		super(type, world, PregnancyStage.P6);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}

	@Override
	protected PregnantP1PreggoMobSystem<TamableZombieGirlP6> createPreggoMobSystem() {
		return new PregnantP1PreggoMobSystem<>(this, MinepreggoModConfig.getTotalTicksOfHungryP6());
	}
	
	@Override
	protected PregnancySystemP6<TamableZombieGirlP6> createPregnancySystem() {
		return new PregnancySystemP6<>(this) {
			@Override
			protected void advanceToNextPregnancyPhase() {
				if (preggoMob.level() instanceof ServerLevel serverLevel) {
					var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P7.get().spawn(serverLevel, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), MobSpawnType.CONVERSION);		
					PreggoMobHelper.copyDataToAdvanceToNextPregnancyPhase(preggoMob, zombieGirl);			
				}
			}
			
			@Override
			protected void initPostMiscarriage() {
				TamableZombieGirlP0.onPostMiscarriage(preggoMob);
			}

			@Override
			protected void initPostBirth() {
				TamableZombieGirlP0.onPostPartum(preggoMob);
			}
		};
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return getBasicAttributes(0.20);
	}

	@Override
	public PregnancySystemP6<TamableZombieGirlP6> getPregnancySystemP6() {
		return pregnancySystem;
	}
}
