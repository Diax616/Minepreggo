package dev.dixmk.minepreggo.world.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public abstract class AbstractDeadFetus extends Item {
	protected AbstractDeadFetus() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON));
	}
}
