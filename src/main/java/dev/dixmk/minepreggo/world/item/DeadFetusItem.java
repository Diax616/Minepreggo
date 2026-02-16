package dev.dixmk.minepreggo.world.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class DeadFetusItem extends Item {
	public DeadFetusItem(Rarity rarity) {
		super(new Item.Properties().stacksTo(64).rarity(rarity));
	}
}
