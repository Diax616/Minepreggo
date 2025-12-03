package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP8;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobPregnancySystemP8;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantPreggoMobSystemP2;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableCreeperGirlP8 extends AbstractTamablePregnantHumanoidCreeperGirl<PregnantPreggoMobSystemP2<TamableCreeperGirlP8>, PreggoMobPregnancySystemP8<TamableCreeperGirlP8>> implements IPregnancyP8<TamableCreeperGirlP8> {
	
	public TamableCreeperGirlP8(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P8.get(), world);
	}

	public TamableCreeperGirlP8(EntityType<TamableCreeperGirlP8> type, Level world) {
		super(type, world, PregnancyPhase.P8);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}
	
	@Override
	protected PregnantPreggoMobSystemP2<TamableCreeperGirlP8> createPreggoMobSystem() {
		return new PregnantPreggoMobSystemP2<>(this, MinepreggoModConfig.getTotalTicksOfHungryP8(), PregnancySystemHelper.TOTAL_TICKS_SEXUAL_APPETITE_P8);
	}
	
	@Override
	protected PreggoMobPregnancySystemP8<TamableCreeperGirlP8> createPregnancySystem() {
		return new PreggoMobPregnancySystemP8<>(this) {	
			
			@Override
			protected void advanceToNextPregnancyPhase() {
				// P8 is last pregnancy stage
			}
			
			@Override
			protected void initPostMiscarriage() {
				TamableCreeperGirl.onPostMiscarriage(pregnantEntity);
			}
			
			@Override
			protected void initPostPartum() {
				TamableCreeperGirl.onPostPartum(pregnantEntity);
			}
		};
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return AbstractTamableHumanoidCreeperGirl.getBasicAttributes(0.19);
	}

	@Override
	public PreggoMobPregnancySystemP8<TamableCreeperGirlP8> getPregnancySystemP8() {
		return pregnancySystem;
	}
}