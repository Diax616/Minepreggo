package dev.dixmk.minepreggo.client.gui.preggo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.checkerframework.checker.nullness.qual.NonNull;

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
import dev.dixmk.minepreggo.world.entity.preggo.IPostPregnancyEntity;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMobData;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.inventory.preggo.AbstractPreggoMobMainMenu;
import dev.dixmk.minepreggo.world.pregnancy.Craving;
import dev.dixmk.minepreggo.world.pregnancy.IFemaleEntity;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancy;
import net.minecraft.client.gui.Font;
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
	
	protected void addPreggoMobCheckBoxes() {		
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

			this.addRenderableWidget(inventoryButton);	
			this.addRenderableWidget(sexButton);
			this.addRenderableWidget(wait);
			this.addRenderableWidget(follow);
			
			state.add(wait);
			state.add(follow);
		}, () -> {
			MinepreggoMod.LOGGER.error("preggoMob was null");
			this.minecraft.player.closeContainer();
			return;
		});	
	}
	
	protected void addPickUpLootCheckBox() {
		this.preggoMob.ifPresent(mob -> {		
			var pickUpItems = new Checkbox(this.leftPos + 6, this.topPos + 53, 20, 20, Component.translatable("gui.minepreggo.preggo_mob_main.checkbox_pickup"), canPickUpLoot) {
				@Override
				public void onPress() {
					super.onPress();
					MinepreggoModPacketHandler.INSTANCE.sendToServer(new UpdatePreggoMobPickUpItemC2SPacket(mob.getId(), this.selected));
				}
			};
			pickUpItems.setTooltip(Tooltip.create(Component.translatable("gui.minepreggo.preggo_mob_inventory.tooltip_checkbox_pickup")));
			this.addRenderableWidget(pickUpItems);
		});
	}
	
	protected void addBreakBlocksCheckBox() {
		this.preggoMob.ifPresent(mob -> {		
			var breakBlocks = new Checkbox(this.leftPos + 6, this.topPos + 77, 20, 20, Component.translatable("gui.minepreggo.preggo_mob_main.checkbox_break"), canBreakBlocks) {
				@Override
				public void onPress() {
					super.onPress();
					MinepreggoModPacketHandler.INSTANCE.sendToServer(new UpdatePreggoMobBreakBlocksC2SPacket(mob.getId(), this.selected));
				}
			};
			breakBlocks.setTooltip(Tooltip.create(Component.translatable("gui.minepreggo.preggo_mob_inventory.tooltip_checkbox_break")));
			this.addRenderableWidget(breakBlocks);
		});
	}
		
	public static<E extends PreggoMob & ITamablePreggoMob<?> & IPostPregnancyEntity> void renderScreenNonPreg(GuiGraphics guiGraphics, int leftPos, int topPos, @NonNull E preggoMob) {
		renderHungryAndHealth(guiGraphics, leftPos, topPos - 20, preggoMob);
		preggoMob.getSyncedPostPregnancy().ifPresent(state -> {
			if (state == PostPregnancy.PARTUM) {			
				renderPostPartumLactation(guiGraphics, leftPos, topPos - 11 - 20, preggoMob.getSyncedPostPartumLactation().orElse(0));
			}
		});	
	}
	
	public static<E extends PreggoMob & ITamablePregnantPreggoMob> void renderScreenP0(GuiGraphics guiGraphics, int leftPos, int topPos, @NonNull E preggoMob) {
		renderHungryAndHealth(guiGraphics, leftPos, topPos, preggoMob);		
	}
	
	public static<E extends PreggoMob & ITamablePregnantPreggoMob> void renderScreenP1(GuiGraphics guiGraphics, int leftPos, int topPos, @NonNull E preggoMob, Function<Craving, ResourceLocation> cravingIconProvider) {
		renderHungryAndHealth(guiGraphics, leftPos, topPos + 10, preggoMob);		
		renderScreenPhaseP1(guiGraphics, leftPos, topPos, preggoMob.getPregnancyData());	
		tryRenderCravingIcon(guiGraphics, leftPos, topPos, preggoMob, cravingIconProvider);	
	}
	
	public static<E extends PreggoMob & ITamablePregnantPreggoMob> void renderScreenP2(GuiGraphics guiGraphics, int leftPos, int topPos, @NonNull E preggoMob, Function<Craving, ResourceLocation> cravingIconProvider) {
		renderHungryAndHealth(guiGraphics, leftPos, topPos + 10, preggoMob);	
		renderScreenPhaseP1(guiGraphics, leftPos, topPos, preggoMob.getPregnancyData());	
		renderScreenPhaseP2(guiGraphics, leftPos, topPos + 5, preggoMob.getPregnancyData());	
		tryRenderCravingIcon(guiGraphics, leftPos, topPos - 5,  preggoMob, cravingIconProvider);
	}
	
	public static<E extends PreggoMob & ITamablePregnantPreggoMob> void renderScreenP3(GuiGraphics guiGraphics, int leftPos, int topPos, @NonNull E preggoMob, Function<Craving, ResourceLocation> cravingIconProvider) {
		renderHungryAndHealth(guiGraphics, leftPos, topPos + 10, preggoMob);
		renderScreenPhaseP1(guiGraphics, leftPos, topPos, preggoMob.getPregnancyData());		
		renderScreenPhaseP2(guiGraphics, leftPos, topPos + 5, preggoMob.getPregnancyData());		
		renderScreenPhaseP3(guiGraphics, leftPos, topPos + 4, preggoMob.getPregnancyData());		
		tryRenderCravingIcon(guiGraphics, leftPos, topPos - 5, preggoMob, cravingIconProvider);
	}
	
	public static<E extends PreggoMob & ITamablePregnantPreggoMob> void renderScreenP4(GuiGraphics guiGraphics, int leftPos, int topPos, @NonNull E preggoMob, Function<Craving, ResourceLocation> cravingIconProvider) {
		renderHungryAndHealth(guiGraphics, leftPos, topPos + 10, preggoMob);	
		renderScreenPhaseP1(guiGraphics, leftPos, topPos, preggoMob.getPregnancyData());		
		renderScreenPhaseP2(guiGraphics, leftPos, topPos + 5, preggoMob.getPregnancyData());		
		renderScreenPhaseP3(guiGraphics, leftPos, topPos + 4, preggoMob.getPregnancyData());	
		renderScreenPhaseP4(guiGraphics, leftPos, topPos, preggoMob.getPregnancyData());		
		tryRenderCravingIcon(guiGraphics, leftPos, topPos, preggoMob, cravingIconProvider);
	}
	
	private static void renderScreenPhaseP1(GuiGraphics guiGraphics, int leftPos, int topPos, ITamablePregnantPreggoMobData p1) {	
		final int craving = p1.getCraving();
		
		for (int i = 0, pos = 74; i < 10; i++, pos += 10) {
			guiGraphics.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, leftPos + pos, topPos + 98, 9, 9, 0, 35, 19, 19, 256, 256);		
		}	
	
		for (int i = 0, pos = 74, oddValue = 1, evenValue = 2; i < 10; ++i, pos += 10, oddValue += 2, evenValue += 2) {		

			if (craving >= evenValue) {
				guiGraphics.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, leftPos + pos, topPos + 98, 9, 9, 38, 35, 19, 19, 256, 256);
			} else if (craving >= oddValue) {
				guiGraphics.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, leftPos + pos, topPos + 98, 9, 9, 19, 35, 19, 19, 256, 256);
			}					
		}
	}
	
	private static void renderScreenPhaseP2(GuiGraphics gui, int leftPos, int topPos, ITamablePregnantPreggoMobData p2) {		

		final int milking = p2.getMilking();

		for (int i = 0, pos = 74, oddValue = 1, evenValue = 2; i < 10; ++i, pos += 10, oddValue += 2, evenValue += 2) {		
			gui.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, leftPos + pos, topPos + 103, 0, 24, 9, 9, 256, 256);		

			if (milking >= evenValue) {
				gui.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, leftPos + pos, topPos + 103, 18, 24, 9, 9, 256, 256);
			} else if (milking >= oddValue) {
				gui.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, leftPos + pos, topPos + 103, 9, 24, 9, 9, 256, 256);
			}			
		}	
	}
	
	private static void renderScreenPhaseP3(GuiGraphics gui, int leftPos, int topPos, ITamablePregnantPreggoMobData p3) {		

		final int bellyRubs = p3.getBellyRubs();

		for (int i = 0, pos = 74, oddValue = 1, evenValue = 2; i < 10; ++i, pos += 10, oddValue += 2, evenValue += 2) {		
			gui.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, leftPos + pos, topPos + 114, 0, 0, 9, 9, 256, 256);		
			if (bellyRubs >= evenValue) {
				gui.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, leftPos + pos, topPos + 114, 18, 0, 9, 9, 256, 256);
			} else if (bellyRubs >= oddValue) {
				gui.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, leftPos + pos, topPos + 114, 9, 0, 9, 9, 256, 256);
			}	
		}	
	}
	
	private static void renderScreenPhaseP4(GuiGraphics gui, int leftPos, int topPos, ITamablePregnantPreggoMobData p4) {		

		final int horny = p4.getHorny();

		for (int i = 0, pos = 74, oddValue = 1, evenValue = 2; i < 10; ++i, pos += 10, oddValue += 2, evenValue += 2) {		
			gui.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, leftPos + pos, topPos + 128, 9, 9, 0, 11, 16, 11, 256, 256);	
			if (horny >= evenValue) {
				gui.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, leftPos + pos, topPos + 128, 9, 9, 32, 11, 16, 11, 256, 256);
			} else if (horny >= oddValue) {
				gui.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, leftPos + pos, topPos + 128, 9, 9, 16, 11, 16, 11, 256, 256);
			}	
		}		
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob<IFemaleEntity>> void renderLabelsNonPreg(GuiGraphics guiGraphics, Font font, E p0, boolean syncedIsPregnant) {	
		guiGraphics.drawString(font, p0.getSimpleNameOrCustom(), 80, 4, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_state"), 75, 21, -12829636, false);
		if (syncedIsPregnant) {
			guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_maybe_pregnant"), 104, 21, -12829636, false);
		} else {
			guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_not_pregnant"), 104, 21, -12829636, false);
		}
	}
	
	public static<E extends PreggoMob & ITamablePregnantPreggoMob> void renderLabelsP0(GuiGraphics guiGraphics, Font font, E p0) {	
		guiGraphics.drawString(font, p0.getSimpleNameOrCustom(), 80, 4, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_state"), 75, 22, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_pregnant"), 107, 22, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_phase"), 75, 37, -12829636, false);
		guiGraphics.drawString(font, p0.getPregnancyData().getCurrentPregnancyPhase().toString(), 107, 37, -12829636, false);
	}
	
	public static<E extends PreggoMob & ITamablePregnantPreggoMob> void renderLabelsP1(GuiGraphics guiGraphics, Font font, E p1) {	
		guiGraphics.drawString(font, p1.getSimpleNameOrCustom(), 80, 4, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_state"), 75, 22, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_pregnant"), 107, 22, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_phase"), 75, 37, -12829636, false);
		guiGraphics.drawString(font, p1.getPregnancyData().getCurrentPregnancyPhase().toString(), 107, 37, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_craving"), 75, 51, -12829636, false);
		
		if (p1.getPregnancyData().getTypeOfCraving() == null) {
			guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_none"), 118, 51, -12829636, false);
		} 
	}
	
	public static<E extends PreggoMob & ITamablePregnantPreggoMob> void renderLabelsP2(GuiGraphics guiGraphics, Font font, E p2) {
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_phase"), 77, 31, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_state"), 77, 17, -12829636, false);
		guiGraphics.drawString(font, p2.getPregnancyData().getCurrentPregnancyPhase().toString(), 109, 31, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_pregnant"), 109, 17, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_craving"), 77, 45, -12829636, false);
		
		guiGraphics.drawString(font, p2.getSimpleNameOrCustom(), 80, 4, -12829636, false);

		if (p2.getPregnancyData().getTypeOfCraving() == null) {
			guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_none"), 118, 45, -12829636, false);
		} 
	}
	
	public static<E extends PreggoMob & ITamablePregnantPreggoMob> void renderLabelsP3(GuiGraphics guiGraphics, Font font, E p3) {
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_phase"), 75, 31, -12829636, false);
		guiGraphics.drawString(font, p3.getPregnancyData().getCurrentPregnancyPhase().toString(), 107, 31, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_state"), 75, 17, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_pregnant"), 107, 17, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_craving"), 75, 45, -12829636, false);

		guiGraphics.drawString(font, p3.getSimpleNameOrCustom(), 80, 4, -12829636, false);

		if (p3.getPregnancyData().getTypeOfCraving() == null) {
			guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_none"), 118, 45, -12829636, false);
		} 
	}
	
	public static<E extends PreggoMob & ITamablePregnantPreggoMob> void renderLabelsP4(GuiGraphics guiGraphics, Font font, E p4) {
		guiGraphics.drawString(font, p4.getSimpleNameOrCustom(), 80, 4, -12829636, false);	
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_phase"), 74, 35, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_craving"), 74, 51, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_state"), 74, 19, -12829636, false);
		guiGraphics.drawString(font, p4.getPregnancyData().getCurrentPregnancyPhase().toString(), 107, 35, -12829636, false);	
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_pregnant"), 106, 19, -12829636, false);
		
		if (p4.getPregnancyData().getTypeOfCraving() == null) {
			guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_none"), 118, 51, -12829636, false);
		} 
	}
	
	private static void renderCravingIcon(GuiGraphics guiGraphics, int leftPos, int topPos, @NonNull ResourceLocation icon) {
		guiGraphics.blit(icon, leftPos + 116, topPos + 45, 0, 0, 16, 16, 16, 16);
	}

	private static<E extends PreggoMob & ITamablePregnantPreggoMob> void tryRenderCravingIcon(GuiGraphics guiGraphics, int leftPos, int topPos, E preggoMob, Function<Craving, ResourceLocation> cravingIconProvider) {
		final var icon = cravingIconProvider.apply(preggoMob.getPregnancyData().getTypeOfCraving());
		if (icon != null) {
			renderCravingIcon(guiGraphics, leftPos, topPos, icon);
		}	
	}
	
	private static<E extends PreggoMob & ITamablePreggoMob<?>> void renderHungryAndHealth(GuiGraphics guiGraphics, int leftPos, int topPos, E preggoMob) {
	    final int totalHearts = (int) Math.ceil(preggoMob.getMaxHealth() / 2.0);
	    final int rows = (int) Math.ceil(totalHearts / 10.0);
	    
	    // Render hungry bar
	    for (int i = 0, pos = 74; i < 10; i++, pos += 10) {
	        guiGraphics.blit(ScreenHelper.MINECRAFT_ICONS_TEXTURE, leftPos + pos, topPos + 56, 16, 27, 9, 9, 256, 256);
	    }
	    
	    final var hungry = preggoMob.getTamableData().getFullness();
	    
	    for (int i = 0, pos = 74, oddValue = 1, evenValue = 2; i < 10; ++i, pos += 10, oddValue += 2, evenValue += 2) {
	        if (hungry >= evenValue) {
	            guiGraphics.blit(ScreenHelper.MINECRAFT_ICONS_TEXTURE, leftPos + pos, topPos + 56, 52, 27, 9, 9, 256, 256);
	        } else if (hungry >= oddValue) {
	            guiGraphics.blit(ScreenHelper.MINECRAFT_ICONS_TEXTURE, leftPos + pos, topPos + 56, 61, 27, 9, 9, 256, 256);
	        }
	    }
	    
	    // Render health bars in multiple rows
	    for (int row = 0; row < rows; row++) {
	        int yOffset = topPos + 67 + (row * 11);
	        int heartsInRow = Math.min(10, totalHearts - (row * 10));
	        
	        // Render empty hearts background
	        for (int i = 0; i < heartsInRow; i++) {
	            int xPos = leftPos + 74 + (i * 10);
	            guiGraphics.blit(ScreenHelper.MINECRAFT_ICONS_TEXTURE, xPos, yOffset, 16, 45, 9, 9, 256, 256);
	        }
	        
	        // Render filled hearts
	        for (int i = 0; i < heartsInRow; i++) {
	        	float health = preggoMob.getHealth();
	            int heartIndex = (row * 10) + i;
	            int xPos = leftPos + 74 + (i * 10);
	            float heartValue = heartIndex * 2;
	            
	            if (health >= heartValue + 2) {
	                guiGraphics.blit(ScreenHelper.MINECRAFT_ICONS_TEXTURE, xPos, yOffset, 52, 0, 9, 9, 256, 256);
	            } else if (health >= heartValue + 1) {
	                guiGraphics.blit(ScreenHelper.MINECRAFT_ICONS_TEXTURE, xPos, yOffset, 61, 0, 9, 9, 256, 256);
	            } else if (health <= 0.75F && heartIndex == 0) {
	                guiGraphics.blit(ScreenHelper.MINECRAFT_ICONS_TEXTURE, xPos, yOffset, 61, 0, 9, 9, 256, 256);
	                return;
	            }
	        }
	    }
	}
	
	private static void renderPostPartumLactation(GuiGraphics guiGraphics, int leftPos, int topPos, int milking) {
		for (int i = 0, pos = 74, oddValue = 1, evenValue = 2; i < 10; ++i, pos += 10, oddValue += 2, evenValue += 2) {		
			guiGraphics.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, leftPos + pos, topPos + 99, 0, 24, 9, 9, 256, 256);		

			if (milking >= evenValue) {
				guiGraphics.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, leftPos + pos, topPos + 99, 18, 24, 9, 9, 256, 256);
			} else if (milking >= oddValue) {
				guiGraphics.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, leftPos + pos, topPos + 99, 9, 24, 9, 9, 256, 256);
			}			
		}
	}
}

