package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP7;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP7MainMenu extends AbstractCreeperGirlMainMenu<TamableHumanoidCreeperGirlP7> {	
	public CreeperGirlP7MainMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_P7_MAIN_MENU.get(), id, inv, extraData, TamableHumanoidCreeperGirlP7.class);
	}
}
