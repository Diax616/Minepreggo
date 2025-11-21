package dev.dixmk.minepreggo.network.chat;

import org.checkerframework.checker.nullness.qual.NonNull;

import com.google.common.collect.ImmutableMap;

import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyPhase;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class MessageHelper {

	private MessageHelper() {}
	
	private static final Int2ObjectMap<String> PLAYER_ARMOR_MESSAGES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(
			0, "chat.minepreggo.player.armor.message.chestplate_p0_does_not_fit",
			1, "chat.minepreggo.player.armor.message.chestplate_p1_does_not_fit",
			2, "chat.minepreggo.player.armor.message.chestplate_p2_does_not_fit",
			3, "chat.minepreggo.player.armor.message.chestplate_p3_does_not_fit",
			4, "chat.minepreggo.player.armor.message.chestplate_p4_does_not_fit",
			5, "chat.minepreggo.player.armor.message.chestplate_p5_does_not_fit",
			6, "chat.minepreggo.player.armor.message.chestplate_p6_does_not_fit",
			7, "chat.minepreggo.player.armor.message.chestplate_p7_does_not_fit",
			8, "chat.minepreggo.player.armor.message.chestplate_p8_does_not_fit"
	));
	
	private static final Int2ObjectMap<String> PREGGO_MOB_ARMOR_MESSAGES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(
			0, "chat.minepreggo.preggo_mob.armor.message.chestplate_p0_does_not_fit",
			1, "chat.minepreggo.preggo_mob.armor.message.chestplate_p1_does_not_fit",
			2, "chat.minepreggo.preggo_mob.armor.message.chestplate_p2_does_not_fit",
			3, "chat.minepreggo.preggo_mob.armor.message.chestplate_p3_does_not_fit",
			4, "chat.minepreggo.preggo_mob.armor.message.chestplate_p4_does_not_fit",
			5, "chat.minepreggo.preggo_mob.armor.message.chestplate_p5_does_not_fit",
			6, "chat.minepreggo.preggo_mob.armor.message.chestplate_p6_does_not_fit",
			7, "chat.minepreggo.preggo_mob.armor.message.chestplate_p7_does_not_fit",
			8, "chat.minepreggo.preggo_mob.armor.message.chestplate_p8_does_not_fit"
	));
	
	
	public static Component getPlayerArmorChestMessage(PregnancyPhase stage) {
		return Component.translatable(PLAYER_ARMOR_MESSAGES.get(stage.ordinal()));
	}
	
	public static Component getPreggoMobArmorChestMessage(PregnancyPhase stage, String name) {
		return Component.translatable(PREGGO_MOB_ARMOR_MESSAGES.get(stage.ordinal()), name);
	}

	public static Component getPreggoMobArmorLeggingsMessage(String name) {
		return Component.translatable("chat.minepreggo.preggo_mob.armor.message.leggings_p1_does_not_fit", name);
	}
	
	public static<T extends PreggoMob & ITamablePreggoMob & IPregnancySystemHandler> void warningOwnerPossibleMiscarriageEvent(@NonNull T preggoMob) {	
		if (preggoMob.isTame() && preggoMob.getOwner() != null  && !preggoMob.level().isClientSide()) {		
			sendMessageToPlayer((Player) preggoMob.getOwner(), Component.translatable("chat.minepreggo.preggo_mob.pregnancy.message.miscarriage_warning", preggoMob.getName()));		
		}			
	}
	
	public static<T extends PreggoMob & ITamablePreggoMob & IPregnancySystemHandler> void warningOwnerPospartumEvent(@NonNull T preggoMob) {	
		if (preggoMob.isTame() && preggoMob.getOwner() != null && !preggoMob.level().isClientSide()) {		
			final var owner = (Player) preggoMob.getOwner();
			sendMessageToPlayer(owner, Component.translatable("chat.minepreggo.preggo_mob.message.post_partum", preggoMob.getSimpleName(), owner.getDisplayName().getString()));	
		}			
	}
	
	public static<T extends PreggoMob & ITamablePreggoMob & IPregnancySystemHandler> void warningOwnerMiscarriageEvent(@NonNull T preggoMob) {	
		if (preggoMob.isTame() && preggoMob.getOwner() != null && !preggoMob.level().isClientSide()) {		
			sendMessageToPlayer((Player) preggoMob.getOwner(), Component.translatable("chat.minepreggo.preggo_mob.message.post_miscarriage", preggoMob.getSimpleName()));	
		}			
	}
	
	public static void sendMessageToPlayer(Player player, Component component) {
		player.displayClientMessage(component, true);	
	}
}
