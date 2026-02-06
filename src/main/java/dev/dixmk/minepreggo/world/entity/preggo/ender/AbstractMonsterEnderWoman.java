package dev.dixmk.minepreggo.world.entity.preggo.ender;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public abstract class AbstractMonsterEnderWoman extends AbstractEnderWoman {

	protected AbstractMonsterEnderWoman(EntityType<? extends AbstractEnderWoman> p_32485_, Level p_32486_) {
		super(p_32485_, p_32486_, Creature.MONSTER);
	}

    public static AttributeSupplier.Builder createDefaultAttributes() {
        return MonsterEnderWomanHelper.createBasicAttributes(0.3D);
	}
	
	@Override
	protected void registerGoals() {	
		this.addBehaviourGoals();
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.0F));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
	}
    
	@Override
	public boolean canBeTamedByPlayer() {
		return false;
	}
	
	public static boolean checkSpawnRules(EntityType<? extends AbstractEnderWoman> p_219014_, ServerLevelAccessor p_219015_, MobSpawnType p_219016_, BlockPos p_219017_, RandomSource p_219018_) {	
		if (p_219015_.getDifficulty() == Difficulty.PEACEFUL) {
			return false;
		}
		
	    if (p_219015_.getLevel().dimension() == Level.END) {
	        var below = p_219015_.getBlockState(p_219017_.below());
	        
	        return below.isSolidRender(p_219015_, p_219017_.below()) 
	               && p_219015_.getBlockState(p_219017_).isAir()
	               && p_219015_.getBlockState(p_219017_.above()).isAir();
	    }
	    else if (p_219015_.getLevel().dimension() == Level.NETHER) {
			return checkMobSpawnRules(p_219014_, p_219015_, p_219016_, p_219017_, p_219018_);
	    }
	      
		return Monster.isDarkEnoughToSpawn(p_219015_, p_219017_, p_219018_) && checkMobSpawnRules(p_219014_, p_219015_, p_219016_, p_219017_, p_219018_);
	}
	
	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
		return InteractionResult.FAIL;
	}
	
	@Override
	public String getSimpleName() {
		return MonsterEnderWomanHelper.SIMPLE_NAME;
	}
}
