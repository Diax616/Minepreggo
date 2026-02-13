package dev.dixmk.minepreggo.world.item.alchemy;

import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.init.MinepreggoModPotions;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class ZeroGravityBellyPotionBrewingRecipe implements IBrewingRecipe {
	@Override
	public boolean isInput(ItemStack input) {
		Item inputItem = input.getItem();
		Potion potion = PotionUtils.getPotion(input);
		return (inputItem == Items.POTION || inputItem == Items.SPLASH_POTION || inputItem == Items.LINGERING_POTION) && (potion == Potions.SLOW_FALLING || potion == Potions.LONG_SLOW_FALLING);
	}

	@Override
	public boolean isIngredient(ItemStack ingredient) {
		return ingredient.is(MinepreggoModItems.HUMAN_BREAST_MILK_BOTTLE.get());
	}

	@Override
	public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
		if (isInput(input) && isIngredient(ingredient)) {
			return PotionUtils.setPotion(new ItemStack(input.getItem()), MinepreggoModPotions.ZERO_GRAVITY_BELLY.get());
		}
		return ItemStack.EMPTY;
	}
}
