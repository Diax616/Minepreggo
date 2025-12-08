package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP1;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP1MainMenu extends AbstractCreeperGirlMainMenu<TamableHumanoidCreeperGirlP1> {	
	public CreeperGirlP1MainMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_P1_MAIN_MENUI.get(), id, inv, extraData, TamableHumanoidCreeperGirlP1.class);
	}
}