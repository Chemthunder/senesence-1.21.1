package net.kindling.senesence.impl.index;

import net.kindling.senesence.impl.Senesence;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Unit;

import java.util.function.UnaryOperator;

public interface SenesenceEnchantmentEffects {
    ComponentType<Unit> HARVEST = create("harvest",
            builder -> builder.codec(Unit.CODEC));
    ComponentType<Unit> SPLINTER = create("splinter",
            builder -> builder.codec(Unit.CODEC));

    static <T> ComponentType<T> create(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, Senesence.id(name), (builderOperator.apply(ComponentType.builder()).build()));
    }

    static void init() {
        // Enchantment Effects are Registered Statically
    }
}
