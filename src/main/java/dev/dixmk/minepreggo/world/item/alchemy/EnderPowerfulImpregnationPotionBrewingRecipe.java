package dev.dixmk.minepreggo.world.item.alchemy;

import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.init.MinepreggoModPotions;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class EnderPowerfulImpregnationPotionBrewingRecipe implements IBrewingRecipe {
	
	@Override
	public boolean isIngredient(ItemStack ingredient) {
		return ingredient.is(MinepreggoModItems.CONCENTRATED_ENDER_LIFE_SUBSTANCE.get());
	}

	@Override
	public boolean isInput(ItemStack input) {
		Item inputItem = input.getItem();
		Potion potion = PotionUtils.getPotion(input);
		boolean flag = (potion == MinepreggoModPotions.IMPREGNATION_POTION_0.get() || potion == MinepreggoModPotions.IMPREGNATION_POTION_1.get() || potion == MinepreggoModPotions.IMPREGNATION_POTION_2.get() || potion == MinepreggoModPotions.IMPREGNATION_POTION_3.get() || potion == MinepreggoModPotions.IMPREGNATION_POTION_4.get());
		return (inputItem == Items.POTION || inputItem == Items.SPLASH_POTION || inputItem == Items.LINGERING_POTION) && flag;
	}

	@Override
	public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
		if (isInput(input) && isIngredient(ingredient)) {
			return PotionUtils.setPotion(new ItemStack(input.getItem()), MinepreggoModPotions.ENDER_POWERFUL_IMPREGNATION.get());
		}
		return ItemStack.EMPTY;
	}
}
