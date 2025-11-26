package com.wojosvanillaplusarrows;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // ====== - Weeping arrow configs - ======
    public static final ModConfigSpec.BooleanValue IS_WEEPING_ARROW_ENABLED = BUILDER
            .comment("Is the WEEPING ARROW enabled? (DEF: true)")
            .define("isWeepingArrowEnabled", true);
    public static final ModConfigSpec.IntValue SET_WEEPING_ARROW_ROPE_LENGTH = BUILDER
            .comment("Set the max length the weeping vine can reach (MIN:0, DEF:7: MAX: 256)\nNote: If set higher then 7 the player can get more vines back then used in the craft")
            .defineInRange("weepingArrowRopeLen", 7, 0, 256);
    public static final ModConfigSpec.IntValue SET_WEEPING_ARROW_STACK_SIZE = BUILDER
            .comment("Set the max stack size for Weeping Arrows (MIN:0, DEF:8, MAX: 64)")
            .defineInRange("weepingArrowMaxStackSize", 8, 0, 64);
    //TODO: Add craft adjustment configs?

    // ====== - Glow Berry Arrow Configs - ======
    public static final ModConfigSpec.BooleanValue IS_GOWBERRY_ARROW_ENABLED = BUILDER
            .comment("Is the GLOWBERRY ARROW enabled? (DEF: true)")
            .define("isGlowberryArrowEnabled", true);
    public static final ModConfigSpec.IntValue SET_GLOWBERRY_ARROW_ROPE_LENGTH = BUILDER
            .comment("Set the max length the glowberry vine can reach (MIN:1, DEF:1: MAX: 256)\nNote: Adjusting this does not adjust the crafting recipe.")
            .defineInRange("glowberryArrowRopeLen", 1, 1, 256);
    public static final ModConfigSpec.IntValue SET_GLOWBERRY_ARROW_STACK_SIZE = BUILDER
            .comment("Set the max stack size for Glowberry Arrows (MIN:0, DEF:8, MAX: 64)")
            .defineInRange("glowberryArrowMaxStackSize", 8, 0, 64);
    //TODO: Add craft adjustment configs?

    // ====== - Enderpearl Arrow Configs - ======
    public static final ModConfigSpec.BooleanValue IS_ENDERPEARL_ARROW_ENABLED = BUILDER
            .comment("Is the ENDERPEARL ARROW enabled? (DEF: true)")
            .define("isEnderpearlArrowEnabled", true);
    public static final ModConfigSpec.IntValue SET_ENDERPEARL_ARROW_STACK_SIZE = BUILDER
            .comment("Set the max stack size for Enderpearl Arrows (MIN:0, DEF:8, MAX: 64)")
            .defineInRange("enderpearlArrowMaxStackSize", 8, 0, 64);

    static final ModConfigSpec SPEC = BUILDER.build();

    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }
}
