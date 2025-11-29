package dev.dixmk.minepreggo.world.entity.preggo;

import java.util.EnumSet;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem.Result;
import dev.dixmk.minepreggo.world.item.IItemCraving;
import dev.dixmk.minepreggo.world.pregnancy.IBreedable;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancyEffectsHandler;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancySystemHandler;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class PreggoMobPregnancySystemP1<
	E extends PreggoMob & ITamablePreggoMob & IPregnancySystemHandler & IPregnancyEffectsHandler> extends PreggoMobPregnancySystemP0<E> {

	private int pregnancyPainTicks = 0;
	private int pregnancysymptonsTicks = 0;
	
	private final Set<PregnancySymptom> validPregnancySymptoms = EnumSet.noneOf(PregnancySymptom.class);

	protected @Nonnegative int totalTicksOfCraving = MinepreggoModConfig.getTotalTicksOfCravingP1();
	
	protected PreggoMobPregnancySystemP1(@Nonnull E preggoMob) {
		super(preggoMob);
		initPregnancySymptomsTimers();
		addNewValidPregnancySymptom(PregnancySymptom.CRAVING);
	}
	
	protected void initPregnancySymptomsTimers() {

	}
	
	protected void addNewValidPregnancySymptom(PregnancySymptom newPregnancySymptom) {
		this.validPregnancySymptoms.add(newPregnancySymptom);
	}
	
	// It has to be executed on server side
	@Override
	protected void evaluatePregnancySystem() {
		if (isMiscarriageActive()) {
			if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
				evaluateMiscarriage(serverLevel);
			}		
			return;
		}
		
		if (!hasPregnancyPain()) {
			if (pregnancyPainTicks > 40) {
				tryInitRandomPregnancyPain();
				pregnancyPainTicks = 0;
			}
			else {
				++pregnancyPainTicks;
			}
		}
		else {
			evaluatePregnancyPains();
		}
		
		if (!hasAllPregnancySymptoms()) {
			if (pregnancysymptonsTicks > 40) {
				tryInitPregnancySymptom();
				pregnancysymptonsTicks = 0;
			}
			else {
				++pregnancysymptonsTicks;
			}
		}
		if (!pregnantEntity.getPregnancySymptoms().isEmpty()) {
			evaluatePregnancySymptoms();
		}
		
		evaluatePregnancyNeeds();
		
		super.evaluatePregnancySystem();
	}
	
	@Override
	protected void evaluatePregnancyNeeds() {	
		evaluateCravingTimer();	
	}
	
	@Override
	public InteractionResult onRightClick(Player source) {		
		var retval = super.onRightClick(source);	
		if (retval != InteractionResult.PASS) {
			return retval;
		}	
					
		var level = pregnantEntity.level();
					
		final Result result;	
		if (!source.getMainHandItem().isEmpty() && (result = evaluateCraving(level, source)) != null) {			
			if (!level.isClientSide) {
				PreggoMobSystem.spawnParticles(pregnantEntity, result);
			}
			return InteractionResult.sidedSuccess(level.isClientSide);
		}
		
		return InteractionResult.PASS;
	}
	
	@Override
	public boolean isRightClickValid(Player source) {
		return super.isRightClickValid(source) && !pregnantEntity.isIncapacitated();
	}

	
	@Override
	protected void evaluateMiscarriage(ServerLevel serverLevel) {	    
        if (pregnantEntity.getPregnancyPainTimer() < PregnancySystemHelper.TOTAL_TICKS_MISCARRIAGE) {
        	pregnantEntity.setPregnancyPainTimer(pregnantEntity.getPregnancyPainTimer() + 1);	        		        	
    		for (ServerPlayer player : serverLevel.getServer().getPlayerList().getPlayers()) {
    		    if (player.distanceToSqr(pregnantEntity) <= 512.0) { 
    				serverLevel.sendParticles(player, ParticleTypes.FALLING_DRIPSTONE_LAVA, true, pregnantEntity.getX(), (pregnantEntity.getY() + pregnantEntity.getBbHeight() * 0.35), pregnantEntity.getZ(),
    						1, 0, 1, 0, 0.02);
    		    }
    		}
    		MinepreggoMod.LOGGER.debug("Miscarriage in progress: class={}, timer={}", pregnantEntity.getClass().getSimpleName(), pregnantEntity.getPregnancyPainTimer());
        } else {  
        	final var babyItem = PregnancySystemHelper.getDeadBabyItem(pregnantEntity.getTypeOfSpecies(), pregnantEntity.getTypeOfCreature());
        	if (babyItem == null) {
				MinepreggoMod.LOGGER.error("Failed to get dead baby item for miscarriage event. mobId={}, mobClass={}",
						pregnantEntity.getId(), pregnantEntity.getClass().getSimpleName());
			} else {
				ItemStack droppedItem = new ItemStack(babyItem, IBreedable.calculateNumOfBabiesByMaxPregnancyStage(pregnantEntity.getLastPregnancyStage()));
				PreggoMobHelper.setItemstackOnOffHand(pregnantEntity, droppedItem);
			}      	
        	initPostMiscarriage();	 
        	pregnantEntity.discard();
        	MinepreggoMod.LOGGER.debug("Miscarriage completed: id={}, class={}", pregnantEntity.getId(), pregnantEntity.getClass().getSimpleName());
        }	
	}
	
	protected void evaluateCravingTimer() {   				
		if (pregnantEntity.getCraving() < PregnancySystemHelper.MAX_CRAVING_LEVEL) {
	        if (pregnantEntity.getCravingTimer() >= totalTicksOfCraving) {
	        	pregnantEntity.incrementCraving();
	        	pregnantEntity.resetCravingTimer();
	        }
	        else {
	        	pregnantEntity.incrementCravingTimer();
	        }
		}	
	}


	@Override
	protected void evaluatePregnancyPains() {
		if (pregnantEntity.getPregnancyPain() == PregnancyPain.MORNING_SICKNESS) {
			if (pregnantEntity.getPregnancyPainTimer() >= PregnancySystemHelper.TOTAL_TICKS_MORNING_SICKNESS) {
				pregnantEntity.clearPregnancyPain();
				pregnantEntity.resetPregnancyPainTimer();
			} else {
				pregnantEntity.incrementPregnancyPainTimer();
			}
		}
	}
	
	@Override
	protected void evaluatePregnancySymptoms() {	
		if (pregnantEntity.getPregnancySymptoms().contains(PregnancySymptom.CRAVING) && pregnantEntity.getCraving() <= PregnancySystemHelper.DESACTIVATE_CRAVING_SYMPTOM) {
			pregnantEntity.removePregnancySymptom(PregnancySymptom.CRAVING);
			pregnantEntity.clearTypeOfCraving();
		}
	}
	
	@Override
	public void evaluateOnSuccessfulHurt(DamageSource damagesource) {	
		if ((pregnantEntity.hasEffect(MinepreggoModMobEffects.PREGNANCY_RESISTANCE.get()) && randomSource.nextFloat() < 0.9F)
				|| (!damagesource.is(DamageTypes.FALL) && !pregnantEntity.getItemBySlot(EquipmentSlot.CHEST).isEmpty() && randomSource.nextFloat() < 0.5)) {
			return;
		}
		
		int damage = 0;
		var currentPregnancyHealth = pregnantEntity.getPregnancyHealth();
		
		if (pregnantEntity.getHealth() < pregnantEntity.getMaxHealth() / 2F) {
			damage = pregnantEntity.getCurrentPregnancyStage().ordinal() + randomSource.nextInt(3);
				
			if (damagesource.is(DamageTypes.EXPLOSION) || damagesource.is(DamageTypes.PLAYER_EXPLOSION) || damagesource.is(DamageTypes.FALL)) {
				damage *= 2;
			}

			currentPregnancyHealth = Math.max(0, currentPregnancyHealth - damage);
			pregnantEntity.setPregnancyHealth(currentPregnancyHealth);			
		} 
		else if (damagesource.is(DamageTypes.EXPLOSION) || damagesource.is(DamageTypes.PLAYER_EXPLOSION) || damagesource.is(DamageTypes.FALL)) {
			currentPregnancyHealth = Math.max(0, currentPregnancyHealth - 5);
			pregnantEntity.setPregnancyHealth(currentPregnancyHealth);
		}
		
		if (damage > 0) {		
			if (pregnantEntity.getPregnancyHealth() < 40) {
				MessageHelper.warningOwnerPossibleMiscarriageEvent(pregnantEntity);
			}					
			MinepreggoMod.LOGGER.debug("PREGNANCY HEALTH: id={}, class={}, pregnancyHealth={}, damage={}, damageSource={}",
					pregnantEntity.getId(), pregnantEntity.getClass().getSimpleName(), currentPregnancyHealth, damage, damagesource);
		}
	}
	
	@Override
	public boolean hasPregnancyPain() {
	    return pregnantEntity.getPregnancyPain() != null;
	}
	
	@Override
	public boolean hasAllPregnancySymptoms() {
		return pregnantEntity.getPregnancySymptoms().containsAll(validPregnancySymptoms);
	}
	
	@Override
	public boolean isMiscarriageActive() {
	    return pregnantEntity.getPregnancyPain() == PregnancyPain.MISCARRIAGE;
	}
	
	@Override
	protected boolean tryInitRandomPregnancyPain() {
	    if (randomSource.nextFloat() < PregnancySystemHelper.LOW_MORNING_SICKNESS_PROBABILITY) {
	        pregnantEntity.setPregnancyPain(PregnancyPain.MORNING_SICKNESS);
	        pregnantEntity.resetPregnancyPainTimer();
	        return true;
	    }
	    return false;
	}
	
	@Override
	protected boolean tryInitPregnancySymptom() {
		if (pregnantEntity.getCraving() >= PregnancySystemHelper.ACTIVATE_CRAVING_SYMPTOM
				&& !pregnantEntity.getPregnancySymptoms().contains(PregnancySymptom.CRAVING)) {
	    	pregnantEntity.addPregnancySymptom(PregnancySymptom.CRAVING);
	    	pregnantEntity.setTypeOfCraving(PregnancySystemHelper.getRandomCraving(randomSource));
	    	
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy symptom: {}, all pregnancy symptoms: {}",
					pregnantEntity.getSimpleName(), PregnancySymptom.CRAVING.name(), pregnantEntity.getPregnancySymptoms());
	    	return true;		
		}
	    return false;
	}
	
	@Nullable
	protected Result evaluateCraving(Level level, Player source) {
		if (!pregnantEntity.getPregnancySymptoms().contains(PregnancySymptom.CRAVING)) {
			return null;
		}
		
	    var mainHandItem = source.getMainHandItem();
	    var currentCraving = pregnantEntity.getCraving();
	    
	    if (currentCraving > PregnancySystemHelper.DESACTIVATE_CRAVING_SYMPTOM && pregnantEntity.isFood(mainHandItem)) {    	
	    	
	    	if (!pregnantEntity.isValidCraving(mainHandItem.getItem()) || !(mainHandItem.getItem() instanceof IItemCraving)) {
	    		return Result.ANGRY;
	    	}
	    	    	
	    	if (!level.isClientSide) {
				mainHandItem.shrink(1);
	            if (mainHandItem.isEmpty()) {
	            	source.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
	            }        
	            source.getInventory().setChanged();
	    		  		
	    		pregnantEntity.setCraving(currentCraving - ((IItemCraving)mainHandItem.getItem()).getGratification());    
            	level.playSound(null, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace("entity.generic.eat")), SoundSource.NEUTRAL, 0.75f, 1);	          
	    	}
            
            return Result.SUCCESS; 
	    }
	    
	    return null;
	}
}