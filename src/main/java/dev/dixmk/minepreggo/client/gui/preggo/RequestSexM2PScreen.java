package dev.dixmk.minepreggo.client.gui.preggo;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.client.gui.ScreenHelper;
import dev.dixmk.minepreggo.network.packet.c2s.ResponseSexRequestM2PC2SPacket;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableMonsterCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamableMonsterEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.RequestSexM2PMenu;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Button.OnPress;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RequestSexM2PScreen extends AbstractRequestSexScreen<PreggoMob, Player, RequestSexM2PMenu> {	
	private Component message = null;
	private boolean isMonster = false;
	private float uOffset = 0;
	private float vOffset = 0;
	private int uWidth = 16;
	private int vHeight = 16;
	
	public RequestSexM2PScreen(RequestSexM2PMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);	
		source.ifPresent(s -> {	
			if (s instanceof AbstractTamableZombieGirl zombieGirl) {
				this.vOffset = 137;
				if (this.isTamablePregnantAndIsHorny(zombieGirl)) {
					this.uOffset = 17;
				}
				else {			
					this.uOffset = 1;
				}
			}
			else if (s instanceof AbstractTamableHumanoidCreeperGirl creeperGirl) {
				this.vOffset = 105;
				if (this.isTamablePregnantAndIsHorny(creeperGirl)) {
					this.uOffset = 17;
				}
				else {			
					this.uOffset = 1;
				}
			}		
			else if (s instanceof AbstractTamableMonsterCreeperGirl creeperGirl) {
				this.vOffset = 201;
				if (this.isTamablePregnantAndIsHorny(creeperGirl)) {
					this.uOffset = 17;
				}
				else {			
					this.uOffset = 1;
				}
				this.isMonster = true;
			}	
			else if (s instanceof AbstractTamableMonsterEnderWoman enderWoman) {
				this.vOffset = 169;
				if (this.isTamablePregnantAndIsHorny(enderWoman)) {
					this.uOffset = 17;
				}
				else {			
					this.uOffset = 1;
				}
				this.isMonster = true;
			}
		});
		
		if (isMonster) {
			message = Component.translatable("gui.minepreggo.sex_request.preggo_mob.horny.label.message.monster");
		}
		else {
			message = createRandomHornyMessage();
		}	
	}
	
	@Override
	protected void renderRequestorIcon(GuiGraphics guiGraphics) {
		guiGraphics.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, this.leftPos + 8, this.topPos + 27, 24, 24, this.uOffset, this.vOffset, this.uWidth, this.vHeight, 256, 256);	
	}
	
	@Override
	protected void renderRequestorMessage(GuiGraphics guiGraphics) {
		if (message != null) {
			guiGraphics.drawString(this.font, this.message, 35, 40, -12829636, false);
		}
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
	
	private boolean isTamablePregnantAndIsHorny(PreggoMob preggoMob) {
		return preggoMob instanceof ITamablePregnantPreggoMob pregnancySystemHandler
				&& pregnancySystemHandler.getPregnancyData().getPregnancySymptoms().containsPregnancySymptom(PregnancySymptom.HORNY);
	}
}
