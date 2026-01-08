package dev.dixmk.minepreggo.world.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class CreeperBreastMilkBottleItem extends AbstractBreastMilk {
	public CreeperBreastMilkBottleItem() {
		super();
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {		
		var result = super.finishUsingItem(itemstack, world, entity);		
		if (!entity.level().isClientSide) {		
			if (entity instanceof PreggoMob preggoMob && preggoMob.getTypeOfSpecies() == Species.CREEPER) {
				preggoMob.heal(4f);	
			}
			else if (entity instanceof Player && entity.hasEffect(MinepreggoModMobEffects.FULL_OF_CREEPERS.get())) {
				entity.heal(2f);
			}
			else if (entity.level() instanceof ServerLevel serverLevel) {
				serverLevel.explode(null, entity.getX(), entity.getY(), entity.getZ(), 1, Level.ExplosionInteraction.MOB);		
			}	
		}
		return result;
	}
}
