package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.world.inventory.preggo.PreggoMobMedicalCheckUpMenu;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkHooks;
import net.minecraft.world.inventory.AbstractContainerMenu;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record RequestPreggoMobMedicalCheckUpPacket(int pregnantEntityId, int scientificIllagerId) {

	public static RequestPreggoMobMedicalCheckUpPacket decode(FriendlyByteBuf buffer) {	
		return new RequestPreggoMobMedicalCheckUpPacket(
				buffer.readVarInt(),
				buffer.readVarInt());
	}
	
	public static void encode(RequestPreggoMobMedicalCheckUpPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.pregnantEntityId);
		buffer.writeVarInt(message.scientificIllagerId);
	}

	
	public static void handler(RequestPreggoMobMedicalCheckUpPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();	
		context.enqueueWork(() -> {		
            if (context.getDirection().getReceptionSide().isServer()) {
    			var serverPlayer = context.getSender();		
    			 			
    			BlockPos bpos = BlockPos.containing(serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ());
    			
    			NetworkHooks.openScreen(serverPlayer, new MenuProvider() {
    				@Override
    				public Component getDisplayName() {
    					return Component.literal("PreggoMobMedicalCheckUpGUIGUI");
    				}

    				@Override
    				public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {						
    					var packet = new FriendlyByteBuf(Unpooled.buffer());
    					packet.writeBlockPos(bpos);
    					packet.writeVarInt(message.pregnantEntityId);
    					packet.writeVarInt(message.scientificIllagerId);				
    					return new PreggoMobMedicalCheckUpMenu(id, inventory, packet);
    				}
    			}, buf -> {
    				buf.writeBlockPos(bpos);
    				buf.writeVarInt(message.pregnantEntityId);
    				buf.writeVarInt(message.scientificIllagerId);
    			});	
            }	
		});
		context.setPacketHandled(true);
	}
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(RequestPreggoMobMedicalCheckUpPacket.class, RequestPreggoMobMedicalCheckUpPacket::encode, RequestPreggoMobMedicalCheckUpPacket::decode, RequestPreggoMobMedicalCheckUpPacket::handler);
	}
}
