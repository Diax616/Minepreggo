package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class AbstractMonsterPregnantHumanoidCreeperGirl extends AbstractMonsterPregnantCreeperGirl {

	protected AbstractMonsterPregnantHumanoidCreeperGirl(EntityType<? extends AbstractMonsterPregnantCreeperGirl> p_21803_, Level p_21804_, PregnancyPhase currentPregnancyStage) {
		super(p_21803_, p_21804_, Creature.HUMANOID, currentPregnancyStage);
	}
	
	@Override
	protected boolean canReplaceArmorBasedInPregnancyPhase(ItemStack armor) {
		final var slot = armor.getEquipmentSlot();
		if (slot == EquipmentSlot.CHEST) {
			return PregnancySystemHelper.canUseChestplate(armor.getItem(), getPregnancyData().getCurrentPregnancyPhase());
		}
		else if (slot == EquipmentSlot.LEGS) {
			return PregnancySystemHelper.canUseLegging(armor.getItem(), getPregnancyData().getCurrentPregnancyPhase());
		}
		return true;
	}
}
