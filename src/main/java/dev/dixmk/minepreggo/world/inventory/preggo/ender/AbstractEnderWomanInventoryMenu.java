package dev.dixmk.minepreggo.world.inventory.preggo.ender;

import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.world.entity.preggo.InventorySlot;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamableEnderWoman;
import dev.dixmk.minepreggo.world.inventory.preggo.AbstractPreggoMobInventaryMenu;
import dev.dixmk.minepreggo.world.item.ItemHelper;
import dev.dixmk.minepreggo.world.level.block.BlockHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public abstract class AbstractEnderWomanInventoryMenu<E extends AbstractTamableEnderWoman> extends AbstractPreggoMobInventaryMenu<E> {

	protected AbstractEnderWomanInventoryMenu(MenuType<?> container, int id, Inventory inv, FriendlyByteBuf extraData, Class<E> preggoMobClass) {
		super(container, id, inv, extraData, preggoMobClass);
	}
	
	@Override
	protected void createInventory(Inventory inv) {
		this.preggoMob.ifPresent(mob -> {			
			var slotMapper = mob.getInventory().getSlotMapper();

			this.addSlot(new SlotItemHandler(internal, slotMapper.getSlotIndex(InventorySlot.HEAD), 8, 8) {
				@Override
				public boolean mayPlace(ItemStack itemstack) {
					return super.mayPlace(itemstack) && itemstack.canEquip(EquipmentSlot.HEAD, mob);
				}
				
			    @Override
			    public boolean mayPickup(Player playerIn) {
			        return super.mayPickup(playerIn) && ItemHelper.canPickUp(playerIn, getItem());
			    }
			});
			this.addSlot(new SlotItemHandler(internal, slotMapper.getSlotIndex(InventorySlot.MAINHAND), 80, 62) {
				private int bothHandsSlot = slotMapper.getSlotIndex(InventorySlot.BOTH_HANDS);
				@Override
				public boolean mayPlace(ItemStack itemstack) {
					if (!internal.getStackInSlot(bothHandsSlot).isEmpty()) {
						MessageHelper.sendTo(mob.getOwner(), "chat.minepreggo.preggo_mob.inventory.message.both_hands_occupied", mob.getSimpleNameOrCustom(), true);					
						return false;
					}
					return true;
				}
			});
			this.addSlot(new SlotItemHandler(internal, slotMapper.getSlotIndex(InventorySlot.OFFHAND), 98, 62) {
				private int bothHandsSlot = slotMapper.getSlotIndex(InventorySlot.BOTH_HANDS);
				@Override
				public boolean mayPlace(ItemStack itemstack) {
					if (!internal.getStackInSlot(bothHandsSlot).isEmpty()) {
						MessageHelper.sendTo(mob.getOwner(), "chat.minepreggo.preggo_mob.inventory.message.both_hands_occupied", mob.getSimpleNameOrCustom(), true);				
						return false;
					}
					return true;
				}
			});
			this.addSlot(new SlotItemHandler(internal, slotMapper.getSlotIndex(InventorySlot.FOOD), 116, 62) {
				@Override
				public boolean mayPlace(ItemStack itemstack) {
					return mob.isFood(itemstack);
				}
			});
			this.addSlot(new SlotItemHandler(internal, slotMapper.getSlotIndex(InventorySlot.BOTH_HANDS), 134, 62) {
				private final int mainHandSlot = slotMapper.getSlotIndex(InventorySlot.MAINHAND);
				private final int offHandSlot = slotMapper.getSlotIndex(InventorySlot.OFFHAND);		
				@Override
				public boolean mayPlace(ItemStack itemstack) {				
					if (!internal.getStackInSlot(mainHandSlot).isEmpty() || !internal.getStackInSlot(offHandSlot).isEmpty()) {
						MessageHelper.sendTo(mob.getOwner(), "chat.minepreggo.preggo_mob.inventory.message.one_hand_occupied", mob.getSimpleNameOrCustom(), true);
						return false;
					}
					else if (!BlockHelper.isBlock(itemstack)) {
						MessageHelper.sendTo(mob.getOwner(), "chat.minepreggo.preggo_mob.inventory.message.not_a_block", mob.getSimpleNameOrCustom(), true);
						return false;
					}
					else if (itemstack.getCount() > 1) {
						MessageHelper.sendTo(mob.getOwner(), "chat.minepreggo.preggo_mob.inventory.message.both_hands_block_one_item", mob.getSimpleNameOrCustom(), true);
						return false;
					}	
					return super.mayPlace(itemstack);
				}
			});		
			
			this.addSlot(new SlotItemHandler(internal, 5, 80, 8));
			this.addSlot(new SlotItemHandler(internal, 6, 98, 8));
			this.addSlot(new SlotItemHandler(internal, 7, 116, 8));
			this.addSlot(new SlotItemHandler(internal, 8, 134, 8));
			this.addSlot(new SlotItemHandler(internal, 9, 152, 8));
			this.addSlot(new SlotItemHandler(internal, 10, 80, 26));
			this.addSlot(new SlotItemHandler(internal, 11, 98, 26));
			this.addSlot(new SlotItemHandler(internal, 12, 116, 26));
			this.addSlot(new SlotItemHandler(internal, 13, 134, 26));
			this.addSlot(new SlotItemHandler(internal, 14, 152, 26));
							
			for (int si = 0; si < 3; ++si)
				for (int sj = 0; sj < 9; ++sj)
					this.addSlot(new Slot(inv, sj + (si + 1) * 9, 0 + 8 + sj * 18, 0 + 84 + si * 18));
			for (int si = 0; si < 9; ++si)
				this.addSlot(new Slot(inv, si, 0 + 8 + si * 18, 0 + 142));

			PreggoMobHelper.syncFromEquipmentSlotToInventory(mob);
			AbstractTamableEnderWoman.syncBlockToInventory(mob);
		});
	}
}
