package dev.dixmk.minepreggo.network.packet.s2c;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoMod;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class RemoveMobEffectS2CPacket {
       private final int entityId;
        private final ResourceLocation effectId;

        public RemoveMobEffectS2CPacket(int entityId, MobEffect effect) {
            this.entityId = entityId;
            this.effectId = ForgeRegistries.MOB_EFFECTS.getKey(effect);
        }

        public RemoveMobEffectS2CPacket(FriendlyByteBuf buf) {
            this.entityId = buf.readInt();
            this.effectId = buf.readResourceLocation();
        }

        public void encode(FriendlyByteBuf buf) {
            buf.writeInt(entityId);
            buf.writeResourceLocation(effectId);
        }

        public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
            NetworkEvent.Context context = contextSupplier.get();
            
    		context.enqueueWork(() -> {
    	        if (context.getDirection().getReceptionSide().isClient()) {  	
                    var entity = Minecraft.getInstance().level.getEntity(entityId);
                    if (entity instanceof LivingEntity livingEntity) {
                        MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(effectId);
                        if (effect != null) {
                            livingEntity.removeEffect(effect);
                        }
                    } else {
                        MinepreggoMod.LOGGER.warn("Entity with ID {} is not a LivingEntity or does not exist.", entityId);
                    }
    	        }	
    		});
            context.setPacketHandled(true);
        }
}