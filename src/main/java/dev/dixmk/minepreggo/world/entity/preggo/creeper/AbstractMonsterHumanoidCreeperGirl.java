package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.world.item.ItemHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class AbstractMonsterHumanoidCreeperGirl extends AbstractMonsterCreeperGirl {

	protected AbstractMonsterHumanoidCreeperGirl(EntityType<? extends PreggoMob> p_21803_, Level p_21804_) {
		super(p_21803_, p_21804_);
		this.typeOfCreature = Creature.HUMANOID;
	}

	public static AttributeSupplier.Builder getBasicAttributes(double movementSpeed) {
		return Mob.createMobAttributes()
		.add(Attributes.MAX_HEALTH, 20)
		.add(Attributes.ATTACK_DAMAGE, 2)
		.add(Attributes.FOLLOW_RANGE, 35)
		.add(Attributes.MOVEMENT_SPEED, movementSpeed);
	}
	
	@Override
	public boolean hasCustomHeadAnimation() {
		return false;
	}
	
	@Override
	protected boolean canReplaceArmorBasedInPregnancyStage(ItemStack armor) {
		return (ItemHelper.isChest(armor) && PreggoMobHelper.canUseChestplate(armor, PregnancyStage.getNonPregnancyStage()))
				|| (ItemHelper.isLegging(armor) && PreggoMobHelper.canUseLegging(armor, PregnancyStage.getNonPregnancyStage()));
	}
}
