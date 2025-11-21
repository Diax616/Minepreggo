package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RemoveMobEffectPacket {
	   private final int entityId;
	    private final ResourceLocation effectId;

	    public RemoveMobEffectPacket(int entityId, MobEffect effect) {
	        this.entityId = entityId;
	        this.effectId = ForgeRegistries.MOB_EFFECTS.getKey(effect);
	    }

	    public RemoveMobEffectPacket(FriendlyByteBuf buf) {
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
	                if (Minecraft.getInstance().level.getEntity(entityId) instanceof LivingEntity livingEntity) {
	                    MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(effectId);
	                    if (effect != null) {
	                        livingEntity.removeEffect(effect);
	                    }
	                }
	                else {
	                	MinepreggoMod.LOGGER.warn("Entity with ID {} is not a LivingEntity or does not exist.", entityId);
	                }
	            }
	        });
	        context.setPacketHandled(true);
	    }
	    
		@SubscribeEvent
		public static void registerMessage(FMLCommonSetupEvent event) {
			MinepreggoModPacketHandler.addNetworkMessage(RemoveMobEffectPacket.class, RemoveMobEffectPacket::encode, RemoveMobEffectPacket::new, RemoveMobEffectPacket::handle);
		}
}
