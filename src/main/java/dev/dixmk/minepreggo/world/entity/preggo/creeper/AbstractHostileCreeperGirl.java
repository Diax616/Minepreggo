package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.IHostilePreggoMob;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;

public abstract class AbstractHostileCreeperGirl extends AbstractCreeperGirl implements IHostilePreggoMob, Enemy {

	protected AbstractHostileCreeperGirl(EntityType<? extends AbstractCreeperGirl> entityType, Level level, Creature typeOfCreature) {
		super(entityType, level, typeOfCreature);	
	}

	@Override
	public boolean canBeTamedByPlayer() {
		return false;
	}
	
	@Override
	public boolean canExplode() {
		switch (this.getCombatMode()) {
		case FIGHT_AND_EXPLODE: {
			return this.getHealth() <= this.getMaxHealth() * 0.6F;
		}
		case DONT_EXPLODE: {
			return false;
		}	
		default:
			return true;
		}
	}
	
	@Override
	protected void registerGoals() {	
		this.goalSelector.addGoal(1, new AbstractCreeperGirl.SwellGoal<>(this) {		
			@Override
			public boolean canUse() {												
				return super.canUse() && canExplode();
			}
		});
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Ocelot.class, 6F, 1, 1.2));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Cat.class, 6F, 1, 1.2));	
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.2, false));
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(3, new FloatGoal(this));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mate) {
		return null;
	}
	
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag dataTag) {
		SpawnGroupData spawnData = super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData, dataTag);
		RandomSource randomsource = level.getRandom();
		if (this.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
			LocalDate localdate = LocalDate.now();
			int i = localdate.get(ChronoField.DAY_OF_MONTH);
			int j = localdate.get(ChronoField.MONTH_OF_YEAR);
			if (j == 10 && i == 31 && randomsource.nextFloat() < 0.25F) {
				this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(randomsource.nextFloat() < 0.1F ? Blocks.JACK_O_LANTERN : Blocks.CARVED_PUMPKIN));
				this.armorDropChances[EquipmentSlot.HEAD.getIndex()] = 0.0F;
			}
		}	
		return spawnData;
	}
	
	public static boolean checkSpawnRules(EntityType<? extends AbstractHostileCreeperGirl> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return level.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(level, pos, random) && checkMobSpawnRules(entityType, level, spawnType, pos, random);
	}
}
