package dev.dixmk.minepreggo.network.packet.c2s;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamableEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.world.pregnancy.SexHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public record RequestSexCinematicP2MC2SPacket(int mobId) {
	
	public static RequestSexCinematicP2MC2SPacket decode(FriendlyByteBuf buffer) {	
		return new RequestSexCinematicP2MC2SPacket(
				buffer.readVarInt());
		}
		
	public static void encode(RequestSexCinematicP2MC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.mobId);
	}
	 	 
    public static void handler(RequestSexCinematicP2MC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {					
            if (context.getDirection().getReceptionSide().isServer()) {
                var source = context.getSender();
                var level = source.level();
                		
                if (level.getEntity(message.mobId) instanceof PreggoMob preggoMob) {      		  
                	if (preggoMob instanceof AbstractTamableCreeperGirl creeperGirl) {
                		SexHelper.initSex(level, source, creeperGirl);
                	}
                	else if (preggoMob instanceof AbstractTamableZombieGirl zombieGirl) {
                		SexHelper.initSex(level, source, zombieGirl);
                	}
                	else if (preggoMob instanceof AbstractTamableEnderWoman enderWoman) {
                		SexHelper.initSex(level, source, enderWoman);
                	}
                } 
            }			
		});
		context.setPacketHandled(true);
    }
}
