package dev.dixmk.minepreggo.event;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.network.capability.EnderPowerDataProvider;
import dev.dixmk.minepreggo.network.capability.PlayerDataProvider;
import dev.dixmk.minepreggo.network.capability.VillagerDataProvider;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.monster.Ill;
import dev.dixmk.minepreggo.world.entity.preggo.IHostilePregnantPreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractHostileHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamablePregnantEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractHostileZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID)
public class EntityEventHandler {

	private EntityEventHandler() {}
	
	@SubscribeEvent
	public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer)) {
			event.addCapability(MinepreggoHelper.fromThisNamespaceAndPath("player_data"), new PlayerDataProvider());
			event.addCapability(MinepreggoHelper.fromThisNamespaceAndPath("ender_power_data"), new EnderPowerDataProvider());
		}
		else if(event.getObject() instanceof Villager) {
			event.addCapability(MinepreggoHelper.fromThisNamespaceAndPath("villager_data"), new VillagerDataProvider());
		}
	}
		
    @SubscribeEvent
    public static void onFinalizeSpawn(MobSpawnEvent.FinalizeSpawn event) {
        var mob = event.getEntity();
       
    	if (mob instanceof AbstractTamablePregnantCreeperGirl creeperGirl && event.getSpawnType() != MobSpawnType.CONVERSION) {  	
        	PreggoMobHelper.initDefaultPregnancy(creeperGirl);    	
        }
        else if (mob instanceof AbstractTamablePregnantZombieGirl zombieGirl && event.getSpawnType() != MobSpawnType.CONVERSION) {  	
        	PreggoMobHelper.initDefaultPregnancy(zombieGirl);
        }
        else if (mob instanceof AbstractTamablePregnantEnderWoman enderWoman && event.getSpawnType() != MobSpawnType.CONVERSION) {  	
        	PreggoMobHelper.initDefaultPregnancy(enderWoman);
        }
        else if (mob instanceof AbstractHostileHumanoidCreeperGirl) {  	
        	mob.setCanPickUpLoot(mob.getRandom().nextFloat() < 0.35F * event.getDifficulty().getSpecialMultiplier());    	
        	if (mob.getType() == MinepreggoModEntities.HOSTILE_HUMANOID_CREEPER_GIRL.get()
        			&& mob.getRandom().nextFloat() < MinepreggoModConfig.SERVER.getBabyCreeperGirlProbability()) {
            	mob.setBaby(true);
        	}   
        }
        else if (mob instanceof AbstractHostileZombieGirl) {     	
        	mob.setCanPickUpLoot(mob.getRandom().nextFloat() < 0.55F * event.getDifficulty().getSpecialMultiplier());     
        	if (mob.getType() == MinepreggoModEntities.HOSTILE_ZOMBIE_GIRL.get()
            		&& mob.getRandom().nextFloat() < MinepreggoModConfig.SERVER.getBabyCreeperGirlProbability()) {
                mob.setBaby(true);    	
        	}    
        } 	
        else if (mob instanceof IronGolem ironGolem) {
        	// TODO: IronGolem still attack AbstractHostileCreeperGirl because they implements Enemy interface, IronGolem class define their target selector to attack all entities that implements Enemy interface
        	ironGolem.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(ironGolem, PreggoMob.class, 5, false, false, target -> 
        		!(target instanceof AbstractCreeperGirl) && target instanceof ITamablePreggoMob<?> tamablePreggoMob && tamablePreggoMob.getTamableData().isSavage()
            ));	
        }
        else if (mob instanceof SnowGolem snowGolem) {
        	snowGolem.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(snowGolem, PreggoMob.class, 10, true, false, target -> 
           		target instanceof ITamablePreggoMob<?> tamablePreggoMob && tamablePreggoMob.getTamableData().isSavage()
        	));	
        }
    	  	  	
    	if (mob instanceof Ill ill) {
    		ill.onFinalizeSpawnWithOwner();
    	}
    	
        PregnancyPhase phase = null;
    	if (mob instanceof ITamablePregnantPreggoMob tamablePregnantPreggoMob) {
    		phase = tamablePregnantPreggoMob.getPregnancyData().getCurrentPregnancyPhase();	
    	}
    	else if (mob instanceof IHostilePregnantPreggoMob monsterPregnantPreggoMob) {
			phase = monsterPregnantPreggoMob.getPregnancyData().getCurrentPregnancyPhase();
			monsterPregnantPreggoMob.getPregnancyData().init();
		}
    	
    	if (phase != null) {
			PregnancySystemHelper.applyGravityModifier(mob, phase);
			PregnancySystemHelper.applyKnockbackResistanceModifier(mob, phase);
		}
    }
}
