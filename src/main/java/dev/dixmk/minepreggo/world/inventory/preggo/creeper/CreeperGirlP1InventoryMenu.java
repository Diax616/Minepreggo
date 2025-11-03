package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP1;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP1InventoryMenu extends AbstractCreeperGirlInventoryMenu<TamableCreeperGirlP1> {
	public CreeperGirlP1InventoryMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_P1_INVENTORY_MENU.get(), id, inv, extraData, TamableCreeperGirlP1.class);
	}
}
