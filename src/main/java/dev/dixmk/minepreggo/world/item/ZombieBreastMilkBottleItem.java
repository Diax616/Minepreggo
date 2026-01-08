package dev.dixmk.minepreggo.world.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class ZombieBreastMilkBottleItem extends AbstractBreastMilk {
	public ZombieBreastMilkBottleItem() {
		super();
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {		
		var result = super.finishUsingItem(itemstack, world, entity);
		if (!entity.level().isClientSide) {		
			if (entity instanceof PreggoMob preggoMob && preggoMob.getTypeOfSpecies() == Species.ZOMBIE) {	
				entity.heal(4f);
			}
			else if (entity instanceof Player && entity.hasEffect(MinepreggoModMobEffects.FULL_OF_ZOMBIES.get())) {
				entity.heal(2f);
			}
			else {
				entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 200, 0, false, true, true));
				entity.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 1, false, true, true));
			}
		}		
		return result;
	}
}
