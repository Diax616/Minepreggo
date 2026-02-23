package dev.dixmk.minepreggo.world.item;

import java.util.List;
import java.util.Optional;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
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
import net.minecraftforge.items.ItemHandlerHelper;

public abstract class AbstractBreastMilk extends Item {
	
	protected AbstractBreastMilk(int nutrition, float saturation) {
		super(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON).food((new FoodProperties.Builder()).nutrition(nutrition).saturationMod(saturation).alwaysEat().build()));
	}

	protected AbstractBreastMilk(int nutrition) {
		this(nutrition, 0.2F);
	}
	
	protected AbstractBreastMilk(float saturation) {
		this(3, saturation);
	}
	
	protected AbstractBreastMilk() {
		this(3, 0.2F);
	}
	
	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.DRINK;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level level, LivingEntity entity) {
		var result = super.finishUsingItem(itemstack, level, entity);
		
		if (!level.isClientSide) {
			if (entity instanceof Player player) {	
				player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> {			
					Optional<List<MobEffect>> effects = cap.getFemaleData().map(femaleData -> {
						if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
							if (player.hasEffect(MinepreggoModMobEffects.ENDER_DRAGON_PREGNANCY.get())) {
								return PlayerHelper.removeEffectsBeingPregnantOfEnderDragon(player, femaleData.getPregnancyData().getCurrentPregnancyPhase());
							}
							else {
								return PlayerHelper.removeEffectsBeingPregnant(player, femaleData.getPregnancyData().getCurrentPregnancyPhase());
							}					
						}
						return LivingEntityHelper.removeEffects(player, effect -> !PregnancySystemHelper.isFemaleEffect(effect));
					});
					if (effects.isPresent()) {
						effects.get().forEach(player::removeEffect);
					}
					else {
						removeEffects(player);
					}
				});		
			}
			else {
				removeEffects(entity);
			}
		}		

		if (entity instanceof Player player) {
			player.getCooldowns().addCooldown(this, 20);
			if (!result.isEmpty()) {
				ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Items.GLASS_BOTTLE));
			}
		}
		
		return result.isEmpty() ? new ItemStack(Items.GLASS_BOTTLE) : result;		
	}
	
	private static void removeEffects(LivingEntity entity) {
		var iter = entity.getActiveEffects().iterator();
		while (iter.hasNext()) {
			entity.removeEffect(iter.next().getEffect());
		}
	}
}
