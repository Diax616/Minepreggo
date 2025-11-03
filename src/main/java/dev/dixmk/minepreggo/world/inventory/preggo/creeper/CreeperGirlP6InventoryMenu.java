package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP6;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP6InventoryMenu extends AbstractCreeperGirlInventoryMenu<TamableCreeperGirlP6> {
	public CreeperGirlP6InventoryMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_P6_INVENTORY_MENU.get(), id, inv, extraData, TamableCreeperGirlP6.class);
	}
}
