package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP8;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemP8;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantP2PreggoMobSystem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableCreeperGirlP8 extends AbstractTamablePregnantHumanoidCreeperGirl<PregnantP2PreggoMobSystem<TamableCreeperGirlP8>, PregnancySystemP8<TamableCreeperGirlP8>> implements IPregnancyP8<TamableCreeperGirlP8> {
	
	public TamableCreeperGirlP8(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P8.get(), world);
	}

	public TamableCreeperGirlP8(EntityType<TamableCreeperGirlP8> type, Level world) {
		super(type, world, PregnancyStage.P8);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}
	
	@Override
	protected PregnantP2PreggoMobSystem<TamableCreeperGirlP8> createPreggoMobSystem() {
		return new PregnantP2PreggoMobSystem<>(this, MinepreggoModConfig.getTotalTicksOfHungryP8());
	}
	
	@Override
	protected PregnancySystemP8<TamableCreeperGirlP8> createPregnancySystem() {
		return new PregnancySystemP8<>(this) {	
			
			@Override
			protected void advanceToNextPregnancyPhase() {
				// P8 is last pregnancy stage
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
	public PregnancySystemP8<TamableCreeperGirlP8> getPregnancySystemP8() {
		return pregnancySystem;
	}
}