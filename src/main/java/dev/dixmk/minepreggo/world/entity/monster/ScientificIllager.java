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
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;
import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.world.entity.npc.Trades;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllQuadrupedCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.IllZombieGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.SelectPregnantEntityForMedicalCheckUpMenu;
import dev.dixmk.minepreggo.world.item.checkup.PrenatalCheckups.PrenatalCheckup;
import io.netty.buffer.Unpooled;
import dev.dixmk.minepreggo.world.entity.preggo.ender.IllEnderGirl;

public class ScientificIllager extends AbstractIllager implements Merchant {
    private MerchantOffers offers;
    private Player tradingPlayer;
    
    private boolean spawnIllEnderGirl = true;
    private boolean spawnIllZombieGirl = true;
    private boolean spawnIllQuadrupedCreeperGirl = true;
    private boolean spawnIllCreeperGirl = true;
    
    private final Map<String, UUID> petsId = new HashMap<>();
    private Map<PrenatalCheckup, Integer> prenatalCheckUpCosts = null;
    
    
	public ScientificIllager(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.SCIENTIFIC_ILLAGER.get(), world);
	}
	
	public ScientificIllager(EntityType<ScientificIllager> type, Level world) {
		super(type, world);
		setMaxUpStep(0.6f);
		xpReward = 12;
		setNoAi(false);
	}
	
    private void updateTrades() {
    	final var trades = Trades.Illager.getRandomTrades(this.random);
		for (var trade : trades) {
			this.offers.add(trade.getOffer(this, this.random));
		}
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
            this.offers = new MerchantOffers();
            this.updateTrades();
         }
         return this.offers;
    }

    public Map<PrenatalCheckup, Integer> getPrenatalCheckUpCosts() {
    	if (this.prenatalCheckUpCosts == null) {
    		this.prenatalCheckUpCosts = PregnancySystemHelper.createPrenatalCheckUpCosts(random, 5, 10);
    	}
    	return this.prenatalCheckUpCosts;
    }
    
    
    @Override
    public void overrideOffers(MerchantOffers offers) {
        this.offers = offers;
    }

    @Override
    public void notifyTrade(MerchantOffer offer) {
        // It does not need, It is not a villager
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
		final List<Integer> list = serverPlayer.level().getEntitiesOfClass(PreggoMob.class, new AABB(blockPos).inflate(16D), e -> e.isOwnedBy(serverPlayer) && e instanceof IPregnancySystemHandler)
					.stream()
					.map(e -> e.getId())
					.collect(Collectors.toCollection(ArrayList::new));
		
		serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 			  		 		 		   		    		
			cap.getFemaleData().ifPresent(femaleData -> {
        		if (femaleData.isPregnant() && femaleData.getPregnancySystem().getCurrentPregnancyStage() != null) {
            		list.add(serverPlayer.getId());		
        		}
			}) 		
		);
		
		if (list.isEmpty()) {
			MessageHelper.sendMessageToPlayer(serverPlayer, Component.translatable("chat.minepreggo.scientific_illager.no_pregnant_entities"));
			return false;
		}
		
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
                return new SelectPregnantEntityForMedicalCheckUpMenu(id, inventory, packetBuffer);
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
	    if (!merchantoffers.isEmpty()) {
	    	compound.put("Offers", merchantoffers.createTag());
	    }
	    compound.putBoolean("SpawnIllEnderGirl", spawnIllEnderGirl);
	    compound.putBoolean("SpawnIllZombieGirl", spawnIllZombieGirl);
	    compound.putBoolean("SpawnIllCreeperGirl", spawnIllCreeperGirl);
	    compound.putBoolean("SpawnIllQuadrupedCreeperGirl", spawnIllQuadrupedCreeperGirl);
	    
	    for (final var entry : this.petsId.entrySet()) {  	
	    	compound.putUUID(entry.getKey(), entry.getValue());
	    }
	    
	    if (this.prenatalCheckUpCosts != null && !this.prenatalCheckUpCosts.isEmpty()) {
	    	compound.put("prenatalCheckUps", PregnancySystemHelper.serializePrenatalCheckupMap(prenatalCheckUpCosts));
	    }
	}
	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);			
		if (compound.contains("Offers")) {
			this.offers = new MerchantOffers(compound.getCompound("Offers"));
		}
		
		this.spawnIllEnderGirl = compound.getBoolean("SpawnIllEnderGirl");
		this.spawnIllZombieGirl = compound.getBoolean("SpawnIllZombieGirl");
		this.spawnIllCreeperGirl = compound.getBoolean("SpawnIllCreeperGirl");
		this.spawnIllQuadrupedCreeperGirl = compound.getBoolean("SpawnIllQuadrupedCreeperGirl");
		this.tryReadPetId("DataIllZombieId", compound);
		this.tryReadPetId("DataIllCreeperId", compound);
		this.tryReadPetId("DataIllQuadrupedCreeperId", compound);
		this.tryReadPetId("DataIllEnderId", compound);	
		
		if (compound.contains("prenatalCheckUps", Tag.TAG_LIST)) {
			prenatalCheckUpCosts = PregnancySystemHelper.deserializePrenatalCheckupMap(compound.getList("prenatalCheckUps", Tag.TAG_COMPOUND));
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
		this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6D));
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));		
	}

	private void tryReadPetId(String key, CompoundTag compound) {
		if (compound.hasUUID(key)) {
			this.petsId.put(key, compound.getUUID(key));
		}
	}
	
	public boolean canIllZombieGirlSpawn() {
		return spawnIllZombieGirl;
	}
	
	public boolean canIllCreeperGirlSpawn() {
		return spawnIllCreeperGirl;
	}
	
	public boolean canIllEnderGirlSpawn() {
		return spawnIllEnderGirl;
	}
	
	public boolean canIllQueadrupedCreeperGirlSpawn() {
		return spawnIllQuadrupedCreeperGirl;
	}
	
	protected boolean spawnIllZombieGirlIfCan(double x, double y, double z) {	
		if (!this.spawnIllZombieGirl) return false;
		this.spawnIllZombieGirl = false;
		if (this.level() instanceof ServerLevel serverLevel && !serverLevel.isClientSide()) {	
			IllZombieGirl mob = MinepreggoModEntities.ILL_ZOMBIE_GIRL.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
			mob.setYRot(this.random.nextFloat() * 360F);
			mob.tameByIllager(this);			
			this.petsId.put("DataIllZombieId", mob.getUUID());	
		}			
		return true;
	}
	
	protected boolean spawnIllCreeperGirlIfCan(double x, double y, double z) {
		if (!this.spawnIllCreeperGirl) return false;
		this.spawnIllCreeperGirl = false;
		if (this.level() instanceof ServerLevel serverLevel && !serverLevel.isClientSide()) {	
			IllCreeperGirl mob = MinepreggoModEntities.ILL_CREEPER_GIRL.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
			mob.setYRot(this.random.nextFloat() * 360F);
			mob.tameByIllager(this);
			this.petsId.put("DataIllCreeperId", mob.getUUID());
		}		
		return true;
	}
	
	protected boolean spawnIllQuadrupedCreeperGirlIfCan(double x, double y, double z) {
		if (!this.spawnIllQuadrupedCreeperGirl) return false;
		this.spawnIllQuadrupedCreeperGirl = false;
		if (this.level() instanceof ServerLevel serverLevel && !serverLevel.isClientSide()) {	
			IllQuadrupedCreeperGirl mob = MinepreggoModEntities.ILL_QUADRUPED_CREEPER_GIRL.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
			mob.setYRot(this.random.nextFloat() * 360F);
			mob.tameByIllager(this);
			this.petsId.put("DataIllQuadrupedCreeperId", mob.getUUID());
		}	
		return true;
	}
	
	protected boolean spawnIllEnderGirlIfCan(double x, double y, double z) {
		if (!this.spawnIllEnderGirl) return false;
		this.spawnIllEnderGirl = false;
		if (this.level() instanceof ServerLevel serverLevel && !serverLevel.isClientSide()) {	
			IllEnderGirl mob = MinepreggoModEntities.ILL_ENDER_GIRL.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
			mob.setYRot(this.random.nextFloat() * 360F);
			mob.tameByIllager(this);
			this.petsId.put("DataIllEnderId", mob.getUUID());
		}		
		return true;
	}
	
	public void trySpawnIllPets() {	
		final var x = this.getX();
		final var y = this.getY();
		final var z = this.getZ();
		this.spawnIllZombieGirlIfCan(x + 1.25, y, z);
		this.spawnIllCreeperGirlIfCan(x - 1.25, y, z);
		this.spawnIllQuadrupedCreeperGirlIfCan(x, y, z + 1.25);
		this.spawnIllEnderGirlIfCan(x, y, z - 1.25);
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
		
		if (!(this.level() instanceof ServerLevel serverLevel)) return;

		MinepreggoMod.LOGGER.debug("ILLAGER SCIENTIFIC DIED, DETACHING PETS");
		
		for (final var uuid : this.petsId.values()) {
			var entity = serverLevel.getEntity(uuid);
			if (entity != null && entity instanceof Ill ill) {
				ill.removeIllagerOwner();
				MinepreggoMod.LOGGER.debug("PET FOUND, id={}, class={}", entity.getId(), entity.getClass().getSimpleName());
			}
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
