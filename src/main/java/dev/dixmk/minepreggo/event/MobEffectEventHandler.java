package dev.dixmk.minepreggo.event;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MobEffectEventHandler {

	private MobEffectEventHandler() {}
	
    @SubscribeEvent
    public static void onEffectRemoved(MobEffectEvent.Remove event) {
        if (event.getEntity().level().isClientSide) {
            return;
        }

        if (event.getEntity() instanceof ServerPlayer player) {
            MobEffectInstance effectInstance = event.getEffectInstance();
            if (effectInstance != null && PregnancySystemHelper.isPregnancyEffect(effectInstance.getEffect())) {
            	PregnancySystemHelper.syncRemovedMobEffect(player, effectInstance.getEffect());
            }
        }
    }

    @SubscribeEvent
    public static void onEffectExpired(MobEffectEvent.Expired event) {
        if (event.getEntity().level().isClientSide) {
            return;
        }

        if (event.getEntity() instanceof ServerPlayer player) {
            MobEffectInstance effectInstance = event.getEffectInstance();
            if (effectInstance != null && PregnancySystemHelper.isPregnancyEffect(effectInstance.getEffect())) {
            	PregnancySystemHelper.syncRemovedMobEffect(player, effectInstance.getEffect());
            }
        }
    }

    @SubscribeEvent
    public static void onEffectAdded(MobEffectEvent.Added event) {
        if (event.getEntity().level().isClientSide) {
            return;
        }

        if (event.getEntity() instanceof ServerPlayer player) {
            MobEffectInstance effectInstance = event.getEffectInstance();
            if (PregnancySystemHelper.isPregnancyEffect(effectInstance.getEffect())) {
            	PregnancySystemHelper.syncNewMobEffect(player, effectInstance);
            }
        }
    }
}
