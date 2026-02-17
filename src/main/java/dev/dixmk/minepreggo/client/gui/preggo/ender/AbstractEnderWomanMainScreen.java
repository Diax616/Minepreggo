package dev.dixmk.minepreggo.client.gui.preggo.ender;

import javax.annotation.CheckForNull;

import com.google.common.collect.ImmutableMap;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.client.gui.preggo.AbstractPreggoMobMainScreen;
import dev.dixmk.minepreggo.network.packet.c2s.MountEnderWomanC2SPacket;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamableEnderWoman;
import dev.dixmk.minepreggo.world.inventory.preggo.ender.AbstractEnderWomanMainMenu;
import dev.dixmk.minepreggo.world.pregnancy.Craving;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractEnderWomanMainScreen 
	<E extends AbstractTamableEnderWoman, G extends AbstractEnderWomanMainMenu<E>> extends AbstractPreggoMobMainScreen<E, G> {

	private static final ImmutableMap<Craving, ResourceLocation> CRAVING_ICONS = ImmutableMap.of(
			Craving.SALTY, MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/ender_slime_jelly_with_salt.png"), 
			Craving.SWEET, MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/ender_slime_jelly_with_chocolate.png"), 
			Craving.SOUR, MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/sour_ender_slime_jelly.png"),
			Craving.SPICY, MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/ender_slime_jelly_with_hot_sauce.png"));
	
	protected AbstractEnderWomanMainScreen(G container, Inventory inventory, Component text) {
		super(container, inventory, text, 1, 153);
	}

	@CheckForNull
	public static ResourceLocation getCravingIcon(Craving craving) {
		if (craving == null) {
			return null;
		}		
		return CRAVING_ICONS.get(craving);
	}
	
	@Override
	public void init() {
		super.init();	
		this.preggoMob.ifPresent(mob -> {
			Button ride = Button.builder(Component.translatable("gui.minepreggo.preggo_mob_main.button.mount"), e -> {
				MinepreggoModPacketHandler.INSTANCE.sendToServer(new MountEnderWomanC2SPacket(mob.getId()));
				this.minecraft.player.closeContainer();
			}).bounds(this.leftPos -53, this.topPos + 59, 51, 20).build();
			this.addRenderableWidget(ride);
			this.addPickUpLootCheckBox();
		});
	}	
}
