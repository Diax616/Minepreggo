package dev.dixmk.minepreggo.world.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public abstract class AbstractBreastMilk extends Item {
	
	protected AbstractBreastMilk(int nutrition, float saturation) {
		super(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON).food((new FoodProperties.Builder()).nutrition(nutrition).saturationMod(saturation).alwaysEat().build()));
	}

	protected AbstractBreastMilk(int nutrition) {
		this(nutrition, 0.2F);
	}
	
	protected AbstractBreastMilk(float saturation) {
		this(2, saturation);
	}
	
	protected AbstractBreastMilk() {
		this(2, 0.2F);
	}
	
	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.DRINK;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		super.finishUsingItem(itemstack, world, entity);
		ItemStack retval = new ItemStack(Items.GLASS_BOTTLE);
		if (itemstack.isEmpty()) {
			return retval;
		} else {
			if (entity instanceof Player player && !player.getAbilities().instabuild && !player.getInventory().add(retval)) {
				player.drop(retval, false);
			}
			return itemstack;
		}
	}
}
