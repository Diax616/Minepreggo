package dev.dixmk.minepreggo.event;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.ender.IllEnderGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractMonsterZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractZombieGirl;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID)
public class EntityEventHandler {

	private EntityEventHandler() {}
	
	@SubscribeEvent
	public static void onEntityJoin(EntityJoinLevelEvent event) {	
		var entity = event.getEntity();
		
		if (entity instanceof AbstractVillager villager) {
			villager.goalSelector.addGoal(2, new AvoidEntityGoal<>(villager, AbstractZombieGirl.class, 6F, 1F, 1.2F));
		} 
		else if (entity instanceof IronGolem golem) {
			golem.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(golem, AbstractZombieGirl.class, false, false));
		} 				
	}
	
	
    @SubscribeEvent
    public static void onFinalizeSpawn(MobSpawnEvent.FinalizeSpawn event) {
        var mob = event.getEntity();
        
        if (mob instanceof AbstractTamablePregnantZombieGirl<?,?> zombieGirl && zombieGirl.getSpawnType() != MobSpawnType.CONVERSION) {
        	PreggoMobHelper.initPregnancy(zombieGirl);
        }
        else if (mob instanceof AbstractTamablePregnantCreeperGirl<?,?> creeperGirl && creeperGirl.getSpawnType() != MobSpawnType.CONVERSION) {
        	PreggoMobHelper.initPregnancy(creeperGirl);
        }        
        else if (mob instanceof AbstractMonsterHumanoidCreeperGirl) {  	
        	mob.setCanPickUpLoot(mob.getRandom().nextFloat() < 0.35F * event.getDifficulty().getSpecialMultiplier());    
        	if (mob.getType() == MinepreggoModEntities.MONSTER_CREEPER_GIRL_P0.get()
        			&& mob.getRandom().nextFloat() < MinepreggoModConfig.getBabyCreeperGirlProbability()) {
            	mob.setBaby(true);
        	}   
            else if (mob.getType() == MinepreggoModEntities.ILL_ZOMBIE_GIRL.get()) {
            	mob.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
            	mob.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
            }  
        }
        else if (mob instanceof AbstractMonsterZombieGirl) {     	
        	mob.setCanPickUpLoot(mob.getRandom().nextFloat() < 0.55F * event.getDifficulty().getSpecialMultiplier());     
        	if (mob.getType() == MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P0.get()
            		&& mob.getRandom().nextFloat() < MinepreggoModConfig.getBabyCreeperGirlProbability()) {
                mob.setBaby(true);    	
        	}   
        }   
        else if (mob instanceof ScientificIllager scientificIllager) {
        	scientificIllager.trySpawnIllPets();     
        }
        else if (mob instanceof IllEnderGirl illEnderGirl) {
        	illEnderGirl.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
        }        
    }
}
