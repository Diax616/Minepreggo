package dev.dixmk.minepreggo.event;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.world.effect.AbstractPlayerPregnancy;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MobEffectEventHandler {

	private MobEffectEventHandler() {}
	
    @SubscribeEvent
    public static void onEffectRemoved(MobEffectEvent.Remove event) {
    	MobEffectInstance effectInstance = event.getEffectInstance();
        if (effectInstance == null) {
        	return;
        }
        
    	LivingEntity entity = event.getEntity();
        MobEffect effect = effectInstance.getEffect();
        Level level = entity.level();
        
    	if (entity instanceof Player player && PregnancySystemHelper.isPregnancyEffect(effect)) {             
            if (effect instanceof AbstractPlayerPregnancy) {
            	player.refreshDimensions();
            }  
            // Only sync from server side to avoid client-side PacketDistributor usage
        	if (!level.isClientSide) {
                PregnancySystemHelper.syncRemovedMobEffect(player, effect);
            	if (effect == MinepreggoModMobEffects.FERTILE.get()) {           		
            		removeMobAttacks(player);
            	}
        	}
    	}
    	else if (!level.isClientSide && entity instanceof PreggoMob && effect == MobEffects.CONFUSION) {
            PregnancySystemHelper.syncRemovedMobEffect(entity, effect);
    	}
    }

    @SubscribeEvent
    public static void onEffectExpired(MobEffectEvent.Expired event) {
    	MobEffectInstance effectInstance = event.getEffectInstance();
        if (effectInstance == null) {
        	return;
        }
        
    	LivingEntity entity = event.getEntity();
        MobEffect effect = effectInstance.getEffect();
        Level level = entity.level();
       
        
        if (entity instanceof Player player && PregnancySystemHelper.isPregnancyEffect(effect)) {
            if (effect instanceof AbstractPlayerPregnancy) {
            	player.refreshDimensions();
            }  
            // Only sync from server side to avoid client-side PacketDistributor usage
        	if (!level.isClientSide) {
                PregnancySystemHelper.syncRemovedMobEffect(player, effectInstance.getEffect());
            	if (effect == MinepreggoModMobEffects.FERTILE.get()) {
            		removeMobAttacks(player);
            	}
        	}
        }
    	else if (!level.isClientSide && entity instanceof PreggoMob && effect == MobEffects.CONFUSION) {
            PregnancySystemHelper.syncRemovedMobEffect(entity, effect);
    	}
    }

    @SubscribeEvent
    public static void onEffectAdded(MobEffectEvent.Added event) {     
        MobEffectInstance effectInstance = event.getEffectInstance();     
        MobEffect effect = effectInstance.getEffect();     
    	LivingEntity entity = event.getEntity();
        Level level = entity.level();        
         
        if (entity instanceof Player && PregnancySystemHelper.isPregnancyEffect(effect)) {
            if (effect instanceof AbstractPlayerPregnancy) {
            	entity.refreshDimensions();
            }            	
            // Only sync from server side to avoid client-side PacketDistributor usage
        	if (!level.isClientSide) {
                PregnancySystemHelper.syncNewMobEffect(entity, effectInstance);
            }
        }
    	else if (!level.isClientSide && entity instanceof PreggoMob && effect == MobEffects.CONFUSION) {
            PregnancySystemHelper.syncNewMobEffect(entity, effectInstance);
    	}
    }
    
    private static void removeMobAttacks(Player player) {
    	player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap ->
    		cap.getFemaleData().ifPresent(femaleData -> {
    			femaleData.clearMobAttacks();
    			femaleData.resetFertilityRate();
    		})	
    	);
    }
}