package dev.dixmk.minepreggo.client.gui.preggo;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.network.packet.ResponseSexRequestM2PC2SPacket;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.RequestSexM2PMenu;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancySystemHandler;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Button.OnPress;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RequestSexM2PScreen extends AbstractRequestSexScreen<PreggoMob, Player, RequestSexM2PMenu> {	
	private final Component message;
	private ResourceLocation icon;
	
	public RequestSexM2PScreen(RequestSexM2PMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);	
		source.ifPresent(s -> {	
			if (s instanceof AbstractTamableZombieGirl<?> zombieGirl) {
				if (zombieGirl instanceof IPregnancySystemHandler pregnancySystemHandler
						&& pregnancySystemHandler.getPregnancySymptoms().contains(PregnancySymptom.HORNY)) {
					this.icon = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_pain1.png");
				}
				else {			
					this.icon = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_horny2.png");
				}
			}
			else if (s instanceof AbstractTamableCreeperGirl<?> creeperGirl) {
				if (creeperGirl instanceof IPregnancySystemHandler pregnancySystemHandler
						&& pregnancySystemHandler.getPregnancySymptoms().contains(PregnancySymptom.HORNY)) {
					this.icon = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/expressions/creeper_girl_face_pain1.png");
				}
				else {			
					this.icon = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/expressions/creeper_girl_face_horny2.png");
				}
			}			
		});
		message = createRandomHornyMessage();
	}
	
	@Override
	protected void renderRequestorIcon(GuiGraphics guiGraphics) {
		if (this.icon != null) {
			guiGraphics.blit(this.icon, this.leftPos + 8, this.topPos + 27, 24, 24, 8, 8, 8, 8, 64, 96);	
		}
	}
	
	@Override
	protected void renderRequestorMessage(GuiGraphics guiGraphics) {
		guiGraphics.drawString(this.font, this.message, 35, 40, -12829636, false);
	}
	
	@Override
	protected ImmutablePair<Button, Button> createButtons() {	
		OnPress yeahAction = e -> {
			source.ifPresent(s -> 
				target.ifPresent(t -> 
					MinepreggoModPacketHandler.INSTANCE.sendToServer(new ResponseSexRequestM2PC2SPacket(s.getId(), t.getId(), true)))
			);		
			player.closeContainer();	
		};

		OnPress nopeAction = e -> {			
			source.ifPresent(s -> 
				target.ifPresent(t -> 
					MinepreggoModPacketHandler.INSTANCE.sendToServer(new ResponseSexRequestM2PC2SPacket(s.getId(), t.getId(), false)))
			);			
			player.closeContainer();
		};	
		
		var yeahButton = Button.builder(Component.translatable("gui.minepreggo.sex_request.button.now"), yeahAction)
				.bounds(this.leftPos + 44, this.topPos + 76, 46, 20).build();
		
		var nopeButton = Button.builder(Component.translatable("gui.minepreggo.sex_request.button.later"), nopeAction)
				.bounds(this.leftPos + 125, this.topPos + 76, 46, 20).build();
		
		return ImmutablePair.of(yeahButton, nopeButton);
	}
	
	private @NonNull Component createRandomHornyMessage() {		
		final var m =	target.map(owner -> {
								int id = owner.getRandom().nextInt(1, 4);
								return Component.translatable(String.format("gui.minepreggo.sex_request.preggo_mob.horny.label.message.%d", id), owner.getDisplayName().getString());
							});	
		return m.isPresent() ? m.get() : Component.translatable("gui.minepreggo.sex_request.label.message");
	}

	@Override
	protected boolean renderTargetName() {
		return true;
	}
}
