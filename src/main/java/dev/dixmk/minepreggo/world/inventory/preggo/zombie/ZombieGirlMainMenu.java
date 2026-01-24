package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import com.machinezoo.noexception.optional.OptionalBoolean;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirl;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlMainMenu extends AbstractZombieGirlMainMenu<TamableZombieGirl> {
	
	public final OptionalBoolean pregnant;
	
	public ZombieGirlMainMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.ZOMBIE_GIRL_MAIN_MENU.get(), id, inv, extraData, TamableZombieGirl.class);
		
		if (extraData != null) {
			this.pregnant = OptionalBoolean.of(extraData.readBoolean());
		} else {
			this.pregnant = OptionalBoolean.empty();
		}
	}
}
