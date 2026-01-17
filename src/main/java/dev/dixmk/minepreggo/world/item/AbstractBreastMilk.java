package dev.dixmk.minepreggo.world.item;

import java.util.List;
import java.util.Optional;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.world.effect.MobEffect;
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
	public ItemStack finishUsingItem(ItemStack itemstack, Level level, LivingEntity entity) {
		if (!level.isClientSide) {
			if (entity instanceof Player player && player.getCapability(MinepreggoCapabilities.PLAYER_DATA).isPresent()) {	
				player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> {			
					Optional<List<MobEffect>> result = cap.getFemaleData().map(femaleData -> {
						if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
							return PlayerHelper.removeEffectsByPregnancyPhase(player, femaleData.getPregnancyData().getCurrentPregnancyStage());
						}
						return PlayerHelper.removeEffects(player, effect -> !PregnancySystemHelper.isFemaleEffect(effect));
					});
					if (result.isPresent()) {
	                    for (MobEffect effect : result.get()) {
                            player.removeEffect(effect);
                        }
					}
					else {
						entity.curePotionEffects(itemstack);
					}
				});		
			}
			else {
				entity.getActiveEffects().stream().toList().forEach(effectInstance -> 
					entity.removeEffect(effectInstance.getEffect())
				);
			}
		}	
		
		if (entity instanceof Player player && !player.getAbilities().instabuild) {
			itemstack.shrink(1);
		}

		return itemstack.isEmpty() ? new ItemStack(Items.GLASS_BOTTLE) : itemstack;		
	}
}
