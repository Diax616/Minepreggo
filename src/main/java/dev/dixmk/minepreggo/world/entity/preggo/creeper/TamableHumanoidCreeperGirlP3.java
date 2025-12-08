package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP3;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobPregnancySystemP3;
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

public class TamableHumanoidCreeperGirlP3 extends AbstractTamablePregnantHumanoidCreeperGirl<PregnantPreggoMobSystemP2<TamableHumanoidCreeperGirlP3>,PreggoMobPregnancySystemP3<TamableHumanoidCreeperGirlP3>> implements IPregnancyP3<TamableHumanoidCreeperGirlP3> {
	
	public TamableHumanoidCreeperGirlP3(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P3.get(), world);
	}

	public TamableHumanoidCreeperGirlP3(EntityType<TamableHumanoidCreeperGirlP3> type, Level world) {
		super(type, world, PregnancyPhase.P3);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}
	
	@Override
	protected PregnantPreggoMobSystemP2<TamableHumanoidCreeperGirlP3> createPreggoMobSystem() {
		return new PregnantPreggoMobSystemP2<>(this, MinepreggoModConfig.getTotalTicksOfHungryP3(), PregnancySystemHelper.TOTAL_TICKS_SEXUAL_APPETITE_P3);
	}
	
	@Override
	protected PreggoMobPregnancySystemP3<TamableHumanoidCreeperGirlP3> createPregnancySystem() {
		return new PreggoMobPregnancySystemP3<TamableHumanoidCreeperGirlP3>(this) {
			@Override
			protected void advanceToNextPregnancyPhase() {
				if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
					var creeperGirl = MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P4.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);
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
		return AbstractTamableHumanoidCreeperGirl.getBasicAttributes(0.23);
	}

	@Override
	public PreggoMobPregnancySystemP3<TamableHumanoidCreeperGirlP3> getPregnancySystemP3() {
		return pregnancySystem;
	}
}
