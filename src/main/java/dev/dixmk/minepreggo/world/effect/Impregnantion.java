package dev.dixmk.minepreggo.world.effect;

import java.util.ArrayList;
import java.util.List;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Baby;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterCreeperGirlP0;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP0;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP1;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.MonsterZombieGirlP0;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP0;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP1;
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
		if (entity instanceof ServerPlayer serverPlayer) {			
			if (PlayerHelper.tryToStartPregnancy(serverPlayer, Baby.HUMAN, amplifier)) {
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
			
			if (entity instanceof MonsterCreeperGirlP0 creeperGirl && !creeperGirl.isBaby()) {
				TamableCreeperGirlP1 nextStage = MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P1.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);
				initPregnancy(creeperGirl, nextStage, amplifier);
			}
			else if (entity instanceof MonsterZombieGirlP0 zombieGirl && !zombieGirl.isBaby()) {
				TamableZombieGirlP1 nextStage = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P1.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);
				initPregnancy(zombieGirl, nextStage, amplifier);
			}
			else if (entity instanceof TamableCreeperGirlP0 creeperGirl) {
				TamableCreeperGirlP1 nextStage = MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P1.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);
				initPregnancy(creeperGirl, nextStage, amplifier);
				PreggoMobHelper.copyOwner(creeperGirl, nextStage);
			}
			else if (entity instanceof TamableZombieGirlP0 zombieGirl) {
				TamableZombieGirlP1 nextStage = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P1.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);
				initPregnancy(zombieGirl, nextStage, amplifier);
				PreggoMobHelper.copyOwner(zombieGirl, nextStage);
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
	
	
	protected static<E extends PreggoMob & ITamablePreggoMob & IPregnancySystemHandler> void initPregnancy(PreggoMob source, E target, int amplifier) {
		PreggoMobHelper.copyRotation(source, target);
		PreggoMobHelper.transferSlots(source, target);
		PreggoMobHelper.syncFromEquipmentSlotToInventory(target);
		PreggoMobHelper.transferAttackTarget(source, target);
		PreggoMobHelper.initPregnancyByPotion(target, amplifier);
		source.discard();
	}
}

