package dev.dixmk.minepreggo.world.entity.preggo.creeper;

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

public class TamableCreeperGirlP0 extends AbstractTamablePregnantHumanoidCreeperGirl<PregnantPreggoMobSystemP0<TamableCreeperGirlP0>, PreggoMobPregnancySystemP0<TamableCreeperGirlP0>> implements IPregnancyP0<TamableCreeperGirlP0> {
	
	public TamableCreeperGirlP0(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P0.get(), world);
	}

	public TamableCreeperGirlP0(EntityType<TamableCreeperGirlP0> type, Level world) {
		super(type, world, PregnancyPhase.P0);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}
	
	@Override
	protected PregnantPreggoMobSystemP0<TamableCreeperGirlP0> createPreggoMobSystem() {
		return new PregnantPreggoMobSystemP0<>(this, MinepreggoModConfig.getTotalTicksOfHungryP1());
	}
	
	@Override
	protected PreggoMobPregnancySystemP0<TamableCreeperGirlP0> createPregnancySystem() {
		return new PreggoMobPregnancySystemP0<TamableCreeperGirlP0>(this) {
			@Override
			protected void advanceToNextPregnancyPhase() {
				if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
					var creeperGirl = MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P1.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);
					PreggoMobHelper.copyDataToAdvanceToNextPregnancyPhase(pregnantEntity, creeperGirl);
				}
			}
		};
	}

	public static AttributeSupplier.Builder createAttributes() {
		return AbstractTamableHumanoidCreeperGirl.getBasicAttributes(0.24);
	}

	@Override
	public PreggoMobPregnancySystemP0<TamableCreeperGirlP0> getPregnancySystemP0() {
		return this.pregnancySystem;
	}

}
