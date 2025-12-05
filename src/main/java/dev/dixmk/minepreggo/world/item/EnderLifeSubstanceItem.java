package dev.dixmk.minepreggo.world.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class EnderLifeSubstanceItem extends Item {
	public EnderLifeSubstanceItem() {
		super(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON));
	}
}
