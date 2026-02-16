package dev.dixmk.minepreggo.world.item.alchemy;

import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.init.MinepreggoModPotions;
import dev.dixmk.minepreggo.world.item.CumSpecimenTubeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public abstract class ImpregnationPotionBrewingRecipe implements IBrewingRecipe {

	/*
	 * tryTransferOwner from CumSpecimenTubeItem is used to transfer the owner data from input to output potions.
	 * The mechanics is not yet implemented, there is no effect of owner data on potions yet.
	 * */
		
	private ImpregnationPotionBrewingRecipe() {}
	
	public static class Amplifier0 extends ImpregnationPotionBrewingRecipe {		
		@Override
		public boolean isInput(ItemStack input) {
			Item inputItem = input.getItem();
			return (inputItem == Items.POTION || inputItem == Items.SPLASH_POTION || inputItem == Items.LINGERING_POTION) && PotionUtils.getPotion(input) == Potions.REGENERATION;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.is(MinepreggoModItems.CUM_SPECIMEN_TUBE.get());
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				var result = PotionUtils.setPotion(new ItemStack(input.getItem()), MinepreggoModPotions.IMPREGNATION_POTION_0.get());
				CumSpecimenTubeItem.tryTransferOwner(input, result);
				return result;
			}
			return ItemStack.EMPTY;
		}				
	}
	
	public static class Amplifier1 extends ImpregnationPotionBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) {
			Item inputItem = input.getItem();
			return (inputItem == Items.POTION || inputItem == Items.SPLASH_POTION || inputItem == Items.LINGERING_POTION) && PotionUtils.getPotion(input) == MinepreggoModPotions.IMPREGNATION_POTION_0.get();
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.is(Items.GLOWSTONE_DUST);
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				var result = PotionUtils.setPotion(new ItemStack(input.getItem()), MinepreggoModPotions.IMPREGNATION_POTION_1.get());
				CumSpecimenTubeItem.tryTransferOwner(input, result);
				return result;
			}
			return ItemStack.EMPTY;
		}	
	}
	
	public static class Amplifier2 extends ImpregnationPotionBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) {
			Item inputItem = input.getItem();
			return (inputItem == Items.POTION || inputItem == Items.SPLASH_POTION || inputItem == Items.LINGERING_POTION) && PotionUtils.getPotion(input) == MinepreggoModPotions.IMPREGNATION_POTION_1.get();
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.is(Items.NETHER_QUARTZ_ORE);
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				var result = PotionUtils.setPotion(new ItemStack(input.getItem()), MinepreggoModPotions.IMPREGNATION_POTION_2.get());
				CumSpecimenTubeItem.tryTransferOwner(input, result);
				return result;
			}
			return ItemStack.EMPTY;
		}	
	}
	
	public static class Amplifier3 extends ImpregnationPotionBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) {
			Item inputItem = input.getItem();
			return (inputItem == Items.POTION || inputItem == Items.SPLASH_POTION || inputItem == Items.LINGERING_POTION) && PotionUtils.getPotion(input) == MinepreggoModPotions.IMPREGNATION_POTION_2.get();
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.is(Items.BLAZE_ROD);
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				var result = PotionUtils.setPotion(new ItemStack(input.getItem()), MinepreggoModPotions.IMPREGNATION_POTION_3.get());
				CumSpecimenTubeItem.tryTransferOwner(input, result);
				return result;
			}
			return ItemStack.EMPTY;
		}	
	}
	
	public static class Amplifier4 extends ImpregnationPotionBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) {
			Item inputItem = input.getItem();
			return (inputItem == Items.POTION || inputItem == Items.SPLASH_POTION || inputItem == Items.LINGERING_POTION) && PotionUtils.getPotion(input) == MinepreggoModPotions.IMPREGNATION_POTION_3.get();
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.is(Items.ENDER_PEARL);
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				var result = PotionUtils.setPotion(new ItemStack(input.getItem()), MinepreggoModPotions.IMPREGNATION_POTION_4.get());
				CumSpecimenTubeItem.tryTransferOwner(input, result);
				return result;
			}
			return ItemStack.EMPTY;
		}	
	}
}
