package net.kindling.senesence.impl.index;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.kindling.senesence.impl.Senesence;
import net.kindling.senesence.impl.client.particle.AuburnLeafParticle;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public interface SenesenceParticles {
    SimpleParticleType AUBURN_LEAF = FabricParticleTypes.simple(true);
    SimpleParticleType SPARK = FabricParticleTypes.simple(true);

    private static void create(String name, ParticleType<?> particle) {
        Registry.register(Registries.PARTICLE_TYPE, Senesence.id(name), particle);
    }

    static void init() {
        create("auburn_leaf", AUBURN_LEAF);
        create("spark", SPARK);
    }

    static void clientInit() {
        ParticleFactoryRegistry.getInstance().register(AUBURN_LEAF, FlameParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(SPARK, EndRodParticle.Factory::new);
    }
}
