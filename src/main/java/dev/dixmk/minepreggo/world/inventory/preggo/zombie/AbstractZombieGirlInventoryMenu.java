package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.utils.TagHelper;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.AbstractPreggoMobInventaryMenu;
import dev.dixmk.minepreggo.world.item.ItemHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
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
			this.customSlots.put(ITamablePreggoMob.HEAD_INVENTORY_SLOT, this.addSlot(new SlotItemHandler(internal, 4, 8, 8) {
				@Override
				public boolean mayPlace(ItemStack itemstack) {
					return ItemHelper.isHelmet(itemstack);
				}
			}));
			
			this.customSlots.put(ITamablePreggoMob.CHEST_INVENTORY_SLOT, this.addSlot(new SlotItemHandler(internal, 3, 8, 26) {
				@Override
				public boolean mayPlace(ItemStack itemstack) {													
					if (!ItemHelper.isChest(itemstack)) {
						return false;
					}					
					var stage = PregnancyStage.getNonPregnancyStage();		
					if (zombieGirl instanceof AbstractTamablePregnantZombieGirl<?,?> pregZombieGirl) {
						stage = pregZombieGirl.getCurrentPregnancyStage();		
					}			
					if (!PreggoMobHelper.canUseChestplate(itemstack, stage) && !player.level().isClientSide()) {                                               
		                player.displayClientMessage(MessageHelper.getPreggoMobArmorChestMessage(stage, zombieGirl.getSimpleName()), true);        
		                return false;
					}				
					return true;
				}
			}));
			this.customSlots.put(ITamablePreggoMob.LEGS_INVENTORY_SLOT, this.addSlot(new SlotItemHandler(internal, 2, 8, 44) {
				@Override
				public boolean mayPlace(ItemStack itemstack) {
					if (!ItemHelper.isLegging(itemstack)) {
						return false;
					}
					var stage = PregnancyStage.getNonPregnancyStage();
					if (zombieGirl instanceof AbstractTamablePregnantZombieGirl<?,?> pregZombieGirl) {
						stage = pregZombieGirl.getCurrentPregnancyStage();			
					}
					if (!PreggoMobHelper.canUseLegging(itemstack, stage) && !player.level().isClientSide()) {            	
		                player.displayClientMessage(MessageHelper.getPreggoMobArmorLeggingsMessage(zombieGirl.getSimpleName()), true);           
		                return false;
					}
					return true;
				}
			}));
			this.customSlots.put(ITamablePreggoMob.FEET_INVENTORY_SLOT, this.addSlot(new SlotItemHandler(internal, 1, 8, 62) {
				@Override
				public boolean mayPlace(ItemStack itemstack) {
					return ItemHelper.isBoot(itemstack);
				}
			}));
			
			this.customSlots.put(ITamablePreggoMob.MAINHAND_INVENTORY_SLOT, this.addSlot(new SlotItemHandler(internal, 0, 77, 62)));
			this.customSlots.put(ITamablePreggoMob.OFFHAND_INVENTORY_SLOT, this.addSlot(new SlotItemHandler(internal, 5, 95, 62)));
			this.customSlots.put(ITamablePreggoMob.FOOD_INVENTORY_SLOT, this.addSlot(new SlotItemHandler(internal, 6, 113, 62) {
				@Override
				public boolean mayPlace(ItemStack itemstack) {
					return itemstack.is(TagHelper.ZOMBIE_GIRL_FOOD);
				}
			}));	
			
			this.customSlots.put(7, this.addSlot(new SlotItemHandler(internal, 7, 134, 8)));
			this.customSlots.put(8, this.addSlot(new SlotItemHandler(internal, 8, 152, 8)));
			this.customSlots.put(9, this.addSlot(new SlotItemHandler(internal, 9, 134, 26)));
			this.customSlots.put(10, this.addSlot(new SlotItemHandler(internal, 10, 152, 26)));
			this.customSlots.put(11, this.addSlot(new SlotItemHandler(internal, 11, 134, 44)));
			this.customSlots.put(12, this.addSlot(new SlotItemHandler(internal, 12, 152, 44)));
			this.customSlots.put(13, this.addSlot(new SlotItemHandler(internal, 13, 134, 62)));
			this.customSlots.put(14, this.addSlot(new SlotItemHandler(internal, 14, 152, 62)));
			
			for (int si = 0; si < 3; ++si)
				for (int sj = 0; sj < 9; ++sj)
					this.addSlot(new Slot(inv, sj + (si + 1) * 9, 0 + 8 + sj * 18, 0 + 84 + si * 18));
			for (int si = 0; si < 9; ++si)
				this.addSlot(new Slot(inv, si, 0 + 8 + si * 18, 0 + 142));

			PreggoMobHelper.syncFromEquipmentSlotToInventory(zombieGirl);
		});
	}
}
