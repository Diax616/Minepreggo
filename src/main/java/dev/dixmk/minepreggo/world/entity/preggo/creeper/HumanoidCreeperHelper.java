package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.ai.goal.BreakBlocksToFollowOwnerGoal;
import dev.dixmk.minepreggo.world.entity.ai.goal.EatGoal;
import dev.dixmk.minepreggo.world.entity.ai.goal.GoalHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Inventory;
import dev.dixmk.minepreggo.world.entity.preggo.InventorySlotMapper;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class HumanoidCreeperHelper {

	static final String SIMPLE_NAME = "Humanoid Creeper Girl";
	
	private HumanoidCreeperHelper() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}
	
	static Inventory createInventory() {
		return new Inventory(InventorySlotMapper.createHumanoidDefault(), 6);
	}
	
	static AttributeSupplier.Builder createBasicAttributes(double movementSpeed) {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 22D)
				.add(Attributes.ATTACK_DAMAGE, 2D)
				.add(Attributes.FOLLOW_RANGE, 35D)
				.add(Attributes.MOVEMENT_SPEED, movementSpeed);
	}
	
	public static EntityType<? extends AbstractTamablePregnantHumanoidCreeperGirl> getEntityType(PregnancyPhase phase) {	
		switch (phase) {
			case P0 -> MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P0.get();
			case P1 -> MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P1.get();
			case P2 -> MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P2.get();
			case P3 -> MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P3.get();
			case P4 -> MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P4.get();
			case P5 -> MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P5.get();
			case P6 -> MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P6.get();
			case P7 -> MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P7.get();	
			case P8 -> MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P8.get();	
		}
		throw new IllegalArgumentException("Unexpected value: " + phase);	
	}
	
	static void reassessTameGoals(AbstractTamableCreeperGirl creeperGirl) {
		if (creeperGirl.isTame()) {	
			GoalHelper.addGoalWithReplacement(creeperGirl, 4, new BreakBlocksToFollowOwnerGoal<>(creeperGirl, 2, 5), true);
			GoalHelper.addGoalWithReplacement(creeperGirl, 6, new EatGoal<>(creeperGirl, 0.6F, 20));
		}
		else {
			GoalHelper.removeGoalByClass(creeperGirl.targetSelector, BreakBlocksToFollowOwnerGoal.class);
			GoalHelper.removeGoalByClass(creeperGirl.goalSelector, EatGoal.class);
		}	
	}
	
	static void reassessTameGoalsBeingPregnant(AbstractTamablePregnantCreeperGirl creeperGirl) {
		if (creeperGirl.isTame()) {	
			GoalHelper.addGoalWithReplacement(creeperGirl, 4, new BreakBlocksToFollowOwnerGoal<>(creeperGirl, 2, 7) {
				@Override
				public boolean canUse() {
					return super.canUse() 
					&& !creeperGirl.getPregnancyData().isIncapacitated();
				}

				@Override
				public boolean canContinueToUse() {
					return super.canContinueToUse()
					&& !creeperGirl.getPregnancyData().isIncapacitated();
				}
			}, true);	
			GoalHelper.addGoalWithReplacement(creeperGirl, 6, new EatGoal<>(creeperGirl, 0.6F, 30) {
				@Override
				public boolean canUse() {
					return super.canUse() 	
					&& !creeperGirl.getPregnancyData().isIncapacitated();
				}
				
				@Override
				public boolean canContinueToUse() {
					return super.canContinueToUse() 
					&& !creeperGirl.getPregnancyData().isIncapacitated();
				}
			});
		}
		else {
			GoalHelper.removeGoalByClass(creeperGirl.targetSelector, BreakBlocksToFollowOwnerGoal.class);
			GoalHelper.removeGoalByClass(creeperGirl.goalSelector, EatGoal.class);
		}
	}

	static void addTamablePregnantBehaviourGoals(AbstractTamablePregnantHumanoidCreeperGirl creeperGirl) {
		creeperGirl.targetSelector.addGoal(4, new BreakBlocksToFollowOwnerGoal<>(creeperGirl, 2, 7) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !creeperGirl.getPregnancyData().isIncapacitated();
			}

			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse()
				&& !creeperGirl.getPregnancyData().isIncapacitated();
			}
		});	

		creeperGirl.goalSelector.addGoal(6, new EatGoal<>(creeperGirl, 0.6F, 30) {
			@Override
			public boolean canUse() {
				return super.canUse() 	
				&& !creeperGirl.getPregnancyData().isIncapacitated();
			}
			
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() 
				&& !creeperGirl.getPregnancyData().isIncapacitated();
			}
		});
	}
}
