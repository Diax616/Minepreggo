package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP0;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobPregnancySystemP0;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyPhase;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantPreggoMobSystemP0;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableZombieGirlP0 extends AbstractTamablePregnantZombieGirl<PregnantPreggoMobSystemP0<TamableZombieGirlP0>, PreggoMobPregnancySystemP0<TamableZombieGirlP0>> implements IPregnancyP0<TamableZombieGirlP0> {
	
	public TamableZombieGirlP0(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0.get(), world);
	}

	public TamableZombieGirlP0(EntityType<TamableZombieGirlP0> type, Level world) {
		super(type, world, PregnancyPhase.P0);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}
	
	@Override
	protected PregnantPreggoMobSystemP0<TamableZombieGirlP0> createPreggoMobSystem() {
		return new PregnantPreggoMobSystemP0<>(this, MinepreggoModConfig.getTotalTicksOfHungryP0());
	}
	
	@Override
	protected PreggoMobPregnancySystemP0<TamableZombieGirlP0> createPregnancySystem() {
		return new PreggoMobPregnancySystemP0<>(this) {
			@Override
			protected void advanceToNextPregnancyPhase() {
				if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
					var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P1.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);		
					PreggoMobHelper.copyDataToAdvanceToNextPregnancyPhase(pregnantEntity, zombieGirl);			
				}			
			}
		};
	}

	public static AttributeSupplier.Builder createAttributes() {
		return getBasicAttributes(0.235);
	}

	@Override
	public PreggoMobPregnancySystemP0<TamableZombieGirlP0> getPregnancySystemP0() {
		return pregnancySystem;
	}
}
