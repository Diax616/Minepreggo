package dev.dixmk.minepreggo.world.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class ZombieBreastMilkBottleItem extends AbstractBreastMilk {
	public ZombieBreastMilkBottleItem() {
		super();
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		if (!entity.hasEffect(MinepreggoModMobEffects.FULL_OF_ZOMBIES.get()) && !entity.level().isClientSide()) {
			entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 200, 0, false, true));
			entity.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 0, false, true));
		}
		
		return super.finishUsingItem(itemstack, world, entity);
	}
}
