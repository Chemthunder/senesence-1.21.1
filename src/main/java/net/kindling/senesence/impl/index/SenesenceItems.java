package net.kindling.senesence.impl.index;

import net.kindling.senesence.impl.Senesence;
import net.kindling.senesence.impl.block.item.CleaverSeedlingBlockItem;
import net.kindling.senesence.impl.item.AuburnCleaverItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.function.Function;

import static net.acoyt.acornlib.api.util.ItemUtils.modifyItemNameColor;

public interface SenesenceItems {
    Item AUBURN_CLEAVER = create("auburn_cleaver", settings -> new AuburnCleaverItem(ToolMaterials.NETHERITE, settings), new Item.Settings().maxCount(1).attributeModifiers(AuburnCleaverItem.createAttributeModifiers()));

    Item CLEAVER_SEEDLING_BI = create("cleaver_seedling", CleaverSeedlingBlockItem::new, new Item.Settings().maxCount(16));



    static Item create(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        Item item = factory.apply(settings);
        if (item instanceof BlockItem blockItem) {
            blockItem.appendBlocks(Item.BLOCK_ITEMS, item);
        }

        return Registry.register(Registries.ITEM, Senesence.id(name), item);
    }

    static void init() {
        modifyItemNameColor(AUBURN_CLEAVER, 0xFF5c3735);
        modifyItemNameColor(CLEAVER_SEEDLING_BI, 0xFF5c3735);
    }
}
