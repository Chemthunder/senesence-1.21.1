package net.kindling.senesence.data.resources;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import static net.kindling.senesence.impl.index.SenesenceItems.*;
import static net.kindling.senesence.impl.index.SenesenceBlocks.*;

public class SenesenceLangGen extends FabricLanguageProvider {
    public SenesenceLangGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        // items
        translationBuilder.add(AUBURN_CLEAVER, "Auburn Cleaver");
        translationBuilder.add(CLEAVER_SEEDLING, "Cleaver Seedling");

        // blocks


        // misc
    }
}
