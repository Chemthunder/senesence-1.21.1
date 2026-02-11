package net.kindling.senesence.impl.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleGroup;
import net.minecraft.particle.SimpleParticleType;

import java.util.Optional;

public class AuburnLeafParticle extends AbstractSlowingParticle {
    protected AuburnLeafParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
        super(clientWorld, d, e, f, g, h, i);
    }

    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    public float getSize(float tickDelta) {
        float f = ((float)this.age + tickDelta) / (float)this.maxAge;
        return this.scale * (1.0F - f * f * 0.5F);
    }

    public Rotator getRotator() {
        return Rotator.Y_AND_W_ONLY;
    }

    protected int getBrightness(float tint) {
        return 9;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            super();
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            AuburnLeafParticle par = new AuburnLeafParticle(clientWorld, d, e, f, g, h, i);
            par.setSprite(this.spriteProvider);
            return par;
        }

//        // $FF: synthetic method
//        public Particle createParticle(final ParticleEffect particleEffect, final ClientWorld clientWorld, final double d, final double e, final double f, final double g, final double h, final double i) {
//            return this.createParticle((SimpleParticleType)particleEffect, clientWorld, d, e, f, g, h, i);
//        }
    }


}
