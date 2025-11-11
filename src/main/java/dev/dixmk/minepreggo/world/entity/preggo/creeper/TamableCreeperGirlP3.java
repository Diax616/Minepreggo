package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP3;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemP3;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantP2PreggoMobSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableCreeperGirlP3 extends AbstractTamablePregnantHumanoidCreeperGirl<PregnantP2PreggoMobSystem<TamableCreeperGirlP3>,PregnancySystemP3<TamableCreeperGirlP3>> implements IPregnancyP3<TamableCreeperGirlP3> {
	
	public TamableCreeperGirlP3(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P3.get(), world);
	}

	public TamableCreeperGirlP3(EntityType<TamableCreeperGirlP3> type, Level world) {
		super(type, world, PregnancyStage.P3);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}
	
	@Override
	protected PregnantP2PreggoMobSystem<TamableCreeperGirlP3> createPreggoMobSystem() {
		return new PregnantP2PreggoMobSystem<>(this, MinepreggoModConfig.getTotalTicksOfHungryP3());
	}
	
	@Override
	protected PregnancySystemP3<TamableCreeperGirlP3> createPregnancySystem() {
		return new PregnancySystemP3<TamableCreeperGirlP3>(this) {
			@Override
			protected void advanceToNextPregnancyPhase() {
				if (preggoMob.level() instanceof ServerLevel serverLevel) {
					var creeperGirl = MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P4.get().spawn(serverLevel, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), MobSpawnType.CONVERSION);
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
		return AbstractTamableHumanoidCreeperGirl.getBasicAttributes(0.23);
	}

	@Override
	public PregnancySystemP3<TamableCreeperGirlP3> getPregnancySystemP3() {
		return pregnancySystem;
	}
}
