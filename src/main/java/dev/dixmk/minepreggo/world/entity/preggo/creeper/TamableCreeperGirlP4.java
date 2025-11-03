package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.Craving;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP4;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemP4;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantPreggoMobSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableCreeperGirlP4 extends AbstractTamablePregnantHumanoidCreeperGirl<PregnantPreggoMobSystem<TamableCreeperGirlP4>,PregnancySystemP4<TamableCreeperGirlP4>> implements IPregnancyP4 {
	
	public TamableCreeperGirlP4(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P4.get(), world);
	}

	public TamableCreeperGirlP4(EntityType<TamableCreeperGirlP4> type, Level world) {
		super(type, world);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}
	
	@Override
	protected PregnantPreggoMobSystem<TamableCreeperGirlP4> createPreggoMobSystem() {
		return new PregnantPreggoMobSystem<>(this, MinepreggoModConfig.getTotalTicksOfHungryP4());
	}
	
	@Override
	protected PregnancySystemP4<TamableCreeperGirlP4> createPregnancySystem() {
		return new PregnancySystemP4<>(this) {
			@Override
			protected void changePregnancyStage() {
				if (preggoMob.level() instanceof ServerLevel serverLevel) {
					var creeperGirl = MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P5.get().spawn(serverLevel, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), MobSpawnType.CONVERSION);
					PreggoMobHelper.transferPregnancyP4Data(preggoMob, creeperGirl);
					PreggoMobHelper.transferInventary(preggoMob, creeperGirl);
					PreggoMobHelper.transferAttackTarget(preggoMob, creeperGirl);
				}
			}
			
			@Override
			protected void postMiscarriage() {
				TamableCreeperGirlP0.onPostPartum(preggoMob);
			}
			
			@Override
			protected void postBirth() {
				TamableCreeperGirlP0.onPostPartum(preggoMob);
			}
		};
	}

	public static AttributeSupplier.Builder createAttributes() {
		return AbstractTamableHumanoidCreeperGirl.getBasicAttributes(0.22);
	}
	
	@Override
	public PregnancyStage getCurrentPregnancyStage() {
		return PregnancyStage.P4;
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
