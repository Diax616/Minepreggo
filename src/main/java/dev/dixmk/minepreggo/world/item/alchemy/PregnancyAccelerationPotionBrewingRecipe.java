package dev.dixmk.minepreggo.world.item.alchemy;

import dev.dixmk.minepreggo.init.MinepreggoModPotions;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public abstract class PregnancyAccelerationPotionBrewingRecipe implements IBrewingRecipe {

	private PregnancyAccelerationPotionBrewingRecipe() {}
	
	public static class Amplifier0 extends PregnancyAccelerationPotionBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) {
			Item inputItem = input.getItem();
			return (inputItem == Items.POTION || inputItem == Items.SPLASH_POTION || inputItem == Items.LINGERING_POTION) && PotionUtils.getPotion(input) == MinepreggoModPotions.FERTILITY.get();
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.is(Items.GLISTERING_MELON_SLICE);
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				return PotionUtils.setPotion(new ItemStack(input.getItem()), MinepreggoModPotions.PREGNANCY_ACCELERATION_0.get());
			}
			return ItemStack.EMPTY;
		}	
	}
	
	public static class Amplifier1 extends PregnancyAccelerationPotionBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) {
			Item inputItem = input.getItem();
			return (inputItem == Items.POTION || inputItem == Items.SPLASH_POTION || inputItem == Items.LINGERING_POTION) && PotionUtils.getPotion(input) == MinepreggoModPotions.PREGNANCY_ACCELERATION_0.get();
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.is(Items.GLOWSTONE_DUST);
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				return PotionUtils.setPotion(new ItemStack(input.getItem()), MinepreggoModPotions.PREGNANCY_ACCELERATION_1.get());
			}
			return ItemStack.EMPTY;
		}	
	}
	
	public static class Amplifier2 extends PregnancyAccelerationPotionBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) {
			Item inputItem = input.getItem();
			return (inputItem == Items.POTION || inputItem == Items.SPLASH_POTION || inputItem == Items.LINGERING_POTION) && PotionUtils.getPotion(input) == MinepreggoModPotions.PREGNANCY_ACCELERATION_1.get();
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.is(Items.NETHER_QUARTZ_ORE);
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				return PotionUtils.setPotion(new ItemStack(input.getItem()), MinepreggoModPotions.PREGNANCY_ACCELERATION_2.get());
			}
			return ItemStack.EMPTY;
		}	
	}
	
	public static class Amplifier3 extends PregnancyAccelerationPotionBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) {
			Item inputItem = input.getItem();
			return (inputItem == Items.POTION || inputItem == Items.SPLASH_POTION || inputItem == Items.LINGERING_POTION) && PotionUtils.getPotion(input) == MinepreggoModPotions.PREGNANCY_ACCELERATION_2.get();
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.is(Items.BLAZE_ROD);
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				return PotionUtils.setPotion(new ItemStack(input.getItem()), MinepreggoModPotions.PREGNANCY_ACCELERATION_3.get());
			}
			return ItemStack.EMPTY;
		}	
	}
	
	public static class Amplifier4 extends PregnancyAccelerationPotionBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) {
			Item inputItem = input.getItem();
			return (inputItem == Items.POTION || inputItem == Items.SPLASH_POTION || inputItem == Items.LINGERING_POTION) && PotionUtils.getPotion(input) == MinepreggoModPotions.PREGNANCY_ACCELERATION_3.get();
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.is(Items.ENDER_PEARL);
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				return PotionUtils.setPotion(new ItemStack(input.getItem()), MinepreggoModPotions.PREGNANCY_ACCELERATION_4.get());
			}
			return ItemStack.EMPTY;
		}	
	}
}