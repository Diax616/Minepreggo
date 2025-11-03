package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.Craving;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP3;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemP3;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantPreggoMobSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableZombieGirlP3 extends AbstractTamablePregnantZombieGirl<PregnantPreggoMobSystem<TamableZombieGirlP3>, PregnancySystemP3<TamableZombieGirlP3>> implements IPregnancyP3 {
	
	public TamableZombieGirlP3(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P3.get(), world);
	}

	public TamableZombieGirlP3(EntityType<TamableZombieGirlP3> type, Level world) {
		super(type, world);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}

	@Override
	protected PregnantPreggoMobSystem<TamableZombieGirlP3> createPreggoMobSystem() {
		return new PregnantPreggoMobSystem<>(this, MinepreggoModConfig.getTotalTicksOfHungryP3());
	}
	
	@Override
	protected PregnancySystemP3<TamableZombieGirlP3> createPregnancySystem() {
		return new PregnancySystemP3<>(this) {
			@Override
			protected void changePregnancyStage() {
				if (preggoMob.level() instanceof ServerLevel serverLevel) {
					var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P4.get().spawn(serverLevel, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), MobSpawnType.CONVERSION);		
					PreggoMobHelper.transferPregnancyP3Data(preggoMob, zombieGirl);			
					PreggoMobHelper.transferInventary(preggoMob, zombieGirl);
					PreggoMobHelper.transferAttackTarget(preggoMob, zombieGirl);
				}
			}
			
			@Override
			protected void postMiscarriage() {
				TamableZombieGirlP0.onPostMiscarriage(preggoMob);
			}
		};
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return getBasicAttributes(0.225);
	}
	
	@Override
	public PregnancyStage getCurrentPregnancyStage() {
		return PregnancyStage.P3;
	}
	
	@Override
	public int getCraving() {
		return this.entityData.get(DATA_CRAVING);
	}

	@Override
	public void setCraving(int craving) {
		this.entityData.set(DATA_CRAVING, craving);
	}
	
	@Override
	public int getCravingTimer() {
		return this.cravingTimer;
	}

	@Override
	public void setCravingTimer(int timer) {
		this.cravingTimer = timer;
	}

	@Override
	public Craving getTypeOfCraving() {
		return this.entityData.get(DATA_CRAVING_CHOSEN);
	}

	@Override
	public void setTypeOfCraving(Craving craving) {
		this.entityData.set(DATA_CRAVING_CHOSEN, craving);
	}

	@Override
	public int getMilking() {
	    return this.entityData.get(DATA_MILKING);
	}
	
	@Override
	public void setMilking(int milking) {
	    this.entityData.set(DATA_MILKING, milking);
	}
	
	@Override
	public int getMilkingTimer() {
	    return this.milkingTimer;
	}
	
	@Override
	public void setMilkingTimer(int timer) {
	    this.milkingTimer = timer;
	}
	
	@Override
	public int getBellyRubs() {
	    return this.entityData.get(DATA_BELLY_RUBS);
	}
	
	@Override
	public void setBellyRubs(int bellyRubs) {
	    this.entityData.set(DATA_BELLY_RUBS, bellyRubs);
	}
	
	@Override
	public int getBellyRubsTimer() {
	    return this.bellyRubsTimer;
	}
	
	@Override
	public void setBellyRubsTimer(int timer) {
        this.bellyRubsTimer = timer;
    }
	
	@Override
	public boolean isValidCraving(Craving craving, Item item) {
		return this.isCraving(craving, item);
	}
}

