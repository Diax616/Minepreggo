package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP8;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobPregnancySystemP8;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantPreggoMobSystemP2;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableZombieGirlP8 extends AbstractTamablePregnantZombieGirl<PregnantPreggoMobSystemP2<TamableZombieGirlP8>, PreggoMobPregnancySystemP8<TamableZombieGirlP8>> implements IPregnancyP8<TamableZombieGirlP8> {
	
	public TamableZombieGirlP8(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P8.get(), world);
	}
	
	public TamableZombieGirlP8(EntityType<TamableZombieGirlP8> type, Level world) {
		super(type, world, PregnancyPhase.P8);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}
	
	@Override
	protected PregnantPreggoMobSystemP2<TamableZombieGirlP8> createPreggoMobSystem() {
		return new PregnantPreggoMobSystemP2<>(this, MinepreggoModConfig.getTotalTicksOfHungryP8());
	}
	
	@Override
	protected PreggoMobPregnancySystemP8<TamableZombieGirlP8> createPregnancySystem() {
		return new PreggoMobPregnancySystemP8<>(this) {
			
			@Override
			protected void advanceToNextPregnancyPhase() {
				// P8 is the last pregnancy stage
			}
			
			@Override
			protected void initPostMiscarriage() {
				TamableZombieGirl.onPostMiscarriage(pregnantEntity);
			}
			
			@Override
			protected void initPostPartum() {
				TamableZombieGirl.onPostPartum(pregnantEntity);
			}		
		};
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return getBasicAttributes(0.19);
	}

	@Override
	public PreggoMobPregnancySystemP8<TamableZombieGirlP8> getPregnancySystemP8() {
		return pregnancySystem;
	}
}
