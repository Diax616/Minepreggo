package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import com.machinezoo.noexception.optional.OptionalBoolean;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirl;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlMainMenu extends AbstractCreeperGirlMainMenu<TamableHumanoidCreeperGirl> {	
	
	public final OptionalBoolean pregnant;
	
	public CreeperGirlMainMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_MAIN_MENU.get(), id, inv, extraData, TamableHumanoidCreeperGirl.class);
	
		if (extraData != null) {
			this.pregnant = OptionalBoolean.of(extraData.readBoolean());
		} else {
			this.pregnant = OptionalBoolean.empty();
		}
	}
}

