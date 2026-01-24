package dev.dixmk.minepreggo.world.entity;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class LivingEntityHelper {

	private LivingEntityHelper() {}
	
	
	public static void copyMobEffects(LivingEntity from, LivingEntity to) {
		from.getActiveEffects().forEach(effect -> to.addEffect(new MobEffectInstance(effect)));
	}
	
}
