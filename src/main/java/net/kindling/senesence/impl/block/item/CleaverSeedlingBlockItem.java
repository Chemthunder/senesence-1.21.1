package net.kindling.senesence.impl.block.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.kindling.senesence.impl.index.SenesenceBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

public class CleaverSeedlingBlockItem extends BlockItem implements ColorableItem {
    public int startColor(ItemStack itemStack) {return 0xFF4b2c33;}
    public int endColor(ItemStack itemStack) {return 0xFF6f4235;}
    public int backgroundColor(ItemStack itemStack) {return 0xF02d172b;}

    public CleaverSeedlingBlockItem(Settings settings) {
        super(SenesenceBlocks.CLEAVER_SEEDLING, settings);
    }
}
