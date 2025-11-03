package dev.dixmk.minepreggo.world.item.alchemy;

import javax.annotation.Nonnull;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;

public class PotionItemFactory {
	
	private PotionItemFactory() {}
	
	public static ItemStack createPotion(@Nonnull Potion potion) {	
		return PotionUtils.setPotion(new ItemStack(Items.POTION), potion);		
	}
	
	public static ItemStack createSplashPotion(@Nonnull Potion potion) {	
		return PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), potion);		
	}
}
