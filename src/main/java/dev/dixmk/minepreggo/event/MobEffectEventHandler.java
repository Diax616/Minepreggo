package dev.dixmk.minepreggo.event;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.effect.Impregnantion;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.common.Mod;



@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MobEffectEventHandler {

	private MobEffectEventHandler() {}
	
    @SubscribeEvent
    public static void onEffectRemoved(MobEffectEvent.Remove event) {
    	MobEffectInstance effectInstance = event.getEffectInstance();
        if (effectInstance == null || effectInstance.getEffect().isInstantenous()) {
        	return;
        }
        
    	LivingEntity entity = event.getEntity();
        MobEffect effect = effectInstance.getEffect();
        Level level = entity.level();
        
        if (level.isClientSide) {
			return;
		}
             
        // Unmark entity for impregnation if Impregnantion effect is removed by other means (like milk)
        if ((effect == MinepreggoModMobEffects.IMPREGNANTION.get() || effect instanceof Impregnantion)) {
        	Impregnantion.unmarkForImpregnation(entity);
        }
        
    	if (entity instanceof ServerPlayer player && MinepreggoHelper.isFromMinepreggo(effect)) {     
    		// Only sync from server side to avoid client-side PacketDistributor usage
            PregnancySystemHelper.syncRemovedMobEffect(player, effect);
    		if (effect == MinepreggoModMobEffects.FERTILE.get()) {           		
        		removeMobAttacks(player);
        	}
    		MinepreggoMod.LOGGER.debug("Removed mob effect {} from player {}", effect.getDescriptionId(), player.getScoreboardName());
    	}
    	else if (entity instanceof PreggoMob && effect == MobEffects.CONFUSION) {
            PregnancySystemHelper.syncRemovedMobEffect(entity, effect);
            MinepreggoMod.LOGGER.debug("Removed mob effect {} from preggo mob {}", effect.getDescriptionId(), entity.getDisplayName().getString());
    	}
    }

    @SubscribeEvent
    public static void onEffectExpired(MobEffectEvent.Expired event) {
    	MobEffectInstance effectInstance = event.getEffectInstance();
        if (effectInstance == null || effectInstance.getEffect().isInstantenous()) {
        	return;
        }
        
    	LivingEntity entity = event.getEntity();
        MobEffect effect = effectInstance.getEffect();
        Level level = entity.level();
       
        if (level.isClientSide) {
			return;
		}
        
        if (entity instanceof ServerPlayer player && MinepreggoHelper.isFromMinepreggo(effect)) {  	
            // Only sync from server side to avoid client-side PacketDistributor usage
            PregnancySystemHelper.syncRemovedMobEffect(player, effect);
        	if (effect == MinepreggoModMobEffects.FERTILE.get()) {
        		removeMobAttacks(player);
        	}
        	MinepreggoMod.LOGGER.debug("Expired mob effect {} from player {}", effect.getDescriptionId(), player.getScoreboardName());
        }
    	else if (entity instanceof PreggoMob && effect == MobEffects.CONFUSION) {
            PregnancySystemHelper.syncRemovedMobEffect(entity, effect);
            MinepreggoMod.LOGGER.debug("Expired mob effect {} from preggo mob {}", effect.getDescriptionId(), entity.getDisplayName().getString());
    	}
    }

    @SubscribeEvent
    public static void onEffectApplicable(MobEffectEvent.Applicable event) {
        MobEffectInstance effectInstance = event.getEffectInstance();      
        MobEffect effect = effectInstance.getEffect();
        if (effect.isInstantenous()) {
            return;
        }
        
        LivingEntity entity = event.getEntity();
        Level level = entity.level();
        
        if (level.isClientSide) {
            return;
        }
        
        if (effect == MinepreggoModMobEffects.ETERNAL_PREGNANCY.get() && !PregnancySystemHelper.canHaveEternalPregnancy(entity)) {
            event.setResult(Event.Result.DENY);
            entity.hurt(new DamageSource(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MAGIC)), 1);
            MinepreggoMod.LOGGER.debug("Prevented mob effect {} from being applied to entity {} due to incompatibility", effect.getDescriptionId(), entity.getDisplayName().getString());
        }
    }

    @SubscribeEvent
    public static void onEffectAdded(MobEffectEvent.Added event) {     
        MobEffectInstance effectInstance = event.getEffectInstance();     
        MobEffect effect = effectInstance.getEffect();     
        
        if (effect.isInstantenous()) {
        	return;
        }
            
    	LivingEntity entity = event.getEntity();
        Level level = entity.level();        
         
        if (level.isClientSide) {
			return;
		}
        
        if ((entity instanceof ServerPlayer && MinepreggoHelper.isFromMinepreggo(effect)) || (entity instanceof PreggoMob && effect == MobEffects.CONFUSION)) {          
        	PregnancySystemHelper.syncNewMobEffect(entity, effectInstance);
        	MinepreggoMod.LOGGER.debug("Added mob effect {} to entity {}", effect.getDescriptionId(), entity.getDisplayName().getString());
        }
    }
    
    private static void removeMobAttacks(ServerPlayer player) {
    	player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap ->
    		cap.getFemaleData().ifPresent(femaleData -> {
    			femaleData.clearMobAttacks();
    			femaleData.resetFertilityRate();
    			MinepreggoMod.LOGGER.debug("Cleared mob attacks for fertile player {}", player.getScoreboardName());
    		})	
    	);
    }
}