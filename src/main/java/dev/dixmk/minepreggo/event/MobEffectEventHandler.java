package dev.dixmk.minepreggo.event;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.network.capability.FemalePlayerImpl;
import dev.dixmk.minepreggo.world.effect.AbstractPlayerPregnancy;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MobEffectEventHandler {

	private MobEffectEventHandler() {}
	
    @SubscribeEvent
    public static void onEffectRemoved(MobEffectEvent.Remove event) {
        if (event.getEntity() instanceof Player player) {
            MobEffectInstance effectInstance = event.getEffectInstance();
            if (effectInstance != null) {
                MobEffect effect = effectInstance.getEffect();
            	if (PregnancySystemHelper.isPregnancyEffect(effect)) {
                    if (effect instanceof AbstractPlayerPregnancy) {
                    	player.refreshDimensions();
                    }  
                    // Only sync from server side to avoid client-side PacketDistributor usage
                	if (!player.level().isClientSide) {
                        PregnancySystemHelper.syncRemovedMobEffect(player, effectInstance.getEffect());
                    	if (effect == MinepreggoModMobEffects.FERTILE.get()) {           		
                    		removeMobAttacks(player);
                    		player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> cap.getBreedableData().resetFertilityRate());
                    	}
                	}
            	}
            }
        }
    }

    @SubscribeEvent
    public static void onEffectExpired(MobEffectEvent.Expired event) {
        if (event.getEntity() instanceof Player player) {
            MobEffectInstance effectInstance = event.getEffectInstance();
            if (effectInstance != null) {
                MobEffect effect = effectInstance.getEffect();
            	if (PregnancySystemHelper.isPregnancyEffect(effect)) {
                    if (effect instanceof AbstractPlayerPregnancy) {
                    	player.refreshDimensions();
                    }  
                    // Only sync from server side to avoid client-side PacketDistributor usage
                	if (!player.level().isClientSide) {
                        PregnancySystemHelper.syncRemovedMobEffect(player, effectInstance.getEffect());
                    	if (effect == MinepreggoModMobEffects.FERTILE.get()) {
                    		removeMobAttacks(player);
                    		player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> cap.getBreedableData().resetFertilityRate());
                    	}
                	}
            	}         	
            }
        }
    }

    @SubscribeEvent
    public static void onEffectAdded(MobEffectEvent.Added event) {
        if (event.getEntity() instanceof Player player) {
            MobEffectInstance effectInstance = event.getEffectInstance();     
            MobEffect effect = effectInstance.getEffect();     
            if (PregnancySystemHelper.isPregnancyEffect(effect)) {
                if (effect instanceof AbstractPlayerPregnancy) {
                	player.refreshDimensions();
                }            	
            	
                // Only sync from server side to avoid client-side PacketDistributor usage
            	if (!player.level().isClientSide) {
                    PregnancySystemHelper.syncRemovedMobEffect(player, effectInstance.getEffect());
                }
            }
        }
    }
    
    private static void removeMobAttacks(Player player) {
    	player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap ->
    		cap.getFemaleData().ifPresent(FemalePlayerImpl::clearMobAttacks)	
    	);
    }
}