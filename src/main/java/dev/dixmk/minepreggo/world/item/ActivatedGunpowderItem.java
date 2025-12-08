package dev.dixmk.minepreggo.world.item;

import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class ActivatedGunpowderItem extends Item {
	public ActivatedGunpowderItem(int nutritionValue) {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(nutritionValue).saturationMod(0.1f).build()));
	}
	
	public ActivatedGunpowderItem() {
		this(5);
	}
	
	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 60;
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {	
		if (!player.hasEffect(MinepreggoModMobEffects.FULL_OF_CREEPERS.get())) {
			return InteractionResultHolder.fail(player.getItemInHand(hand));
		}
		return super.use(level, player, hand);
	}
}
