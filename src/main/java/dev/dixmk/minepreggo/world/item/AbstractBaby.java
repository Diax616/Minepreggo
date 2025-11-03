package dev.dixmk.minepreggo.world.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public abstract class AbstractBaby extends Item {
	protected AbstractBaby() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
	}
}
