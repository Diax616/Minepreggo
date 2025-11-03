package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import java.util.ArrayList;
import java.util.List;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.client.gui.component.ToggleableCheckbox;
import dev.dixmk.minepreggo.client.gui.preggo.AbstractPreggoMobMainScreen;
import dev.dixmk.minepreggo.network.packet.UpdateCreeperGirlCombatModePacket;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl.CombatMode;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.AbstractCreeperGirlMainMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public abstract class AbstractCreeperGirlMainScreen
	<E extends AbstractTamableCreeperGirl<?>, G extends AbstractCreeperGirlMainMenu<E>> extends AbstractPreggoMobMainScreen<E, G> {
	
	private final List<ToggleableCheckbox> combatModes = new ArrayList<>();

	protected AbstractCreeperGirlMainScreen(G container, Inventory inventory, Component text) {
		super(container, inventory, text, 1, 89);	
	}

	@Override
	public void init() {
		super.init();							
		this.addCombatModeCheckboxes();
	}	
	
	private void addCombatModeCheckboxes() {
		this.preggoMob.ifPresent(mob -> {
			final var currentCombatMode = mob.getcombatMode();
			final var creeperGirlId = mob.getId();
						
			var explodeCheckBox = ToggleableCheckbox.builder(this.leftPos + 190, this.topPos, 20, 20, Component.translatable("gui.minepreggo.creeper_girl_main.checkbox_explode"), currentCombatMode == CombatMode.EXPLODE)
					.group(combatModes)
					.onSelect(() -> MinepreggoModPacketHandler.INSTANCE.sendToServer(new UpdateCreeperGirlCombatModePacket(x, y, z, CombatMode.EXPLODE, creeperGirlId)))
					.build();
		
			var dontExplodeCheckBox = ToggleableCheckbox.builder(this.leftPos + 190, this.topPos + 36, 20, 20, Component.translatable("gui.minepreggo.creeper_girl_main.checkbox_dont_explode"), currentCombatMode == CombatMode.DONT_EXPLODE)
					.group(combatModes)
					.onSelect(() -> MinepreggoModPacketHandler.INSTANCE.sendToServer(new UpdateCreeperGirlCombatModePacket(x, y, z, CombatMode.DONT_EXPLODE, creeperGirlId)))
					.build();
			
			var fightAndExplodeCheckBox = ToggleableCheckbox.builder(this.leftPos + 190, this.topPos + 72, 20, 20, Component.translatable("gui.minepreggo.creeper_girl_main.checkbox_fight_and_explode"), currentCombatMode == CombatMode.FIGHT_AND_EXPLODE)
					.group(combatModes)
					.onSelect(() -> MinepreggoModPacketHandler.INSTANCE.sendToServer(new UpdateCreeperGirlCombatModePacket(x, y, z, CombatMode.FIGHT_AND_EXPLODE, creeperGirlId)))
					.build();
				
			this.addRenderableWidget(explodeCheckBox);
			this.addRenderableWidget(dontExplodeCheckBox);
			this.addRenderableWidget(fightAndExplodeCheckBox);
			
			combatModes.add(explodeCheckBox);
			combatModes.add(dontExplodeCheckBox);
			combatModes.add(fightAndExplodeCheckBox);
		});		
	}
}
