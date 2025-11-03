package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP4;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP4MainMenu extends AbstractZombieGirlMainMenu<TamableZombieGirlP4> {
	public ZombieGirlP4MainMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.ZOMBIE_GIRL_P4_MAIN_MENU.get(), id, inv, extraData, TamableZombieGirlP4.class);
	}
}

