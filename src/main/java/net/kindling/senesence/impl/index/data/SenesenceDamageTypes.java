package net.kindling.senesence.impl.index.data;

import net.kindling.senesence.impl.Senesence;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public interface SenesenceDamageTypes {
    RegistryKey<DamageType> BURY = create("bury");
    RegistryKey<DamageType> SPREADED = create("spreaded");

    private static RegistryKey<DamageType> create(String id) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Senesence.id(id));
    }

    static void bootstrap(Registerable<DamageType> registerable) {
        registerable.register(BURY, new DamageType("bury", 0.0F));
        registerable.register(SPREADED, new DamageType("spreaded", 0.5F));
    }

    static DamageSource create(World world, RegistryKey<DamageType> key, @Nullable Entity source, @Nullable Entity attacker) {
        return new DamageSource(world.getRegistryManager().getWrapperOrThrow(RegistryKeys.DAMAGE_TYPE).getOrThrow(key), source, attacker);
    }

    static DamageSource create(World world, RegistryKey<DamageType> key, @Nullable Entity attacker) {
        return new DamageSource(world.getRegistryManager().getWrapperOrThrow(RegistryKeys.DAMAGE_TYPE).getOrThrow(key), attacker);
    }

    static DamageSource create(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().getWrapperOrThrow(RegistryKeys.DAMAGE_TYPE).getOrThrow(key));
    }
}
