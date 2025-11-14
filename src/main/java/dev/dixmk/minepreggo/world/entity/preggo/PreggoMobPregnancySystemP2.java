package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.network.capability.IPregnancyEffectsHandler;
import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem.Result;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class PreggoMobPregnancySystemP2<E extends PreggoMob
	& ITamablePreggoMob & IPregnancySystemHandler & IPregnancyEffectsHandler> extends PreggoMobPregnancySystemP1<E> {
	
	protected @Nonnegative int totalTicksOfMilking = MinepreggoModConfig.getTotalTicksOfMilkingP2();
	
	protected PreggoMobPregnancySystemP2(@Nonnull E preggoMob) {
		super(preggoMob);
		this.totalTicksOfCraving = MinepreggoModConfig.getTotalTicksOfCravingP2();
	}
	
	@Override
	protected void evaluatePregnancyEffects() {	
		super.evaluatePregnancyEffects();
		evaluateMilkingTimer();
	}
	
	@Override
	public InteractionResult onRightClick(Player source) {		
		var retval = super.onRightClick(source);	
		if (retval != InteractionResult.PASS) {
			return retval;
		}			
		
		var level = pregnantEntity.level();	
		final Result result = evaluateMilking(level, source);
		
		if (result != null) {			
			if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
				PreggoMobSystem.spawnParticles(pregnantEntity, serverLevel, result);
			}
			return InteractionResult.sidedSuccess(level.isClientSide);	
		}
		
		return InteractionResult.PASS;
	}
	
	protected void evaluateMilkingTimer() {
		if (pregnantEntity.getMilking() < PregnancySystemHelper.MAX_MILKING_LEVEL) {
	        if (pregnantEntity.getMilkingTimer() >= totalTicksOfMilking) {
	        	pregnantEntity.incrementMilking();
	        	pregnantEntity.resetMilkingTimer();
	        }
	        else {
	        	pregnantEntity.incrementMilkingTimer();
	        }
		}	
	}

	@Override
	protected boolean tryInitPregnancySymptom() {
		if (super.tryInitPregnancySymptom()) {
			return true;
		}
		if (pregnantEntity.getMilking() >= PregnancySystemHelper.ACTIVATE_MILKING_SYMPTOM) {
	    	pregnantEntity.setPregnancySymptom(PregnancySymptom.MILKING);
	    	return true;		
		}
		return false;
	}
	
	@Override
	protected boolean tryInitRandomPregnancyPain() {
	    if (randomSource.nextFloat() < PregnancySystemHelper.MEDIUM_MORNING_SICKNESS_PROBABILITY) {
	        pregnantEntity.setPregnancyPain(PregnancyPain.MORNING_SICKNESS);
	        pregnantEntity.resetPregnancyPainTimer();
	        return true;
	    }
	    return false;
	}
	
	@Override
	public boolean canBeAngry() {
		return super.canBeAngry() || pregnantEntity.getMilking() >= PregnancySystemHelper.MAX_MILKING_LEVEL;
	}
	
	@Nullable
	public Result evaluateMilking(Level level, Player source) {
			
	    var mainHandItem = source.getMainHandItem().getItem();
	    var currentMilking = pregnantEntity.getMilking();
		
	    if (currentMilking >= PregnancySystemHelper.MILKING_VALUE
	    		&& mainHandItem == Items.GLASS_BOTTLE) {    	

            source.getInventory().clearOrCountMatchingItems(p -> mainHandItem == p.getItem(), 1, source.inventoryMenu.getCraftSlots());
            currentMilking = Math.max(0, currentMilking - PregnancySystemHelper.MILKING_VALUE);
            pregnantEntity.setMilking(currentMilking);               
            var milkItem = PregnancySystemHelper.getMilkItem(pregnantEntity.getSpecies());
           
            if (milkItem != null) {
            	ItemHandlerHelper.giveItemToPlayer(source, new ItemStack(milkItem));
			} else {
				MinepreggoMod.LOGGER.warn("Milk item is null for species: {}", pregnantEntity.getSpecies());
			}
            
            if (!level.isClientSide) {
            	level.playSound(null, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace("entity.cow.milk")), SoundSource.NEUTRAL, 0.75f, 1);	
         
                if (pregnantEntity.getPregnancySymptom() == PregnancySymptom.MILKING
                		&& currentMilking <= PregnancySystemHelper.DESACTIVATE_MILKING_SYMPTOM) {
        	    	pregnantEntity.clearPregnancySymptom();
                } 
            }
         
            return Result.SUCCESS;   
	    }
		
	    return null;
	}
}
