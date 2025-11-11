package dev.dixmk.minepreggo.event;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.network.capability.Gender;
import dev.dixmk.minepreggo.network.capability.PlayerDataProvider;
import dev.dixmk.minepreggo.network.capability.PlayerPregnancyEffectsProvider;
import dev.dixmk.minepreggo.network.capability.PlayerPregnancySystemProvider;
import dev.dixmk.minepreggo.network.packet.SyncPlayerDataS2CPacket;
import dev.dixmk.minepreggo.server.ServerSexCinematicManager;
import dev.dixmk.minepreggo.utils.DebugHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemHelper;
import dev.dixmk.minepreggo.world.inventory.preggo.PlayerJoinsWorldMenu;
import dev.dixmk.minepreggo.world.item.IItemCraving;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID)
public class PlayerEventHandler {

	private PlayerEventHandler() {}
	
	
	// SYCN CAPABILITIES ON PLAYER LOGIN/RESPAWN/DIMENSION CHANGE START
	
	@SubscribeEvent
	public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer)) {
			event.addCapability(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "player_pregnancy_system"), new PlayerPregnancySystemProvider());
			event.addCapability(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "player_data"), new PlayerDataProvider());
			event.addCapability(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "player_pregnancy_effects"), new PlayerPregnancyEffectsProvider());
		}
	}
	
	@SubscribeEvent
	public static void onPlayerLoggedInSync(PlayerEvent.PlayerLoggedInEvent event) {
		if (event.getEntity() instanceof ServerPlayer serverPlayer && !serverPlayer.level().isClientSide) {	
			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(c -> {			
				c.sync(serverPlayer);
				
				if (c.canShowMainMenu()) {
					showPlayerMainMenu(serverPlayer);
				}
			});
			
			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_EFFECTS).ifPresent(c -> c.sync(serverPlayer));
			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_SYSTEM).ifPresent(c -> c.sync(serverPlayer));
		}
	}
	
	@SubscribeEvent
	public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {	
		if (event.getEntity() instanceof ServerPlayer serverPlayer && !serverPlayer.level().isClientSide) {	
			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(c -> c.sync(serverPlayer));	
			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_EFFECTS).ifPresent(c -> c.sync(serverPlayer));
			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_SYSTEM).ifPresent(c -> c.sync(serverPlayer));
		}
	}

	@SubscribeEvent
	public static void onPlayerRespawnedSync(PlayerEvent.PlayerRespawnEvent event) {
		if (event.getEntity() instanceof ServerPlayer serverPlayer && !serverPlayer.level().isClientSide) {	
			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(c -> {
				c.sync(serverPlayer);
				
				if (c.canShowMainMenu()) {
					showPlayerMainMenu(serverPlayer);
				}
			});
			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_EFFECTS).ifPresent(c -> c.sync(serverPlayer));
			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_SYSTEM).ifPresent(c -> c.sync(serverPlayer));
		}
	}

	@SubscribeEvent
	public static void onPlayerChangedDimensionSync(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (event.getEntity() instanceof ServerPlayer serverPlayer && !serverPlayer.level().isClientSide) {	
			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(c -> c.sync(serverPlayer));
			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_EFFECTS).ifPresent(c -> c.sync(serverPlayer));
			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_SYSTEM).ifPresent(c -> c.sync(serverPlayer));
		}
	}
	
	@SubscribeEvent
	public static void clonePlayer(PlayerEvent.Clone event) {
        final var originalPlayer = event.getOriginal();
        originalPlayer.revive();
        
        
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

        if (newPlayer instanceof ServerPlayer serverPlayer && !serverPlayer.level().isClientSide) {
        	newPlayerDataCap.ifPresent(c -> c.sync(serverPlayer));
            newPregnancyEffectsCap.ifPresent(c -> c.sync(serverPlayer));
            newPregnancySystemCap.ifPresent(c -> c.sync(serverPlayer));
        }     
	}
	
	@SubscribeEvent
	public static void onPlayerTracking(PlayerEvent.StartTracking event) {
	    if (event.getTarget() instanceof ServerPlayer serverPlayer && !serverPlayer.level().isClientSide) {
	    	serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
	            MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer),
	                new SyncPlayerDataS2CPacket(serverPlayer.getUUID(), cap.getGender(), cap.isUsingCustomSkin()))
	        );
	    }
	}
	
	// SYCN CAPABILITIES ON PLAYER LOGIN/RESPAWN/DIMENSION CHANGE END
	
	
	
	// PREGNANCY EFFECTS IN PLAYER HANDLING START

	// Craving gratification handling
	@SubscribeEvent
	public static void onUseItemFinish(LivingEntityUseItemEvent.Finish event) {	
		final var mainHandItem = event.getItem().getItem();
		
		if (!(event.getEntity() instanceof ServerPlayer player)) return;
			
		if (!(mainHandItem instanceof IItemCraving itemCraving)) {
			MinepreggoMod.LOGGER.debug("Item used is not an IItemCraving: {}", mainHandItem);
			return;
		}
				
		player.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_EFFECTS).ifPresent(cap -> {
			if (cap.isValidCraving(mainHandItem)) {		
				MinepreggoMod.LOGGER.debug("Player {} satisfied craving with item: {} by {}", player.getName().getString(), mainHandItem, itemCraving.getGratification());
				cap.decrementCraving(itemCraving.getGratification());
				cap.sync(player);
			}
		});		
	}

	// Milking handling
	@SubscribeEvent
	public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
		if (!(event.getEntity() instanceof ServerPlayer serverPlayer) || event.getItemStack().getItem() != Items.GLASS_BOTTLE) return;

		final var playerData = serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve();
		
		if (playerData.isEmpty() || playerData.get().getGender() != Gender.FEMALE || serverPlayer.level().isClientSide) {
			return;
		}
		
		serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_EFFECTS).ifPresent(cap -> {
			if (cap.getMilking() >= PregnancySystemHelper.MILKING_VALUE) {	
				var itemStack = serverPlayer.getMainHandItem();			
				if (!itemStack.isEmpty()) {
					cap.decrementMilking(PregnancySystemHelper.MILKING_VALUE);
					itemStack.setCount(itemStack.getCount() - 1);
					ItemHandlerHelper.giveItemToPlayer(serverPlayer, new ItemStack(MinepreggoModItems.HUMAN_BREAST_MILK_BOTTLE.get()));				
					serverPlayer.level().playSound(null, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), SoundEvents.COW_MILK, SoundSource.PLAYERS, 1F, 1F);
					cap.sync(serverPlayer);				
					MinepreggoMod.LOGGER.debug("Player {} milked. Current milking value: {}", serverPlayer.getName().getString(), cap.getMilking());
				}					
			}
		});
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
	
	// PREGNANCY EFFECTS IN PLAYER HANDLING END
	
	
	
	
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
}
