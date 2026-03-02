package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.Inventory;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class AbstractTamableMonsterCreeperGirl extends AbstractTamableCreeperGirl {

	protected AbstractTamableMonsterCreeperGirl(EntityType<? extends AbstractTamableCreeperGirl> entityType, Level level) {
		super(entityType, level, Creature.MONSTER);
	}
	
	@Override
	protected boolean canReplaceArmorBasedInPregnancyPhase(ItemStack armor) {				
		return false;
	}
	
	@Override
	public SoundEvent getHurtSound(DamageSource damageSource) {
		return SoundEvents.CREEPER_HURT;
	}
	
	@Override
	public SoundEvent getDeathSound() {
		return SoundEvents.CREEPER_DEATH;
	}
	
	@Override
	protected Inventory createInventory() {
		return MonsterCreeperHelper.createInventory();
	}
	
	@Override
	public String getSimpleName() {
		return MonsterCreeperHelper.SIMPLE_NAME;
	}
	
	@Override
	protected void reassessTameGoals() {
		super.reassessTameGoals();
		MonsterCreeperHelper.reassessTameGoals(this);
	}
}
