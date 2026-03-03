package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import java.util.List;

import dev.dixmk.minepreggo.world.entity.monster.Ill;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;

public class IllCreeperHelper {

	private IllCreeperHelper() {
		throw new IllegalStateException("Utility class");
	}
	
	static<E extends AbstractHostileCreeperGirl & Ill> void addDefaultGoals(E target) {
    	target.goalSelector.addGoal(1, new AbstractCreeperGirl.SwellGoal<>(target) {		
			@Override
			public boolean canUse() {												
				return super.canUse() && (!target.isTame() || target.getOwner() == null);
			}
		});
		
    	target.goalSelector.addGoal(3, new MeleeAttackGoal(target, 1.2, false));
    	target.goalSelector.addGoal(3, new FloatGoal(target));
    	target.goalSelector.addGoal(6, new RandomLookAroundGoal(target));
    	target.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(target, Cat.class, true));
    	target.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(target, Ocelot.class, true));
    	target.goalSelector.addGoal(7, new LookAtPlayerGoal(target, Player.class, 8.0F));
	}
	
	static<E extends AbstractHostileCreeperGirl & Ill> InteractionResult mobInteract(E target, Player sourceentity, InteractionHand hand) {
		ItemStack itemstack = sourceentity.getItemInHand(hand);
		if (itemstack.is(ItemTags.CREEPER_IGNITERS)) {	
			target.setTarget(sourceentity);
			AABB aabb = AABB.unitCubeFromLowerCorner(target.position()).inflate(12, 8, 12);
	        List<Mob> list = target.level().getEntitiesOfClass(Mob.class, aabb, EntitySelector.NO_SPECTATORS);
	        for (var mob : list) {
                if (target != mob
                		&& !(mob.isAlliedTo(sourceentity))
                		&& (mob instanceof Ill || mob instanceof AbstractIllager)) {
                		mob.setTarget(sourceentity);
                }   	
	        }	
			return InteractionResult.sidedSuccess(target.level().isClientSide);
		}	
		
		return InteractionResult.FAIL;
	}
}
