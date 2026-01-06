package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoMod;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class SyncMobEffectPacket {
    private final int entityId;
    private final ResourceLocation effectId;
    private final int duration;
    private final int amplifier;
    private final boolean ambient;
    private final boolean visible;
    private final boolean showIcon;

    public SyncMobEffectPacket(int entityId, MobEffectInstance effectInstance) {
        this.entityId = entityId;
        this.effectId = ForgeRegistries.MOB_EFFECTS.getKey(effectInstance.getEffect());
        this.duration = effectInstance.getDuration();
        this.amplifier = effectInstance.getAmplifier();
        this.ambient = effectInstance.isAmbient();
        this.visible = effectInstance.isVisible();
        this.showIcon = effectInstance.showIcon();
    }

    public SyncMobEffectPacket(FriendlyByteBuf buf) {
        this.entityId = buf.readInt();
        this.effectId = buf.readResourceLocation();
        this.duration = buf.readInt();
        this.amplifier = buf.readInt();
        this.ambient = buf.readBoolean();
        this.visible = buf.readBoolean();
        this.showIcon = buf.readBoolean();
    }
    
	public static void encode(SyncMobEffectPacket message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.entityId);
		buffer.writeResourceLocation(message.effectId);
		buffer.writeInt(message.duration);
		buffer.writeInt(message.amplifier);
		buffer.writeBoolean(message.ambient);
		buffer.writeBoolean(message.visible);
		buffer.writeBoolean(message.showIcon);
	}

    public static void handle(SyncMobEffectPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {    
            if (context.getDirection().getReceptionSide().isClient()) {  
                if (Minecraft.getInstance().level.getEntity(message.entityId) instanceof LivingEntity livingEntity) {    
                    MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(message.effectId);
                    if (effect == null) {
                        return;
                    }
                    livingEntity.addEffect(new MobEffectInstance(effect, message.duration, message.amplifier, message.ambient, message.visible, message.showIcon));
                }
                else {
                    MinepreggoMod.LOGGER.warn("SyncMobEffectPacket: Entity with ID {} is not a LivingEntity or does not exist.", message.entityId);
                }
            }
        });
        context.setPacketHandled(true);
    }
}