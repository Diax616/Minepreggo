package dev.dixmk.minepreggo.world.inventory.preggo.ender;

import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamableEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamableMonsterEnderWoman;
import dev.dixmk.minepreggo.world.inventory.preggo.AbstractPreggoMobInventaryMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;

public abstract class AbstractEnderWomanInventoryMenu<E extends AbstractTamableEnderWoman> extends AbstractPreggoMobInventaryMenu<E> {

	protected AbstractEnderWomanInventoryMenu(MenuType<?> container, int id, Inventory inv, FriendlyByteBuf extraData, Class<E> preggoMobClass) {
		super(container, id, inv, extraData, AbstractTamableEnderWoman.INVENTORY_SIZE, preggoMobClass);
	}

	public abstract static class AbstractMonsterInventoryMenu<E extends AbstractTamableMonsterEnderWoman> extends AbstractEnderWomanInventoryMenu<E> {
		protected AbstractMonsterInventoryMenu(MenuType<?> container, int id, Inventory inv, FriendlyByteBuf extraData, Class<E> preggoMobClass) {
			super(container, id, inv, extraData, preggoMobClass);
		}
		

	}
}
