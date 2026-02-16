package dev.dixmk.minepreggo.world.item.alchemy;

import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.init.MinepreggoModPotions;
import dev.dixmk.minepreggo.world.item.CumSpecimenTubeItem;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public abstract class CreeperImpregnationPotionBrewingRecipe implements IBrewingRecipe {

	/*
	 * tryTransferOwner from CumSpecimenTubeItem is used to transfer the owner data from input to output potions.
	 * The mechanics is not yet implemented, there is no effect of owner data on potions yet.
	 * */
	
	/*
	 * TODO: The type of Ender Impregnation Potion (monster or humanoid) is chosen randomly here. It would be better to have a way for the player to choose which type they want.
	 * */
	
	private CreeperImpregnationPotionBrewingRecipe() {}
	
	@Override
	public boolean isIngredient(ItemStack ingredient) {
		return ingredient.is(MinepreggoModItems.CREEPER_LIFE_SUBSTANCE.get());
	}
	
	public static class Amplifier0 extends CreeperImpregnationPotionBrewingRecipe {		
		@Override
		public boolean isInput(ItemStack input) {
			Item inputItem = input.getItem();
			return (inputItem == Items.POTION || inputItem == Items.SPLASH_POTION || inputItem == Items.LINGERING_POTION) && PotionUtils.getPotion(input) == MinepreggoModPotions.IMPREGNATION_POTION_0.get();
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				var result = PotionUtils.setPotion(new ItemStack(input.getItem()), PregnancySystemHelper.RANDOM_SOURCE.nextBoolean() ? MinepreggoModPotions.CREEPER_IMPREGNATION_0.get() : MinepreggoModPotions.HUMANOID_CREEPER_IMPREGNATION_0.get());
				CumSpecimenTubeItem.tryTransferOwner(input, result);
				return result;
			}
			return ItemStack.EMPTY;
		}				
	}
	
	public static class Amplifier1 extends CreeperImpregnationPotionBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) {
			Item inputItem = input.getItem();
			return (inputItem == Items.POTION || inputItem == Items.SPLASH_POTION || inputItem == Items.LINGERING_POTION) && PotionUtils.getPotion(input) == MinepreggoModPotions.IMPREGNATION_POTION_1.get();
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				var result = PotionUtils.setPotion(new ItemStack(input.getItem()), PregnancySystemHelper.RANDOM_SOURCE.nextBoolean() ? MinepreggoModPotions.CREEPER_IMPREGNATION_1.get() : MinepreggoModPotions.HUMANOID_CREEPER_IMPREGNATION_1.get());
				CumSpecimenTubeItem.tryTransferOwner(input, result);
				return result;
			}
			return ItemStack.EMPTY;
		}	
	}
	
	public static class Amplifier2 extends CreeperImpregnationPotionBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) {
			Item inputItem = input.getItem();
			return (inputItem == Items.POTION || inputItem == Items.SPLASH_POTION || inputItem == Items.LINGERING_POTION) && PotionUtils.getPotion(input) == MinepreggoModPotions.IMPREGNATION_POTION_2.get();
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				var result = PotionUtils.setPotion(new ItemStack(input.getItem()), PregnancySystemHelper.RANDOM_SOURCE.nextBoolean() ? MinepreggoModPotions.CREEPER_IMPREGNATION_2.get() : MinepreggoModPotions.HUMANOID_CREEPER_IMPREGNATION_2.get());
				CumSpecimenTubeItem.tryTransferOwner(input, result);
				return result;
			}
			return ItemStack.EMPTY;
		}	
	}
	
	public static class Amplifier3 extends CreeperImpregnationPotionBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) {
			Item inputItem = input.getItem();
			return (inputItem == Items.POTION || inputItem == Items.SPLASH_POTION || inputItem == Items.LINGERING_POTION) && PotionUtils.getPotion(input) == MinepreggoModPotions.IMPREGNATION_POTION_3.get();
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				var result = PotionUtils.setPotion(new ItemStack(input.getItem()), PregnancySystemHelper.RANDOM_SOURCE.nextBoolean() ? MinepreggoModPotions.CREEPER_IMPREGNATION_3.get() : MinepreggoModPotions.HUMANOID_CREEPER_IMPREGNATION_3.get());
				CumSpecimenTubeItem.tryTransferOwner(input, result);
				return result;
			}
			return ItemStack.EMPTY;
		}	
	}
	
	public static class Amplifier4 extends CreeperImpregnationPotionBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) {
			Item inputItem = input.getItem();
			return (inputItem == Items.POTION || inputItem == Items.SPLASH_POTION || inputItem == Items.LINGERING_POTION) && PotionUtils.getPotion(input) == MinepreggoModPotions.IMPREGNATION_POTION_4.get();
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				var result = PotionUtils.setPotion(new ItemStack(input.getItem()), PregnancySystemHelper.RANDOM_SOURCE.nextBoolean() ? MinepreggoModPotions.CREEPER_IMPREGNATION_4.get() : MinepreggoModPotions.HUMANOID_CREEPER_IMPREGNATION_4.get());
				CumSpecimenTubeItem.tryTransferOwner(input, result);
				return result;
			}
			return ItemStack.EMPTY;
		}	
	}
}
