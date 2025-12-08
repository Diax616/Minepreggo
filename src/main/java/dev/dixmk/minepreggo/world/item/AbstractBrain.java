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

public abstract class AbstractBrain extends Item {

    protected AbstractBrain(int nutritionValue) {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON).food((new FoodProperties.Builder()).nutrition(nutritionValue).saturationMod(0.2f).build()));
	}

    protected AbstractBrain() {
    	this(7);
    }
     
	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 40;
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {	
		if (!player.hasEffect(MinepreggoModMobEffects.FULL_OF_ZOMBIES.get())) {
			return InteractionResultHolder.fail(player.getItemInHand(hand));
		}
		return super.use(level, player, hand);
	}
}
