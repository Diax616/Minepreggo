package dev.dixmk.minepreggo.world.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class HumanBreastMilkBottleItem extends AbstractBreastMilk {
	public HumanBreastMilkBottleItem() {
		super(3);
	}

	
	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {		
		if (entity instanceof Player && !entity.level().isClientSide) {
			entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 0, false, true));
		}
		return super.finishUsingItem(itemstack, world, entity);
	}
}

