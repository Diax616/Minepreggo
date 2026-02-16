package dev.dixmk.minepreggo.world.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class LifeSubstanceItem extends Item {
	public LifeSubstanceItem(Rarity rarity) {
		super(new Item.Properties().stacksTo(16).rarity(rarity));
	}
}
