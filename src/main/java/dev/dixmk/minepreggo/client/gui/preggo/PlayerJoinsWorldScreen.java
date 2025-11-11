package dev.dixmk.minepreggo.client.gui.preggo;

import java.util.ArrayList;
import java.util.List;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.client.gui.component.ToggleableCheckbox;
import dev.dixmk.minepreggo.network.capability.Gender;
import dev.dixmk.minepreggo.network.packet.UpdatePlayerDataC2SPacket;
import dev.dixmk.minepreggo.network.packet.UpdateShowPlayerMainMenuC2SPacket;
import dev.dixmk.minepreggo.world.inventory.preggo.PlayerJoinsWorldMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class PlayerJoinsWorldScreen extends AbstractContainerScreen<PlayerJoinsWorldMenu> {
	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/player_main_gui.png");

	protected final Level level;
	protected final Player player;
	
	private final List<ToggleableCheckbox> gender = new ArrayList<>();	
	protected ToggleableCheckbox female;
	protected ToggleableCheckbox male;

	public PlayerJoinsWorldScreen(PlayerJoinsWorldMenu container, Inventory inv, Component text) {
		super(container, inv, text);
		this.level = container.level;
		this.player = container.player;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.minepreggo.player_main_gui.label_minepreggo"), 59, 3, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.minepreggo.player_main_gui.label_gender"), 69, 16, -12829636, false);	
		
		if (!male.selected()) {
			guiGraphics.drawString(this.font, Component.translatable("gui.minepreggo.player_main_gui.label_skin"), 73, 80, -12829636, false);
		}
	}

	@Override
	public void init() {
		super.init();
		
		female = ToggleableCheckbox.builder(this.leftPos + 15, this.topPos + 43, 20, 20, Component.translatable("gui.minepreggo.player_main_gui.isFemaleCheckbox"), true)
				.group(gender)
				.build();		
					
		male = ToggleableCheckbox.builder(this.leftPos + 96, this.topPos + 43, 20, 20, Component.translatable("gui.minepreggo.player_main_gui.isMasculineCheckbox"), false)
				.group(gender)
				.build();	
			
		var customSkin = new Checkbox(this.leftPos + 15, this.topPos + 97, 20, 20,Component.translatable("gui.minepreggo.player_main_gui.isCustomSkinCheckBox"), false) {		
			@Override
			public void onPress() {
				if (!male.selected()) {
					super.onPress();					
				}
			}
			
			@Override
			public void renderWidget(GuiGraphics p_283124_, int p_282925_, int p_282705_, float p_282612_) {
				if (!male.selected()) {
					super.renderWidget(p_283124_, p_282925_, p_282705_, p_282612_);
				}
			}
		};
			
		var button = Button.builder(Component.literal("Ok"), e -> {						
			if ((female.selected())) {						
				MinepreggoModPacketHandler.INSTANCE.sendToServer(new UpdatePlayerDataC2SPacket(player.getUUID(), Gender.FEMALE, customSkin.selected()));
				MinepreggoModPacketHandler.INSTANCE.sendToServer(new UpdateShowPlayerMainMenuC2SPacket(player.getUUID(), false));		
				player.closeContainer();		
			}		
			else if (male.selected()) {		
				MinepreggoModPacketHandler.INSTANCE.sendToServer(new UpdatePlayerDataC2SPacket(player.getUUID(), Gender.MALE, false));
				MinepreggoModPacketHandler.INSTANCE.sendToServer(new UpdateShowPlayerMainMenuC2SPacket(player.getUUID(), false));		
				player.closeContainer();	
			}	
		}).bounds(this.leftPos + 67, this.topPos + 140, 35, 20).build();
		

		this.gender.add(female);
		this.gender.add(male);
		this.addRenderableWidget(female);	
		this.addRenderableWidget(male);	
		this.addRenderableWidget(customSkin);	
		this.addRenderableWidget(button);	
	}
}
