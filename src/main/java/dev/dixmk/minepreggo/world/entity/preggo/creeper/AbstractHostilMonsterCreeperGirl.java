package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class AbstractHostilMonsterCreeperGirl extends AbstractHostilCreeperGirl {

	protected AbstractHostilMonsterCreeperGirl(EntityType<? extends AbstractHostilCreeperGirl> p_21803_, Level p_21804_) {
		super(p_21803_, p_21804_, Creature.MONSTER);
		this.setCanPickUpLoot(false);
	}

	@Override
	public boolean canBeTamedByPlayer() {
		return false;
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
	
	@Override
	public String getSimpleName() {
		return MonsterCreeperHelper.SIMPLE_NAME;
	}
}
