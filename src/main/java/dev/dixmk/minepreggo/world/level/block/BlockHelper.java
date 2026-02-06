package dev.dixmk.minepreggo.world.level.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;

public class BlockHelper {

	private BlockHelper() {}
	
	public static boolean isBlock(ItemStack itemStack) {
		return itemStack.getItem() instanceof BlockItem;
	}
}
