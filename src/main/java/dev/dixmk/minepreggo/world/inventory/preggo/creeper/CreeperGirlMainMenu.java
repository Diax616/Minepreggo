package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirl;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlMainMenu extends AbstractCreeperGirlMainMenu<TamableCreeperGirl> {	
	public CreeperGirlMainMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_MAIN_MENU.get(), id, inv, extraData, TamableCreeperGirl.class);
	}
}

