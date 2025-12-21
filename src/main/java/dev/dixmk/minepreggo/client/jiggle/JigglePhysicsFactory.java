package dev.dixmk.minepreggo.client.jiggle;

import java.util.function.Supplier;

import org.checkerframework.checker.nullness.qual.NonNull;

import com.google.common.collect.ImmutableMap;

import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JigglePhysicsFactory {

	private JigglePhysicsFactory() {}
	
	public static @NonNull WrapperBoobsJiggle createLightweightBoobs(float additionalYPos, boolean axisX, boolean axisZ) {
        var leftBoobJiggle = BoobJigglePhysics.builder(0.0f, true)
        		.maxDisplacement(0.4F)
        		.damping(0.4f)
        		.springStrength(2.5F)
        		.movementMultiplier(0.7F)
        		.sideInfluence(0.2F);
        var rightBoobJiggle = BoobJigglePhysics.builder(Mth.PI, false)
        		.maxDisplacement(0.4F)
        		.damping(0.4f)
        		.springStrength(3.0F)
        		.movementMultiplier(0.7F)
        		.sideInfluence(0.2F);    
        return new WrapperBoobsJiggle(additionalYPos, leftBoobJiggle, rightBoobJiggle, axisX, axisZ);
	}
	
	public static @NonNull WrapperBoobsJiggle createBoobs(float additionalYPos, boolean axisX, boolean axisZ) {
        var leftBoobJiggle = BoobJigglePhysics.builder(0.0f, true)
        		.maxDisplacement(0.35F)
        		.damping(0.4f)
        		.springStrength(2.5F)
        		.movementMultiplier(0.7F)
        		.sideInfluence(0.2F); 
        var rightBoobJiggle = BoobJigglePhysics.builder(Mth.PI, false)
        		.maxDisplacement(0.35F)
        		.damping(0.4f)
        		.springStrength(3.0F)
        		.movementMultiplier(0.7F)
        		.sideInfluence(0.2F); 
        return new WrapperBoobsJiggle(additionalYPos, leftBoobJiggle, rightBoobJiggle, axisX, axisZ);
	}
	
	public static @NonNull WrapperBoobsJiggle createHeavyweightBoobs(float additionalYPos, boolean axisX, boolean axisZ) {
        var leftBoobJiggle = BoobJigglePhysics.builder(0.0f, true)
        		.maxDisplacement(0.25F)
        		.damping(0.4f)
        		.springStrength(3.5F)
        		.movementMultiplier(0.7F)
        		.sideInfluence(0.2F); 
        var rightBoobJiggle = BoobJigglePhysics.builder(Mth.PI, false)
        		.maxDisplacement(0.25F)
        		.damping(0.4f)
        		.springStrength(3.5F)
        		.movementMultiplier(0.7F)
        		.sideInfluence(0.2F); 	 	 
        return new WrapperBoobsJiggle(additionalYPos, leftBoobJiggle, rightBoobJiggle, axisX, axisZ);
	}
	
	//BUG: a high value (x > 1) for damping provokes that belly does not move
	private static final ImmutableMap<PregnancyPhase, Supplier<BellyJigglePhysics.Builder>> BELLY_JIGGLE = ImmutableMap.of(		
			PregnancyPhase.P0, () -> BellyJigglePhysics.builder().maxDisplacement(0.7F).movementMultiplier(0.6F),
			PregnancyPhase.P1, () -> BellyJigglePhysics.builder().maxDisplacement(0.7F).movementMultiplier(0.6F),
			PregnancyPhase.P2, () -> BellyJigglePhysics.builder().maxDisplacement(0.7F).movementMultiplier(0.65F),
			PregnancyPhase.P3, () -> BellyJigglePhysics.builder().maxDisplacement(0.7F).maxRotation(0.4F).movementMultiplier(0.65F),
			PregnancyPhase.P4, () -> BellyJigglePhysics.builder().maxDisplacement(0.7F).maxRotation(0.4F).movementMultiplier(0.7F),
			PregnancyPhase.P5, () -> BellyJigglePhysics.builder().maxDisplacement(0.7F).maxRotation(0.4F).movementMultiplier(0.7F),
			PregnancyPhase.P6, () -> BellyJigglePhysics.builder().maxDisplacement(0.7F).maxRotation(0.4F).movementMultiplier(0.75F),
			PregnancyPhase.P7, () -> BellyJigglePhysics.builder().maxDisplacement(0.7F).maxRotation(0.4F).movementMultiplier(0.75F),
			PregnancyPhase.P8, () -> BellyJigglePhysics.builder().maxDisplacement(0.7F).maxRotation(0.4F).movementMultiplier(0.8F)		
			);
	
	public static @NonNull BellyJigglePhysics createBelly(float additionalYPos, PregnancyPhase phase) {
		var supplier = BELLY_JIGGLE.get(phase);
		BellyJigglePhysics.Builder builder = supplier != null ? supplier.get() : BellyJigglePhysics.builder();	
		return builder.additionalYPos(additionalYPos).build();
	}
	
	public static @NonNull WrapperButtJiggle createLightweightButt(float additionalYPos) {
        var leftbuttJiggle = ButtJigglePhysics.builder()
        		.maxDisplacement(1.0F);    
        var rightbuttJiggle = ButtJigglePhysics.builder()
        		.maxDisplacement(1.0F);
        return new WrapperButtJiggle(additionalYPos, leftbuttJiggle, rightbuttJiggle);
	}
	
	public static @NonNull WrapperButtJiggle createHeavyweightButt(float additionalYPos) {
        var leftbuttJiggle = ButtJigglePhysics.builder()
        		.maxDisplacement(0.8F);  
        var rightbuttJiggle = ButtJigglePhysics.builder()
        		.maxDisplacement(0.8F);
        return new WrapperButtJiggle(additionalYPos, leftbuttJiggle, rightbuttJiggle);
	}
}
