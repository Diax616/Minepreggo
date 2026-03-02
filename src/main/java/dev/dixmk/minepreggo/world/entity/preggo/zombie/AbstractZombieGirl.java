package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.goal.RemoveBlockGoal;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.LevelAccessor;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.utils.TagHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public abstract class AbstractZombieGirl extends PreggoMob {
	
	private static final String SIMPLE_NAME = "Zombie Girl";
	
	protected AbstractZombieGirl(EntityType<? extends PreggoMob> entityType, Level level, Creature typeOfCreature) {
	      super(entityType, level, Species.ZOMBIE, typeOfCreature);
	}
	
	@Override
	public MobType getMobType() {
		return MobType.UNDEAD;
	}
		
	@Override
	public @NonNull Species getTypeOfSpecies() {
		return Species.ZOMBIE;
	}
	
	@Override
	public boolean hasJigglePhysics() {
		return true;
	}
	
	protected boolean isSunSensitive() {
		return true;
	}
	
	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !this.isTame();
	}
	
	@Override
	public String getSimpleName() {
		return SIMPLE_NAME;
	}
	
	@Override
	protected boolean shouldDespawnInPeaceful() {
		return !this.isTame();
	}
	
	@Override
	public SoundSource getSoundSource() {
		return SoundSource.HOSTILE;
	}
	
	@Override
	public boolean isFood(ItemStack stack) {
		return stack.is(TagHelper.ZOMBIE_FOOD);
	}
	
	@Override
	public boolean isFoodToTame(ItemStack stack) {
		return stack.is(MinepreggoModItems.VILLAGER_BRAIN.get());
	}
    
	@Override
   	public void aiStep() {
      super.aiStep();
      if (this.isAlive()) {
         boolean flag = this.isSunBurnTick();
         if (flag) {
            ItemStack itemstack = this.getItemBySlot(EquipmentSlot.HEAD);
            if (!itemstack.isEmpty()) {
               flag = false;
            }
            if (flag) {
               this.setSecondsOnFire(8);
            }
         }
      }
   }

	@Override
	public boolean killedEntity(ServerLevel level, LivingEntity killed) {
		boolean flag = super.killedEntity(level, killed);
		if ((level.getDifficulty() == Difficulty.NORMAL || level.getDifficulty() == Difficulty.HARD) && killed instanceof Villager villager && net.minecraftforge.event.ForgeEventFactory.canLivingConvert(killed, EntityType.ZOMBIE_VILLAGER, timer -> {})) {
			if (level.getDifficulty() != Difficulty.HARD && this.random.nextBoolean()) {
				return flag;
			}

			ZombieVillager zombievillager = villager.convertTo(EntityType.ZOMBIE_VILLAGER, false);
			if (zombievillager != null) {
				zombievillager.finalizeSpawn(level, level.getCurrentDifficultyAt(zombievillager.blockPosition()), MobSpawnType.CONVERSION, new Zombie.ZombieGroupData(false, true), (CompoundTag)null);
				zombievillager.setVillagerData(villager.getVillagerData());
				zombievillager.setGossips(villager.getGossips().store(NbtOps.INSTANCE));
				zombievillager.setTradeOffers(villager.getOffers().createTag());
				zombievillager.setVillagerXp(villager.getVillagerXp());
				net.minecraftforge.event.ForgeEventFactory.onLivingConvert(killed, zombievillager);
				if (!this.isSilent()) {
					level.levelEvent((Player)null, 1026, this.blockPosition(), 0);
				}

				flag = false;
			}
		}

		return flag;
	}
	
	@Override
	protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
		super.populateDefaultEquipmentSlots(random, difficulty);
		if (random.nextFloat() < (this.level().getDifficulty() == Difficulty.HARD ? 0.05F : 0.01F)) {
			if (random.nextInt(3) == 0) {
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
			} else {
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SHOVEL));
			}
		}
	}
	
	@Override
	protected ResourceLocation getDefaultLootTable() {
	    return MinepreggoHelper.fromThisNamespaceAndPath("entities/abstract_zombie_girl_loot");
	}
	
	@Override
	public boolean canHoldItem(ItemStack stack) {
		return !this.isPassenger() && super.canHoldItem(stack);
	}

	@Override
	public boolean wantsToPickUp(ItemStack stack) {
		return !stack.is(Items.GLOW_INK_SAC) && super.wantsToPickUp(stack);
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return SoundEvents.GENERIC_HURT;
	}

	@Override
	public SoundEvent getDeathSound() {
		return SoundEvents.GENERIC_DEATH;
	}
			
	protected static class ZombieGirlAttackTurtleEggGoal extends RemoveBlockGoal {   
	  public ZombieGirlAttackTurtleEggGoal(AbstractZombieGirl mob, double speedModifier, int searchRange) {
         super(Blocks.TURTLE_EGG, mob, speedModifier, searchRange);
      }

      @Override
      public void playDestroyProgressSound(LevelAccessor level, BlockPos pos) {
    	  level.playSound(null, pos, SoundEvents.ZOMBIE_DESTROY_EGG, SoundSource.HOSTILE, 0.5F, 0.9F + level.getRandom().nextFloat() * 0.2F);
      }

      @Override
      public void playBreakSound(Level level, BlockPos pos) {
    	  level.playSound(null, pos, SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + level.random.nextFloat() * 0.2F);
      }

      @Override
      public double acceptedDistance() {
         return 1.14D;
      } 	
   }
}
