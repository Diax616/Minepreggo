package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP6;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemP6;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantP2PreggoMobSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableCreeperGirlP6 extends AbstractTamablePregnantHumanoidCreeperGirl<PregnantP2PreggoMobSystem<TamableCreeperGirlP6>,PregnancySystemP6<TamableCreeperGirlP6>> implements IPregnancyP6<TamableCreeperGirlP6> {
	
	public TamableCreeperGirlP6(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P6.get(), world);
	}

	public TamableCreeperGirlP6(EntityType<TamableCreeperGirlP6> type, Level world) {
		super(type, world, PregnancyStage.P6);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}
	
	@Override
	protected PregnantP2PreggoMobSystem<TamableCreeperGirlP6> createPreggoMobSystem() {
		return new PregnantP2PreggoMobSystem<>(this, MinepreggoModConfig.getTotalTicksOfHungryP6());
	}
	
	@Override
	protected PregnancySystemP6<TamableCreeperGirlP6> createPregnancySystem() {
		return new PregnancySystemP6<>(this) {
			@Override
			protected void advanceToNextPregnancyPhase() {
				if (preggoMob.level() instanceof ServerLevel serverLevel) {
					var creeperGirl = MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P7.get().spawn(serverLevel, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), MobSpawnType.CONVERSION);
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
		return AbstractTamableHumanoidCreeperGirl.getBasicAttributes(0.20);
	}

	@Override
	public PregnancySystemP6<TamableCreeperGirlP6> getPregnancySystemP6() {
		return pregnancySystem;
	}
}
