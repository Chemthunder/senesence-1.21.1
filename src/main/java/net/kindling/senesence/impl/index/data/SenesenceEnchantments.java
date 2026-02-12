package net.kindling.senesence.impl.index.data;

import net.kindling.senesence.impl.Senesence;
import net.kindling.senesence.impl.index.SenesenceEnchantmentEffects;
import net.kindling.senesence.impl.index.SenesenceItems;
import net.kindling.senesence.impl.index.tag.SenesenceItemTags;
import net.minecraft.block.Block;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public interface SenesenceEnchantments {
    RegistryKey<Enchantment> HARVEST = create("harvest");
    RegistryKey<Enchantment> SPLINTER = create("splinter");

    private static RegistryKey<Enchantment> create(String id) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, Senesence.id(id));
    }

    static void bootstrap(Registerable<Enchantment> registerable) {
        RegistryEntryLookup<Enchantment> enchantmentLookup = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        RegistryEntryLookup<EntityType<?>> entityTypeLookup = registerable.getRegistryLookup(RegistryKeys.ENTITY_TYPE);
        RegistryEntryLookup<Block> blockLookup = registerable.getRegistryLookup(RegistryKeys.BLOCK);
        RegistryEntryLookup<Item> itemLookup = registerable.getRegistryLookup(RegistryKeys.ITEM);

        registerable.register(HARVEST, Enchantment.builder(Enchantment.definition(
                                itemLookup.getOrThrow(SenesenceItemTags.AUTUMNAL),
                                2,
                                1,
                                Enchantment.leveledCost(5, 0),
                                Enchantment.leveledCost(17, 0),
                                7,
                                AttributeModifierSlot.MAINHAND
                        ))
                        .addEffect(SenesenceEnchantmentEffects.HARVEST)
                        .build(HARVEST.getValue())
        );

        registerable.register(SPLINTER, Enchantment.builder(Enchantment.definition(
                                itemLookup.getOrThrow(SenesenceItemTags.AUTUMNAL),
                                2,
                                1,
                                Enchantment.leveledCost(5, 0),
                                Enchantment.leveledCost(17, 0),
                                7,
                                AttributeModifierSlot.MAINHAND
                        ))
                        .addEffect(SenesenceEnchantmentEffects.SPLINTER)
                        .build(SPLINTER.getValue())
        );
    }
}
