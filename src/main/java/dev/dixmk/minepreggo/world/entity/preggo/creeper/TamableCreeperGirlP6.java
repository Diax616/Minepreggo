package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP6;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyPhase;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobPregnancySystemP6;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantPreggoMobSystemP2;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableCreeperGirlP6 extends AbstractTamablePregnantHumanoidCreeperGirl<PregnantPreggoMobSystemP2<TamableCreeperGirlP6>,PreggoMobPregnancySystemP6<TamableCreeperGirlP6>> implements IPregnancyP6<TamableCreeperGirlP6> {
	
	public TamableCreeperGirlP6(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P6.get(), world);
	}

	public TamableCreeperGirlP6(EntityType<TamableCreeperGirlP6> type, Level world) {
		super(type, world, PregnancyPhase.P6);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}
	
	@Override
	protected PregnantPreggoMobSystemP2<TamableCreeperGirlP6> createPreggoMobSystem() {
		return new PregnantPreggoMobSystemP2<>(this, MinepreggoModConfig.getTotalTicksOfHungryP6());
	}
	
	@Override
	protected PreggoMobPregnancySystemP6<TamableCreeperGirlP6> createPregnancySystem() {
		return new PreggoMobPregnancySystemP6<>(this) {
			@Override
			protected void advanceToNextPregnancyPhase() {
				if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
					var creeperGirl = MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P7.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);
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
		return AbstractTamableHumanoidCreeperGirl.getBasicAttributes(0.20);
	}

	@Override
	public PreggoMobPregnancySystemP6<TamableCreeperGirlP6> getPregnancySystemP6() {
		return pregnancySystem;
	}
}
