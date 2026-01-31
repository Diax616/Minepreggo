package dev.dixmk.minepreggo.world.effect;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.network.packet.s2c.RemoveMobEffectS2CPacket;
import dev.dixmk.minepreggo.network.packet.s2c.SyncMobEffectS2CPacket;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.PacketDistributor;

public class MobEffectHelper {

	private MobEffectHelper() {}
	
	public static void syncNewMobEffect(LivingEntity entity, MobEffectInstance effect) {
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity),
				new SyncMobEffectS2CPacket(entity.getId(), effect));
	}
	
	public static void syncRemovedMobEffect(LivingEntity entity, MobEffect effect) {
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity),
				new RemoveMobEffectS2CPacket(entity.getId(), effect));
	}
}
