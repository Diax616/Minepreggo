package dev.dixmk.minepreggo.world.inventory.preggo.ender;

import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamableEnderWoman;
import dev.dixmk.minepreggo.world.inventory.preggo.AbstractPreggoMobMainMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;

public abstract class AbstractEnderWomanMainMenu<E extends AbstractTamableEnderWoman> extends AbstractPreggoMobMainMenu<E> {

	protected AbstractEnderWomanMainMenu(MenuType<?> p_38851_, int p_38852_, Inventory inv, FriendlyByteBuf extraData, Class<E> preggoMobClass) {
		super(p_38851_, p_38852_, inv, extraData, preggoMobClass);
	}

}
