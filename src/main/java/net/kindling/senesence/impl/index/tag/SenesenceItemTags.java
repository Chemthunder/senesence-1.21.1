package net.kindling.senesence.impl.index.tag;

import net.kindling.senesence.impl.Senesence;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public interface SenesenceItemTags {
    TagKey<Item> AUTUMNAL = create("autumnal");

    private static TagKey<Item> create(String id) {
        return TagKey.of(RegistryKeys.ITEM, Senesence.id(id));
    }
}
