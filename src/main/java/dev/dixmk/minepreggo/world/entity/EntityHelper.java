package dev.dixmk.minepreggo.world.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class EntityHelper {

	private EntityHelper() {}
	
	public static boolean spawnItemOn(Entity entity, ItemStack stack) {	
	    if (!stack.isEmpty()) {
	        ItemEntity item = new ItemEntity(
	            entity.level(),
	            entity.getX(), entity.getY(), entity.getZ(),
	            stack.copy()
	        );
	        item.setDefaultPickUpDelay();
	        entity.level().addFreshEntity(item);
	        return true;
	    }
	    return false;

	}
	
	public static boolean spawnItemOn(Entity entity, Item item) {	
		return spawnItemOn(entity, new ItemStack(item));
	}
	
	public static void copyName(Entity source, Entity target) {
		if (source.hasCustomName())
			target.setCustomName(source.getCustomName());
	}
}
