package net.kindling.senesence.impl.block;

import net.kindling.senesence.impl.util.SeedlingProperty;
import net.kindling.senesence.impl.util.SenesenceProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class CleaverSeedlingBlock extends Block {
    private static final EnumProperty<SeedlingProperty> SEEDLING = SenesenceProperties.SEEDLING_PROPERTY;
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(SEEDLING);
    }

    public CleaverSeedlingBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState()
                .with(SEEDLING, SeedlingProperty.SMALL));
    }

    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int i = state.get(SenesenceProperties.SEEDLING_PROPERTY).ordinal();
        if (i < 2){
            world.getBlockState(pos).with(SEEDLING, SeedlingProperty.values()[i+1]);
        }
        super.randomTick(state, world, pos, random);
    }
}
