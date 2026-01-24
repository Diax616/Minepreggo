package dev.dixmk.minepreggo.world.entity;

import javax.annotation.CheckForNull;

import com.google.common.collect.ImmutableMap;

import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.world.entity.LivingEntity;

public class BellyPartFactory {

	private BellyPartFactory() {}
	
	private static final ImmutableMap<PregnancyPhase, BellyPartConfig> HUMANOID_BELLY_CONFIGS = ImmutableMap.of(
			PregnancyPhase.P5, new BellyPartConfig(0.2f, 0.2f, 0.0f, 0.5f, 0.615f),
			PregnancyPhase.P6, new BellyPartConfig(0.3f, 0.3f, 0.0f, 0.5f, 0.7f),
			PregnancyPhase.P7, new BellyPartConfig(0.4f, 0.4f, 0.0f, 0.5f, 0.8f),
			PregnancyPhase.P8, new BellyPartConfig(0.5f, 0.5f, 0.0f, 0.5f, 0.9f)
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
