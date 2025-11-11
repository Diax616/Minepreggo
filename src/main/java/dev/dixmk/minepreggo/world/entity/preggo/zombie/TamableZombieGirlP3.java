package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP3;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemP3;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantP1PreggoMobSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableZombieGirlP3 extends AbstractTamablePregnantZombieGirl<PregnantP1PreggoMobSystem<TamableZombieGirlP3>, PregnancySystemP3<TamableZombieGirlP3>> implements IPregnancyP3<TamableZombieGirlP3> {
	
	public TamableZombieGirlP3(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P3.get(), world);
	}

	public TamableZombieGirlP3(EntityType<TamableZombieGirlP3> type, Level world) {
		super(type, world, PregnancyStage.P3);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}

	@Override
	protected PregnantP1PreggoMobSystem<TamableZombieGirlP3> createPreggoMobSystem() {
		return new PregnantP1PreggoMobSystem<>(this, MinepreggoModConfig.getTotalTicksOfHungryP3());
	}
	
	@Override
	protected PregnancySystemP3<TamableZombieGirlP3> createPregnancySystem() {
		return new PregnancySystemP3<>(this) {
			@Override
			protected void advanceToNextPregnancyPhase() {
				if (preggoMob.level() instanceof ServerLevel serverLevel) {
					var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P4.get().spawn(serverLevel, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), MobSpawnType.CONVERSION);		
					PreggoMobHelper.copyDataToAdvanceToNextPregnancyPhase(preggoMob, zombieGirl);			
				}
			}
			
			@Override
			protected void initPostMiscarriage() {
				TamableZombieGirlP0.onPostMiscarriage(preggoMob);
			}
		};
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return getBasicAttributes(0.225);
	}

	@Override
	public PregnancySystemP3<TamableZombieGirlP3> getPregnancySystemP3() {
		return pregnancySystem;
	}
}

