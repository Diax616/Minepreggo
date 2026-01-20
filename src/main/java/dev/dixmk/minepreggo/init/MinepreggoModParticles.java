package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.MinepreggoMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MinepreggoModParticles {
	
	private	MinepreggoModParticles() {}
	
    public static final DeferredRegister<ParticleType<?>> REGISTRY =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MinepreggoMod.MODID);

    public static final RegistryObject<SimpleParticleType> NAUSEA =
    		REGISTRY.register("nausea", () -> new SimpleParticleType(false));

}