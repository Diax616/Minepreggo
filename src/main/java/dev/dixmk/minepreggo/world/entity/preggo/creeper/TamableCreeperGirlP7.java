package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP7;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobPregnancySystemP7;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantPreggoMobSystemP2;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableCreeperGirlP7 extends AbstractTamablePregnantHumanoidCreeperGirl<PregnantPreggoMobSystemP2<TamableCreeperGirlP7>,PreggoMobPregnancySystemP7<TamableCreeperGirlP7>> implements IPregnancyP7<TamableCreeperGirlP7> {
	
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
	protected PregnantPreggoMobSystemP2<TamableCreeperGirlP7> createPreggoMobSystem() {
		return new PregnantPreggoMobSystemP2<>(this, MinepreggoModConfig.getTotalTicksOfHungryP7());
	}
	
	@Override
	protected PreggoMobPregnancySystemP7<TamableCreeperGirlP7> createPregnancySystem() {
		return new PreggoMobPregnancySystemP7<>(this) {		
			@Override
			protected void advanceToNextPregnancyPhase() {
				if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
					var creeperGirl = MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P8.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);
					PreggoMobHelper.copyDataToAdvanceToNextPregnancyPhase(pregnantEntity, creeperGirl);
				}
			}
			
			@Override
			protected void initPostMiscarriage() {
				TamableCreeperGirlP0.onPostMiscarriage(pregnantEntity);
			}
			
			@Override
			protected void initPostPartum() {
				TamableCreeperGirlP0.onPostPartum(pregnantEntity);
			}
		};
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return AbstractTamableHumanoidCreeperGirl.getBasicAttributes(0.19);
	}

	@Override
	public PreggoMobPregnancySystemP7<TamableCreeperGirlP7> getPregnancySystemP7() {
		return pregnancySystem;
	}
}