package dev.dixmk.minepreggo.world.item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

public abstract class FemaleChestPlateItem extends ArmorItem implements IFemaleArmor {

	protected FemaleChestPlateItem(ArmorMaterial material, Type armorType, Properties properties) {
		super(material, armorType, properties);
	}

	@Override
	public boolean areBoobsExposed() {
		return false;
	}
}
