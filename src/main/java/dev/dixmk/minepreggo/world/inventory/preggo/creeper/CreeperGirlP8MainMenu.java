package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP8;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP8MainMenu extends AbstractCreeperGirlMainMenu<TamableCreeperGirlP8> {	
	public CreeperGirlP8MainMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_P8_MAIN_MENU.get(), id, inv, extraData, TamableCreeperGirlP8.class);
	}
}

