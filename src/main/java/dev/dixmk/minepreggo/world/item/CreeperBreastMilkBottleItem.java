package dev.dixmk.minepreggo.world.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;

public class CreeperBreastMilkBottleItem extends AbstractBreastMilk {
	public CreeperBreastMilkBottleItem() {
		super();
	}


	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		if (entity.level() instanceof ServerLevel serverLevel
				&& !entity.hasEffect(MinepreggoModMobEffects.FULL_OF_CREEPERS.get())
				&& world.random.nextFloat() < 0.35F) {
			serverLevel.explode(entity, entity.getX(), entity.getY(), entity.getZ(), 1, Level.ExplosionInteraction.MOB);		
		}
	
		return super.finishUsingItem(itemstack, world, entity);
	}
}
