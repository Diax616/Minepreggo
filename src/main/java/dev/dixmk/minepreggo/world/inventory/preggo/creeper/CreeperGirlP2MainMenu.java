package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP2;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP2MainMenu extends AbstractCreeperGirlMainMenu<TamableCreeperGirlP2> {	
	public CreeperGirlP2MainMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_P2_MAIN_MENU.get(), id, inv, extraData, TamableCreeperGirlP2.class);
	}
}
