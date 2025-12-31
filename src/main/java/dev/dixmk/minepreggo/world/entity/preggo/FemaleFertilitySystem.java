package dev.dixmk.minepreggo.world.entity.preggo;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem.Result;
import dev.dixmk.minepreggo.world.pregnancy.FemaleEntityImpl;
import dev.dixmk.minepreggo.world.pregnancy.IFemaleEntity;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancy;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.ItemHandlerHelper;

public abstract class FemaleFertilitySystem<E extends PreggoMob & IFemaleEntity & ITamablePreggoMob<FemaleEntityImpl>> extends FertilitySystem<E> {

	protected int discomfortTick = 0;
	
	protected FemaleFertilitySystem(E preggoMob) {
		super(preggoMob);
	}

	@Override
	protected void evaluateFertilitySystem() {
		if (preggoMob.isPregnant()) {			
			if (!isExperiencingDiscomfort()) {			
				if (discomfortTick > 20) {
					tryStartRandomDiscomfort();
					discomfortTick = 0;
				}
				else {
					++discomfortTick;
				}
			}	
			evaluatePregnancyInitializerTimer();
		} 
		else {
			evaluatePostPregnancy();
			super.evaluateFertilitySystem();
		}
	}
	
	public InteractionResult onRightClick(Player source) {
		if (!preggoMob.isOwnedBy(source)) {
			return InteractionResult.PASS;
		}	
		return evaluatePostPartumLactation(source) != null ? InteractionResult.SUCCESS : InteractionResult.PASS;
	}
	
	protected Result evaluatePostPartumLactation(Player source) {
	    
		var result = preggoMob.getPostPregnancyData().map(post -> {
				if (post.getPostPregnancy() == PostPregnancy.PARTUM) {
					return post.getPostPartumLactation();
				}
				return null;
			});
		
		if (result.isPresent()) {
		    var mainHandItem = source.getMainHandItem();
		    int currentMilking = result.get();
			
		    if (currentMilking < PregnancySystemHelper.MILKING_VALUE || mainHandItem.isEmpty() || mainHandItem.getItem() != Items.GLASS_BOTTLE) {   
		    	return null;
		    }
		    
		    if (preggoMob.level().isClientSide) {
	        	preggoMob.playSound(SoundEvents.COW_MILK, 0.8F, 0.8F + preggoMob.getRandom().nextFloat() * 0.3F);
		    }
		    
	        if (!preggoMob.level().isClientSide) {    	
	        	MinepreggoMod.LOGGER.debug("{} {}", mainHandItem, mainHandItem.getCount());
	
	            currentMilking = Math.max(0, currentMilking - PregnancySystemHelper.MILKING_VALUE);
	          
	            // Brigde Server - Client
	            preggoMob.setPostPartumLactation(currentMilking);               
	            	          
	            var milkItem = PregnancySystemHelper.getMilkItem(preggoMob.getTypeOfSpecies());
	           
	            if (milkItem != null) {
	            	ItemHandlerHelper.giveItemToPlayer(source, new ItemStack(milkItem));
				} else {
					MinepreggoMod.LOGGER.warn("Milk item is null for species: {}", preggoMob.getTypeOfSpecies());
				}
	        	
	            mainHandItem.shrink(1);
	            
	            if (mainHandItem.isEmpty()) {
	                source.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
	            }        
	            source.getInventory().setChanged();
	                      
	        	MinepreggoMod.LOGGER.debug("{} {}", mainHandItem, mainHandItem.getCount());
	        }
	     
	        return Result.SUCCESS; 
		}

		return null;
	}
	
	
	
	protected void evaluatePregnancyInitializerTimer() {			    	
        if (preggoMob.getPregnancyInitializerTimer() >= MinepreggoModConfig.getTicksToStartPregnancy()) {
        	startPregnancy();
        	preggoMob.discard();
        } else {
        	preggoMob.incrementPregnancyInitializerTimer();
        } 
	}
	
	protected void evaluatePostPregnancy() {
		var result = preggoMob.getPostPregnancyData().map(post -> {
					if (post.getPostPregnancyTimer() > MinepreggoModConfig.getTotalTicksOfPostPregnancyPhase()) {
						post.resetPostPregnancyTimer();					
						if (post.getPostPregnancy() == PostPregnancy.PARTUM) {				
							
							// It uses a EntityDataAccessor like bridge to sync the data between server and client
							preggoMob.setPostPartumLactation(0);
						}	
						return true;
					}
					else {
						post.incrementPostPregnancyTimer();
					}
					
					if (post.getPostPregnancy() == PostPregnancy.PARTUM) {
						var lactation = post.getPostPartumLactation();				
						if (lactation < PregnancySystemHelper.MAX_MILKING_LEVEL) {
							if (post.getPostPartumLactationTimer() > MinepreggoModConfig.getTotalTicksOfMaternityLactation()) {
								post.resetPostPartumLactationTimer();
								++lactation;
								
								// It uses a EntityDataAccessor like bridge to sync the data between server and client
								preggoMob.setPostPartumLactation(lactation);
								
								if (lactation >= PregnancySystemHelper.ACTIVATE_MILKING_SYMPTOM) {
									PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(preggoMob, EquipmentSlot.CHEST);
								}						
							}
							else {
								post.incrementPostPartumLactationTimer();
							}
						}
					}
					return false;
				});
		
		result.ifPresent(isEnd -> {
			if (isEnd) {
				if (!preggoMob.tryRemovePostPregnancyPhase()) {
					MinepreggoMod.LOGGER.error("Failed to remove post pregnancy phase from entity {}", preggoMob.getDisplayName().getString());
				}
				else {
					afterPostPregnancy();
				}
			}
		});	
	}
	
	protected boolean tryStartRandomDiscomfort() {
        if (randomSource.nextFloat() < 0.005F && !preggoMob.hasEffect(MobEffects.CONFUSION)) {
        	preggoMob.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 300, 0, false, true, true));                 	
        	return true;
        }    
        return false;
	}
	
	protected boolean isExperiencingDiscomfort() {
		return preggoMob.hasEffect(MobEffects.CONFUSION);
	}
	
	protected abstract void startPregnancy();
	
	protected abstract void afterPostPregnancy();
}
