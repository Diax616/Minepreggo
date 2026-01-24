package dev.dixmk.minepreggo;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.network.packet.c2s.RequestBellyRubbingAnimationC2SPacket;
import dev.dixmk.minepreggo.network.packet.c2s.RequestPlayerMedicalCheckUpC2SPacket;
import dev.dixmk.minepreggo.network.packet.c2s.RequestPreggoMobInventoryMenuC2SPacket;
import dev.dixmk.minepreggo.network.packet.c2s.RequestPreggoMobMedicalCheckUpC2SPacket;
import dev.dixmk.minepreggo.network.packet.c2s.RequestSexCinematicP2MC2SPacket;
import dev.dixmk.minepreggo.network.packet.c2s.RequestSexCinematicP2PC2SPacket;
import dev.dixmk.minepreggo.network.packet.c2s.RequestSexM2PC2SPacket;
import dev.dixmk.minepreggo.network.packet.c2s.RequestSexP2PC2SPacket;
import dev.dixmk.minepreggo.network.packet.c2s.ResponseSexRequestM2PC2SPacket;
import dev.dixmk.minepreggo.network.packet.c2s.ResponseSexRequestP2PC2SPacket;
import dev.dixmk.minepreggo.network.packet.c2s.StopPlayerAnimationC2SPacket;
import dev.dixmk.minepreggo.network.packet.c2s.UpdateBellyRubbingStateC2SPacket;
import dev.dixmk.minepreggo.network.packet.c2s.UpdateCreeperGirlCombatModeC2SPacket;
import dev.dixmk.minepreggo.network.packet.c2s.UpdatePlayerDataC2SPacket;
import dev.dixmk.minepreggo.network.packet.c2s.UpdatePreggoMobBreakBlocksC2SPacket;
import dev.dixmk.minepreggo.network.packet.c2s.UpdatePreggoMobPickUpItemC2SPacket;
import dev.dixmk.minepreggo.network.packet.c2s.UpdatePreggoMobWaitC2SPacket;
import dev.dixmk.minepreggo.network.packet.c2s.UpdateShowPlayerMainMenuC2SPacket;
import dev.dixmk.minepreggo.network.packet.s2c.RemovePlayerJigglePhysicsS2CPacket;
import dev.dixmk.minepreggo.network.packet.s2c.RemoveMobEffectS2CPacket;
import dev.dixmk.minepreggo.network.packet.s2c.RemovePostPregnancyDataS2CPacket;
import dev.dixmk.minepreggo.network.packet.s2c.RemovePreggoMobJigglePhysicsS2CPacket;
import dev.dixmk.minepreggo.network.packet.s2c.RenderSexOverlayS2CPacket;
import dev.dixmk.minepreggo.network.packet.s2c.ResetPregnancyS2CPacket;
import dev.dixmk.minepreggo.network.packet.s2c.SexCinematicControlP2MS2CPacket;
import dev.dixmk.minepreggo.network.packet.s2c.SexCinematicControlP2PS2CPacket;
import dev.dixmk.minepreggo.network.packet.s2c.SyncCinematicStateS2CPacket;
import dev.dixmk.minepreggo.network.packet.s2c.SyncFemalePlayerDataS2CPacket;
import dev.dixmk.minepreggo.network.packet.s2c.SyncJigglePhysicsS2CPacket;
import dev.dixmk.minepreggo.network.packet.s2c.SyncMobEffectS2CPacket;
import dev.dixmk.minepreggo.network.packet.s2c.SyncPlayerAnimationS2CPacket;
import dev.dixmk.minepreggo.network.packet.s2c.SyncPlayerDataS2CPacket;
import dev.dixmk.minepreggo.network.packet.s2c.SyncPlayerLactationS2CPacket;
import dev.dixmk.minepreggo.network.packet.s2c.SyncPregnancyEffectsS2CPacket;
import dev.dixmk.minepreggo.network.packet.s2c.SyncPregnancySystemS2CPacket;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class MinepreggoModPacketHandler {

	private MinepreggoModPacketHandler() {}

	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE  = NetworkRegistry.newSimpleChannel(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	private static int messageID = 0;

	public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
		INSTANCE.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
		messageID++;
	}
	
	public static void registerMessages() {
		addNetworkMessage(RemovePlayerJigglePhysicsS2CPacket.class, RemovePlayerJigglePhysicsS2CPacket::encode, RemovePlayerJigglePhysicsS2CPacket::decode, RemovePlayerJigglePhysicsS2CPacket::handler);
		addNetworkMessage(RemoveMobEffectS2CPacket.class, RemoveMobEffectS2CPacket::encode, RemoveMobEffectS2CPacket::new, RemoveMobEffectS2CPacket::handle);
		addNetworkMessage(RemovePostPregnancyDataS2CPacket.class, RemovePostPregnancyDataS2CPacket::encode, RemovePostPregnancyDataS2CPacket::decode, RemovePostPregnancyDataS2CPacket::handler);
		addNetworkMessage(RenderSexOverlayS2CPacket.class, RenderSexOverlayS2CPacket::encode, RenderSexOverlayS2CPacket::decode, RenderSexOverlayS2CPacket::handler);
		addNetworkMessage(RequestBellyRubbingAnimationC2SPacket.class, RequestBellyRubbingAnimationC2SPacket::encode, RequestBellyRubbingAnimationC2SPacket::decode, RequestBellyRubbingAnimationC2SPacket::handler);
		addNetworkMessage(RequestPlayerMedicalCheckUpC2SPacket.class, RequestPlayerMedicalCheckUpC2SPacket::encode, RequestPlayerMedicalCheckUpC2SPacket::decode, RequestPlayerMedicalCheckUpC2SPacket::handler);
		addNetworkMessage(RequestPreggoMobInventoryMenuC2SPacket.class, RequestPreggoMobInventoryMenuC2SPacket::encode, RequestPreggoMobInventoryMenuC2SPacket::decode, RequestPreggoMobInventoryMenuC2SPacket::handler);
		addNetworkMessage(RequestPreggoMobMedicalCheckUpC2SPacket.class, RequestPreggoMobMedicalCheckUpC2SPacket::encode, RequestPreggoMobMedicalCheckUpC2SPacket::decode, RequestPreggoMobMedicalCheckUpC2SPacket::handler);
		addNetworkMessage(RequestSexCinematicP2MC2SPacket.class, RequestSexCinematicP2MC2SPacket::encode, RequestSexCinematicP2MC2SPacket::decode, RequestSexCinematicP2MC2SPacket::handler);
		addNetworkMessage(RequestSexCinematicP2PC2SPacket.class, RequestSexCinematicP2PC2SPacket::encode, RequestSexCinematicP2PC2SPacket::decode, RequestSexCinematicP2PC2SPacket::handler);
		addNetworkMessage(RequestSexM2PC2SPacket.class, RequestSexM2PC2SPacket::encode, RequestSexM2PC2SPacket::decode, RequestSexM2PC2SPacket::handler);
		addNetworkMessage(RequestSexP2PC2SPacket.class, RequestSexP2PC2SPacket::encode, RequestSexP2PC2SPacket::decode, RequestSexP2PC2SPacket::handler);
		addNetworkMessage(ResetPregnancyS2CPacket.class, ResetPregnancyS2CPacket::encode, ResetPregnancyS2CPacket::decode, ResetPregnancyS2CPacket::handler);
		addNetworkMessage(ResponseSexRequestM2PC2SPacket.class, ResponseSexRequestM2PC2SPacket::encode, ResponseSexRequestM2PC2SPacket::decode, ResponseSexRequestM2PC2SPacket::handler);
		addNetworkMessage(ResponseSexRequestP2PC2SPacket.class, ResponseSexRequestP2PC2SPacket::encode, ResponseSexRequestP2PC2SPacket::decode, ResponseSexRequestP2PC2SPacket::handler);
		addNetworkMessage(SexCinematicControlP2MS2CPacket.class, SexCinematicControlP2MS2CPacket::encode, SexCinematicControlP2MS2CPacket::decode, SexCinematicControlP2MS2CPacket::handler);
		addNetworkMessage(SexCinematicControlP2PS2CPacket.class, SexCinematicControlP2PS2CPacket::encode, SexCinematicControlP2PS2CPacket::decode, SexCinematicControlP2PS2CPacket::handler);
		addNetworkMessage(StopPlayerAnimationC2SPacket.class, StopPlayerAnimationC2SPacket::encode, StopPlayerAnimationC2SPacket::decode, StopPlayerAnimationC2SPacket::handler);
		addNetworkMessage(SyncCinematicStateS2CPacket.class, SyncCinematicStateS2CPacket::encode, SyncCinematicStateS2CPacket::decode, SyncCinematicStateS2CPacket::handler);
		addNetworkMessage(SyncFemalePlayerDataS2CPacket.class, SyncFemalePlayerDataS2CPacket::encode, SyncFemalePlayerDataS2CPacket::decode, SyncFemalePlayerDataS2CPacket::handler);
		addNetworkMessage(SyncMobEffectS2CPacket.class, SyncMobEffectS2CPacket::encode, SyncMobEffectS2CPacket::new, SyncMobEffectS2CPacket::handle);
		addNetworkMessage(SyncPlayerDataS2CPacket.class, SyncPlayerDataS2CPacket::encode, SyncPlayerDataS2CPacket::decode, SyncPlayerDataS2CPacket::handler);
		addNetworkMessage(SyncPlayerLactationS2CPacket.class, SyncPlayerLactationS2CPacket::encode, SyncPlayerLactationS2CPacket::decode, SyncPlayerLactationS2CPacket::handler);
		addNetworkMessage(SyncPregnancyEffectsS2CPacket.class, SyncPregnancyEffectsS2CPacket::encode, SyncPregnancyEffectsS2CPacket::decode, SyncPregnancyEffectsS2CPacket::handler);
		addNetworkMessage(SyncPregnancySystemS2CPacket.class, SyncPregnancySystemS2CPacket::encode, SyncPregnancySystemS2CPacket::decode, SyncPregnancySystemS2CPacket::handler);
		addNetworkMessage(UpdateBellyRubbingStateC2SPacket.class, UpdateBellyRubbingStateC2SPacket::encode, UpdateBellyRubbingStateC2SPacket::decode, UpdateBellyRubbingStateC2SPacket::handler);
		addNetworkMessage(UpdateCreeperGirlCombatModeC2SPacket.class, UpdateCreeperGirlCombatModeC2SPacket::encode, UpdateCreeperGirlCombatModeC2SPacket::decode, UpdateCreeperGirlCombatModeC2SPacket::handler);
		addNetworkMessage(SyncPlayerAnimationS2CPacket.class, SyncPlayerAnimationS2CPacket::encode, SyncPlayerAnimationS2CPacket::decode, SyncPlayerAnimationS2CPacket::handle);
		addNetworkMessage(UpdatePlayerDataC2SPacket.class, UpdatePlayerDataC2SPacket::encode, UpdatePlayerDataC2SPacket::decode, UpdatePlayerDataC2SPacket::handler);
		addNetworkMessage(UpdatePreggoMobBreakBlocksC2SPacket.class, UpdatePreggoMobBreakBlocksC2SPacket::encode, UpdatePreggoMobBreakBlocksC2SPacket::decode, UpdatePreggoMobBreakBlocksC2SPacket::handler);
		addNetworkMessage(UpdatePreggoMobPickUpItemC2SPacket.class, UpdatePreggoMobPickUpItemC2SPacket::encode, UpdatePreggoMobPickUpItemC2SPacket::decode, UpdatePreggoMobPickUpItemC2SPacket::handler);
		addNetworkMessage(UpdatePreggoMobWaitC2SPacket.class, UpdatePreggoMobWaitC2SPacket::encode, UpdatePreggoMobWaitC2SPacket::decode, UpdatePreggoMobWaitC2SPacket::handler);
		addNetworkMessage(UpdateShowPlayerMainMenuC2SPacket.class, UpdateShowPlayerMainMenuC2SPacket::encode, UpdateShowPlayerMainMenuC2SPacket::decode, UpdateShowPlayerMainMenuC2SPacket::handler);	
		addNetworkMessage(SyncJigglePhysicsS2CPacket.class, SyncJigglePhysicsS2CPacket::encode, SyncJigglePhysicsS2CPacket::decode, SyncJigglePhysicsS2CPacket::handler);	
		addNetworkMessage(RemovePreggoMobJigglePhysicsS2CPacket.class, RemovePreggoMobJigglePhysicsS2CPacket::encode, RemovePreggoMobJigglePhysicsS2CPacket::decode, RemovePreggoMobJigglePhysicsS2CPacket::handler);
	}
}
