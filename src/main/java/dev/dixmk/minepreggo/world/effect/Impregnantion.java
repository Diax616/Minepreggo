package dev.dixmk.minepreggo.world.effect;

import java.util.Optional;
import java.util.UUID;
import java.util.WeakHashMap;

import org.apache.commons.lang3.tuple.ImmutableTriple;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.EntityHelper;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.MonsterZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirl;
import dev.dixmk.minepreggo.world.pregnancy.IFemaleEntity;
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

public class Impregnantion extends MobEffect {
	
	// Map to track entities that should apply the effect when it expires naturally
	private static final WeakHashMap<UUID, Integer> ENTITIES_TO_IMPREGNATE = new WeakHashMap<>();
	
	public Impregnantion() {
		super(MobEffectCategory.NEUTRAL, -10092442);
	}
	
	public Impregnantion(int color) {
		super(MobEffectCategory.NEUTRAL, color);		
	}
	
	/**
	 * Marks an entity to be impregnated when the effect expires naturally.
	 * This should be called when the effect is about to expire.
	 */
	public static void markForImpregnation(LivingEntity entity, int amplifier) {
		ENTITIES_TO_IMPREGNATE.put(entity.getUUID(), amplifier);
	}
	
	/**
	 * Removes the impregnation mark from an entity.
	 * This should be called when the effect is removed by other means (like milk).
	 */
	public static void unmarkForImpregnation(LivingEntity entity) {
		ENTITIES_TO_IMPREGNATE.remove(entity.getUUID());
	}
	
	/**
	 * Checks if an entity is marked for impregnation.
	 */
	public static boolean isMarkedForImpregnation(LivingEntity entity) {
		return ENTITIES_TO_IMPREGNATE.containsKey(entity.getUUID());
	}
	
	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {	
		if (entity.level().isClientSide) {
			return;
		}
		
		// Only apply impregnation if entity is marked (effect expired naturally)
		if (!isMarkedForImpregnation(entity)) {
			return;
		}
		
		// Get the stored amplifier (in case it's different from the parameter)
		Integer storedAmplifier = ENTITIES_TO_IMPREGNATE.get(entity.getUUID());
		if (storedAmplifier != null) {
			amplifier = storedAmplifier;
		}
		
		// Remove the mark
		unmarkForImpregnation(entity);
		
		applyImpregnationEffect(entity, amplifier);
	}
	
	protected void applyImpregnationEffect(LivingEntity entity, int amplifier) {
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
			
			if (entity instanceof MonsterHumanoidCreeperGirl creeperGirl && !creeperGirl.isBaby()) {
				var nextStage = MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P0.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);
				initPregnancy(creeperGirl, nextStage, amplifier);
			}
			else if (entity instanceof MonsterZombieGirl zombieGirl && !zombieGirl.isBaby()) {
				var nextStage = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);
				initPregnancy(zombieGirl, nextStage, amplifier);
			}
			else if (entity instanceof TamableHumanoidCreeperGirl creeperGirl && creeperGirl.getGenderedData().getPostPregnancyData().isEmpty()) {
				var nextStage = MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P0.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);
				initPregnancyInTamable(creeperGirl, nextStage, amplifier);
			}
			else if (entity instanceof TamableZombieGirl zombieGirl && zombieGirl.getGenderedData().getPostPregnancyData().isEmpty()) {
				var nextStage = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);
				initPregnancyInTamable(zombieGirl, nextStage, amplifier);
			}
			else {
				entity.hurt(new DamageSource(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)), 1);
			}
		}
	}
	
	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
	
	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {	
		if (!entity.level().isClientSide) {
			markForImpregnation(entity, amplifier);
		}
	}
	
	protected static<E extends PreggoMob & ITamablePregnantPreggoMob> void initPregnancy(PreggoMob source, E target, int amplifier) {
		LivingEntityHelper.copyRotation(source, target);
		EntityHelper.copyName(source, target);
		LivingEntityHelper.copyHealth(source, target);
		LivingEntityHelper.transferSlots(source, target);
		PreggoMobHelper.syncFromEquipmentSlotToInventory(target);
		LivingEntityHelper.transferAttackTarget(source, target);
		LivingEntityHelper.copyMobEffects(source, target);
		PreggoMobHelper.initPregnancyByPotion(target, ImmutableTriple.of(Optional.empty(), target.getTypeOfSpecies(), target.getTypeOfCreature()), amplifier);
		source.discard();
	}
	
	protected static
	<S extends PreggoMob & ITamablePreggoMob<IFemaleEntity>,
	T extends PreggoMob & ITamablePregnantPreggoMob> void initPregnancyInTamable(S source, T target, int amplifier) {
		PreggoMobHelper.copyOwner(source, target);
		PreggoMobHelper.copyTamableData(source, target);
		PreggoMobHelper.transferInventory(source, target);
		initPregnancy(source, target, amplifier);
	}
}

