package dev.dixmk.minepreggo.world.entity.preggo.ender;

import java.util.EnumSet;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.apache.commons.lang3.function.ToBooleanBiFunction;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Inventory;
import dev.dixmk.minepreggo.world.entity.preggo.InventorySlot;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class MonsterEnderWomanHelper {

	static final String SIMPLE_NAME = "Monster Ender Woman";
	
	private MonsterEnderWomanHelper() {}

	private static<E extends AbstractTamableEnderWoman> void travel(Predicate<E> predicate, E enderWoman, Vec3 dir, Consumer<Vec3> parentMethod) {
		if (predicate.test(enderWoman) && enderWoman.getFirstPassenger() instanceof  LivingEntity owner) {
			enderWoman.setYRot(owner.getYRot());
			enderWoman.yRotO = enderWoman.getYRot();
			enderWoman.setXRot(owner.getXRot() * 0.5F);		
			enderWoman.setYRot(enderWoman.getYRot() % 360.0F);
			enderWoman.setXRot(enderWoman.getXRot() % 360.0F);
			enderWoman.yBodyRot = owner.getYRot();
			enderWoman.yHeadRot = owner.getYRot();
			enderWoman.setSpeed((float) enderWoman.getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.5f);
			float forward = owner.zza;
			float strafe = owner.xxa;
			parentMethod.accept(new Vec3(strafe, 0, forward));
			double d1 = enderWoman.getX() - enderWoman.xo;
			double d0 = enderWoman.getZ() - enderWoman.zo;
			float f1 = (float) Math.sqrt(d1 * d1 + d0 * d0) * 4;
			if (f1 > 1.0F)
				f1 = 1.0F;
			enderWoman.walkAnimation.setSpeed(enderWoman.walkAnimation.speed() + (f1 - enderWoman.walkAnimation.speed()) * 0.4F);
			enderWoman.walkAnimation.position(enderWoman.walkAnimation.position() + enderWoman.walkAnimation.speed());
			enderWoman.calculateEntityAnimation(true);
		}
		else {
			parentMethod.accept(dir);
		}
	}
	
	static void travel(AbstractTamableEnderWoman enderWoman, Vec3 dir, Consumer<Vec3> parentMethod) {
		travel(MonsterEnderWomanHelper::canTravel, enderWoman, dir, parentMethod);
	}
	
	static void travelBeingPregnant(AbstractTamablePregnantEnderWoman enderWoman, Vec3 dir, Consumer<Vec3> parentMethod) {
		travel(MonsterEnderWomanHelper::canTravelBeingPregnant, enderWoman, dir, parentMethod);
	}
	
	static boolean canTeleportWithOwner(AbstractTamableEnderWoman enderWoman) {
		if (enderWoman.isAggressive() || enderWoman.getTamableData().isWaiting() || !(enderWoman.getOwner() instanceof Player owner) ) {
			return false;
		}
		return (enderWoman.isVehicle() && enderWoman.getFirstPassenger() instanceof LivingEntity passenger && passenger.getUUID().equals(owner.getUUID())) || enderWoman.distanceToSqr(owner) <= 4.0D;
	}
	
	static boolean canTeleportWithOwnerBeingPregnant(AbstractTamablePregnantEnderWoman enderWoman) {
		return !enderWoman.getPregnancyData().isIncapacitated() && canTeleportWithOwner(enderWoman);
	}
	
	static double getMyRidingOffset() {
		return -0.35D;
	}
	
    static AttributeSupplier.Builder createTamableAttributes(double movementSpeed) {
        return Mob.createMobAttributes()
        		.add(Attributes.MAX_HEALTH, 46.0D)
        		.add(Attributes.MOVEMENT_SPEED, movementSpeed)
        		.add(Attributes.ATTACK_DAMAGE, 7.0D)
        		.add(Attributes.FOLLOW_RANGE, 64.0D);
	}
	
    static AttributeSupplier.Builder createBasicAttributes(double movementSpeed) {
        return Mob.createMobAttributes()
        		.add(Attributes.MAX_HEALTH, 40.0D)
        		.add(Attributes.MOVEMENT_SPEED, movementSpeed)
        		.add(Attributes.ATTACK_DAMAGE, 7.0D)
        		.add(Attributes.FOLLOW_RANGE, 64.0D);
	}
     
	static boolean hurt(AbstractTamableEnderWoman enderWoman, DamageSource damagesource, float amount, ToBooleanBiFunction<DamageSource, Float> parentMethod) {
		boolean result = parentMethod.applyAsBoolean(damagesource, amount);
		if (result && !enderWoman.level().isClientSide && enderWoman.isVehicle() && enderWoman.getFirstPassenger() instanceof LivingEntity passenger) {
			enderWoman.stopRiding();
			passenger.stopRiding();
		}				
		return result;
	}
	
	static Inventory createInventory() {
		return new Inventory(
				EnumSet.of(
						InventorySlot.HEAD,
						InventorySlot.MAINHAND,
						InventorySlot.OFFHAND,
						InventorySlot.FOOD,
						InventorySlot.BOTH_HANDS
				),10);
	}
	
	private static boolean canTravel(AbstractTamableEnderWoman enderWoman) {
		return enderWoman.isVehicle() && 
				enderWoman.getFirstPassenger() instanceof Player owner && 
				enderWoman.isOwnedBy(owner) &&
				!enderWoman.isAggressive();
	}
	
	private static boolean canTravelBeingPregnant(AbstractTamablePregnantEnderWoman enderWoman) {
		return canTravel(enderWoman) && !enderWoman.getPregnancyData().isIncapacitated();
	}
	
	public static EntityType<? extends AbstractTamablePregnantMonsterEnderWoman> getEntityType(PregnancyPhase phase) {	
		switch (phase) {
			case P0 -> MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P0.get();
			case P1 -> MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P1.get();
			case P2 -> MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P2.get();
			case P3 -> MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P3.get();
			case P4 -> MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P4.get();
			case P5 -> MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P5.get();
			case P6 -> MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P6.get();
			case P7 -> MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P7.get();	
			case P8 -> MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P8.get();	
		}
		throw new IllegalArgumentException("Unexpected value: " + phase);	
	}
	
	static boolean canBeMountedBy(AbstractTamableEnderWoman enderWoman, LivingEntity target) {		
		boolean flag = enderWoman.isOwnedBy(target);
		if (flag && target instanceof ServerPlayer player) {
			Optional<Boolean> isTooPregnant = player.getCapability(MinepreggoCapabilities.PLAYER_DATA)
					.resolve()
					.flatMap(cap -> cap.getFemaleData().resolve())
					.map(femaleData -> {
						return femaleData.isPregnant()
								&& femaleData.isPregnancyDataInitialized()
								&& femaleData.getPregnancyData().getCurrentPregnancyPhase().compareTo(PregnancyPhase.P3) >= 0;
					});
			
			if (isTooPregnant.isPresent()) {	
				boolean result = isTooPregnant.get().booleanValue();
				if (result) {
					String message = "chat.minepreggo.player.pregnancy.message.cannot_ride.ender_woman.generic." + enderWoman.getRandom().nextInt(1, 3);
					MessageHelper.sendTo(player, Component.translatable(message, enderWoman.getSimpleNameOrCustom()), true);
				}	
				return !result;
			}	
		}

		return flag;
	}
	
	static boolean canBeMountedBeingPregnantBy(AbstractTamablePregnantEnderWoman enderWoman, LivingEntity target) {		
		if (!canBeMountedBy(enderWoman, target)) {
			return false;
		}
		
		if (target instanceof ServerPlayer player) {
			PregnancyPain pregnancyPain = enderWoman.getPregnancyData().getPregnancyPain();
			if (pregnancyPain == null) {
				return true;
			}
			
			switch (pregnancyPain) {
				case MORNING_SICKNESS:
					MessageHelper.sendTo(player, Component.translatable("chat.minepreggo.player.message.cannot_ride.ender_woman.pregnant.morning_sickness", enderWoman.getSimpleNameOrCustom()), true);
					return false;
				case WATER_BREAKING, PREBIRTH, BIRTH:
					MessageHelper.sendTo(player, Component.translatable("chat.minepreggo.player.message.cannot_ride.ender_woman.pregnant.labor", enderWoman.getSimpleNameOrCustom()), true);
					return false;
				case CONTRACTION, MISCARRIAGE:
					MessageHelper.sendTo(player, Component.translatable("chat.minepreggo.player.message.cannot_ride.ender_woman.pregnant.common_pregnancy_pain", enderWoman.getSimpleNameOrCustom()), true);
					return false;
				default:
					return true;
			}
		}
		
		return false;
	}
}

