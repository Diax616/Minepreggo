package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.Inventory;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class AbstractTamableHumanoidCreeperGirl extends AbstractTamableCreeperGirl {

	protected AbstractTamableHumanoidCreeperGirl(EntityType<? extends AbstractTamableCreeperGirl> p_21803_, Level p_21804_) {
		super(p_21803_, p_21804_, Creature.HUMANOID);
	}

	@Override
	protected boolean canReplaceArmorBasedInPregnancyPhase(ItemStack armor) {	
		if (LivingEntity.getEquipmentSlotForItem(armor) == EquipmentSlot.CHEST) {
			return PreggoMobHelper.canUseChestPlateInLactation(this.getGenderedData(), armor.getItem());
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
		HumanoidCreeperHelper.reassessTameGoals(this);
	}
	
	@Override
	public String getSimpleName() {
		return HumanoidCreeperHelper.SIMPLE_NAME;
	}
}