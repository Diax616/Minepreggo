package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import net.minecraft.world.entity.player.Inventory;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP0;
import net.minecraft.network.FriendlyByteBuf;

public class CreeperGirlP0InventoryMenu extends AbstractCreeperGirlInventoryMenu<TamableCreeperGirlP0> {
	public CreeperGirlP0InventoryMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_P0_INVENTORY_MENU.get(), id, inv, extraData, TamableCreeperGirlP0.class);
	}
}
