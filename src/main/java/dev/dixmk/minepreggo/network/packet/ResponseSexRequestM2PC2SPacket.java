package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.client.particle.ParticleHelper;
import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.server.ServerCinematicManager;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobFace;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancyEffectsHandler;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancySystemHandler;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record ResponseSexRequestM2PC2SPacket(int preggoMobId, int playerId, boolean accept) {

	public static ResponseSexRequestM2PC2SPacket decode(FriendlyByteBuf buffer) {	
		return new ResponseSexRequestM2PC2SPacket(
				buffer.readVarInt(),
				buffer.readVarInt(),
				buffer.readBoolean());
	}
	
	public static void encode(ResponseSexRequestM2PC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.preggoMobId);
		buffer.writeVarInt(message.playerId);
		buffer.writeBoolean(message.accept);
	}
	
	public static void handler(ResponseSexRequestM2PC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			if (context.getDirection().getReceptionSide().isServer()) {	
				var sender = context.getSender();
				var level = sender.level();

				final PreggoMob source = level.getEntity(message.preggoMobId) instanceof PreggoMob s ? s : null;
				final ServerPlayer target = level.getEntity(message.playerId) instanceof ServerPlayer t ? t : null;
					
				if (source == null || target == null || !target.getUUID().equals(sender.getUUID())) return;
				
				// TODO: Sex event requested by a PreggoMob can only happen if the PreggoMob is pregnant, but She can't request if she's not pregnant. It should allow even if not pregnant.  
				if (message.accept) {																
					if (PreggoMobHelper.canActivateSexEvent(target, source)
							&& source instanceof ITamablePreggoMob<?> tamablePreggoMob 
							&& source instanceof IPregnancySystemHandler tamablePregnancySystemHandler 
							&& source instanceof IPregnancyEffectsHandler tamableEffectsHandler) {
						accept(target, source, tamablePreggoMob, tamableEffectsHandler, tamablePregnancySystemHandler);
					}	
				}
				else {
					reject(target, source);
				}
			}
		});
		context.setPacketHandled(true);
	}
	
	private static void accept(ServerPlayer target, PreggoMob source, ITamablePreggoMob<?> tamablePreggoMob, IPregnancyEffectsHandler tamableEffectsHandler, IPregnancySystemHandler tamableIPregnancySystemHandler) {
		MinepreggoModPacketHandler.queueServerWork(20, () -> ParticleHelper.spawnRandomlyFromServer(target, ParticleTypes.HEART));
		MinepreggoModPacketHandler.queueServerWork(40, () -> ParticleHelper.spawnRandomlyFromServer(target, ParticleTypes.HEART));
		MinepreggoModPacketHandler.queueServerWork(60, () -> ParticleHelper.spawnRandomlyFromServer(target, ParticleTypes.HEART));			
			
		ServerCinematicManager.getInstance().start(
				target,
				source,
				() -> {
					ParticleHelper.spawnRandomlyFromServer(source, ParticleTypes.HEART);
					tamablePreggoMob.setFaceState(PreggoMobFace.BLUSHED);  
				}, () -> {
					tamablePreggoMob.cleanFaceState();
					tamableEffectsHandler.setHorny(0);
					tamableIPregnancySystemHandler.removePregnancySymptom(PregnancySymptom.HORNY);
					tamablePreggoMob.getGenderedData().setSexualAppetite(0);
				});
		
		tamablePreggoMob.setCinematicOwner(target);
		tamablePreggoMob.setCinematicEndTime(target.level().getGameTime() + 120 * 2 + 60);
  
        MinepreggoModPacketHandler.INSTANCE.send(
            PacketDistributor.PLAYER.with(() -> target),
            new SexCinematicControlP2MS2CPacket(true, source.getId())
        );	               
		MinepreggoModPacketHandler.INSTANCE.send(
			PacketDistributor.PLAYER.with(() -> target),
			new RenderSexOverlayS2CPacket(120, 60)); 
		
		return;
	}
	
	private static void reject(ServerPlayer target, PreggoMob source) {
		MessageHelper.sendTo(target, Component.translatable("chat.minepreggo.pregnant.preggo_mob.message.rejection_by_its_owner", source.getSimpleName()));
		ParticleHelper.spawnRandomlyFromServer(target, ParticleTypes.SMOKE);
	}
	
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(ResponseSexRequestM2PC2SPacket.class, ResponseSexRequestM2PC2SPacket::encode, ResponseSexRequestM2PC2SPacket::decode, ResponseSexRequestM2PC2SPacket::handler);
	}
}
