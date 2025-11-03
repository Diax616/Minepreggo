package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.goal.RemoveBlockGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.LevelAccessor;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.utils.TagHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
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
import net.minecraftforge.registries.ForgeRegistries;


public abstract class AbstractZombieGirl extends PreggoMob implements Enemy {
	
	protected AbstractZombieGirl(EntityType<? extends PreggoMob> p_21803_, Level p_21804_) {
	      super(p_21803_, p_21804_);
	      this.reassessTameGoals();	     
		}
	
	@Override
	public MobType getMobType() {
		return MobType.UNDEAD;
	}
		
	protected boolean isSunSensitive() {
		return true;
	}
	
	@Override
	public boolean removeWhenFarAway(double p_27598_) {
		return !this.isTame();
	}
	
	@Override
	public boolean canBeLeashed(Player p_21813_) {
		return false;
	}
	
	@Override
	public String getSimpleName() {
		return this.hasCustomName() ? this.getDisplayName().getString() : "Zombie Girl";
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
		return stack.is(TagHelper.ZOMBIE_GIRL_FOOD);
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
	public boolean killedEntity(ServerLevel p_219160_, LivingEntity p_219161_) {
		boolean flag = super.killedEntity(p_219160_, p_219161_);
		if ((p_219160_.getDifficulty() == Difficulty.NORMAL || p_219160_.getDifficulty() == Difficulty.HARD) && p_219161_ instanceof Villager villager && net.minecraftforge.event.ForgeEventFactory.canLivingConvert(p_219161_, EntityType.ZOMBIE_VILLAGER, (timer) -> {})) {
			if (p_219160_.getDifficulty() != Difficulty.HARD && this.random.nextBoolean()) {
				return flag;
			}

			ZombieVillager zombievillager = villager.convertTo(EntityType.ZOMBIE_VILLAGER, false);
			if (zombievillager != null) {
				zombievillager.finalizeSpawn(p_219160_, p_219160_.getCurrentDifficultyAt(zombievillager.blockPosition()), MobSpawnType.CONVERSION, new Zombie.ZombieGroupData(false, true), (CompoundTag)null);
				zombievillager.setVillagerData(villager.getVillagerData());
				zombievillager.setGossips(villager.getGossips().store(NbtOps.INSTANCE));
				zombievillager.setTradeOffers(villager.getOffers().createTag());
				zombievillager.setVillagerXp(villager.getVillagerXp());
				net.minecraftforge.event.ForgeEventFactory.onLivingConvert(p_219161_, zombievillager);
				if (!this.isSilent()) {
					p_219160_.levelEvent((Player)null, 1026, this.blockPosition(), 0);
				}

				flag = false;
			}
		}

		return flag;
	}
	
	@Override
	protected void populateDefaultEquipmentSlots(RandomSource p_219165_, DifficultyInstance p_219166_) {
		super.populateDefaultEquipmentSlots(p_219165_, p_219166_);
		if (p_219165_.nextFloat() < (this.level().getDifficulty() == Difficulty.HARD ? 0.05F : 0.01F)) {
			int i = p_219165_.nextInt(3);
			if (i == 0) {
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
			} else {
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SHOVEL));
			}
		}
	}
	
	@Override
	protected ResourceLocation getDefaultLootTable() {
	    return ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "entities/abstract_zombie_girl_loot");
	}
	
	@Override
	public boolean canHoldItem(ItemStack p_34332_) {
		return !this.isPassenger() && super.canHoldItem(p_34332_);
	}

	@Override
	public boolean wantsToPickUp(ItemStack p_182400_) {
		return !p_182400_.is(Items.GLOW_INK_SAC) && super.wantsToPickUp(p_182400_);
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace("entity.generic.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace("entity.generic.death"));
	}
			
	
	protected static class ZombieGirlAttackTurtleEggGoal extends RemoveBlockGoal {
     
	  public ZombieGirlAttackTurtleEggGoal(AbstractZombieGirl p_34344_, double p_34345_, int p_34346_) {
         super(Blocks.TURTLE_EGG, p_34344_, p_34345_, p_34346_);
      }

      @Override
      public void playDestroyProgressSound(LevelAccessor p_34351_, BlockPos p_34352_) {
         p_34351_.playSound((Player)null, p_34352_, SoundEvents.ZOMBIE_DESTROY_EGG, SoundSource.HOSTILE, 0.5F, 0.9F + p_34351_.getRandom().nextFloat() * 0.2F);
      }

      @Override
      public void playBreakSound(Level p_34348_, BlockPos p_34349_) {
         p_34348_.playSound((Player)null, p_34349_, SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + p_34348_.random.nextFloat() * 0.2F);
      }

      @Override
      public double acceptedDistance() {
         return 1.14D;
      } 	
   }
}
