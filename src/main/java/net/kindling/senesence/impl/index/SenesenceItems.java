package net.kindling.senesence.impl.index;

import net.kindling.senesence.impl.Senesence;
import net.kindling.senesence.impl.item.FloralCleaverItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.function.Function;

public interface SenesenceItems {
    Item FLORAL_CLEAVER = create("floral_cleaver", settings -> new FloralCleaverItem(SenesenceToolMaterials.FLORAL, settings), new Item.Settings().maxCount(1));




    static Item create(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        Item item = factory.apply(settings);
        if (item instanceof BlockItem blockItem) {
            blockItem.appendBlocks(Item.BLOCK_ITEMS, item);
        }

        return Registry.register(Registries.ITEM, Senesence.id(name), item);
    }

    static void init() {
        //
    }
}
