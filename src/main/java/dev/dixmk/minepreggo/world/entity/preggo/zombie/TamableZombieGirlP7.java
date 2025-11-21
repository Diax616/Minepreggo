package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP7;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyPhase;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobPregnancySystemP7;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantPreggoMobSystemP2;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableZombieGirlP7 extends AbstractTamablePregnantZombieGirl<PregnantPreggoMobSystemP2<TamableZombieGirlP7>, PreggoMobPregnancySystemP7<TamableZombieGirlP7>> implements IPregnancyP7<TamableZombieGirlP7> {
		
	public TamableZombieGirlP7(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P7.get(), world);
	}

	public TamableZombieGirlP7(EntityType<TamableZombieGirlP7> type, Level world) {
		super(type, world, PregnancyPhase.P7);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}
	
	@Override
	protected PregnantPreggoMobSystemP2<TamableZombieGirlP7> createPreggoMobSystem() {
		return new PregnantPreggoMobSystemP2<>(this, MinepreggoModConfig.getTotalTicksOfHungryP7());
	}
	
	@Override
	protected PreggoMobPregnancySystemP7<TamableZombieGirlP7> createPregnancySystem() {
		return new PreggoMobPregnancySystemP7<>(this) {
			@Override
			protected void advanceToNextPregnancyPhase() {
				if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
					var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P8.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);		
					PreggoMobHelper.copyDataToAdvanceToNextPregnancyPhase(pregnantEntity, zombieGirl);			
				}
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
	public PreggoMobPregnancySystemP7<TamableZombieGirlP7> getPregnancySystemP7() {
		return pregnancySystem;
	}
}
