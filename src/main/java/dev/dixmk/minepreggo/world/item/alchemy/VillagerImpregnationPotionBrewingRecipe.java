package dev.dixmk.minepreggo.world.item.alchemy;

import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.init.MinepreggoModPotions;
import dev.dixmk.minepreggo.world.item.CumSpecimenTubeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public abstract class VillagerImpregnationPotionBrewingRecipe implements IBrewingRecipe {

	/*
	 * tryTransferOwner from CumSpecimenTubeItem is used to transfer the owner data from input to output potions.
	 * The mechanics is not yet implemented, there is no effect of owner data on potions yet.
	 * */
	
	private VillagerImpregnationPotionBrewingRecipe() {}
	
	@Override
	public boolean isIngredient(ItemStack ingredient) {
		return ingredient.is(MinepreggoModItems.VILLAGER_LIFE_SUBSTANCE.get());
	}
	
	public static class A0 extends VillagerImpregnationPotionBrewingRecipe {		
		@Override
		public boolean isInput(ItemStack input) {
			Item inputItem = input.getItem();
			return (inputItem == Items.POTION || inputItem == Items.SPLASH_POTION || inputItem == Items.LINGERING_POTION) && PotionUtils.getPotion(input) == MinepreggoModPotions.IMPREGNATION_POTION_0.get();
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				var result = PotionUtils.setPotion(new ItemStack(input.getItem()), MinepreggoModPotions.VILLAGER_IMPREGNATION_0.get());
				CumSpecimenTubeItem.tryTransferOwner(input, result);
				return result;
			}
			return ItemStack.EMPTY;
		}				
	}
	
	public static class A1 extends VillagerImpregnationPotionBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) {
			Item inputItem = input.getItem();
			return (inputItem == Items.POTION || inputItem == Items.SPLASH_POTION || inputItem == Items.LINGERING_POTION) && PotionUtils.getPotion(input) == MinepreggoModPotions.IMPREGNATION_POTION_1.get();
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				var result = PotionUtils.setPotion(new ItemStack(input.getItem()), MinepreggoModPotions.VILLAGER_IMPREGNATION_1.get());
				CumSpecimenTubeItem.tryTransferOwner(input, result);
				return result;
			}
			return ItemStack.EMPTY;
		}	
	}
	
	public static class A2 extends VillagerImpregnationPotionBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) {
			Item inputItem = input.getItem();
			return (inputItem == Items.POTION || inputItem == Items.SPLASH_POTION || inputItem == Items.LINGERING_POTION) && PotionUtils.getPotion(input) == MinepreggoModPotions.IMPREGNATION_POTION_2.get();
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				var result = PotionUtils.setPotion(new ItemStack(input.getItem()), MinepreggoModPotions.VILLAGER_IMPREGNATION_2.get());
				CumSpecimenTubeItem.tryTransferOwner(input, result);
				return result;
			}
			return ItemStack.EMPTY;
		}	
	}
	
	public static class A3 extends VillagerImpregnationPotionBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) {
			Item inputItem = input.getItem();
			return (inputItem == Items.POTION || inputItem == Items.SPLASH_POTION || inputItem == Items.LINGERING_POTION) && PotionUtils.getPotion(input) == MinepreggoModPotions.IMPREGNATION_POTION_3.get();
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				var result = PotionUtils.setPotion(new ItemStack(input.getItem()), MinepreggoModPotions.VILLAGER_IMPREGNATION_3.get());
				CumSpecimenTubeItem.tryTransferOwner(input, result);
				return result;
			}
			return ItemStack.EMPTY;
		}	
	}
	
	public static class A4 extends VillagerImpregnationPotionBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) {
			Item inputItem = input.getItem();
			return (inputItem == Items.POTION || inputItem == Items.SPLASH_POTION || inputItem == Items.LINGERING_POTION) && PotionUtils.getPotion(input) == MinepreggoModPotions.IMPREGNATION_POTION_4.get();
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				var result = PotionUtils.setPotion(new ItemStack(input.getItem()), MinepreggoModPotions.VILLAGER_IMPREGNATION_4.get());
				CumSpecimenTubeItem.tryTransferOwner(input, result);
				return result;
			}
			return ItemStack.EMPTY;
		}	
	}
}
