package dev.dixmk.minepreggo.client.gui.preggo;

import org.apache.commons.lang3.tuple.Pair;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.client.gui.ScreenHelper;
import dev.dixmk.minepreggo.network.packet.ResponseSexRequestM2PC2SPacket;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractZombieGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.RequestSexM2PMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button.OnPress;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RequestSexM2PScreen extends AbstractRequestSexScreen<PreggoMob, Player, RequestSexM2PMenu> {
	
	private int xSexSprite = 0; 
	private int ySexSprite = 0;
	
	public RequestSexM2PScreen(RequestSexM2PMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);	
		source.ifPresent(s -> {
			if (s instanceof AbstractZombieGirl) {
				this.xSexSprite = 1;
				this.ySexSprite = 137;
			}
			else if (s instanceof AbstractCreeperGirl) {
				this.xSexSprite = 1;
				this.ySexSprite = 105;
			}
		});
	}

	@Override
	protected void renderComponents(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		guiGraphics.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, this.leftPos + 38, this.topPos + 27, 24, 24, xSexSprite, ySexSprite, 16, 16, 256, 256);	
	}

	@Override
	protected Pair<OnPress, OnPress> createActions() {	
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
		
		return Pair.of(yeahAction, nopeAction);
	}
}
