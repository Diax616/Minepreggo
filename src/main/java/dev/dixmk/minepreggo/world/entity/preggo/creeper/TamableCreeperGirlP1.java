package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import net.minecraftforge.network.PlayMessages;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP1;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemP1;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantP1PreggoMobSystem;


public class TamableCreeperGirlP1 extends AbstractTamablePregnantHumanoidCreeperGirl<PregnantP1PreggoMobSystem<TamableCreeperGirlP1>, PregnancySystemP1<TamableCreeperGirlP1>> implements IPregnancyP1<TamableCreeperGirlP1> {
	
	public TamableCreeperGirlP1(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P1.get(), world);
	}

	public TamableCreeperGirlP1(EntityType<TamableCreeperGirlP1> type, Level world) {
		super(type, world, PregnancyStage.P1);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}
	
	@Override
	protected PregnantP1PreggoMobSystem<TamableCreeperGirlP1> createPreggoMobSystem() {
		return new PregnantP1PreggoMobSystem<>(this, MinepreggoModConfig.getTotalTicksOfHungryP1());
	}
	
	@Override
	protected PregnancySystemP1<TamableCreeperGirlP1> createPregnancySystem() {
		return new PregnancySystemP1<TamableCreeperGirlP1>(this) {
			@Override
			protected void advanceToNextPregnancyPhase() {
				if (preggoMob.level() instanceof ServerLevel serverLevel) {
					var creeperGirl = MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P2.get().spawn(serverLevel, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), MobSpawnType.CONVERSION);
					PreggoMobHelper.copyDataToAdvanceToNextPregnancyPhase(preggoMob, creeperGirl);
				}
			}
			
			@Override
			protected void initPostMiscarriage() {
				TamableCreeperGirlP0.onPostPartum(preggoMob);
			}
		};
	}

	public static AttributeSupplier.Builder createAttributes() {
		return AbstractTamableHumanoidCreeperGirl.getBasicAttributes(0.24);
	}

	@Override
	public PregnancySystemP1<TamableCreeperGirlP1> getPregnancySystemP1() {
		return this.pregnancySystem;
	}
}
