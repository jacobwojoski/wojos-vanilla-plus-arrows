package com.wojosvanillaplusarrows.entity;

import com.wojosvanillaplusarrows.WojosVanillaPlusArrows;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {

    // Create the main registry for entities
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, WojosVanillaPlusArrows.MOD_ID);

    // -----------------------------
    //  Weeping Vine Arrow Entity
    // -----------------------------
    public static final DeferredHolder<EntityType<?>, EntityType<WeepingVineArrowEntity>>
        WEEPING_VINE_ARROW = ENTITIES.register("weeping_vine_arrow",
            () -> EntityType.Builder.<WeepingVineArrowEntity>of(
                        WeepingVineArrowEntity::new,
                        MobCategory.MISC
                )
                .sized(0.5F, 0.5F)       // Arrow size
                .clientTrackingRange(4)
                .updateInterval(20)
                .build("weeping_vine_arrow")
    );

    // -----------------------------
    //  Glowberry Arrow Entity
    // -----------------------------
    public static final DeferredHolder<EntityType<?>, EntityType<GlowberryArrowEntity>>
            GLOWBERRY_ARROW = ENTITIES.register("glowberry_arrow",
            () -> EntityType.Builder.<GlowberryArrowEntity>of(
                        GlowberryArrowEntity::new,
                        MobCategory.MISC
                )
                .sized(0.5F, 0.5F)       // Arrow size
                .clientTrackingRange(4)
                .updateInterval(20)
                .build("glowberry_arrow")
        );

    // Call this in your main mod constructor
    public static void register(IEventBus bus) {
        ENTITIES.register(bus);
    }
}
