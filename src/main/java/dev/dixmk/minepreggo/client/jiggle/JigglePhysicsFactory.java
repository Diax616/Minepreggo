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
        		.maxDisplacement(0.6F)
        		.movementMultiplier(0.7F)
        		.sideInfluence(0.5F);
      
        var rightBoobJiggle = BoobJigglePhysics.builder(Mth.PI, false)
        		.maxDisplacement(0.6F)
        		.movementMultiplier(0.7F)
        		.sideInfluence(0.5F);        
        return new WrapperBoobsJiggle(additionalYPos, leftBoobJiggle, rightBoobJiggle, axisX, axisZ);
	}
	
	public static @NonNull WrapperBoobsJiggle createBoobs(float additionalYPos, boolean axisX, boolean axisZ) {
        var leftBoobJiggle = BoobJigglePhysics.builder(0.0f, true)
        		.maxDisplacement(0.4F)
        		.movementMultiplier(0.6F)
        		.sideInfluence(0.4F); 
        var rightBoobJiggle = BoobJigglePhysics.builder(Mth.PI, false)
        		.maxDisplacement(0.4F)
        		.movementMultiplier(0.6F)
        		.sideInfluence(0.4F); 	 
        return new WrapperBoobsJiggle(additionalYPos, leftBoobJiggle, rightBoobJiggle, axisX, axisZ);
	}
	
	public static @NonNull WrapperBoobsJiggle createHeavyweightBoobs(float additionalYPos, boolean axisX, boolean axisZ) {
        var leftBoobJiggle = BoobJigglePhysics.builder(0.0f, true)
        		.maxDisplacement(0.2F)
        		.movementMultiplier(0.5F)
        		.sideInfluence(0.3F); 
        var rightBoobJiggle = BoobJigglePhysics.builder(Mth.PI, false)
        		.maxDisplacement(0.2F)
        		.movementMultiplier(0.5F)
        		.sideInfluence(0.3F); 	 	 
        return new WrapperBoobsJiggle(additionalYPos, leftBoobJiggle, rightBoobJiggle, axisX, axisZ);
	}
	
	//a high value (x > 1) for damping provokes belly does not move
	private static final ImmutableMap<PregnancyPhase, Supplier<BellyJigglePhysics.Builder>> BELLY_JIGGLE = ImmutableMap.of(		
			PregnancyPhase.P0, () -> BellyJigglePhysics.builder().damping(0.6F).maxDisplacement(0.8F),
			PregnancyPhase.P1, () -> BellyJigglePhysics.builder().damping(0.65F).maxDisplacement(0.85F),
			PregnancyPhase.P2, () -> BellyJigglePhysics.builder().damping(0.7F).maxDisplacement(0.9F).movementMultiplier(1.2F),
			PregnancyPhase.P3, () -> BellyJigglePhysics.builder().maxDisplacement(1.2F).maxRotation(1.1F).movementMultiplier(1.3F),
			PregnancyPhase.P4, () -> BellyJigglePhysics.builder().maxDisplacement(1.2F).maxRotation(1.1F).movementMultiplier(1.3F),
			PregnancyPhase.P5, () -> BellyJigglePhysics.builder().maxDisplacement(1.3F).maxRotation(1.2F).movementMultiplier(1.4F),
			PregnancyPhase.P6, () -> BellyJigglePhysics.builder().maxDisplacement(1.3F).maxRotation(1.2F).movementMultiplier(1.4F),
			PregnancyPhase.P7, () -> BellyJigglePhysics.builder().maxDisplacement(1.4F).maxRotation(1.3F).movementMultiplier(1.5F),
			PregnancyPhase.P8, () -> BellyJigglePhysics.builder().maxDisplacement(1.4F).maxRotation(1.3F).movementMultiplier(1.5F)		
			);
	
	public static @NonNull BellyJigglePhysics createBelly(float additionalYPos, PregnancyPhase phase) {
		var supplier = BELLY_JIGGLE.get(phase);
		BellyJigglePhysics.Builder builder = supplier != null ? supplier.get() : BellyJigglePhysics.builder();	
		return builder.additionalYPos(additionalYPos).build();
	}
	
	public static @NonNull WrapperButtJiggle createLightweightButt(float additionalYPos) {
        var leftbuttJiggle = ButtJigglePhysics.builder()
        		.damping(0.5F)
        		.maxDisplacement(1.2F);    
        var rightbuttJiggle = ButtJigglePhysics.builder()
        		.damping(0.5F)
        		.maxDisplacement(1.2F);
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
