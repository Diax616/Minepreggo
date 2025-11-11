package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP5;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemP5;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantP2PreggoMobSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableCreeperGirlP5 extends AbstractTamablePregnantHumanoidCreeperGirl<PregnantP2PreggoMobSystem<TamableCreeperGirlP5>,PregnancySystemP5<TamableCreeperGirlP5>> implements IPregnancyP5<TamableCreeperGirlP5> {
	
	public TamableCreeperGirlP5(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P5.get(), world);
	}

	public TamableCreeperGirlP5(EntityType<TamableCreeperGirlP5> type, Level world) {
		super(type, world, PregnancyStage.P5);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}

	@Override
	protected PregnantP2PreggoMobSystem<TamableCreeperGirlP5> createPreggoMobSystem() {
		return new PregnantP2PreggoMobSystem<>(this, MinepreggoModConfig.getTotalTicksOfHungryP5());
	}
	
	@Override
	protected PregnancySystemP5<TamableCreeperGirlP5> createPregnancySystem() {
		return new PregnancySystemP5<>(this) {
			@Override
			protected void advanceToNextPregnancyPhase() {
				if (preggoMob.level() instanceof ServerLevel serverLevel) {
					var creeperGirl = MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P6.get().spawn(serverLevel, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), MobSpawnType.CONVERSION);
					PreggoMobHelper.copyDataToAdvanceToNextPregnancyPhase(preggoMob, creeperGirl);
				}
			}
			
			@Override
			protected void initPostMiscarriage() {
				TamableCreeperGirlP0.onPostPartum(preggoMob);
			}
			
			@Override
			protected void initPostBirth() {
				TamableCreeperGirlP0.onPostPartum(preggoMob);
			}
		};
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return AbstractTamableHumanoidCreeperGirl.getBasicAttributes(0.215);
	}

	@Override
	public PregnancySystemP5<TamableCreeperGirlP5> getPregnancySystemP5() {
		return pregnancySystem;
	}
}
