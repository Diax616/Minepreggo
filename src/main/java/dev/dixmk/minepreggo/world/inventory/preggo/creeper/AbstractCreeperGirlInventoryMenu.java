package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.utils.TagHelper;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.AbstractPreggoMobInventaryMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public abstract class AbstractCreeperGirlInventoryMenu<E extends AbstractTamableCreeperGirl> extends AbstractPreggoMobInventaryMenu<E> {
		
	protected AbstractCreeperGirlInventoryMenu(MenuType<?> menuType, int id, Inventory inv, FriendlyByteBuf extraData, Class<E> creeperGirlClass) {
		super(menuType, id, inv, extraData, AbstractTamableCreeperGirl.INVENTORY_SIZE, creeperGirlClass);
	}
		
	@Override
	protected void createInventory(Inventory inv) {
		this.preggoMob.ifPresent(creeperGirl -> {
			
			this.initVanillaEquipmentSlots(inv);
			
			this.addSlot(new SlotItemHandler(internal, ITamablePreggoMob.FOOD_INVENTORY_SLOT, 113, 62) {
				@Override
				public boolean mayPlace(ItemStack itemstack) {
					return itemstack.is(TagHelper.CREEPER_GIRL_FOOD);
				}
			});
			
			this.addSlot(new SlotItemHandler(internal, 7, 134, 8));
			this.addSlot(new SlotItemHandler(internal, 8, 152, 8));
			this.addSlot(new SlotItemHandler(internal, 9, 134, 26));
			this.addSlot(new SlotItemHandler(internal, 10, 152, 26));
			this.addSlot(new SlotItemHandler(internal, 11, 134, 44));
			this.addSlot(new SlotItemHandler(internal, 12, 152, 44));
			
			
			for (int si = 0; si < 3; ++si)
				for (int sj = 0; sj < 9; ++sj)
					this.addSlot(new Slot(inv, sj + (si + 1) * 9, 0 + 8 + sj * 18, 0 + 84 + si * 18));
			for (int si = 0; si < 9; ++si)
				this.addSlot(new Slot(inv, si, 0 + 8 + si * 18, 0 + 142));
		
			PreggoMobHelper.syncFromEquipmentSlotToInventory(creeperGirl);
		});
	}
}
