package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP7;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemP7;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantP2PreggoMobSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableCreeperGirlP7 extends AbstractTamablePregnantHumanoidCreeperGirl<PregnantP2PreggoMobSystem<TamableCreeperGirlP7>,PregnancySystemP7<TamableCreeperGirlP7>> implements IPregnancyP7<TamableCreeperGirlP7> {
	
	public TamableCreeperGirlP7(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P7.get(), world);
	}

	public TamableCreeperGirlP7(EntityType<TamableCreeperGirlP7> type, Level world) {
		super(type, world, PregnancyStage.P7);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}
	
	@Override
	protected PregnantP2PreggoMobSystem<TamableCreeperGirlP7> createPreggoMobSystem() {
		return new PregnantP2PreggoMobSystem<>(this, MinepreggoModConfig.getTotalTicksOfHungryP7());
	}
	
	@Override
	protected PregnancySystemP7<TamableCreeperGirlP7> createPregnancySystem() {
		return new PregnancySystemP7<>(this) {		
			@Override
			protected void advanceToNextPregnancyPhase() {
				if (preggoMob.level() instanceof ServerLevel serverLevel) {
					var creeperGirl = MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P8.get().spawn(serverLevel, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), MobSpawnType.CONVERSION);
					PreggoMobHelper.copyDataToAdvanceToNextPregnancyPhase(preggoMob, creeperGirl);
				}
			}
			
			@Override
			protected void initPostMiscarriage() {
				TamableCreeperGirlP0.onPostMiscarriage(preggoMob);
			}
			
			@Override
			protected void initPostBirth() {
				TamableCreeperGirlP0.onPostPartum(preggoMob);
			}
		};
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return AbstractTamableHumanoidCreeperGirl.getBasicAttributes(0.19);
	}

	@Override
	public PregnancySystemP7<TamableCreeperGirlP7> getPregnancySystemP7() {
		return pregnancySystem;
	}
}