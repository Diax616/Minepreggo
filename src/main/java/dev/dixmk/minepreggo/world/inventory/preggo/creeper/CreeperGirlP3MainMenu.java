package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP3;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP3MainMenu extends AbstractCreeperGirlMainMenu<TamableHumanoidCreeperGirlP3> {	
	public CreeperGirlP3MainMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_P3_MAIN_MENU.get(), id, inv, extraData, TamableHumanoidCreeperGirlP3.class);
	}
}
