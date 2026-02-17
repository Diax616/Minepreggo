package dev.dixmk.minepreggo.world.entity.preggo.ender;

import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.EntityHelper;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class HostileMonsterEnderWoman extends AbstractHostileEnderWoman {
	
	public HostileMonsterEnderWoman(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.HOSTILE_MONSTER_ENDER_WOMAN.get(), world);
	}

	public HostileMonsterEnderWoman(EntityType<HostileMonsterEnderWoman> type, Level world) {
		super(type, world, Creature.MONSTER);
		xpReward = 12;
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
			TamableMonsterEnderWoman next = MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN.get().spawn(serverLevel, BlockPos.containing(this.getX(), this.getY(), this.getZ()), MobSpawnType.CONVERSION);
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
		return MonsterEnderWomanHelper.createTamableAttributes(0.3);
	}
}
