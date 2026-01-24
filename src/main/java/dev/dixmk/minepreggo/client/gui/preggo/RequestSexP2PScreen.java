package dev.dixmk.minepreggo.client.gui.preggo;

import org.apache.commons.lang3.tuple.ImmutablePair;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.network.packet.c2s.ResponseSexRequestP2PC2SPacket;
import dev.dixmk.minepreggo.world.inventory.preggo.RequestSexP2PMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Button.OnPress;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RequestSexP2PScreen extends AbstractRequestSexScreen<Player, Player, RequestSexP2PMenu> {
	private ResourceLocation icon = null;
	
	public RequestSexP2PScreen(RequestSexP2PMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);		
		source.ifPresent(s -> {
			if (s instanceof AbstractClientPlayer a) {
				this.icon = a.getSkinTextureLocation();
			}
		});
	}

	@Override
	protected void renderRequestorIcon(GuiGraphics guiGraphics) {
		if (icon != null) {
			guiGraphics.blit(icon, this.leftPos + 8, this.topPos + 27, 24, 24, 8, 8, 8, 8, 64, 64);	
		}	
	}

	@Override
	protected void renderRequestorMessage(GuiGraphics guiGraphics) {
		guiGraphics.drawString(this.font, Component.translatable("gui.minepreggo.sex_request.label.message"), 35, 40, -12829636, false);	
	}
	
	@Override
	protected ImmutablePair<Button, Button> createButtons() {		
		OnPress yeahAction = e -> {
			source.ifPresent(s -> 
				target.ifPresent(t -> 
					MinepreggoModPacketHandler.INSTANCE.sendToServer(new ResponseSexRequestP2PC2SPacket(s.getId(), t.getId(), true)))
			);		
			player.closeContainer();	
		};

		OnPress nopeAction = e -> {			
			source.ifPresent(s -> 
				target.ifPresent(t -> 
					MinepreggoModPacketHandler.INSTANCE.sendToServer(new ResponseSexRequestP2PC2SPacket(s.getId(), t.getId(), false)))
			);			
			player.closeContainer();
		};	
		
		var yeahButton = Button.builder(Component.translatable("gui.minepreggo.sex_request.button.yeah"), yeahAction)
				.bounds(this.leftPos + 44, this.topPos + 76, 46, 20).build();
		
		var nopeButton = Button.builder(Component.translatable("gui.minepreggo.sex_request.button.nope"), nopeAction)
				.bounds(this.leftPos + 125, this.topPos + 76, 46, 20).build();
		
		return ImmutablePair.of(yeahButton, nopeButton);
	}
	
	@Override
	protected boolean renderTargetName() {
		return true;
	}
}

