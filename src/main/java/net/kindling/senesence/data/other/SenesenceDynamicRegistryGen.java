package net.kindling.senesence.data.other;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.kindling.senesence.impl.Senesence;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class SenesenceDynamicRegistryGen extends FabricDynamicRegistryProvider {
    public SenesenceDynamicRegistryGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup, Entries entries) {
        entries.addAll(wrapperLookup.getWrapperOrThrow(RegistryKeys.DAMAGE_TYPE));
    }

    public String getName() {
        return Senesence.MOD_ID + "_dynamic";
    }
}
