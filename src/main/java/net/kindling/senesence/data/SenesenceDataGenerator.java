package net.kindling.senesence.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.kindling.senesence.impl.index.data.SenesenceDamageTypes;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class SenesenceDataGenerator implements DataGeneratorEntrypoint {

	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

	}

    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.DAMAGE_TYPE, SenesenceDamageTypes::bootstrap);
    }
}
