package dev.dixmk.minepreggo.event;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.network.capability.PlayerDataProvider;
import dev.dixmk.minepreggo.network.capability.PregnancyEffectsProvider;
import dev.dixmk.minepreggo.network.capability.PregnancySystemProvider;
import dev.dixmk.minepreggo.network.packet.SyncPlayerDataS2CPacket;
import dev.dixmk.minepreggo.server.ServerSexCinematicManager;
import dev.dixmk.minepreggo.utils.DebugHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.inventory.preggo.PlayerJoinsWorldMenu;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID)
public class PlayerEventHandler {

	private PlayerEventHandler() {}
	
	@SubscribeEvent
	public static void onRightClickEntity(PlayerInteractEvent.EntityInteract event) {	
		if (event.getTarget() instanceof PreggoMob t) {
			DebugHelper.check(t);
		}
	}
		
	@SubscribeEvent
	public static void onPlayerQuit(PlayerEvent.PlayerLoggedOutEvent event) {
	    if (event.getEntity() instanceof ServerPlayer player) {
	        ServerSexCinematicManager.end(player);
	    }
	}
	
	@SubscribeEvent
	public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer)) {
			event.addCapability(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "player_pregnancy_system"), new PregnancySystemProvider());
			event.addCapability(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "player_data"), new PlayerDataProvider());
			event.addCapability(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "player_pregnancy_effects"), new PregnancyEffectsProvider());
		}
	}
	
	@SubscribeEvent
	public static void onPlayerLoggedInSync(PlayerEvent.PlayerLoggedInEvent event) {
		if (event.getEntity() instanceof ServerPlayer serverPlayer) {	
			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(c -> {			
				c.sync(serverPlayer);
				
				if (c.canShowMainMenu()) {
					showPlayerMainMenu(serverPlayer);
				}
			});
		}
	}

	@SubscribeEvent
	public static void onPlayerRespawnedSync(PlayerEvent.PlayerRespawnEvent event) {
		if (event.getEntity() instanceof ServerPlayer serverPlayer) {	
			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(c -> {
				c.sync(serverPlayer);
				
				if (c.canShowMainMenu()) {
					showPlayerMainMenu(serverPlayer);
				}
			});
		}
	}

	@SubscribeEvent
	public static void onPlayerChangedDimensionSync(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (event.getEntity() instanceof ServerPlayer serverPlayer) {	
			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(c -> c.sync(serverPlayer));
		}
	}
	
	@SubscribeEvent
	public static void clonePlayer(PlayerEvent.Clone event) {
        if (event.getOriginal().level().isClientSide()) return;

        final var originalPlayer = event.getOriginal();
        final var newPlayer = event.getEntity();
        
        var origialPlayerDataCap = originalPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA);
        var newPlayerDataCap = newPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA);
		
        var origialPregnancySystemCap = originalPlayer.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_SYSTEM);
        var newPregnancySystemCap = newPlayer.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_SYSTEM);
      
        var origialPregnancyEffectsCap = originalPlayer.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_EFFECTS);
        var newPregnancyEffectsCap = newPlayer.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_EFFECTS);
        
        if (!event.isWasDeath()) {       
        	origialPlayerDataCap.ifPresent(oriCap -> newPlayerDataCap.ifPresent(newCap -> newCap.copyFrom(oriCap)));       
        	origialPregnancySystemCap.ifPresent(oriCap -> newPregnancySystemCap.ifPresent(newCap -> newCap.copyFrom(oriCap)));       
        	origialPregnancyEffectsCap.ifPresent(oriCap -> newPregnancyEffectsCap.ifPresent(newCap -> newCap.copyFrom(oriCap))); 
        } 

        if (newPlayer instanceof ServerPlayer serverPlayer) {
        	newPlayerDataCap.ifPresent(c -> c.sync(serverPlayer));
            newPregnancyEffectsCap.ifPresent(c -> c.sync(serverPlayer));
            newPregnancySystemCap.ifPresent(c -> c.sync(serverPlayer));
        }     
	}
	
	@SubscribeEvent
	public static void onPlayerTracking(PlayerEvent.StartTracking event) {
	    if (event.getTarget() instanceof ServerPlayer target) {
	        target.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
	            MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> target),
	                new SyncPlayerDataS2CPacket(target.getUUID(), cap.getGender(), cap.isUsingCustomSkin()))
	        );
	    }
	}
	
	private static void showPlayerMainMenu(ServerPlayer serverPlayer) {
		BlockPos bpos = BlockPos.containing(serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ());	
		NetworkHooks.openScreen(serverPlayer, new MenuProvider() {
			@Override
			public Component getDisplayName() {
				return Component.literal("PlayerMainGUI");
			}
			@Override
			public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
				return new PlayerJoinsWorldMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(bpos));
			}
		}, bpos);
	}
}
