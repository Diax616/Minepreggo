package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class AbstractTamableHumanoidCreeperGirl extends AbstractTamableCreeperGirl {

	protected AbstractTamableHumanoidCreeperGirl(EntityType<? extends PreggoMob> p_21803_, Level p_21804_) {
		super(p_21803_, p_21804_, Creature.HUMANOID);
	}

	public static AttributeSupplier.Builder getBasicAttributes(double movementSpeed) {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 22D)
				.add(Attributes.ATTACK_DAMAGE, 2D)
				.add(Attributes.FOLLOW_RANGE, 35D)
				.add(Attributes.MOVEMENT_SPEED, movementSpeed);
	}
	
	@Override
	protected boolean canReplaceArmorBasedInPregnancyPhase(ItemStack armor) {	
		if (LivingEntity.getEquipmentSlotForItem(armor) == EquipmentSlot.CHEST) {
			return PreggoMobHelper.canUseChestPlateInLactation(this.getGenderedData(), armor.getItem());
		}			
		return true;
	}
}