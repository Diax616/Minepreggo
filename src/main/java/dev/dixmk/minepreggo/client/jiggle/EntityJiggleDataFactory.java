package dev.dixmk.minepreggo.client.jiggle;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Factory class for creating PlayerJiggleData instances based on pregnancy phase and model type.
 * This factory encapsulates all the configuration for jiggle physics across different pregnancy phases
 * and model types (Custom vs Predefined).
 */
@OnlyIn(Dist.CLIENT)
public class EntityJiggleDataFactory {
    
    private EntityJiggleDataFactory() {}
    
    public static @NonNull EntityJiggleData create(@NonNull JigglePositionConfig config, @Nullable PregnancyPhase phase) { 
    	if (phase == null) {
			return createNonPregnancy(config);
		}  	
    	return switch (phase) {
            case P0, P1 -> createLightweightPregnancy(config);
            case P2 -> createSubstantialPregnancy(config);
            case P3, P4 -> createWeightyPregnancy(config);
            case P5, P6, P7 -> createHeavyweightPregnancy(config);
            case P8 -> createMassivePregnancy(config);
            default -> throw new IllegalArgumentException("Unsupported pregnancy phase: " + phase);
        };
    }

    private static EntityJiggleData createNonPregnancy(@NonNull JigglePositionConfig config) {
        BoobsJigglePhysicsWrapper boobs = new BoobsJigglePhysicsWrapper(config.originalYBoobsPos, JigglePhysicsConfigs.LIGHTWEIGHT_BOOBS_CONFIG.getLeft(), JigglePhysicsConfigs.LIGHTWEIGHT_BOOBS_CONFIG.getRight(), true, true);
        return new EntityJiggleData(boobs, null, null);
    }  
    
    private static EntityJiggleData createLightweightPregnancy(@NonNull JigglePositionConfig config) {
		BoobsJigglePhysicsWrapper boobs = new BoobsJigglePhysicsWrapper(config.originalYBoobsPos, JigglePhysicsConfigs.LIGHTWEIGHT_BOOBS_CONFIG.getLeft(), JigglePhysicsConfigs.LIGHTWEIGHT_BOOBS_CONFIG.getRight(), true, true);
		BellyJigglePhysics belly = new BellyJigglePhysics(config.originalYBellyPos, JigglePhysicsConfigs.LIGHTWEIGHT_BELLY_CONFIG);
		return new EntityJiggleData(boobs, belly, null);
	}
    
    private static EntityJiggleData createSubstantialPregnancy(@NonNull JigglePositionConfig config) {
        BoobsJigglePhysicsWrapper boobs = new BoobsJigglePhysicsWrapper(config.originalYBoobsPos, JigglePhysicsConfigs.DEFAULT_BOOBS_CONFIG.getLeft(), JigglePhysicsConfigs.DEFAULT_BOOBS_CONFIG.getRight(), false, false);
        BellyJigglePhysics belly = new BellyJigglePhysics(config.originalYBellyPos, JigglePhysicsConfigs.DEFAULT_BELLY_CONFIG);
        return new EntityJiggleData(boobs, belly, null);
    }
    
    private static EntityJiggleData createWeightyPregnancy(@NonNull JigglePositionConfig config) {
        BoobsJigglePhysicsWrapper boobs = new BoobsJigglePhysicsWrapper(config.originalYBoobsPos, JigglePhysicsConfigs.DEFAULT_BOOBS_CONFIG.getLeft(), JigglePhysicsConfigs.DEFAULT_BOOBS_CONFIG.getRight(), false, false);
        BellyJigglePhysics belly = new BellyJigglePhysics(config.originalYBellyPos, JigglePhysicsConfigs.DEFAULT_BELLY_CONFIG);
        ButtJigglePhysicsWrapper butt = new ButtJigglePhysicsWrapper(config.originalYButtPos, JigglePhysicsConfigs.DEFAULT_BUTT_CONFIG.getLeft(), JigglePhysicsConfigs.DEFAULT_BUTT_CONFIG.getRight());
        return new EntityJiggleData(boobs, belly, butt);
    }
    
    private static EntityJiggleData createHeavyweightPregnancy(@NonNull JigglePositionConfig config) {
        BoobsJigglePhysicsWrapper boobs = new BoobsJigglePhysicsWrapper(config.originalYBoobsPos, JigglePhysicsConfigs.HEAVYWEIGHT_BOOBS_CONFIG.getLeft(), JigglePhysicsConfigs.HEAVYWEIGHT_BOOBS_CONFIG.getRight(), false, false);
        BellyJigglePhysics belly = new BellyJigglePhysics(config.originalYBellyPos, JigglePhysicsConfigs.DEFAULT_BELLY_CONFIG);
        ButtJigglePhysicsWrapper butt = new ButtJigglePhysicsWrapper(config.originalYButtPos, JigglePhysicsConfigs.DEFAULT_BUTT_CONFIG.getLeft(), JigglePhysicsConfigs.DEFAULT_BUTT_CONFIG.getRight());
		return new EntityJiggleData(boobs, belly, butt);
	}
    
    private static EntityJiggleData createMassivePregnancy(@NonNull JigglePositionConfig config) {
		BoobsJigglePhysicsWrapper boobs = new BoobsJigglePhysicsWrapper(config.originalYBoobsPos, JigglePhysicsConfigs.VERY_HEAVYWEIGHT_BOOBS_CONFIG.getLeft(), JigglePhysicsConfigs.VERY_HEAVYWEIGHT_BOOBS_CONFIG.getRight(), false, false);
		BellyJigglePhysics belly = new BellyJigglePhysics(config.originalYBellyPos, JigglePhysicsConfigs.VERY_HEAVYWEIGHT_BELLY_CONFIG);
		ButtJigglePhysicsWrapper butt = new ButtJigglePhysicsWrapper(config.originalYButtPos, JigglePhysicsConfigs.DEFAULT_BUTT_CONFIG.getLeft(), JigglePhysicsConfigs.DEFAULT_BUTT_CONFIG.getRight());
		return new EntityJiggleData(boobs, belly, butt);
	}

    @OnlyIn(Dist.CLIENT)
    @Immutable
    public static class JigglePositionConfig {
    	private final float originalYBoobsPos;
    	private final float originalYBellyPos;
    	private final float originalYButtPos;

		private JigglePositionConfig(float originalYBoobsPos, float originalYBellyPos, float originalYButtPos) {
			this.originalYBoobsPos = originalYBoobsPos;
			this.originalYBellyPos = originalYBellyPos;
			this.originalYButtPos = originalYButtPos;
		}
		
		public static JigglePositionConfig boobs(float originalYBoobsPos) {
			return new JigglePositionConfig(originalYBoobsPos, -1F, -1F);
		}
		
		public static JigglePositionConfig boobsAndBelly(float originalYBoobsPos, float originalYBellyPos) {
			return new JigglePositionConfig(originalYBoobsPos, originalYBellyPos, -1F);
		}
		
		public static JigglePositionConfig boobsAndBellyAndButt(float originalYBoobsPos, float originalYBellyPos, float originalYButtPos) {
			return new JigglePositionConfig(originalYBoobsPos, originalYBellyPos, originalYButtPos);
		}

		@Override
		public String toString() {
			return "JigglePositionConfig [originalYBoobsPos=" + originalYBoobsPos + ", originalYBellyPos="
					+ originalYBellyPos + ", originalYButtPos=" + originalYButtPos + "]";
		}
    }
}
