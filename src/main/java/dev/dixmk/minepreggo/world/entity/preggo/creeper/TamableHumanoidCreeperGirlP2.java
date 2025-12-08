package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP2;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobPregnancySystemP2;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantPreggoMobSystemP2;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableHumanoidCreeperGirlP2 extends AbstractTamablePregnantHumanoidCreeperGirl<PregnantPreggoMobSystemP2<TamableHumanoidCreeperGirlP2>,PreggoMobPregnancySystemP2<TamableHumanoidCreeperGirlP2>> implements IPregnancyP2<TamableHumanoidCreeperGirlP2> {
	
	public TamableHumanoidCreeperGirlP2(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P2.get(), world);
	}

	public TamableHumanoidCreeperGirlP2(EntityType<TamableHumanoidCreeperGirlP2> type, Level world) {
		super(type, world, PregnancyPhase.P2);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}
	
	@Override
	protected PregnantPreggoMobSystemP2<TamableHumanoidCreeperGirlP2> createPreggoMobSystem() {
		return new PregnantPreggoMobSystemP2<>(this, MinepreggoModConfig.getTotalTicksOfHungryP2(), PregnancySystemHelper.TOTAL_TICKS_SEXUAL_APPETITE_P2);
	}
	
	@Override
	protected PreggoMobPregnancySystemP2<TamableHumanoidCreeperGirlP2> createPregnancySystem() {
		return new PreggoMobPregnancySystemP2<TamableHumanoidCreeperGirlP2>(this) {
			@Override
			protected void advanceToNextPregnancyPhase() {
				if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
					var creeperGirl = MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P3.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);
					PreggoMobHelper.transferAllData(pregnantEntity, creeperGirl);
				}
			}
			
			@Override
			protected void initPostMiscarriage() {
				TamableHumanoidCreeperGirl.onPostPartum(pregnantEntity);
			}
		};
	}

	public static AttributeSupplier.Builder createAttributes() {
		return AbstractTamableHumanoidCreeperGirl.getBasicAttributes(0.235);
	}

	@Override
	public PreggoMobPregnancySystemP2<TamableHumanoidCreeperGirlP2> getPregnancySystemP2() {
		return pregnancySystem;
	}
}
