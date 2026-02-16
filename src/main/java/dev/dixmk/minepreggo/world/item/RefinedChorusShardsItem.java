package dev.dixmk.minepreggo.world.item;

import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ChorusFruitItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class RefinedChorusShardsItem extends ChorusFruitItem {

	public RefinedChorusShardsItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON).food((new FoodProperties.Builder()).nutrition(8).alwaysEat().saturationMod(0.3f).build()));
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 40;
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {	
		if (player.hasEffect(MinepreggoModMobEffects.FULL_OF_ENDERS.get()) ||
				player.hasEffect(MinepreggoModMobEffects.ENDER_DRAGON_ESSENCE.get()) ||
				player.hasEffect(MinepreggoModMobEffects.ENDER_ESSENCE.get())) {
			return super.use(level, player, hand);
		}	
		return InteractionResultHolder.fail(player.getItemInHand(hand));
	}
	
	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {		
		var result = super.finishUsingItem(itemstack, world, entity);		
		if (!entity.level().isClientSide) {	
			entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 0, false, true));
		}
		return result;
	}
}
