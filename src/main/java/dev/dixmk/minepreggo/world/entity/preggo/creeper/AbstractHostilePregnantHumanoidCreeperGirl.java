package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class AbstractHostilePregnantHumanoidCreeperGirl extends AbstractHostilePregnantCreeperGirl {

	protected AbstractHostilePregnantHumanoidCreeperGirl(EntityType<? extends AbstractHostilePregnantCreeperGirl> entityType, Level level, PregnancyPhase currentPregnancyStage) {
		super(entityType, level, Creature.HUMANOID, currentPregnancyStage);
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
	
	@Override
	public String getSimpleName() {
		return HumanoidCreeperHelper.SIMPLE_NAME;
	}
	
	@Override
	public SoundEvent getDeathSound() {
		return MinepreggoModSounds.PREGNANT_PREGGO_MOB_DEATH.get();
	}
	
	@Override
	protected ExplosionData updateExplosionByPregnancyPhase(PregnancyPhase pregnancyPhase) {
		return HumanoidCreeperHelper.getExplosionValuesByPregnancyPhase(pregnancyPhase);
	}
}
