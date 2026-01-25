package dev.dixmk.minepreggo.world.entity;

import javax.annotation.CheckForNull;

import com.google.common.collect.ImmutableMap;

import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.world.entity.LivingEntity;

public class BellyPartFactory {

	private BellyPartFactory() {}
	
	// Do not increase parameter offsetY over 0.55f, in players the belly collision hitbox push the player down into the ground
	private static final ImmutableMap<PregnancyPhase, BellyPartConfig> HUMANOID_BELLY_CONFIGS = ImmutableMap.of(
			PregnancyPhase.P4, new BellyPartConfig(0.5f, 0.5f, 0.0f, 0.55f, 0.365f),
			PregnancyPhase.P5, new BellyPartConfig(0.525f, 0.525f, 0.0f, 0.55f, 0.435f),
			PregnancyPhase.P6, new BellyPartConfig(0.575f, 0.575f, 0.0f, 0.55f, 0.525f),
			PregnancyPhase.P7, new BellyPartConfig(0.625f, 0.625f, 0.0f, 0.55f, 0.6f),
			PregnancyPhase.P8, new BellyPartConfig(0.675f, 0.675f, 0.0f, 0.55f, 0.65f)
	);
	
	@CheckForNull
	public static BellyPart createHumanoidBellyPart(LivingEntity parent, PregnancyPhase phase) {
		BellyPartConfig config = HUMANOID_BELLY_CONFIGS.get(phase);
		if (config == null) {
			return null;
		}
		return createBellyPart(parent, config);
	}
	
	@CheckForNull
	public static BellyPartConfig getHumanoidBellyConfig(PregnancyPhase phase) {
		return HUMANOID_BELLY_CONFIGS.get(phase);
	}
	
	public static BellyPart createBellyPart(LivingEntity parent, BellyPartConfig config) {
		return new BellyPart(
			parent,
			config.width(),
			config.height(),
			config.offsetX(),
			config.offsetY(),
			config.offsetZ()	
		);
	}

	public static record BellyPartConfig(float width, float height, float offsetX, float offsetY, float offsetZ) {}
	
}
