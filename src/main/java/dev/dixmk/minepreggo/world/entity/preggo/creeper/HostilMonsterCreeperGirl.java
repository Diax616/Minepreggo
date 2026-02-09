package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.EntityHelper;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class HostilMonsterCreeperGirl extends AbstractHostilMonsterCreeperGirl {

	public HostilMonsterCreeperGirl(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.HOSTILE_MONSTER_CREEPER_GIRL.get(), world);
	}

	public HostilMonsterCreeperGirl(EntityType<HostilMonsterCreeperGirl> type, Level world) {
		super(type, world);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}
	
	@Override
	public boolean canBeTamedByPlayer() {
		return true;
	}
	
	@Override
	protected void afterTaming() {
		if (this.level() instanceof ServerLevel serverLevel) {
			TamableMonsterCreeperGirl next = MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL.get().spawn(serverLevel, BlockPos.containing(this.getX(), this.getY(), this.getZ()), MobSpawnType.CONVERSION);
			LivingEntityHelper.copyRotation(this, next);
			EntityHelper.copyName(this, next);
			LivingEntityHelper.copyHealth(this, next);
			LivingEntityHelper.transferSlots(this, next);
			PreggoMobHelper.syncFromEquipmentSlotToInventory(next);
			PreggoMobHelper.copyOwner(this, next);
			next.getTamableData().setSavage(false);		
			this.discard();
		}
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return MonsterCreeperHelper.createBasicAttributes(0.27);
	}
}
