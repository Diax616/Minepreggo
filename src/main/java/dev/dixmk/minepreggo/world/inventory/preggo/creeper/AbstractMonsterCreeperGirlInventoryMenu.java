package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.world.entity.preggo.InventorySlot;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterCreeperHelper;
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

public abstract class AbstractMonsterCreeperGirlInventoryMenu<E extends AbstractTamableCreeperGirl> extends AbstractPreggoMobInventaryMenu<E> {

	protected AbstractMonsterCreeperGirlInventoryMenu(MenuType<?> container, int id, Inventory inv, FriendlyByteBuf extraData, Class<E> preggoMobClass) {
		super(container, id, inv, extraData, preggoMobClass);
	}

	@Override
	protected void createInventory(Inventory inv) {
		this.preggoMob.ifPresent(creeperGirl -> {
			var slotMapper = creeperGirl.getInventory().getSlotMapper();
			
			this.addSlot(new SlotItemHandler(internal, slotMapper.getSlotIndex(InventorySlot.HEAD), 8, 8) {
				@Override
				public boolean mayPlace(ItemStack itemstack) {
					return super.mayPlace(itemstack) && itemstack.canEquip(EquipmentSlot.HEAD, creeperGirl);
				}
				
			    @Override
			    public boolean mayPickup(Player playerIn) {
			        return super.mayPickup(playerIn) && ItemHelper.canPickUp(playerIn, getItem());
			    }
			});
			this.addSlot(new SlotItemHandler(internal, slotMapper.getSlotIndex(InventorySlot.MOUTH), 80, 62) {
				@Override
				public boolean mayPlace(ItemStack itemstack) {
					if (BlockHelper.isBlock(itemstack)) {
						MessageHelper.sendTo(creeperGirl.getOwner(), "chat.minepreggo.preggo_mob.inventory.message.cannot_place_block_on_mouth", creeperGirl.getSimpleNameOrCustom(), true);
						return false;
					}
					return super.mayPlace(itemstack);
				}
			});
			this.addSlot(new SlotItemHandler(internal, 2, 134, 8));
			this.addSlot(new SlotItemHandler(internal, 3, 152, 8));
			this.addSlot(new SlotItemHandler(internal, 4, 134, 26));
			this.addSlot(new SlotItemHandler(internal, 5, 152, 26));
			this.addSlot(new SlotItemHandler(internal, 6, 134, 44));
			this.addSlot(new SlotItemHandler(internal, 7, 152, 44));
			
			for (int si = 0; si < 3; ++si)
				for (int sj = 0; sj < 9; ++sj)
					this.addSlot(new Slot(inv, sj + (si + 1) * 9, 8 + sj * 18, 84 + si * 18));
			for (int si = 0; si < 9; ++si)
				this.addSlot(new Slot(inv, si, 8 + si * 18, 142));

			PreggoMobHelper.syncFromEquipmentSlotToInventory(creeperGirl);
			MonsterCreeperHelper.syncItemOnMouthToCustom(creeperGirl);
		});
	}
}
