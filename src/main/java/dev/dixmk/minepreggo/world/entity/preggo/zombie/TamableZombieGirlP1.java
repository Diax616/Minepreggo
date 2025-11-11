package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP1;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemP1;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantP1PreggoMobSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableZombieGirlP1 extends AbstractTamablePregnantZombieGirl<PregnantP1PreggoMobSystem<TamableZombieGirlP1>, PregnancySystemP1<TamableZombieGirlP1>> implements IPregnancyP1<TamableZombieGirlP1> {
	
	public TamableZombieGirlP1(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P1.get(), world);
	}

	public TamableZombieGirlP1(EntityType<TamableZombieGirlP1> type, Level world) {
		super(type, world, PregnancyStage.P1);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}
	
	@Override
	protected PregnantP1PreggoMobSystem<TamableZombieGirlP1> createPreggoMobSystem() {
		return new PregnantP1PreggoMobSystem<>(this, MinepreggoModConfig.getTotalTicksOfHungryP1());
	}
	
	@Override
	protected PregnancySystemP1<TamableZombieGirlP1> createPregnancySystem() {
		return new PregnancySystemP1<>(this) {
			@Override
			protected void advanceToNextPregnancyPhase() {
				if (preggoMob.level() instanceof ServerLevel serverLevel) {
					var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P2.get().spawn(serverLevel, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), MobSpawnType.CONVERSION);		
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
		return getBasicAttributes(0.235);
	}

	@Override
	public PregnancySystemP1<TamableZombieGirlP1> getPregnancySystemP1() {
		return pregnancySystem;
	}
}
