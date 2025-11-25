package com.wojosvanillaplusarrows.item;

import com.wojosvanillaplusarrows.WojosVanillaPlusArrows;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final TagKey<Item> MY_TAG = TagKey.create(
            // The registry key. The type of the registry must match the generic type of the tag.
            Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath("minecraft", "arrows")
    );

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, WojosVanillaPlusArrows.MOD_ID);

    public static final DeferredHolder<Item, ArrowItem> WEEPING_VINE_ARROW =
            ITEMS.register("weeping_vine_arrow",
                    () -> new WeepingVineArrowItem(new Item.Properties().stacksTo(8))
            );

    public static final DeferredHolder<Item, ArrowItem> GLOW_BERRY_ARROW =
            ITEMS.register("glowberry_arrow",
                    () -> new GlowberryArrowItem(new Item.Properties().stacksTo(8))
            );

    // Optional helper method
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}