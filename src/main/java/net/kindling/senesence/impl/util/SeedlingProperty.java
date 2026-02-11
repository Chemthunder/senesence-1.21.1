package net.kindling.senesence.impl.util;

import net.minecraft.util.StringIdentifiable;

public enum SeedlingProperty implements StringIdentifiable {
    SMALL("small"),
    MEDIUM("medium"),
    GROWN("grown");

    private final String name;

    SeedlingProperty(final String name) {
        this.name = name;
    }

    public String asString() {
        return name;
    }
}
