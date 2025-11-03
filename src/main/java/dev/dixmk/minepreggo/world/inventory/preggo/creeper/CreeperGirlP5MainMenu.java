package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP5;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP5MainMenu extends AbstractCreeperGirlMainMenu<TamableCreeperGirlP5> {	
	public CreeperGirlP5MainMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_P5_MAIN_MENUI.get(), id, inv, extraData, TamableCreeperGirlP5.class);
	}
}
