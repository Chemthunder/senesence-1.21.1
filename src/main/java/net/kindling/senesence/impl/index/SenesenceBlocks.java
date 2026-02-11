package net.kindling.senesence.impl.index;

import net.acoyt.acornlib.impl.item.TranslationBlockItem;
import net.kindling.senesence.impl.Senesence;
import net.kindling.senesence.impl.block.CleaverSeedlingBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

import java.util.function.Function;

public interface SenesenceBlocks {
    Block CLEAVER_SEEDLING = createWithItem("cleaver_seedling", CleaverSeedlingBlock::new, AbstractBlock.Settings.copy(Blocks.VINE).sounds(BlockSoundGroup.VINE), new Item.Settings());

    static Block create(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = factory.apply(settings);
        return Registry.register(Registries.BLOCK, Senesence.id(name), block);
    }

    static Block createWithItem(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings, Item.Settings itemSetting) {
        Block block = create(name, factory, settings);
        SenesenceItems.create(name, itemSettings -> new TranslationBlockItem(block, itemSettings), itemSetting);
        return block;
    }

    static void init() {
        //
    }

    static void clientInit() {
        //
    }
}
