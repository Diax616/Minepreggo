package dev.dixmk.minepreggo.world.item;


import net.minecraft.world.level.Level;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.player.Player;

import org.checkerframework.checker.index.qual.NonNegative;

import dev.dixmk.minepreggo.world.pregnancy.Craving;
import net.minecraft.world.entity.LivingEntity;

public class LemonIcePopsiclesItem extends Item implements IItemCraving {
	public LemonIcePopsiclesItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(2).saturationMod(0.1f).alwaysEat().build()));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		super.finishUsingItem(itemstack, world, entity);
		ItemStack retval = new ItemStack(Items.STICK);
		if (itemstack.isEmpty()) {
			return retval;
		} else {
			if (entity instanceof Player player && !player.getAbilities().instabuild && !player.getInventory().add(retval)) {
				player.drop(retval, false);
			}
			return itemstack;
		}
	}
	
	@Override
	public @NonNegative int getGratification() {
		return 5;
	}

	@Override
	public Craving getTypeOfCraving() {
		return Craving.SOUR;
	}
}
