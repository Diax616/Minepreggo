package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import net.minecraft.world.entity.player.Inventory;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP0;
import net.minecraft.network.FriendlyByteBuf;

public class CreeperGirlP0MainMenu extends AbstractCreeperGirlMainMenu<TamableHumanoidCreeperGirlP0> {	
	public CreeperGirlP0MainMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_P0_MAIN_MENU.get(), id, inv, extraData, TamableHumanoidCreeperGirlP0.class);
	}
}
