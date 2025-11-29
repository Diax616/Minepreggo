package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP4;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobPregnancySystemP4;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantPreggoMobSystemP2;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableCreeperGirlP4 extends AbstractTamablePregnantHumanoidCreeperGirl<PregnantPreggoMobSystemP2<TamableCreeperGirlP4>,PreggoMobPregnancySystemP4<TamableCreeperGirlP4>> implements IPregnancyP4<TamableCreeperGirlP4> {
	
	public TamableCreeperGirlP4(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P4.get(), world);
	}

	public TamableCreeperGirlP4(EntityType<TamableCreeperGirlP4> type, Level world) {
		super(type, world, PregnancyPhase.P4);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}
	
	@Override
	protected PregnantPreggoMobSystemP2<TamableCreeperGirlP4> createPreggoMobSystem() {
		return new PregnantPreggoMobSystemP2<>(this, MinepreggoModConfig.getTotalTicksOfHungryP4());
	}
	
	@Override
	protected PreggoMobPregnancySystemP4<TamableCreeperGirlP4> createPregnancySystem() {
		return new PreggoMobPregnancySystemP4<>(this) {
			@Override
			protected void advanceToNextPregnancyPhase() {
				if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
					var creeperGirl = MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P5.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);
					PreggoMobHelper.copyDataToAdvanceToNextPregnancyPhase(pregnantEntity, creeperGirl);
				}
			}
			
			@Override
			protected void initPostMiscarriage() {
				TamableCreeperGirl.onPostPartum(pregnantEntity);
			}
			
			@Override
			protected void initPostPartum() {
				TamableCreeperGirl.onPostPartum(pregnantEntity);
			}
		};
	}

	public static AttributeSupplier.Builder createAttributes() {
		return AbstractTamableHumanoidCreeperGirl.getBasicAttributes(0.22);
	}

	@Override
	public PreggoMobPregnancySystemP4<TamableCreeperGirlP4> getPregnancySystemP4() {
		return pregnancySystem;
	}
}
