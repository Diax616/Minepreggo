package dev.dixmk.minepreggo.world.entity.preggo.ender;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.IHostilePreggoMob;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public abstract class AbstractHostileEnderWoman extends AbstractEnderWoman implements IHostilePreggoMob, Enemy {

	protected AbstractHostileEnderWoman(EntityType<? extends AbstractEnderWoman> entityType, Level level, Creature creatureType) {
		super(entityType, level, creatureType);
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
	
	@Override
	public boolean hasCustomHeadAnimation() {
		return false;
	}
	
	@Override
	public String getSimpleName() {
		return MonsterEnderWomanHelper.SIMPLE_NAME;
	}
	
	public static boolean checkSpawnRules(EntityType<? extends AbstractHostileEnderWoman> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {	
		if (level.getDifficulty() == Difficulty.PEACEFUL) {
			return false;
		}
		
	    if (level.getLevel().dimension() == Level.END) {
	        var below = level.getBlockState(pos.below());
	        
	        return below.isSolidRender(level, pos.below()) 
	               && level.getBlockState(pos).isAir()
	               && level.getBlockState(pos.above()).isAir();
	    }
	    else if (level.getLevel().dimension() == Level.NETHER) {
			return Mob.checkMobSpawnRules(entityType, level, spawnType, pos, random);
	    }
	      
		return Monster.isDarkEnoughToSpawn(level, pos, random) && Mob.checkMobSpawnRules(entityType, level, spawnType, pos, random);
	}
}
