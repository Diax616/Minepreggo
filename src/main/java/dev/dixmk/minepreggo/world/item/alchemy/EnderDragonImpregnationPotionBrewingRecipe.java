package dev.dixmk.minepreggo.world.item.alchemy;

import dev.dixmk.minepreggo.init.MinepreggoModPotions;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class EnderDragonImpregnationPotionBrewingRecipe implements IBrewingRecipe {
	
	@Override
	public boolean isIngredient(ItemStack ingredient) {
		return ingredient.is(Items.DRAGON_EGG);
	}

	@Override
	public boolean isInput(ItemStack input) {
		Item inputItem = input.getItem();
		return (inputItem == Items.POTION || inputItem == Items.SPLASH_POTION || inputItem == Items.LINGERING_POTION) && PotionUtils.getPotion(input) == MinepreggoModPotions.ENDER_POWERFUL_IMPREGNATION.get();
	}

	@Override
	public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
		if (isInput(input) && isIngredient(ingredient)) {
			return PotionUtils.setPotion(new ItemStack(input.getItem()), MinepreggoModPotions.ENDER_DRAGON_IMPREGNATION.get());
		}
		return ItemStack.EMPTY;
	}
}
