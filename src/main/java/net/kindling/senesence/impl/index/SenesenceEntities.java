package net.kindling.senesence.impl.index;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.kindling.senesence.impl.Senesence;
import net.kindling.senesence.impl.client.entity.CleaverEntityRenderer;
import net.kindling.senesence.impl.entity.CleaverEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public interface SenesenceEntities {
    EntityType<CleaverEntity> CLEAVER = create("cleaver", EntityType.Builder.<CleaverEntity>create(
            CleaverEntity::new,
            SpawnGroup.MISC
    ).dimensions(1.7F, 1.0F));

    private static <T extends Entity> EntityType<T> create(String name, EntityType.Builder<T> builder) {
        return Registry.register(Registries.ENTITY_TYPE, Senesence.id(name), builder.build());
    }

    static void init() {
        //
    }

    static void clientInit() {
          EntityRendererRegistry.register(CLEAVER, CleaverEntityRenderer::new);
    }
}
