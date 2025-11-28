package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class AbstractMonsterQuadrupedCreeperGirl extends AbstractMonsterCreeperGirl {

	protected AbstractMonsterQuadrupedCreeperGirl(EntityType<? extends AbstractMonsterCreeperGirl> p_21803_, Level p_21804_) {
		super(p_21803_, p_21804_, Creature.MONSTER);
		this.setCanPickUpLoot(false);
	}

	protected static AttributeSupplier.Builder getBasicAttributes(double movementSpeed) {
		return Mob.createMobAttributes()
		.add(Attributes.MAX_HEALTH, 24)
		.add(Attributes.ATTACK_DAMAGE, 3)
		.add(Attributes.FOLLOW_RANGE, 35)
		.add(Attributes.MOVEMENT_SPEED, movementSpeed);
	}
	
	public static AttributeSupplier.Builder createDefaultAttributes() {
		return getBasicAttributes(0.24);
	}
	
	@Override
	public boolean hasCustomHeadAnimation() {
		return false;
	}
	
	@Override
	public SoundEvent getHurtSound(DamageSource p_32309_) {
		return SoundEvents.CREEPER_HURT;
	}
	
	@Override
	public SoundEvent getDeathSound() {
		return SoundEvents.CREEPER_DEATH;
	}
	
	@Override
	protected boolean canReplaceArmorBasedInPregnancyPhase(ItemStack armor) {
		return false;
	}
}
