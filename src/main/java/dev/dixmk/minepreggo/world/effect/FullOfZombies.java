package dev.dixmk.minepreggo.world.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

public class FullOfZombies extends MobEffect {

	public FullOfZombies() {
		super(MobEffectCategory.HARMFUL, -16751053);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {	
        if (entity.getItemBySlot(EquipmentSlot.HEAD).isEmpty() && !entity.isOnFire() && tryBurn(entity)) {
         	 entity.setSecondsOnFire(8);
        }    
	}
	
	@SuppressWarnings("deprecation")
	private boolean tryBurn(LivingEntity entity) {
		if (entity.level().isDay() && !entity.level().isClientSide) {
			float f = entity.getLightLevelDependentMagicValue();
			BlockPos blockpos = BlockPos.containing(entity.getX(), entity.getEyeY(), entity.getZ());
			boolean flag = entity.isInWaterRainOrBubble() || entity.isInPowderSnow || entity.wasInPowderSnow;
			if (f > 0.5F && entity.getRandom().nextFloat() * 30.0F < (f - 0.4F) * 2.0F && !flag && entity.level().canSeeSky(blockpos)) {
				return true;
			}
		}

		return false;
	}
	
	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
