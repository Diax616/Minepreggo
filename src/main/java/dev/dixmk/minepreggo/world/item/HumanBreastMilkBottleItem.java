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
		var result = super.finishUsingItem(itemstack, world, entity);
		if (!entity.level().isClientSide) {
			if (entity instanceof Player) {
				entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 120, 0, false, true, true));
			}
			else {
				entity.heal(2f);
			}		
		}	
		return result;
	}
}

