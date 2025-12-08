package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP5;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP5InventoryMenu extends AbstractCreeperGirlInventoryMenu<TamableHumanoidCreeperGirlP5> {
	public CreeperGirlP5InventoryMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_P5_INVENTORY_MENU.get(), id, inv, extraData, TamableHumanoidCreeperGirlP5.class);
	}
}