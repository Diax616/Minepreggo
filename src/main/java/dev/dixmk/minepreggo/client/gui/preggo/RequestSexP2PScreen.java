package dev.dixmk.minepreggo.client.gui.preggo;

import org.apache.commons.lang3.tuple.Pair;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.network.packet.ResponseSexRequestP2PC2SPacket;
import dev.dixmk.minepreggo.world.inventory.preggo.RequestSexP2PMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button.OnPress;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

public class RequestSexP2PScreen extends AbstractRequestSexScreen<Player, Player, RequestSexP2PMenu> {
	private int xSexSprite = 0; 
	private int ySexSprite = 0;
	private ResourceLocation icon = null;
	
	public RequestSexP2PScreen(RequestSexP2PMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);		
		source.ifPresent(s -> {
			if (s instanceof AbstractClientPlayer a) {
				this.xSexSprite = 8;
				this.ySexSprite = 8;	
				this.icon = a.getSkinTextureLocation();
			}
		});
	}

	@Override
	protected void renderComponents(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		if (icon != null) {
			guiGraphics.blit(icon, this.leftPos + 38, this.topPos + 27, 24, 24, xSexSprite, ySexSprite, 8, 8, 64, 64);	
		}	
	}

	@Override
	protected Pair<OnPress, OnPress> createActions() {		
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
		return Pair.of(yeahAction, nopeAction);
	}
}

