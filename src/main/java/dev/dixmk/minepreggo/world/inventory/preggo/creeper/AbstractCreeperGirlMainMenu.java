package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.AbstractPreggoMobMainMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;

public abstract class AbstractCreeperGirlMainMenu<E extends AbstractTamableCreeperGirl<?>> extends AbstractPreggoMobMainMenu<E> {

	protected AbstractCreeperGirlMainMenu(MenuType<?> menuType, int id, Inventory inv, FriendlyByteBuf extraData, Class<E> creeperGirlClass) {
		super(menuType, id, inv, extraData, creeperGirlClass);
	}
}
