package dev.dixmk.minepreggo.network.chat;

import java.util.function.UnaryOperator;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;

import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class MessageHelper {

	private MessageHelper() {}

	private static final ImmutableMap<PregnancyPhase, String> PLAYER_ARMOR_MESSAGES;	
	private static final ImmutableMap<PregnancyPhase, String> PREGGO_MOB_ARMOR_MESSAGES;
	
	static {			
		UnaryOperator<String> preggoMobArmorTemplate = phase -> String.format("chat.minepreggo.preggo_mob.armor.message.chestplate_does_not_fit.p%s", phase);
		UnaryOperator<String> playerArmorTemplate = phase -> String.format("chat.minepreggo.player.armor.message.chestplate_does_not_fit.p%s", phase);
	
		PLAYER_ARMOR_MESSAGES = ImmutableMap.of(
				PregnancyPhase.P1, playerArmorTemplate.apply("1"),
				PregnancyPhase.P2, playerArmorTemplate.apply("2"),
				PregnancyPhase.P3, playerArmorTemplate.apply("3"),
				PregnancyPhase.P4, playerArmorTemplate.apply("4"),
				PregnancyPhase.P5, playerArmorTemplate.apply("5"),
				PregnancyPhase.P6, playerArmorTemplate.apply("6"),
				PregnancyPhase.P7, playerArmorTemplate.apply("7"),
				PregnancyPhase.P8, playerArmorTemplate.apply("8")
				);
			
		PREGGO_MOB_ARMOR_MESSAGES = ImmutableMap.of(
				PregnancyPhase.P1, preggoMobArmorTemplate.apply("1"),
				PregnancyPhase.P2, preggoMobArmorTemplate.apply("2"),
				PregnancyPhase.P3, preggoMobArmorTemplate.apply("3"),
				PregnancyPhase.P4, preggoMobArmorTemplate.apply("4"),
				PregnancyPhase.P5, preggoMobArmorTemplate.apply("5"),
				PregnancyPhase.P6, preggoMobArmorTemplate.apply("6"),
				PregnancyPhase.P7, preggoMobArmorTemplate.apply("7"),
				PregnancyPhase.P8, preggoMobArmorTemplate.apply("8")			
				);
	}

	public static boolean warnFittedArmor(Player player, PregnancyPhase pregnancyPhase) {
		return warnFittedArmor(player, pregnancyPhase, true);
	}
	
	public static boolean warnFittedArmor(Player player, PregnancyPhase pregnancyPhase, boolean bar) {
		ServerPlayer serverPlayer;
		String message;
		if ((message = PLAYER_ARMOR_MESSAGES.get(pregnancyPhase)) != null && (serverPlayer = asServerPlayer(player)) != null) {
			sendTo(serverPlayer, Component.translatable(message), bar);	
			return true;
		}			
	
		return false;
	}
	
	public static boolean warnFittedArmor(Player owner, PreggoMob preggoMob, PregnancyPhase pregnancyPhase) {
		ServerPlayer serverPlayer;
		String message;
		if (preggoMob.isOwnedBy(owner) && (message = PREGGO_MOB_ARMOR_MESSAGES.get(pregnancyPhase)) != null && (serverPlayer = asServerPlayer(owner)) != null) {
			sendTo(serverPlayer, Component.translatable(message, preggoMob.getSimpleName()));		
			return true;
		}				
		return false;
	}
	
	@Nullable
	public static ServerPlayer asServerPlayer(Player player) {
		if (player instanceof ServerPlayer sp && !sp.level().isClientSide) {
			return sp;
		}
		return null;
	}

    public static void sendTo(ServerPlayer player, Component message) {
    	sendTo(player, message, false);
    }
      
    public static void sendTo(ServerPlayer player, PreggoMob preggoMob, Component message) {
        if (player != null && preggoMob.isOwnedBy(player) && !player.level().isClientSide) {
            sendTo(player, message, false);
        }
    }
    
    public static void sendTo(ServerPlayer player, Component message, boolean bar) {
        if (player != null && !player.level().isClientSide) {
            player.displayClientMessage(message, bar);
        }
    }
}
