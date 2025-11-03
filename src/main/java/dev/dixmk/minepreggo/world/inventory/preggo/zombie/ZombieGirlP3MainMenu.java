package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP3;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP3MainMenu extends AbstractZombieGirlMainMenu<TamableZombieGirlP3> {
	public ZombieGirlP3MainMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.ZOMBIE_GIRL_P3_MAIN_MENU.get(), id, inv, extraData, TamableZombieGirlP3.class);
	}
}

