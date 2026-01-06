package dev.dixmk.minepreggo.client.jiggle;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.world.entity.player.SkinType;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Factory class for creating PlayerJiggleData instances based on pregnancy phase and model type.
 * This factory encapsulates all the configuration for jiggle physics across different pregnancy phases
 * and model types (Custom vs Predefined).
 */
@OnlyIn(Dist.CLIENT)
public class PlayerJiggleDataFactory {
    
    private PlayerJiggleDataFactory() {}
    
    public static @NonNull PlayerJiggleData create(@NonNull PregnancyPhase phase, @NonNull SkinType modelType) {
        switch (phase) {
            case P0:
                return createP0();
            case P1:
                return createP1();
            case P2:
                return createP2();
            case P3:
                return createP3();
            case P4:
                return createP4(modelType);
            case P5:
                return createP5(modelType);
            case P6:
                return createP6(modelType);
            case P7:
                return createP7(modelType);
            case P8:
                return createP8(modelType);
            default:
                throw new IllegalArgumentException("Unknown pregnancy phase: " + phase);
        }
    }
    
    
    public static PlayerJiggleData createNonPregnancy() {
        WrapperBoobsJiggle boobs = JigglePhysicsFactory.createLightweightBoobs(2.0F, true, true);
        return new PlayerJiggleData(boobs, null, null);
    }  
    
    private static PlayerJiggleData createP0() {
        WrapperBoobsJiggle boobs = JigglePhysicsFactory.createLightweightBoobs(2.0F, true, true);
        BellyJigglePhysics belly = JigglePhysicsFactory.createBelly(6.5F, PregnancyPhase.P0);
        return new PlayerJiggleData(boobs, belly, null);
    }

    private static PlayerJiggleData createP1() {
        WrapperBoobsJiggle boobs = JigglePhysicsFactory.createLightweightBoobs(2.0F, true, true);
        BellyJigglePhysics belly = JigglePhysicsFactory.createBelly(6.0F, PregnancyPhase.P1);
        return new PlayerJiggleData(boobs, belly, null);
    }

    private static PlayerJiggleData createP2() {
        WrapperBoobsJiggle boobs = JigglePhysicsFactory.createBoobs(2.0F, false, false);
        BellyJigglePhysics belly = JigglePhysicsFactory.createBelly(4.95F, PregnancyPhase.P2);
        return new PlayerJiggleData(boobs, belly, null);
    }

    private static PlayerJiggleData createP3() {
        WrapperBoobsJiggle boobs = JigglePhysicsFactory.createBoobs(2.0F, false, false);
        BellyJigglePhysics belly = JigglePhysicsFactory.createBelly(5.0F, PregnancyPhase.P3);
        return new PlayerJiggleData(boobs, belly, null);
    }

    private static PlayerJiggleData createP4(SkinType modelType) {
        WrapperBoobsJiggle boobs = modelType == SkinType.CUSTOM
            ? JigglePhysicsFactory.createHeavyweightBoobs(1.0F, false, false)
            : JigglePhysicsFactory.createHeavyweightBoobs(1.75F, false, false);
        BellyJigglePhysics belly = JigglePhysicsFactory.createBelly(5.0F, PregnancyPhase.P4);
        WrapperButtJiggle butt = JigglePhysicsFactory.createLightweightButt(0.0F);
        return new PlayerJiggleData(boobs, belly, butt);
    }

    private static PlayerJiggleData createP5(SkinType modelType) {
        WrapperBoobsJiggle boobs = modelType == SkinType.CUSTOM
            ? JigglePhysicsFactory.createHeavyweightBoobs(0.85F, false, false)
            : JigglePhysicsFactory.createHeavyweightBoobs(1.55F, false, false);
        BellyJigglePhysics belly = JigglePhysicsFactory.createBelly(5.5F, PregnancyPhase.P5);
        WrapperButtJiggle butt = JigglePhysicsFactory.createLightweightButt(0.0F);
        return new PlayerJiggleData(boobs, belly, butt);
    }

    private static PlayerJiggleData createP6(SkinType modelType) {
        WrapperBoobsJiggle boobs = modelType == SkinType.CUSTOM
            ? JigglePhysicsFactory.createHeavyweightBoobs(0.7F, false, false)
            : JigglePhysicsFactory.createHeavyweightBoobs(1.45F, false, false);
        BellyJigglePhysics belly = modelType == SkinType.CUSTOM
            ? JigglePhysicsFactory.createBelly(9.0F, PregnancyPhase.P6)
            : JigglePhysicsFactory.createBelly(5.75F, PregnancyPhase.P6);
        WrapperButtJiggle butt = JigglePhysicsFactory.createHeavyweightButt(0.0F);

        return new PlayerJiggleData(boobs, belly, butt);
    }
    
    private static PlayerJiggleData createP7(SkinType modelType) {
        WrapperBoobsJiggle boobs = modelType == SkinType.CUSTOM
            ? JigglePhysicsFactory.createHeavyweightBoobs(0.6F, false, false)
            : JigglePhysicsFactory.createHeavyweightBoobs(1.35F, false, false);
        BellyJigglePhysics belly = modelType == SkinType.CUSTOM
            ? JigglePhysicsFactory.createBelly(9.0F, PregnancyPhase.P7)
            : JigglePhysicsFactory.createBelly(9.15F, PregnancyPhase.P7);
        WrapperButtJiggle butt = JigglePhysicsFactory.createHeavyweightButt(0.0F);
        return new PlayerJiggleData(boobs, belly, butt);
    }
    
    private static PlayerJiggleData createP8(SkinType modelType) {
        WrapperBoobsJiggle boobs = modelType == SkinType.CUSTOM
            ? JigglePhysicsFactory.createHeavyweightBoobs(0.4F, false, false)
            : JigglePhysicsFactory.createHeavyweightBoobs(1.25F, false, false);
        BellyJigglePhysics belly = modelType == SkinType.CUSTOM
            ? JigglePhysicsFactory.createBelly(10.0F, PregnancyPhase.P8)
            : JigglePhysicsFactory.createBelly(9.25F, PregnancyPhase.P8);
        WrapperButtJiggle butt = JigglePhysicsFactory.createHeavyweightButt(0.0F);
        return new PlayerJiggleData(boobs, belly, butt);
    }
}
