package net.kindling.senesence.data.other;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.kindling.senesence.impl.index.SenesenceItems;
import net.kindling.senesence.impl.index.tag.SenesenceItemTags;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class SenesenceItemTagGen extends FabricTagProvider.ItemTagProvider {
    public SenesenceItemTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup woah) {
        this.getOrCreateTagBuilder(SenesenceItemTags.AUTUMNAL)
                .add(SenesenceItems.AUBURN_CLEAVER)
                .setReplace(false);

        this.getOrCreateTagBuilder(ItemTags.AXES)
                .add(SenesenceItems.AUBURN_CLEAVER)
                .setReplace(false);
    }
}
