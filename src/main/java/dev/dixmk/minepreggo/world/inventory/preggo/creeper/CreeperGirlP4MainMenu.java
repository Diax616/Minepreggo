package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP4;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP4MainMenu extends AbstractCreeperGirlMainMenu<TamableCreeperGirlP4> {	
	public CreeperGirlP4MainMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_P4_MAIN_MENU.get(), id, inv, extraData, TamableCreeperGirlP4.class);
	}
}
