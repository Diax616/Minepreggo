package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.CheckForNull;

import com.google.common.collect.ImmutableMap;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.client.gui.component.ToggleableCheckbox;
import dev.dixmk.minepreggo.client.gui.preggo.AbstractPreggoMobMainScreen;
import dev.dixmk.minepreggo.network.packet.c2s.UpdateCreeperGirlCombatModeC2SPacket;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl.CombatMode;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.AbstractCreeperGirlMainMenu;
import dev.dixmk.minepreggo.world.pregnancy.Craving;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractCreeperGirlMainScreen
	<E extends AbstractTamableCreeperGirl, G extends AbstractCreeperGirlMainMenu<E>> extends AbstractPreggoMobMainScreen<E, G> {
	
	protected static final ImmutableMap<Craving, ResourceLocation> CRAVING_ICONS = ImmutableMap.of(
			Craving.SALTY, MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/activated_gunpowder_with_salt.png"), 
			Craving.SWEET, MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/activated_gunpowder_with_chocolate.png"), 
			Craving.SOUR, MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/sour_activated_gunpowder.png"),
			Craving.SPICY, MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/activated_gunpowder_with_hot_sauce.png"));
					
	private final List<ToggleableCheckbox> combatModes = new ArrayList<>();
	protected final CombatMode currentCombatMode;
	
	protected AbstractCreeperGirlMainScreen(G container, Inventory inventory, Component text, int xSexSprite, int ySexSprite) {
		super(container, inventory, text, xSexSprite, ySexSprite);
		this.currentCombatMode = container.currentCombatMode.orElse(CombatMode.DONT_EXPLODE);
	}
	
	@Override
	public void init() {
		super.init();	
		this.addCombatModeCheckboxes();
	}	
	
	private void addCombatModeCheckboxes() {
		this.preggoMob.ifPresent(mob -> {
			final var creeperGirlId = mob.getId();
						
			var explodeCheckBox = ToggleableCheckbox.builder(this.leftPos + 190, this.topPos, 20, 20, Component.translatable("gui.minepreggo.creeper_girl_main.checkbox_explode"), currentCombatMode == CombatMode.EXPLODE)
					.group(combatModes)
					.onSelect(() -> MinepreggoModPacketHandler.INSTANCE.sendToServer(new UpdateCreeperGirlCombatModeC2SPacket(CombatMode.EXPLODE, creeperGirlId)))
					.build();
		
			var dontExplodeCheckBox = ToggleableCheckbox.builder(this.leftPos + 190, this.topPos + 36, 20, 20, Component.translatable("gui.minepreggo.creeper_girl_main.checkbox_dont_explode"), currentCombatMode == CombatMode.DONT_EXPLODE)
					.group(combatModes)
					.onSelect(() -> MinepreggoModPacketHandler.INSTANCE.sendToServer(new UpdateCreeperGirlCombatModeC2SPacket(CombatMode.DONT_EXPLODE, creeperGirlId)))
					.build();
			
			var fightAndExplodeCheckBox = ToggleableCheckbox.builder(this.leftPos + 190, this.topPos + 72, 20, 20, Component.translatable("gui.minepreggo.creeper_girl_main.checkbox_fight_and_explode"), currentCombatMode == CombatMode.FIGHT_AND_EXPLODE)
					.group(combatModes)
					.onSelect(() -> MinepreggoModPacketHandler.INSTANCE.sendToServer(new UpdateCreeperGirlCombatModeC2SPacket(CombatMode.FIGHT_AND_EXPLODE, creeperGirlId)))
					.build();
				
			this.addRenderableWidget(explodeCheckBox);
			this.addRenderableWidget(dontExplodeCheckBox);
			this.addRenderableWidget(fightAndExplodeCheckBox);
			
			combatModes.add(explodeCheckBox);
			combatModes.add(dontExplodeCheckBox);
			combatModes.add(fightAndExplodeCheckBox);
		});		
	}
	
	@CheckForNull
	public static ResourceLocation getCravingIcon(Craving craving) {
		if (craving == null) {
			return null;
		}
		return CRAVING_ICONS.get(craving);	
	}
}
