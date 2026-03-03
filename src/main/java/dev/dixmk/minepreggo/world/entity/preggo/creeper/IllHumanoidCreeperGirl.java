package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.init.MinepreggoEntities;
import dev.dixmk.minepreggo.world.entity.monster.Ill;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.network.PlayMessages;
 
public class IllHumanoidCreeperGirl extends AbstractHostileHumanoidCreeperGirl implements Ill {

	public IllHumanoidCreeperGirl(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoEntities.ILL_HUMANOID_CREEPER_GIRL.get(), world);
	}

	public IllHumanoidCreeperGirl(EntityType<IllHumanoidCreeperGirl> type, Level world) {
		super(type, world);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
		setPersistenceRequired();
	}
	
	@Override
	public boolean canBeTamedByPlayer() {
		return false;
	}

	@Override
	public boolean canExplode() {
		return this.getOwner() == null || !this.isTame();
	}

	@Override
	public void tameByIllager(ScientificIllager illagerScientific) {
		this.setOwnerUUID(illagerScientific.getUUID());
		this.setTame(true);
	}
	
	@Override
	public void removeIllagerOwner() {
		this.setOwnerUUID(null);
    	this.setTame(false);	
		Ill.addBehaviourGoalsWhenOwnerDies(this);
	}
	
	@Override
	public void die(DamageSource source) {
		super.die(source);
		if (this.getOwner() instanceof ScientificIllager owner && owner.isAlive() && !this.level().isClientSide) {
			owner.removePet(this.getUUID());
		}		
	}
	
	@Override
	@Nullable
	public LivingEntity getOwner() {	
        if (this.getOwnerUUID() == null || this.level().isClientSide) return null;
        return (LivingEntity) ((ServerLevel) this.level()).getEntity(this.getOwnerUUID());
	}
	
	@Override
	public boolean isAlliedTo(Entity other) {
	    if (other instanceof Ill || other instanceof AbstractIllager) 
	        return true;    
	    return super.isAlliedTo(other);
	}
	
	@Override
	protected void registerGoals() {	
		IllCreeperHelper.addDefaultGoals(this);
		Ill.addBehaviourGoals(this);
	}

	@Override
	protected void reassessTameGoals() {
		if (this.isTame()) {
	    	Ill.addTamableBehaviourGoals(this);
		} else {
			Ill.removeTamableBehaviourGoals(this);
		}
	}
	
	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {					
		return IllCreeperHelper.mobInteract(this, sourceentity, hand);
	}
	
	@Override
	public ItemStack getPickResult() {
	    return ItemStack.EMPTY;
	}
	
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag dataTag) {
		if (spawnGroupData instanceof Ill.IllGroupData illGroupData && illGroupData.hasOwner()) {	
	        final float specialMultiplier = difficulty.getSpecialMultiplier(); // 0.0 (Easy) -> 1.0 (Hard)
	        final float poweredChance = Mth.lerp(specialMultiplier, 0.15F, 0.35F);
	        if (this.random.nextFloat() < poweredChance) {
	            this.setPower(true);
	        }
		}
		return super.finalizeSpawn(level, difficulty, spawnType, null, dataTag);
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return HumanoidCreeperHelper.createBasicAttributes(0.235);
	}
}
