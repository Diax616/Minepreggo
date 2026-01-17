package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class AbstractTamablePregnantHumanoidCreeperGirl extends AbstractTamablePregnantCreeperGirl {

	protected AbstractTamablePregnantHumanoidCreeperGirl(EntityType<? extends AbstractTamableCreeperGirl> p_21803_, Level p_21804_, PregnancyPhase pStage) {
		super(p_21803_, p_21804_, Creature.HUMANOID, pStage);
	}
	
	@Override
	protected boolean canReplaceArmorBasedInPregnancyPhase(ItemStack armor) {	
		final var slot = LivingEntity.getEquipmentSlotForItem(armor);				
		if (slot == EquipmentSlot.CHEST) {
			return PregnancySystemHelper.canUseChestplate(armor.getItem(), this.tamablePregnantPreggoMobData.getCurrentPregnancyStage(), false) && PreggoMobHelper.canUseChestPlateInLactation(this, armor.getItem());
		}
		else if (slot == EquipmentSlot.LEGS) {
			return PregnancySystemHelper.canUseLegging(armor.getItem(), this.tamablePregnantPreggoMobData.getCurrentPregnancyStage());
		}
		return true;
	}
	
	public static EntityType<? extends AbstractTamablePregnantHumanoidCreeperGirl> getEntityType(PregnancyPhase phase) {	
		switch (phase) {
		case P0: {
			return MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P0.get();
		}
		case P1: {
			return MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P1.get();
		}
		case P2: {
			return MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P2.get();
		}
		case P3: {
			return MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P3.get();
		}
		case P4: {
			return MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P4.get();
		}
		case P5: {
			return MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P5.get();
		}
		case P6: {
			return MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P6.get();
		}
		case P7: {
			return MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P7.get();
		}
		case P8: {
			return MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P8.get();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + phase);
		}		
	}
}
