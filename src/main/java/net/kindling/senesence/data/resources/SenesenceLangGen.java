package net.kindling.senesence.data.resources;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import static net.kindling.senesence.impl.index.SenesenceItems.*;
import static net.kindling.senesence.impl.index.SenesenceBlocks.*;
import static net.kindling.senesence.impl.index.data.SenesenceEnchantments.*;
import static net.kindling.senesence.impl.index.data.SenesenceDamageTypes.*;

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
        registerDamageType(translationBuilder, BURY,
                "%1$s was buried under autumn leaves",
                "%1$s was buried under autumn leaves by %2$s wielding %3$s",
                "%1$s was buried under autumn leaves by %2$s"
        );

        registerDamageType(translationBuilder, SPREADED,
                "%1$s was covered in sparks",
                "%1$s was covered in spakrs by %2$s wielding %3$s",
                "%1$s was covered in sparks by %2$s"
        );

        translationBuilder.addEnchantment(HARVEST, "Harvest");
        translationBuilder.add("enchantment.senesence.harvest.desc", "Allows the Auburn Cleaver to charge up every crit, unleashing [] upon full charge.");

        translationBuilder.addEnchantment(SPLINTER, "Splinter");
        translationBuilder.add("enchantment.senesence.splinter.desc", "Allows the wielder to throw a splintered version of the Auburn Cleaver, which will deal damage to all nearby entities upon hitting the ground.");
    }

    public void registerDamageType(TranslationBuilder builder, RegistryKey<DamageType> registryKey, String normal, String item, String player) {
        String key = "death.attack." + registryKey.getValue().getPath();
        builder.add(key, normal);
        builder.add(key + ".item", item);
        builder.add(key + ".player", player);
    }
}
