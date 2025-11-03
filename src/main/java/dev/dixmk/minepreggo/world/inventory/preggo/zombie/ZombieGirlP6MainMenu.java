package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP6;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP6MainMenu extends AbstractZombieGirlMainMenu<TamableZombieGirlP6> {
	public ZombieGirlP6MainMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.ZOMBIE_GIRL_P6_MAIN_MENU.get(), id, inv, extraData, TamableZombieGirlP6.class);
	}
}
