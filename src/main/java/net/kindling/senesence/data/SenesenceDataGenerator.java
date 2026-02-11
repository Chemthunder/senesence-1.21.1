package net.kindling.senesence.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.kindling.senesence.data.other.SenesenceDynamicRegistryGen;
import net.kindling.senesence.data.resources.SenesenceLangGen;
import net.kindling.senesence.data.resources.SenesenceModelGen;
import net.kindling.senesence.impl.index.data.SenesenceDamageTypes;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class SenesenceDataGenerator implements DataGeneratorEntrypoint {

	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(SenesenceDynamicRegistryGen::new);
        pack.addProvider(SenesenceLangGen::new);
        pack.addProvider(SenesenceModelGen::new);
	}

    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.DAMAGE_TYPE, SenesenceDamageTypes::bootstrap);
    }
}
