package dev.dixmk.minepreggo.world.entity.monster;

import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MerchantMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.util.GoalUtils;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.world.entity.npc.Trades;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllCreeperGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.PlayerPrenatalCheckUpMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.SelectPregnantEntityForPrenatalCheckUpMenu;
import dev.dixmk.minepreggo.world.pregnancy.IObstetrician;
import dev.dixmk.minepreggo.world.pregnancy.PrenatalCheckupCostHolder;
import dev.dixmk.minepreggo.world.pregnancy.PrenatalCheckupCostHolder.PrenatalCheckupCost;
import io.netty.buffer.Unpooled;
import dev.dixmk.minepreggo.world.entity.preggo.ender.IllEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.IllZombieGirl;

public class ScientificIllager extends AbstractIllager implements Merchant, IObstetrician {
    private MerchantOffers offers;
    private Player tradingPlayer; 
    private PrenatalCheckupCostHolder prenatalCheckUpCosts = new PrenatalCheckupCostHolder(3, 10);
	private boolean spawnedPets = false;
    private Set<UUID> petsUUID = null;
    private int restockTimer = 0;
    
	public ScientificIllager(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.SCIENTIFIC_ILLAGER.get(), world);
	}
	
	public ScientificIllager(EntityType<ScientificIllager> type, Level world) {
		super(type, world);
		setMaxUpStep(0.6f);
		xpReward = 12;
		setNoAi(false);
	}
	
    private MerchantOffers createTrades() {
    	final var trades = Trades.Illager.getRandomTrades(this.random);
    	MerchantOffers newOffers = new MerchantOffers();
		for (var trade : trades) {
			newOffers.add(trade.getOffer(this, this.random));
		}
		return newOffers;
    }
	
	@Override
	public boolean canJoinRaid() {
		return false;
	}
    
	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}
	
	@Override
	protected boolean shouldDespawnInPeaceful() {
		return false;
	}
    
    @Override
    public void setTradingPlayer(@Nullable Player player) {
        this.tradingPlayer = player;
    }

    @Override
    @Nullable
    public Player getTradingPlayer() {
        return this.tradingPlayer;
    }

    @Override
    public MerchantOffers getOffers() {
        if (this.offers == null) {
            this.offers = this.createTrades();
         }
         return this.offers;
    }
    
    @Override
    public PrenatalCheckupCost getPrenatalCheckupCosts() {
    	return this.prenatalCheckUpCosts.getValue();
    }
    
    @Override
    public void overrideOffers(MerchantOffers offers) {
        this.offers = offers;
    }

    @Override
    public void notifyTrade(MerchantOffer offer) {
    	if (!this.level().isClientSide) {
            offer.increaseUses();
    	}
    }

    @Override
    public void notifyTradeUpdated(ItemStack stack) {
    	// It does not need, It is not a villager
    }
    
    @Override
    public int getVillagerXp() {
        return 0;
    }

    @Override
    public void overrideXp(int xp) {
    	// It does not need, It is not a villager
    }

    @Override
    public boolean isClientSide() {
        return this.level().isClientSide;
    }
        
	@Override
	public boolean showProgressBar() {
		return false;
	}

	@Override
	public SoundEvent getNotifyTradeSound() {
	      return SoundEvents.PILLAGER_CELEBRATE;
	}
    
    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        	
    	if (this.isAggressive()) return InteractionResult.FAIL;
    	  
    	if (!player.isShiftKeyDown()) {   		
    		if (!this.level().isClientSide) {
                this.setTradingPlayer(player);
                this.openTradingScreen(player, this.getDisplayName(), 1); 
    		}  		
            return InteractionResult.sidedSuccess(this.level().isClientSide);
    	}
	
		if (player instanceof ServerPlayer serverPlayer) {  			
			tryOpenPrenatalCheckUpMenu(serverPlayer);
		}  	
		     
        return InteractionResult.sidedSuccess(this.level().isClientSide);
    }
  
    
    private boolean tryOpenPrenatalCheckUpMenu(ServerPlayer serverPlayer) {   	
    	if (serverPlayer.level().isClientSide) {
    		return false;
    	}	
    	
		final var blockPos = serverPlayer.blockPosition();
		final var scientificIllagerId = this.getId();   		
		final List<Integer> list = serverPlayer.level().getEntitiesOfClass(PreggoMob.class, new AABB(blockPos).inflate(16D), e -> e.isOwnedBy(serverPlayer) && e instanceof ITamablePregnantPreggoMob)
					.stream()
					.map(e -> e.getId())
					.collect(Collectors.toCollection(ArrayList::new));
		
		serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 			  		 		 		   		    		
			cap.getFemaleData().ifPresent(femaleData -> {
        		if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
            		list.add(serverPlayer.getId());		
        		}
			}) 		
		);
		
		if (list.isEmpty()) {
			MessageHelper.sendTo(serverPlayer, Component.translatable("chat.minepreggo.scientific_illager.no_pregnant_entities"));
			return false;
		}
		
		setTradingPlayer(serverPlayer);
		
		NetworkHooks.openScreen(serverPlayer, new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.literal("CreeperGirlInventoryGUI");
            }
            
            @Override
            public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
                packetBuffer.writeBlockPos(blockPos);
                packetBuffer.writeVarInt(scientificIllagerId);
                packetBuffer.writeCollection(list, FriendlyByteBuf::writeVarInt);              
                return new SelectPregnantEntityForPrenatalCheckUpMenu(id, inventory, packetBuffer);
            }
		}
					
		,buf -> {
			buf.writeBlockPos(blockPos);
		    buf.writeVarInt(scientificIllagerId);
		    buf.writeCollection(list, FriendlyByteBuf::writeVarInt); 
		});  
		
		return true;
    }
    
	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);				
	    MerchantOffers merchantoffers = this.getOffers();
	    compound.putBoolean("spawnedPets", this.spawnedPets);
	    compound.putInt("RestockTimer", this.restockTimer);
	    if (!merchantoffers.isEmpty()) {
	    	compound.put("Offers", merchantoffers.createTag());
	    }
	    
	    if (prenatalCheckUpCosts.isInitialized()) {
	    	compound.put("prenatalCheckUpCosts", prenatalCheckUpCosts.serializeNBT());
	    }
		
		if (petsUUID != null && !petsUUID.isEmpty()) {
			ListTag pets = new ListTag();
			petsUUID.forEach(uuid -> {
				CompoundTag tag = new CompoundTag();
				tag.putUUID("petId", uuid);
				pets.add(tag);
			});
			compound.put("DataPetsUUID", pets);
		}
	}
	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);		
		this.spawnedPets = compound.getBoolean("spawnedPets");
		this.restockTimer = compound.getInt("RestockTimer");
		if (compound.contains("Offers")) {
			this.offers = new MerchantOffers(compound.getCompound("Offers"));
		}
		
		if (compound.contains("prenatalCheckUpCosts", Tag.TAG_COMPOUND)) {
			prenatalCheckUpCosts.deserializeNBT(compound.getCompound("prenatalCheckUpCosts"));
		}

		if (petsUUID == null && compound.contains("DataPetsUUID", Tag.TAG_LIST)) {
			petsUUID = new HashSet<>(4);
			ListTag  list = compound.getList("DataPetsUUID", Tag.TAG_COMPOUND);
			list.forEach(tag -> petsUUID.add(((CompoundTag) tag).getUUID("petId")));
		}
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(2, new AbstractIllager.RaiderOpenDoorGoal(this));
		this.goalSelector.addGoal(3, new Raider.HoldGroundAttackGoal(this, 10.0F));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, Raider.class)).setAlertOthers());
		this.targetSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
		this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6D) {
			@Override
			public boolean canUse() {
				return super.canUse() && getTradingPlayer() == null;
			}
			
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && getTradingPlayer() == null;
			}
		});
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));		
	}

	private boolean spawnPets() {			
		if (!this.spawnedPets && this.level() instanceof ServerLevel serverLevel) {	
			this.spawnedPets = true;
			this.petsUUID = new HashSet<>(4);			
			final var x = this.getX();
			final var y = this.getY();
			final var z = this.getZ();
						
			IllZombieGirl zombieGirl = MinepreggoModEntities.ILL_ZOMBIE_GIRL.get().spawn(serverLevel, BlockPos.containing(x + 1.25, y, z), MobSpawnType.MOB_SUMMONED);
			zombieGirl.setYRot(this.random.nextFloat() * 360F);
			zombieGirl.tameByIllager(this);
			petsUUID.add(zombieGirl.getUUID());
						
			IllHumanoidCreeperGirl humanoidCreeperGirl = MinepreggoModEntities.ILL_HUMANOID_CREEPER_GIRL.get().spawn(serverLevel, BlockPos.containing(x - 1.25, y, z), MobSpawnType.MOB_SUMMONED);
			humanoidCreeperGirl.setYRot(this.random.nextFloat() * 360F);
			humanoidCreeperGirl.tameByIllager(this);	
			petsUUID.add(humanoidCreeperGirl.getUUID());
			
			IllCreeperGirl creeperGirl = MinepreggoModEntities.ILL_CREEPER_GIRL.get().spawn(serverLevel, BlockPos.containing(x, y, z + 1.25), MobSpawnType.MOB_SUMMONED);
			creeperGirl.setYRot(this.random.nextFloat() * 360F);
			creeperGirl.tameByIllager(this);
			petsUUID.add(creeperGirl.getUUID());
			
			IllEnderWoman enderWoman = MinepreggoModEntities.ILL_ENDER_WOMAN.get().spawn(serverLevel, BlockPos.containing(x, y, z - 1.25), MobSpawnType.MOB_SUMMONED);
			enderWoman.setYRot(this.random.nextFloat() * 360F);
			enderWoman.tameByIllager(this);
			petsUUID.add(enderWoman.getUUID());					
			return true;
		}	
		return false;
	}
	
	@Override
	public boolean hurt(DamageSource source, float amount) {
		boolean result = super.hurt(source, amount);	
	    if (result && !this.level().isClientSide && this.getTradingPlayer() != null) {
            ServerPlayer player = (ServerPlayer) this.getTradingPlayer();
            if (player.containerMenu instanceof PlayerPrenatalCheckUpMenu.IllagerMenu || 
            		player.containerMenu instanceof SelectPregnantEntityForPrenatalCheckUpMenu ||
            		player.containerMenu instanceof MerchantMenu) {
            	player.closeContainer();
            }
            this.setTradingPlayer(null);
	    }
	    return result;
	}

	@Override
	public void aiStep() {
	    super.aiStep();
	    if (this.level().isClientSide) {
	        return;
	    }
	    
	    if (this.offers != null && hasOutOfStockOffer()) {          
            if (this.restockTimer >= 12000 && this.getTradingPlayer() == null) {
                this.restockTimer = 0;
                this.offers = this.createTrades();
            } else {
            	++this.restockTimer;
            }
	    }
	}
		
	private boolean hasOutOfStockOffer() {
	    for (MerchantOffer offer : this.offers) {
	        if (offer.isOutOfStock()) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public boolean removePet(UUID uuid) {
		if (petsUUID != null) {
			return petsUUID.remove(uuid);
		}
		return false;
	}
	
	@Override
	protected void customServerAiStep() {
	    if (!this.isNoAi() && GoalUtils.hasGroundPathNavigation(this)) {
	        boolean flag = ((ServerLevel)this.level()).isRaided(this.blockPosition());
	        ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(flag);
	    }

	    super.customServerAiStep();
	}

	public static AttributeSupplier.Builder createAttributes() {
	    return Monster.createMonsterAttributes()
	    		.add(Attributes.MOVEMENT_SPEED, 0.35)
	    		.add(Attributes.FOLLOW_RANGE, 24.0D)
	    		.add(Attributes.MAX_HEALTH, 24.0D)
	    		.add(Attributes.ATTACK_DAMAGE, 5.0D);
	}

	@Override
	public AbstractIllager.IllagerArmPose getArmPose() {
	    if (this.isAggressive()) {
	        return AbstractIllager.IllagerArmPose.ATTACKING;
	    }
	    else {
	        return this.isCelebrating() ? AbstractIllager.IllagerArmPose.CELEBRATING : AbstractIllager.IllagerArmPose.CROSSED;
	    }
	}

	public SoundEvent getCelebrateSound() {
	    return SoundEvents.VINDICATOR_CELEBRATE;
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_34088_, DifficultyInstance p_34089_, MobSpawnType p_34090_, @Nullable SpawnGroupData p_34091_, @Nullable CompoundTag p_34092_) {
	    SpawnGroupData spawngroupdata = super.finalizeSpawn(p_34088_, p_34089_, p_34090_, p_34091_, p_34092_);
	    ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(true);
	    RandomSource randomsource = p_34088_.getRandom();
	    this.populateDefaultEquipmentSlots(randomsource, p_34089_);
	    this.populateDefaultEquipmentEnchantments(randomsource, p_34089_);
	    this.spawnPets();
	    return spawngroupdata;
	}
	
	@Override
	protected void populateDefaultEquipmentSlots(RandomSource p_219149_, DifficultyInstance p_219150_) {
	    if (this.getCurrentRaid() == null) {
	        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_AXE));
	    }
	}

	@Override
	public boolean isAlliedTo(Entity p_34110_) {
	    if (super.isAlliedTo(p_34110_) || p_34110_ instanceof Ill) {
	        return true;
	    }
	    else if (p_34110_ instanceof LivingEntity livingEntity && livingEntity.getMobType() == MobType.ILLAGER) {
	        return this.getTeam() == null && p_34110_.getTeam() == null;
	    }
	    else {
	        return false;
	    }
	}

	@Override
	public void die(DamageSource source) {
		super.die(source);		
		if (this.level() instanceof ServerLevel serverLevel && this.petsUUID != null) {			
			this.petsUUID.forEach(uuid -> {
				if (serverLevel.getEntity(uuid) instanceof Ill ill) {
					ill.removeIllagerOwner();
				}
			});
		}
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
	    return SoundEvents.VINDICATOR_AMBIENT;
	}

	@Override
	protected SoundEvent getDeathSound() {
	    return SoundEvents.VINDICATOR_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource p_34103_) {
	    return SoundEvents.VINDICATOR_HURT;
	}

	public void applyRaidBuffs(int p_34079_, boolean p_34080_) {
	      ItemStack itemstack = new ItemStack(Items.IRON_AXE);
	      Raid raid = this.getCurrentRaid();
	      int i = 1;
	      if (p_34079_ > raid.getNumGroups(Difficulty.NORMAL)) {
	         i = 2;
	      }

	      boolean flag = this.random.nextFloat() <= raid.getEnchantOdds();
	      if (flag) {
	         Map<Enchantment, Integer> map = Maps.newHashMap();
	         map.put(Enchantments.SHARPNESS, i);
	         EnchantmentHelper.setEnchantments(map, itemstack);
	      }

	      this.setItemSlot(EquipmentSlot.MAINHAND, itemstack);
	}
}
