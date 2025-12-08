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
	
	public static @NonNull WrapperBoobsJiggle createLightweightBoobs(float additionalYPos) {
        var leftBoobJiggle = BoobJigglePhysics.builder(0.0f, true);
        var rightBoobJiggle = BoobJigglePhysics.builder(Mth.PI, false);
        return new WrapperBoobsJiggle(additionalYPos, leftBoobJiggle, rightBoobJiggle);
	}
	
	public static @NonNull WrapperBoobsJiggle createBoobs(float additionalYPos) {
        var leftBoobJiggle = BoobJigglePhysics.builder(0.0f, true);    
        var rightBoobJiggle = BoobJigglePhysics.builder(Mth.PI, false); 	 
        return new WrapperBoobsJiggle(additionalYPos, leftBoobJiggle, rightBoobJiggle);
	}
	
	public static @NonNull WrapperBoobsJiggle createHeavyweightBoobs(float additionalYPos) {
        var leftBoobJiggle = BoobJigglePhysics.builder(0.0f, true)
        		.movementMultiplier(0.7F)
        		.damping(0.4F)
				.maxRotationY(0.5F);  
        var rightBoobJiggle = BoobJigglePhysics.builder(Mth.PI, false)
        		.movementMultiplier(0.7F)
        		.damping(0.4F)
				.maxRotationY(0.5F); 	 
        return new WrapperBoobsJiggle(additionalYPos, leftBoobJiggle, rightBoobJiggle);
	}
	
	// BUG: a high value for damping provokes belly does not move
	private static final ImmutableMap<PregnancyPhase, Supplier<BellyJigglePhysics.Builder>> BELLY_JIGGLE = ImmutableMap.of(		
			PregnancyPhase.P0, () -> BellyJigglePhysics.builder().damping(0.6F).maxDisplacement(0.8F),
			PregnancyPhase.P1, () -> BellyJigglePhysics.builder().damping(0.6F).maxDisplacement(0.8F),
			PregnancyPhase.P2, () -> BellyJigglePhysics.builder().damping(0.6F).maxDisplacement(0.8F).movementMultiplier(1.2F),
			PregnancyPhase.P3, () -> BellyJigglePhysics.builder().maxDisplacement(1.2F).maxRotation(1.1F).movementMultiplier(1.6F),
			PregnancyPhase.P4, () -> BellyJigglePhysics.builder().maxDisplacement(1.2F).maxRotation(1.1F).movementMultiplier(1.6F),
			PregnancyPhase.P5, () -> BellyJigglePhysics.builder().maxDisplacement(1.2F).maxRotation(1.1F).movementMultiplier(0.7F),
			PregnancyPhase.P6, () -> BellyJigglePhysics.builder().maxDisplacement(1.6F).maxRotation(1.3F).movementMultiplier(0.9F),
			PregnancyPhase.P7, () -> BellyJigglePhysics.builder().maxDisplacement(1.6F).maxRotation(1.3F).movementMultiplier(1.1F),
			PregnancyPhase.P8, () -> BellyJigglePhysics.builder().maxDisplacement(1.6F).maxRotation(1.3F).movementMultiplier(1.2F)		
			);
	
	public static @NonNull BellyJigglePhysics createBelly(float additionalYPos, PregnancyPhase phase) {
		var supplier = BELLY_JIGGLE.get(phase);
		BellyJigglePhysics.Builder builder;
		if (supplier != null) {
			builder = supplier.get();
		}
		else {
			builder = BellyJigglePhysics.builder();
		}
		
		return builder.additionalYPos(additionalYPos).build();
	}
	
	public static @NonNull WrapperButtJiggle createLightweightButt(float additionalYPos) {
        var leftbuttJiggle = ButtJigglePhysics.builder()
        		.damping(0.9F)
        		.springStrength(1F);
        var rightbuttJiggle = ButtJigglePhysics.builder()
        		.damping(0.9F)
        		.springStrength(1F);
        
        return new WrapperButtJiggle(additionalYPos, leftbuttJiggle, rightbuttJiggle);
	}
	
	public static @NonNull WrapperButtJiggle createHeavyweightButt(float additionalYPos) {
        var leftbuttJiggle = ButtJigglePhysics.builder()
        		.damping(1.3F)
        		.springStrength(1.4F);
        var rightbuttJiggle = ButtJigglePhysics.builder()
        		.damping(1.3F)
        		.springStrength(1.4F);
        
        return new WrapperButtJiggle(additionalYPos, leftbuttJiggle, rightbuttJiggle);
	}
}
