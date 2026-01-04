package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PlayEntitySoundS2CPacket {
    private final int entityId;
    private final ResourceLocation soundId;
    private final float volume;
    private final float pitch;
	
    public PlayEntitySoundS2CPacket(int entityId, SoundEvent sound, float volume, float pitch, int num) {
        this.entityId = entityId;
        this.soundId = ForgeRegistries.SOUND_EVENTS.getKey(sound);
        this.volume = volume;
        this.pitch = pitch;
    }

    public PlayEntitySoundS2CPacket(FriendlyByteBuf buffer) {
        this.entityId = buffer.readInt();
        this.soundId = buffer.readResourceLocation();
        this.volume = buffer.readFloat();
        this.pitch = buffer.readFloat();
    }
    
    public static void encode(PlayEntitySoundS2CPacket packet, FriendlyByteBuf buffer) {
        buffer.writeInt(packet.entityId);
        buffer.writeResourceLocation(packet.soundId);
        buffer.writeFloat(packet.volume);
        buffer.writeFloat(packet.pitch);
    }

    public static void handle(PlayEntitySoundS2CPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {   	
            if (context.getDirection().getReceptionSide().isClient()) {  
                var level = Minecraft.getInstance().level;
                if (level != null) {
                    var entity = level.getEntity(message.entityId);
                    if (entity != null) {
                        SoundEvent sound = ForgeRegistries.SOUND_EVENTS.getValue(message.soundId);
                        if (sound != null) {
                            // Usar playSound en lugar de entity.playSound para que se reproduzca en todos los clientes
                            level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), sound, entity.getSoundSource(), message.volume, message.pitch);
                        }
                    }
                }
            }
        });
        context.setPacketHandled(true);
    }
    
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(PlayEntitySoundS2CPacket.class, PlayEntitySoundS2CPacket::encode, PlayEntitySoundS2CPacket::new, PlayEntitySoundS2CPacket::handle);
	}
}
