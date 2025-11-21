package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirl;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlInventoryMenu extends AbstractCreeperGirlInventoryMenu<TamableCreeperGirl> {
	public CreeperGirlInventoryMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_INVENTORY_MENU.get(), id, inv, extraData, TamableCreeperGirl.class);
	}
}
