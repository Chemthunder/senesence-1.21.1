package net.kindling.senesence.data.resources;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

import static net.kindling.senesence.impl.index.SenesenceItems.*;

public class SenesenceModelGen extends FabricModelProvider {
    public SenesenceModelGen(FabricDataOutput output) {
        super(output);
    }

    public void generateBlockStateModels(BlockStateModelGenerator generator) {

    }

    public void generateItemModels(ItemModelGenerator generator) {
        generator.register(AUBURN_CLEAVER, Models.GENERATED);
        generator.register(CLEAVER_SEEDLING_BI, Models.GENERATED);
    }
}