package dev.dixmk.minepreggo.client.jiggle;

import javax.annotation.Nullable;

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
    	
    	switch (phase) {
            case P0:
                return createP0(config);
            case P1:
                return createP1(config);
            case P2:
                return createP2(config);
            case P3:
                return createP3(config);
            case P4:
                return createP4(config);
            case P5:
                return createP5(config);
            case P6:
                return createP6(config);
            case P7:
                return createP7(config);
            case P8:
                return createP8(config);
            default:
                throw new IllegalArgumentException("Unknown pregnancy phase: " + phase);
        }
    }
    
    private static EntityJiggleData createNonPregnancy(@NonNull JigglePositionConfig config) {
        BoobsJigglePhysicsWrapper boobs = JigglePhysicsFactory.createLightweightBoobs(config.originalYBoobsPos, true, true);
        return new EntityJiggleData(boobs, null, null);
    }  
    
    private static EntityJiggleData createP0(@NonNull JigglePositionConfig config) {
        BoobsJigglePhysicsWrapper boobs = JigglePhysicsFactory.createLightweightBoobs(config.originalYBoobsPos, true, true);
        BellyJigglePhysics belly = JigglePhysicsFactory.createBelly(config.originalYBellyPos, PregnancyPhase.P0);
        return new EntityJiggleData(boobs, belly, null);
    }

    private static EntityJiggleData createP1(@NonNull JigglePositionConfig config) {
        BoobsJigglePhysicsWrapper boobs = JigglePhysicsFactory.createLightweightBoobs(config.originalYBoobsPos, true, true);
        BellyJigglePhysics belly = JigglePhysicsFactory.createBelly(config.originalYBellyPos, PregnancyPhase.P1);
        return new EntityJiggleData(boobs, belly, null);
    }

    private static EntityJiggleData createP2(@NonNull JigglePositionConfig config) {
        BoobsJigglePhysicsWrapper boobs = JigglePhysicsFactory.createBoobs(config.originalYBoobsPos, false, false);
        BellyJigglePhysics belly = JigglePhysicsFactory.createBelly(config.originalYBellyPos, PregnancyPhase.P2);
        return new EntityJiggleData(boobs, belly, null);
    }

    private static EntityJiggleData createP3(@NonNull JigglePositionConfig config) {
        BoobsJigglePhysicsWrapper boobs = JigglePhysicsFactory.createBoobs(config.originalYBoobsPos, false, false);
        BellyJigglePhysics belly = JigglePhysicsFactory.createBelly(config.originalYBellyPos, PregnancyPhase.P3);
        ButtJigglePhysicsWrapper butt = JigglePhysicsFactory.createLightweightButt(config.originalYButtPos);
        return new EntityJiggleData(boobs, belly, butt);
    }

    private static EntityJiggleData createP4(@NonNull JigglePositionConfig config) {
        BoobsJigglePhysicsWrapper boobs = JigglePhysicsFactory.createHeavyweightBoobs(config.originalYBoobsPos, false, false);
        BellyJigglePhysics belly = JigglePhysicsFactory.createBelly(config.originalYBellyPos, PregnancyPhase.P4);
        ButtJigglePhysicsWrapper butt = JigglePhysicsFactory.createLightweightButt(config.originalYButtPos);
        return new EntityJiggleData(boobs, belly, butt);
    }

    private static EntityJiggleData createP5(@NonNull JigglePositionConfig config) {
        BoobsJigglePhysicsWrapper boobs = JigglePhysicsFactory.createHeavyweightBoobs(config.originalYBoobsPos, false, false);
        BellyJigglePhysics belly = JigglePhysicsFactory.createBelly(config.originalYBellyPos, PregnancyPhase.P5);
        ButtJigglePhysicsWrapper butt = JigglePhysicsFactory.createLightweightButt(config.originalYButtPos);
        return new EntityJiggleData(boobs, belly, butt);
    }

    private static EntityJiggleData createP6(@NonNull JigglePositionConfig config) {
        BoobsJigglePhysicsWrapper boobs = JigglePhysicsFactory.createHeavyweightBoobs(config.originalYBoobsPos, false, false);
        BellyJigglePhysics belly = JigglePhysicsFactory.createBelly(config.originalYBellyPos, PregnancyPhase.P6);
        ButtJigglePhysicsWrapper butt = JigglePhysicsFactory.createHeavyweightButt(config.originalYButtPos);
        return new EntityJiggleData(boobs, belly, butt);
    }
    
    private static EntityJiggleData createP7(@NonNull JigglePositionConfig config) {
        BoobsJigglePhysicsWrapper boobs = JigglePhysicsFactory.createHeavyweightBoobs(config.originalYBoobsPos, false, false);
        BellyJigglePhysics belly = JigglePhysicsFactory.createBelly(config.originalYBellyPos, PregnancyPhase.P7);
        ButtJigglePhysicsWrapper butt = JigglePhysicsFactory.createHeavyweightButt(config.originalYButtPos);
        return new EntityJiggleData(boobs, belly, butt);
    }
    
    private static EntityJiggleData createP8(@NonNull JigglePositionConfig config) {
        BoobsJigglePhysicsWrapper boobs = JigglePhysicsFactory.createHeavyweightBoobs(config.originalYBoobsPos, false, false);
        BellyJigglePhysics belly = JigglePhysicsFactory.createBelly(config.originalYBellyPos, PregnancyPhase.P8);
        ButtJigglePhysicsWrapper butt = JigglePhysicsFactory.createHeavyweightButt(config.originalYButtPos);
        return new EntityJiggleData(boobs, belly, butt);
    }
    
    @OnlyIn(Dist.CLIENT)
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
