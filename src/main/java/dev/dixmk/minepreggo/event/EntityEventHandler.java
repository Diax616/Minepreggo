package dev.dixmk.minepreggo.event;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.network.capability.EnderPowerDataProvider;
import dev.dixmk.minepreggo.network.capability.PlayerDataProvider;
import dev.dixmk.minepreggo.network.capability.VillagerDataProvider;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
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
    public static void onJoinLevel(EntityJoinLevelEvent event) {
        var mob = event.getEntity();
       
        if (mob instanceof IronGolem ironGolem) {
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
    }
}
