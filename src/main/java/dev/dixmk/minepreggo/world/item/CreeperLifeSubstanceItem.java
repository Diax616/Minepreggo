package dev.dixmk.minepreggo.world.item;


import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class CreeperLifeSubstanceItem extends Item {
	public CreeperLifeSubstanceItem() {
		super(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON));
	}
}
