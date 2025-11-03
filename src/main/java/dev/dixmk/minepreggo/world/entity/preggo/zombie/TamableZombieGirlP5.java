package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.Craving;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP5;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemP5;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantPreggoMobSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableZombieGirlP5 extends AbstractTamablePregnantZombieGirl<PregnantPreggoMobSystem<TamableZombieGirlP5>, PregnancySystemP5<TamableZombieGirlP5>> implements IPregnancyP5 {
		
	public TamableZombieGirlP5(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P5.get(), world);
	}

	public TamableZombieGirlP5(EntityType<TamableZombieGirlP5> type, Level world) {
		super(type, world);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}

	@Override
	protected PregnantPreggoMobSystem<TamableZombieGirlP5> createPreggoMobSystem() {
		return new PregnantPreggoMobSystem<>(this, MinepreggoModConfig.getTotalTicksOfHungryP5());
	}
	
	@Override
	protected PregnancySystemP5<TamableZombieGirlP5> createPregnancySystem() {
		return new PregnancySystemP5<>(this) {
			@Override
			protected void changePregnancyStage() {
				if (preggoMob.level() instanceof ServerLevel serverLevel) {
					var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P6.get().spawn(serverLevel, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), MobSpawnType.CONVERSION);		
					PreggoMobHelper.transferPregnancyP4Data(preggoMob, zombieGirl);			
					PreggoMobHelper.transferInventary(preggoMob, zombieGirl);
					PreggoMobHelper.transferAttackTarget(preggoMob, zombieGirl);
				}
			}
			
			@Override
			protected void postMiscarriage() {
				TamableZombieGirlP0.onPostMiscarriage(preggoMob);
			}

			@Override
			protected void postBirth() {
				TamableZombieGirlP0.onPostPartum(preggoMob);
			}
		};
	}

	public static AttributeSupplier.Builder createAttributes() {
		return getBasicAttributes(0.215);
	}
	
	@Override
	public PregnancyStage getCurrentPregnancyStage() {
		return PregnancyStage.P5;
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
	public int getHorny() {
        return this.entityData.get(DATA_HORNY);
    }
	
	@Override
	public void setHorny(int horny) {
        this.entityData.set(DATA_HORNY, horny);
    }
	
	@Override
	public int getHornyTimer() {
        return this.hornyTimer;
    }
	
	@Override
	public void setHornyTimer(int timer) {
        this.hornyTimer = timer;
    }
	
	@Override
	public boolean isValidCraving(Craving craving, Item item) {
		return this.isCraving(craving, item);
	}
}

