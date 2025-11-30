package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobPregnancySystemP0;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantPreggoMobSystemP0;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class AbstractTamablePregnantHumanoidCreeperGirl<S extends PregnantPreggoMobSystemP0<?>, P extends PreggoMobPregnancySystemP0<?>> extends AbstractTamablePregnantCreeperGirl<S, P> {

	protected AbstractTamablePregnantHumanoidCreeperGirl(EntityType<? extends AbstractTamableCreeperGirl<?>> p_21803_, Level p_21804_, PregnancyPhase pStage) {
		super(p_21803_, p_21804_, Creature.HUMANOID, pStage);
	}
	
	@Override
	protected boolean canReplaceArmorBasedInPregnancyPhase(ItemStack armor) {	
		final var slot = LivingEntity.getEquipmentSlotForItem(armor);				
		if (slot == EquipmentSlot.CHEST) {
			return PregnancySystemHelper.canUseChestplate(this, armor.getItem(), this.getCurrentPregnancyStage());
		}
		else if (slot == EquipmentSlot.LEGS) {
			return PregnancySystemHelper.canUseLegging(armor.getItem(), this.getCurrentPregnancyStage());
		}
		return true;
	}
}
