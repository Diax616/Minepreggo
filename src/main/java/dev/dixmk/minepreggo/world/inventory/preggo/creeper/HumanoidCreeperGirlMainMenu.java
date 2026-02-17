package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import java.util.Optional;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirl;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class HumanoidCreeperGirlMainMenu extends AbstractCreeperGirlMainMenu<TamableHumanoidCreeperGirl> {	
	
	public final Optional<Boolean> pregnant;
	
	public HumanoidCreeperGirlMainMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_MAIN_MENU.get(), id, inv, extraData, TamableHumanoidCreeperGirl.class);
	
		if (extraData != null) {
			this.pregnant = Optional.of(extraData.readBoolean());
		} else {
			this.pregnant = Optional.empty();
		}
	}
}

