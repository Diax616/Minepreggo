package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.utils.TagHelper;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.AbstractPreggoMobInventaryMenu;
import dev.dixmk.minepreggo.world.item.ItemHelper;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancySystemHandler;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public abstract class AbstractZombieGirlInventoryMenu<E extends AbstractTamableZombieGirl<?>> extends AbstractPreggoMobInventaryMenu<E> {

	protected AbstractZombieGirlInventoryMenu(MenuType<?> menuType, int id, Inventory inv, FriendlyByteBuf extraData, Class<E> zombieGirlClass) {
		super(menuType, id, inv, extraData, AbstractTamableZombieGirl.INVENTORY_SIZE, zombieGirlClass);	
	}

	@Override
	protected void createInventory(Inventory inv) {
		this.preggoMob.ifPresent(zombieGirl -> {
					
			this.addSlot(new SlotItemHandler(internal, ITamablePreggoMob.HEAD_INVENTORY_SLOT, 8, 8) {
				@Override
				public boolean mayPlace(ItemStack itemstack) {
					return ItemHelper.isHelmet(itemstack);
				}
			});
			
			this.addSlot(new SlotItemHandler(internal, ITamablePreggoMob.CHEST_INVENTORY_SLOT, 8, 26) {
				@Override
				public boolean mayPlace(ItemStack itemstack) {	
					var armor = itemstack.getItem();
					boolean flag = ItemHelper.isChest(armor);			
					
					if (!flag) {
						return false;
					}
					
					if (zombieGirl instanceof IPregnancySystemHandler pregnancySystem) {
						final var pregnancyPhase = pregnancySystem.getCurrentPregnancyStage();					
						if (!PregnancySystemHelper.canUseChestplate(armor, pregnancyPhase, false)) {
							MessageHelper.warnFittedArmor((Player) zombieGirl.getOwner(), zombieGirl, pregnancyPhase);
			                flag = false;
						}	
						else if (!PreggoMobHelper.canUseChestPlateInLactation(pregnancySystem, armor)) {
							MessageHelper.sendTo(MessageHelper.asServerPlayer((Player) zombieGirl.getOwner()), Component.translatable("chat.minepreggo.preggo_mob.armor.message.lactating", zombieGirl.getSimpleName()));
			                flag = false;
						}
					}					
					else {                      							
						if (!PreggoMobHelper.canUseChestPlateInLactation(zombieGirl, armor)) {
							MessageHelper.sendTo(MessageHelper.asServerPlayer((Player) zombieGirl.getOwner()), Component.translatable("chat.minepreggo.preggo_mob.armor.message.lactating", zombieGirl.getSimpleName()));
							flag = false;
						}
					}
			
					return flag;			
				}
			});
			
			this.addSlot(new SlotItemHandler(internal, ITamablePreggoMob.LEGS_INVENTORY_SLOT, 8, 44) {
				@Override
				public boolean mayPlace(ItemStack itemstack) {
					var armor = itemstack.getItem();
					boolean flag = ItemHelper.isLegging(armor);			
					
					if (!flag) {
						return false;
					}
					
					if (zombieGirl instanceof IPregnancySystemHandler pregnancySystem) {
						final var pregnancyPhase = pregnancySystem.getCurrentPregnancyStage();					
						if (!PregnancySystemHelper.canUseLegging(armor, pregnancyPhase)) {
							MessageHelper.sendTo(MessageHelper.asServerPlayer((Player) zombieGirl.getOwner()), Component.translatable("chat.minepreggo.preggo_mob.armor.message.leggings_does_not_fit.p3_to_p8", zombieGirl.getSimpleName()));
			                return false;
						}			
					}												
					return true;			
				}
			});
	
			this.addSlot(new SlotItemHandler(internal, ITamablePreggoMob.FEET_INVENTORY_SLOT, 8, 62) {
				@Override
				public boolean mayPlace(ItemStack itemstack) {
					return ItemHelper.isBoot(itemstack);
				}
			});
			
			this.addSlot(new SlotItemHandler(internal, ITamablePreggoMob.MAINHAND_INVENTORY_SLOT, 77, 62));
			this.addSlot(new SlotItemHandler(internal, ITamablePreggoMob.OFFHAND_INVENTORY_SLOT, 95, 62));
			
			this.addSlot(new SlotItemHandler(internal, ITamablePreggoMob.FOOD_INVENTORY_SLOT, 113, 62) {
				@Override
				public boolean mayPlace(ItemStack itemstack) {
					return itemstack.is(TagHelper.ZOMBIE_GIRL_FOOD);
				}
			});
			
			this.addSlot(new SlotItemHandler(internal, 7, 134, 8));
			this.addSlot(new SlotItemHandler(internal, 8, 152, 8));
			this.addSlot(new SlotItemHandler(internal, 9, 134, 26));
			this.addSlot(new SlotItemHandler(internal, 10, 152, 26));
			this.addSlot(new SlotItemHandler(internal, 11, 134, 44));
			this.addSlot(new SlotItemHandler(internal, 12, 152, 44));
			this.addSlot(new SlotItemHandler(internal, 13, 134, 62));
			this.addSlot(new SlotItemHandler(internal, 14, 152, 62));
			
			for (int si = 0; si < 3; ++si)
				for (int sj = 0; sj < 9; ++sj)
					this.addSlot(new Slot(inv, sj + (si + 1) * 9, 0 + 8 + sj * 18, 0 + 84 + si * 18));
			for (int si = 0; si < 9; ++si)
				this.addSlot(new Slot(inv, si, 0 + 8 + si * 18, 0 + 142));

			PreggoMobHelper.syncFromEquipmentSlotToInventory(zombieGirl);
		});
	}
}
