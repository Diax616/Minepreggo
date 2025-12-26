package dev.dixmk.minepreggo.event;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.network.capability.PlayerDataProvider;
import dev.dixmk.minepreggo.network.capability.VillagerDataProvider;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractMonsterZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractZombieGirl;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.npc.AbstractVillager;
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
			event.addCapability(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "minepreggo_player_data"), new PlayerDataProvider());
		}
		else if(event.getObject() instanceof Villager) {
			event.addCapability(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "minepreggo_villager_data"), new VillagerDataProvider());
		}
	}
		
    @SubscribeEvent
    public static void onFinalizeSpawn(MobSpawnEvent.FinalizeSpawn event) {
        var mob = event.getEntity();
           
        if (mob instanceof AbstractTamablePregnantCreeperGirl<?,?> creeperGirl && event.getSpawnType() != MobSpawnType.CONVERSION) {  	
        	PreggoMobHelper.initDefaultPregnancy(creeperGirl);
        }
        else if (mob instanceof AbstractTamablePregnantZombieGirl<?,?> zombieGirl && event.getSpawnType() != MobSpawnType.CONVERSION) {  	
        	PreggoMobHelper.initDefaultPregnancy(zombieGirl);
        }
        else if (mob instanceof AbstractMonsterHumanoidCreeperGirl) {  	
        	mob.setCanPickUpLoot(mob.getRandom().nextFloat() < 0.35F * event.getDifficulty().getSpecialMultiplier());    
        	if (mob.getType() == MinepreggoModEntities.MONSTER_HUMANOID_CREEPER_GIRL.get()
        			&& mob.getRandom().nextFloat() < MinepreggoModConfig.getBabyCreeperGirlProbability()) {
            	mob.setBaby(true);
        	}   
        }
        else if (mob instanceof AbstractMonsterZombieGirl) {     	
        	mob.setCanPickUpLoot(mob.getRandom().nextFloat() < 0.55F * event.getDifficulty().getSpecialMultiplier());     
        	if (mob.getType() == MinepreggoModEntities.MONSTER_ZOMBIE_GIRL.get()
            		&& mob.getRandom().nextFloat() < MinepreggoModConfig.getBabyCreeperGirlProbability()) {
                mob.setBaby(true);    	
        	}    
        }   
        else if (mob instanceof AbstractVillager villager) {
			villager.goalSelector.addGoal(2, new AvoidEntityGoal<>(villager, AbstractZombieGirl.class, 6F, 1F, 1.2F));
		} 
		else if (mob instanceof IronGolem golem) {
			golem.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(golem, AbstractZombieGirl.class, false, false));
		}
    }
}
