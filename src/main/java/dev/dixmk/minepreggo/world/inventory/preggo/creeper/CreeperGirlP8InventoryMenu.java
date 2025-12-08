package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP8;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP8InventoryMenu extends AbstractCreeperGirlInventoryMenu<TamableHumanoidCreeperGirlP8> {
	public CreeperGirlP8InventoryMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_P8_INVENTORY_MENU.get(), id, inv, extraData, TamableHumanoidCreeperGirlP8.class);
	}
}
