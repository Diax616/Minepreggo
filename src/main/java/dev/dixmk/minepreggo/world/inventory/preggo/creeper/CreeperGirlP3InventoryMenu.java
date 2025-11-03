package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP3;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP3InventoryMenu extends AbstractCreeperGirlInventoryMenu<TamableCreeperGirlP3> {
	public CreeperGirlP3InventoryMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_P3_INVENTORY_MENU.get(), id, inv, extraData, TamableCreeperGirlP3.class);
	}
}
