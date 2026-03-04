package dev.dixmk.minepreggo.world.item;

import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.DyeableLeatherItem;

public abstract class DyeableFemaleChestPlateItem extends FemaleChestPlateItem implements DyeableLeatherItem, IFemaleArmor {

	protected DyeableFemaleChestPlateItem(ArmorMaterial material, Type armorType, Properties properties) {
		super(material, armorType, properties);
	}
	
}
