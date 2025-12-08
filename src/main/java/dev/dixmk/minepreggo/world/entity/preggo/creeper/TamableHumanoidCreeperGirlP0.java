package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP0;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobPregnancySystemP0;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantPreggoMobSystemP0;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableHumanoidCreeperGirlP0 extends AbstractTamablePregnantHumanoidCreeperGirl<PregnantPreggoMobSystemP0<TamableHumanoidCreeperGirlP0>, PreggoMobPregnancySystemP0<TamableHumanoidCreeperGirlP0>> implements IPregnancyP0<TamableHumanoidCreeperGirlP0> {
	
	public TamableHumanoidCreeperGirlP0(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P0.get(), world);
	}

	public TamableHumanoidCreeperGirlP0(EntityType<TamableHumanoidCreeperGirlP0> type, Level world) {
		super(type, world, PregnancyPhase.P0);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}
	
	@Override
	protected PregnantPreggoMobSystemP0<TamableHumanoidCreeperGirlP0> createPreggoMobSystem() {
		return new PregnantPreggoMobSystemP0<>(this, MinepreggoModConfig.getTotalTicksOfHungryP1(), PregnancySystemHelper.TOTAL_TICKS_SEXUAL_APPETITE_P0);
	}
	
	@Override
	protected PreggoMobPregnancySystemP0<TamableHumanoidCreeperGirlP0> createPregnancySystem() {
		return new PreggoMobPregnancySystemP0<TamableHumanoidCreeperGirlP0>(this) {
			@Override
			protected void advanceToNextPregnancyPhase() {
				if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
					var creeperGirl = MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P1.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);
					PreggoMobHelper.transferAllData(pregnantEntity, creeperGirl);
				}
			}
		};
	}

	public static AttributeSupplier.Builder createAttributes() {
		return AbstractTamableHumanoidCreeperGirl.getBasicAttributes(0.24);
	}

	@Override
	public PreggoMobPregnancySystemP0<TamableHumanoidCreeperGirlP0> getPregnancySystemP0() {
		return this.pregnancySystem;
	}

}
