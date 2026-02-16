package dev.dixmk.minepreggo.network.packet.c2s;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.world.entity.EnderPowerHelper;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.NetworkEvent;

public record ShootEnderDragonExplosiveBallC2SPacket(BlockPos targetPos) {

	public static ShootEnderDragonExplosiveBallC2SPacket decode(FriendlyByteBuf buffer) {	
		return new ShootEnderDragonExplosiveBallC2SPacket(
				buffer.readBlockPos());
	}
	
	public static void encode(ShootEnderDragonExplosiveBallC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeBlockPos(message.targetPos);
	}
	
	public static void handler(ShootEnderDragonExplosiveBallC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
            if (context.getDirection().getReceptionSide().isServer()) {
    			var serverPlayer = context.getSender();		
    			if (serverPlayer.hasEffect(MinepreggoModMobEffects.ENDER_DRAGON_ESSENCE.get())) {    				
    				if (PlayerHelper.isPregnantAndInLabor(serverPlayer)) {
    					MessageHelper.sendTo(serverPlayer, Component.translatable("chat.minepreggo.ender_power.message.in_labor"), true);
						return;
					}
    				if (EnderPowerHelper.tryShootFireball(serverPlayer, message.targetPos)) {
    					PlayerHelper.getCurrentPregnancyPhase(serverPlayer).ifPresent(phase -> {
    						if (phase.compareTo(PregnancyPhase.P4) >= 0) {
    							LivingEntityHelper.playSoundNearTo(serverPlayer, MinepreggoModSounds.getRandomStomachGrowls(serverPlayer.getRandom()));
							}
    					});
    				}
    			}	
            }		
		});
		context.setPacketHandled(true);
	}
}
