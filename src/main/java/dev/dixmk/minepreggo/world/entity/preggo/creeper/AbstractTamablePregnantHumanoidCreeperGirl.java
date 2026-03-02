package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.Inventory;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class AbstractTamablePregnantHumanoidCreeperGirl extends AbstractTamablePregnantCreeperGirl {

	protected AbstractTamablePregnantHumanoidCreeperGirl(EntityType<? extends AbstractTamableCreeperGirl> entityType, Level level, PregnancyPhase pStage) {
		super(entityType, level, Creature.HUMANOID, pStage);
	}
	
	@Override
	protected boolean canReplaceArmorBasedInPregnancyPhase(ItemStack armor) {	
		final var slot = LivingEntity.getEquipmentSlotForItem(armor);				
		if (slot == EquipmentSlot.CHEST) {
			return PregnancySystemHelper.canUseChestplate(armor.getItem(), this.pregnancyData.getCurrentPregnancyPhase(), false) && PreggoMobHelper.canUseChestPlateInLactation(this, armor.getItem());
		}
		else if (slot == EquipmentSlot.LEGS) {
			return PregnancySystemHelper.canUseLegging(armor.getItem(), this.pregnancyData.getCurrentPregnancyPhase());
		}
		return true;
	}

	@Override
	protected Inventory createInventory() {
		return HumanoidCreeperHelper.createInventory();
	}

	@Override
	protected void reassessTameGoals() {
		super.reassessTameGoals();
		HumanoidCreeperHelper.reassessTameGoalsBeingPregnant(this);
	}
	
	@Override
	public String getSimpleName() {
		return HumanoidCreeperHelper.SIMPLE_NAME;
	}
	
	@Override
	public SoundEvent getDeathSound() {
		return MinepreggoModSounds.PREGNANT_PREGGO_MOB_DEATH.get();
	}
	
	@Override
	protected ExplosionData updateExplosionByPregnancyPhase(PregnancyPhase pregnancyPhase) {
		return HumanoidCreeperHelper.getExplosionValuesByPregnancyPhase(pregnancyPhase);
	}
}
