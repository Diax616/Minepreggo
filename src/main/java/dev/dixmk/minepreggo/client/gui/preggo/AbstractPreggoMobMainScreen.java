package dev.dixmk.minepreggo.client.gui.preggo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.client.gui.ScreenHelper;
import dev.dixmk.minepreggo.client.gui.component.ToggleableCheckbox;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.network.packet.c2s.RequestPreggoMobInventoryMenuC2SPacket;
import dev.dixmk.minepreggo.network.packet.c2s.RequestSexCinematicP2MC2SPacket;
import dev.dixmk.minepreggo.network.packet.c2s.UpdatePreggoMobBreakBlocksC2SPacket;
import dev.dixmk.minepreggo.network.packet.c2s.UpdatePreggoMobPickUpItemC2SPacket;
import dev.dixmk.minepreggo.network.packet.c2s.UpdatePreggoMobWaitC2SPacket;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.inventory.preggo.AbstractPreggoMobMainMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractPreggoMobMainScreen
	<E extends PreggoMob & ITamablePreggoMob<?>, M extends AbstractPreggoMobMainMenu<E>> extends AbstractContainerScreen<M> {
	
	protected static final ResourceLocation DEFAULT_P0_MAIN_GUI_TEXTURE = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/default_preggo_mob_p0_main_gui.png");
	protected static final ResourceLocation DEFAULT_P1_MAIN_GUI_TEXTURE = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/default_preggo_mob_p1_main_gui.png");
	protected static final ResourceLocation DEFAULT_P2_MAIN_GUI_TEXTURE = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/default_preggo_mob_p2_main_gui.png");
	protected static final ResourceLocation DEFAULT_P3_MAIN_GUI_TEXTURE = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/default_preggo_mob_p3_main_gui.png");
	protected static final ResourceLocation DEFAULT_P4_MAIN_GUI_TEXTURE = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/default_preggo_mob_p4_main_gui.png");
	
	protected final Level world;
	protected final int x;
	protected final int y;
	protected final int z;
	
	protected final int xSexSprite;
	protected final int ySexSprite;
	
	protected final Player entity;
	protected final Optional<E> preggoMob;
	protected final boolean canPickUpLoot;
	protected final boolean canBreakBlocks;
	protected ImageButton inventoryButton;
	protected ImageButton sexButton;
	
	private final List<ToggleableCheckbox> state = new ArrayList<>();
	
	protected AbstractPreggoMobMainScreen(M container, Inventory inventory, Component text, int xSexSprite, int ySexSprite) {
		super(container, inventory, text);	
		this.world = container.level;	
		var pos = container.getPos();
		
		if (pos.isPresent()) {
			this.x = pos.get().x;
			this.y = pos.get().y;
			this.z = pos.get().z;	
		}
		else {
			this.x = 0;
			this.y = 0;
			this.z = 0;
		}
		
		this.xSexSprite = xSexSprite;
		this.ySexSprite = ySexSprite;
		this.entity = container.player;
		this.preggoMob = container.getPreggoMob();
		this.canPickUpLoot = container.getCanPickUpLoot().orElse(false);
		this.canBreakBlocks = container.getCanBreakBlocks().orElse(false);
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);	
	}
	
	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}
	
	@Override
	public void tick() {
		super.tick();	
		this.preggoMob.ifPresent(e -> {
			if (e.isAggressive()) {
				this.minecraft.player.closeContainer();
			}
		});
	}
	
	@Override
	public void init() {
		super.init();
		this.addPreggoMobCheckBoxes();
	}	
	
	private void addPreggoMobCheckBoxes() {		
		this.preggoMob.ifPresentOrElse(mob -> {			
			final var isWaiting = mob.getTamableData().isWaiting();
			final var id = mob.getId();
					
			inventoryButton = new ImageButton(this.leftPos - 24, this.topPos + 6, 16, 16, 1, 57, 16, ScreenHelper.MINEPREGGO_ICONS_TEXTURE, 256, 256, 
					e -> MinepreggoModPacketHandler.INSTANCE.sendToServer(new RequestPreggoMobInventoryMenuC2SPacket(id)));	
			inventoryButton.setTooltip(Tooltip.create(Component.translatable("gui.minepreggo.preggo_mob_inventory.tooltip_inventory")));
								
			sexButton = new ImageButton(this.leftPos - 24, this.topPos + 32, 16, 16, this.xSexSprite, this.ySexSprite, 16, ScreenHelper.MINEPREGGO_ICONS_TEXTURE, 256, 256, 
					e -> {
						MinepreggoModPacketHandler.INSTANCE.sendToServer(new RequestSexCinematicP2MC2SPacket(id));
						this.minecraft.player.closeContainer();
					});							
			sexButton.setTooltip(Tooltip.create(Component.translatable("gui.minepreggo.preggo_mob_inventory.tooltip_sex")));
				
			var wait = ToggleableCheckbox.builder(this.leftPos + 6, this.topPos + 5, 20, 20, Component.translatable("gui.minepreggo.preggo_mob_main.checkbox_wait"), isWaiting)
					.group(state)
					.onSelect(() -> MinepreggoModPacketHandler.INSTANCE.sendToServer(new UpdatePreggoMobWaitC2SPacket(id, true)))
					.build();
		
			var follow = ToggleableCheckbox.builder(this.leftPos + 6, this.topPos + 29, 20, 20, Component.translatable("gui.minepreggo.preggo_mob_main.checkbox_follow"), !isWaiting)
					.group(state)
					.onSelect(() -> MinepreggoModPacketHandler.INSTANCE.sendToServer(new UpdatePreggoMobWaitC2SPacket(id, false)))
					.build();
			
			var pickUpItems = new Checkbox(this.leftPos + 6, this.topPos + 53, 20, 20, Component.translatable("gui.minepreggo.preggo_mob_main.checkbox_pickup"), canPickUpLoot) {
				@Override
				public void onPress() {
					super.onPress();
					MinepreggoModPacketHandler.INSTANCE.sendToServer(new UpdatePreggoMobPickUpItemC2SPacket(id, this.selected));
				}
			};
			pickUpItems.setTooltip(Tooltip.create(Component.translatable("gui.minepreggo.preggo_mob_inventory.tooltip_checkbox_pickup")));

					
			var breakBlocks = new Checkbox(this.leftPos + 6, this.topPos + 77, 20, 20, Component.translatable("gui.minepreggo.preggo_mob_main.checkbox_break"), canBreakBlocks) {
				@Override
				public void onPress() {
					super.onPress();
					MinepreggoModPacketHandler.INSTANCE.sendToServer(new UpdatePreggoMobBreakBlocksC2SPacket(id, this.selected));
				}
			};
			breakBlocks.setTooltip(Tooltip.create(Component.translatable("gui.minepreggo.preggo_mob_inventory.tooltip_checkbox_break")));

			
			this.addRenderableWidget(inventoryButton);	
			this.addRenderableWidget(sexButton);
			this.addRenderableWidget(wait);
			this.addRenderableWidget(follow);
			this.addRenderableWidget(pickUpItems);
			this.addRenderableWidget(breakBlocks);	
			
			state.add(wait);
			state.add(follow);
		}, () -> {
			MinepreggoMod.LOGGER.error("preggoMob was null");
			this.minecraft.player.closeContainer();
			return;
		});	
	}
}

