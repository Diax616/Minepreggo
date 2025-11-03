package dev.dixmk.minepreggo.world.item;


import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class LemonItem extends Item {
	public LemonItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}
