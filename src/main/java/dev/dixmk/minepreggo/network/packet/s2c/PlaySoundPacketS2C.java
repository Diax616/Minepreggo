package dev.dixmk.minepreggo.network.packet.s2c;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class PlaySoundPacketS2C {
	private final ResourceLocation soundId;
	private final BlockPos pos;
	private final float volume;
	private final float pitch;

	public PlaySoundPacketS2C(SoundEvent soundId, BlockPos pos, float volume, float pitch) {
		this.soundId = soundId.getLocation();
		this.pos = pos;
		this.volume = volume;
		this.pitch = pitch;
	}

	private PlaySoundPacketS2C(ResourceLocation soundId, BlockPos pos, float volume, float pitch) {
		this.soundId = soundId;
		this.pos = pos;
		this.volume = volume;
		this.pitch = pitch;
	}
	
	// Encoder
	public static void encode(PlaySoundPacketS2C packet, FriendlyByteBuf buf) {
		buf.writeResourceLocation(packet.soundId);
		buf.writeBlockPos(packet.pos);
		buf.writeFloat(packet.volume);
		buf.writeFloat(packet.pitch);
	}

	// Decoder
	public static PlaySoundPacketS2C decode(FriendlyByteBuf buf) {
		ResourceLocation soundId = buf.readResourceLocation();
		BlockPos pos = buf.readBlockPos();
		float volume = buf.readFloat();
		float pitch = buf.readFloat();
		return new PlaySoundPacketS2C(soundId, pos, volume, pitch);
	}

	 public static void handler(PlaySoundPacketS2C packet, Supplier<NetworkEvent.Context> ctx) {
		 NetworkEvent.Context context = ctx.get();
		 context.enqueueWork(() -> {
			 if (context.getDirection().getReceptionSide().isClient()) {	
				 Minecraft mc = Minecraft.getInstance();
		            if (mc.level != null) {
		                SoundEvent soundEvent = ForgeRegistries.SOUND_EVENTS.getValue(packet.soundId);
		                if (soundEvent != null) {
		                    mc.level.playLocalSound(
		                        packet.pos.getX() + 0.5,
		                        packet.pos.getY() + 0.5,
		                        packet.pos.getZ() + 0.5,
		                        soundEvent,
		                        SoundSource.BLOCKS,
		                        packet.volume,
		                        packet.pitch,
		                        false
		                    );
		                }
		            }
			 }
		 });
		 context.setPacketHandled(true);
	 }
}
