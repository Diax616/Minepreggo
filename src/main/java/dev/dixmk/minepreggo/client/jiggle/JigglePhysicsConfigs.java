package dev.dixmk.minepreggo.client.jiggle;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JigglePhysicsConfigs {

	private JigglePhysicsConfigs() {}
	
	public static final ImmutablePair<BoobJigglePhysicsConfig, BoobJigglePhysicsConfig> LIGHTWEIGHT_BOOBS_CONFIG = ImmutablePair.of(
			new BoobJigglePhysicsConfig(BoobJigglePhysicsConfig.builder(0.0f, true)
		    		.maxDisplacement(0.4F)
		    		.damping(0.4f)
		    		.springStrength(2.5F)
		    		.movementMultiplier(0.7F)
		    		.maxRotationX(0.065f)
		    		.maxRotationZ(0.065f)
		    		.sideInfluence(0.2F)),
			new BoobJigglePhysicsConfig(BoobJigglePhysicsConfig.builder(Mth.PI, false)
		    		.maxDisplacement(0.4F)
		    		.damping(0.4f)
		    		.springStrength(2.5F)
		    		.movementMultiplier(0.7F)
		    		.maxRotationX(0.065f)
		    		.maxRotationZ(0.065f)
		    		.sideInfluence(0.2F))
			);
	
	public static final ImmutablePair<BoobJigglePhysicsConfig, BoobJigglePhysicsConfig> DEFAULT_BOOBS_CONFIG = ImmutablePair.of(
			new BoobJigglePhysicsConfig(BoobJigglePhysicsConfig.builder(0.0f, true)
	        		.maxDisplacement(0.35F)
	        		.damping(0.4f)
	        		.springStrength(3.0F)
	        		.movementMultiplier(0.7F)
	        		.sideInfluence(0.2F)),
			new BoobJigglePhysicsConfig(BoobJigglePhysicsConfig.builder(Mth.PI, false)
	        		.maxDisplacement(0.35F)
	        		.damping(0.4f)
	        		.springStrength(3.0F)
	        		.movementMultiplier(0.7F)
	        		.sideInfluence(0.2F))
			);
	
	public static final ImmutablePair<BoobJigglePhysicsConfig, BoobJigglePhysicsConfig> HEAVYWEIGHT_BOOBS_CONFIG = ImmutablePair.of(
			new BoobJigglePhysicsConfig(BoobJigglePhysicsConfig.builder(0.0f, true)
	        		.maxDisplacement(0.25F)
	        		.damping(0.4f)
	        		.springStrength(3.5F)
	        		.movementMultiplier(0.7F)
	        		.sideInfluence(0.2F)),
			new BoobJigglePhysicsConfig(BoobJigglePhysicsConfig.builder(Mth.PI, false)
	        		.maxDisplacement(0.25F)
	        		.damping(0.4f)
	        		.springStrength(3.5F)
	        		.movementMultiplier(0.7F)
	        		.sideInfluence(0.2F))
			);
	
	public static final ImmutablePair<BoobJigglePhysicsConfig, BoobJigglePhysicsConfig> VERY_HEAVYWEIGHT_BOOBS_CONFIG = ImmutablePair.of(
			new BoobJigglePhysicsConfig(BoobJigglePhysicsConfig.builder(0.0f, true)
	        		.maxDisplacement(0.2F)
	        		.damping(0.4f)
	        		.springStrength(4.0F)
	        		.movementMultiplier(0.6F)
	        		.sideInfluence(0.2F)),
			new BoobJigglePhysicsConfig(BoobJigglePhysicsConfig.builder(Mth.PI, false)
	        		.maxDisplacement(0.2F)
	        		.damping(0.4f)
	        		.springStrength(4.0F)
	        		.movementMultiplier(0.6F)
	        		.sideInfluence(0.2F))
			);
	
	public static final BellyJigglePhysicsConfig LIGHTWEIGHT_BELLY_CONFIG = new BellyJigglePhysicsConfig(BellyJigglePhysicsConfig.builder()
			.maxDisplacement(0.7F)
			.movementMultiplier(0.65F));
	
	public static final BellyJigglePhysicsConfig DEFAULT_BELLY_CONFIG = new BellyJigglePhysicsConfig(BellyJigglePhysicsConfig.builder()
			.maxDisplacement(0.7F)
			.maxRotation(0.6F)
			.movementMultiplier(0.6F));
	
	public static final BellyJigglePhysicsConfig HEAVYWEIGHT_BELLY_CONFIG = new BellyJigglePhysicsConfig(BellyJigglePhysicsConfig.builder()
			.maxDisplacement(0.7F)
			.maxRotation(0.65F)
			.movementMultiplier(0.55F));
	
	public static final BellyJigglePhysicsConfig VERY_HEAVYWEIGHT_BELLY_CONFIG = new BellyJigglePhysicsConfig(BellyJigglePhysicsConfig.builder()
			.maxDisplacement(0.7F)
			.maxRotation(0.6F)
			.movementMultiplier(0.5F));
		
	public static final ImmutablePair<ButtJigglePhysicsConfig, ButtJigglePhysicsConfig> DEFAULT_BUTT_CONFIG = ImmutablePair.of(
			new ButtJigglePhysicsConfig(ButtJigglePhysicsConfig.builder()
					.maxDisplacement(1.0F)),
			new ButtJigglePhysicsConfig(ButtJigglePhysicsConfig.builder()
					.maxDisplacement(1.0F))
			);
}
