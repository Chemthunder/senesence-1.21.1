package net.kindling.senesence.impl.index;

import com.mojang.serialization.Codec;
import net.kindling.senesence.impl.Senesence;
import net.minecraft.component.ComponentType;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.List;
import java.util.function.UnaryOperator;

public interface SenesenceDataComponents {
    ComponentType<Integer> CLEAVER_SEEDS = create("cleaver_seeds", builder -> builder.codec(Codec.INT));
    ComponentType<Boolean> SPLINTERED = create("splintered", builder -> builder.codec(Codec.BOOL));
    ComponentType<Boolean> THROWN_CLEAVER = create("thrown_cleaver", builder -> builder.codec(Codec.BOOL));

    static <T> ComponentType<T> create(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Senesence.id(name), (builderOperator.apply(ComponentType.builder()).build()));
    }

    static void init() {
        //
    }
}
