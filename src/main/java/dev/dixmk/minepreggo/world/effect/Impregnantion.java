package dev.dixmk.minepreggo.world.effect;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutableTriple;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.MonsterZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirl;
import dev.dixmk.minepreggo.world.pregnancy.FemaleEntityImpl;
import dev.dixmk.minepreggo.world.pregnancy.IFemaleEntity;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancySystemHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class Impregnantion extends MobEffect {
	
	public Impregnantion() {
		super(MobEffectCategory.NEUTRAL, -10092442);
	}
	
	public Impregnantion(int color) {
		super(MobEffectCategory.NEUTRAL, color);		
	}
	
	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {	
		if (entity.level().isClientSide) {
			return;
		}
		
		if (entity instanceof ServerPlayer serverPlayer) {			
			if (PlayerHelper.tryStartPregnancyByPotion(serverPlayer, ImmutableTriple.of(Optional.empty(), Species.HUMAN, Creature.HUMANOID), amplifier)) {
				MinepreggoMod.LOGGER.info("Player {} has become pregnant.", serverPlayer.getName().getString());
			}
			else {
				MinepreggoMod.LOGGER.info("Player {} could not become pregnant.", serverPlayer.getName().getString());
			}							
		}		
		else if (entity.level() instanceof ServerLevel serverLevel) {		
			final double x = entity.getX();
			final double y = entity.getY();	
			final double z = entity.getZ();
			
			if (entity instanceof MonsterCreeperGirl creeperGirl && !creeperGirl.isBaby()) {
				var nextStage = MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P0.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);
				initPregnancy(creeperGirl, nextStage, amplifier);
			}
			else if (entity instanceof MonsterZombieGirl zombieGirl && !zombieGirl.isBaby()) {
				var nextStage = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);
				initPregnancy(zombieGirl, nextStage, amplifier);
			}
			else if (entity instanceof TamableCreeperGirl creeperGirl) {
				var nextStage = MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P0.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);
				PreggoMobHelper.copyOwner(creeperGirl, nextStage);
				initPregnancy(creeperGirl, nextStage, amplifier);
			}
			else if (entity instanceof TamableZombieGirl zombieGirl) {
				var nextStage = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);
				PreggoMobHelper.copyOwner(zombieGirl, nextStage);
				initPregnancy(zombieGirl, nextStage, amplifier);
			}
			else {
				entity.hurt(new DamageSource(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)), 1);
			}
		}
	}
	
	@Override
	public List<ItemStack> getCurativeItems() {
		ArrayList<ItemStack> cures = new ArrayList<>();
		cures.add(new ItemStack(Items.MILK_BUCKET));
		return cures;
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
	
	
	protected static<E extends PreggoMob & ITamablePreggoMob<FemaleEntityImpl> & IFemaleEntity & IPregnancySystemHandler> void initPregnancy(PreggoMob source, E target, int amplifier) {
		PreggoMobHelper.copyRotation(source, target);
		PreggoMobHelper.transferSlots(source, target);
		PreggoMobHelper.syncFromEquipmentSlotToInventory(target);
		PreggoMobHelper.transferAttackTarget(source, target);
		PreggoMobHelper.initPregnancyByPotion(target, ImmutableTriple.of(Optional.empty(), target.getTypeOfSpecies(), target.getTypeOfCreature()), amplifier);
		source.discard();
	}
}

