package dev.dixmk.minepreggo.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NauseaParticle extends TextureSheetParticle {

    protected NauseaParticle(ClientLevel level, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteSet spriteSet) {
        super(level, x, y, z, velocityX, velocityY, velocityZ);
        this.setSprite(spriteSet.get(level.random));
        this.setSize(0.2f, 0.2f);
		this.gravity = -0.1f;
        this.quadSize *= 1.75F;
        this.lifetime = 10;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    // Provider factory required by RegisterParticleProvidersEvent.registerSpriteSet
    public static ParticleProvider<SimpleParticleType> provider(SpriteSet sprites) {
        return (type, level, x, y, z, vx, vy, vz) -> new NauseaParticle(level, x, y, z, vx, vy, vz, sprites);
    }
}