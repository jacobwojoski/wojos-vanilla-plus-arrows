package com.wojosvanillaplusarrows.item;

import com.wojosvanillaplusarrows.WojosVanillaPlusArrows;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, WojosVanillaPlusArrows.MOD_ID);

    public static final DeferredHolder<Item, WeepingVineArrowItem> WEEPING_VINE_ARROW =
            ITEMS.register("weeping_vine_arrow",
                    () -> new WeepingVineArrowItem(new Item.Properties())
            );

    public static final DeferredHolder<Item, GlowberryArrowItem> GLOWBERRY_ARROW =
            ITEMS.register("glowberry_arrow",
                    () -> new GlowberryArrowItem(new Item.Properties())
            );

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
