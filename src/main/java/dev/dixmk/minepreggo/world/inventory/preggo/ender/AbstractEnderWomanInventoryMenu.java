package dev.dixmk.minepreggo.world.inventory.preggo.ender;

import dev.dixmk.minepreggo.world.entity.preggo.InventorySlot;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamableEnderWoman;
import dev.dixmk.minepreggo.world.inventory.preggo.AbstractPreggoMobInventaryMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraftforge.items.SlotItemHandler;

public abstract class AbstractEnderWomanInventoryMenu<E extends AbstractTamableEnderWoman> extends AbstractPreggoMobInventaryMenu<E> {

	protected AbstractEnderWomanInventoryMenu(MenuType<?> container, int id, Inventory inv, FriendlyByteBuf extraData, Class<E> preggoMobClass) {
		super(container, id, inv, extraData, preggoMobClass);

	}
	
	@Override
	protected void createInventory(Inventory inv) {
		this.preggoMob.ifPresent(creeperGirl -> {			
			var slotMapper = creeperGirl.getInventory().getSlotMapper();
			
			this.addSlot(new SlotItemHandler(internal, slotMapper.getSlotIndex(InventorySlot.HEAD), 8, 8));
			this.addSlot(new SlotItemHandler(internal, slotMapper.getSlotIndex(InventorySlot.MAINHAND), 80, 62));
			this.addSlot(new SlotItemHandler(internal, slotMapper.getSlotIndex(InventorySlot.OFFHAND), 98, 62));
			this.addSlot(new SlotItemHandler(internal, slotMapper.getSlotIndex(InventorySlot.FOOD), 116, 62));
			this.addSlot(new SlotItemHandler(internal, slotMapper.getSlotIndex(InventorySlot.BOTH_HANDS), 134, 62));
			
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

			PreggoMobHelper.syncFromEquipmentSlotToInventory(creeperGirl);
		});
	}
}
