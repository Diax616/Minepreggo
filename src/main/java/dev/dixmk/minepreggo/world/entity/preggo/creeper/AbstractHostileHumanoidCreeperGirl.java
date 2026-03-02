package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class AbstractHostileHumanoidCreeperGirl extends AbstractHostileCreeperGirl {

	protected AbstractHostileHumanoidCreeperGirl(EntityType<? extends AbstractHostileCreeperGirl> entityType, Level level) {
		super(entityType, level, Creature.HUMANOID);
	}
	
	@Override
	public boolean hasCustomHeadAnimation() {
		return false;
	}
	
	@Override
	protected boolean canReplaceArmorBasedInPregnancyPhase(ItemStack armor) {
		if (armor.getEquipmentSlot() == EquipmentSlot.CHEST) {
			return PreggoMobHelper.canUseChestplate(armor.getItem());
		}			
		return true;
	}
	
	@Override
	public String getSimpleName() {
		return HumanoidCreeperHelper.SIMPLE_NAME;
	}
}
