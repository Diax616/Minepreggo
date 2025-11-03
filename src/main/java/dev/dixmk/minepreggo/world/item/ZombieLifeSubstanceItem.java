package dev.dixmk.minepreggo.world.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class ZombieLifeSubstanceItem extends Item {
	public ZombieLifeSubstanceItem() {
		super(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON));
	}
}
