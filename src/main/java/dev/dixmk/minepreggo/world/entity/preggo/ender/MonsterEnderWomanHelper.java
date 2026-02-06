package dev.dixmk.minepreggo.world.entity.preggo.ender;

import java.util.EnumSet;
import java.util.function.Consumer;

import org.apache.commons.lang3.function.ToBooleanBiFunction;

import dev.dixmk.minepreggo.world.entity.preggo.Inventory;
import dev.dixmk.minepreggo.world.entity.preggo.InventorySlot;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class MonsterEnderWomanHelper {

	static final String SIMPLE_NAME = "Monster Ender Woman";
	
	private MonsterEnderWomanHelper() {}

	static void travel(AbstractTamableEnderWoman enderWoman, Vec3 dir, Consumer<Vec3> parentMethod) {
		Entity entity = enderWoman.getFirstPassenger();
		if (entity != null && enderWoman.isVehicle() && !enderWoman.isAggressive()) {
			enderWoman.setYRot(entity.getYRot());
			enderWoman.yRotO = enderWoman.getYRot();
			enderWoman.setXRot(entity.getXRot() * 0.5F);		
			enderWoman.setYRot(enderWoman.getYRot() % 360.0F);
			enderWoman.setXRot(enderWoman.getXRot() % 360.0F);
			enderWoman.yBodyRot = entity.getYRot();
			enderWoman.yHeadRot = entity.getYRot();
			if (entity instanceof LivingEntity passenger) {
				enderWoman.setSpeed((float) enderWoman.getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.5f);
				float forward = passenger.zza;
				float strafe = passenger.xxa;
				parentMethod.accept(new Vec3(strafe, 0, forward));
			}
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
	
	static boolean canTeleportWithOwner(AbstractTamableEnderWoman enderWoman) {
	    return enderWoman.isVehicle() && enderWoman.getFirstPassenger() instanceof Player ownerPlayer && enderWoman.isOwnedBy(ownerPlayer);
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
}
