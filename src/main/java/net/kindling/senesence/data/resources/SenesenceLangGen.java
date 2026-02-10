package net.kindling.senesence.data.resources;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import static net.kindling.senesence.impl.index.SenesenceItems.*;

public class SenesenceLangGen extends FabricLanguageProvider {
    protected SenesenceLangGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        // items
        translationBuilder.add(FLORAL_CLEAVER, "Floral Cleaver");


        // blocks


        // misc
    }
}
