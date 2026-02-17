package dev.dixmk.minepreggo.world.item;

import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EnderBreastMilkBottleItem extends AbstractBreastMilk {
	
	public EnderBreastMilkBottleItem() {
		super(5);
	}
	
	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {		
		var result = super.finishUsingItem(itemstack, world, entity);
		if (!entity.level().isClientSide) {	
			if (entity.hasEffect(MinepreggoModMobEffects.ENDER_ESSENCE.get()) || entity.hasEffect(MinepreggoModMobEffects.ENDER_DRAGON_ESSENCE.get())) {
				entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 0));
			}
			else if (entity instanceof PreggoMob preggoMob && preggoMob.getTypeOfSpecies() == Species.ENDER) {	
				entity.heal(4f);
			}
			else if (entity instanceof Player && entity.hasEffect(MinepreggoModMobEffects.FULL_OF_ENDERS.get())) {
				entity.heal(2f);
			}
			else {
				LivingEntityHelper.randomTeleport(entity, SoundEvents.ENDERMAN_TELEPORT, 10, 20);
			}
		}		
		return result;
	}
}
