package com.wojosvanillaplusarrows;

import com.wojosvanillaplusarrows.entity.ModEntities;
import com.wojosvanillaplusarrows.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.event.TagsUpdatedEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(WojosVanillaPlusArrows.MOD_ID)
public class WojosVanillaPlusArrows {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "wojosvanillaplusarrows";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public WojosVanillaPlusArrows(IEventBus modEventBus, ModContainer modContainer) {
        ModItems.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        //modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        NeoForge.EVENT_BUS.register(this);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        // Debug check after items are registered
        LOGGER.info("Common Setup Check: Weeping Vine Arrow in arrows tag? " + ModItems.WEEPING_VINE_ARROW.get().builtInRegistryHolder().is(ItemTags.ARROWS));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.WEEPING_VINE_ARROW.get());
        }
    }

    public static final TagKey<Item> MY_TAG = TagKey.create(
            // The registry key. The type of the registry must match the generic type of the tag.
            Registries.ITEM,
            // The location of the tag. This example will put our tag at data/examplemod/tags/blocks/example_tag.json.
            ResourceLocation.fromNamespaceAndPath("wojosvanillaplusarrows", "arrowplus")
    );

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");

        // Debug check after items are registered
        LOGGER.info("Server Starting Check Weeping Vine Arrow in arrows tag? {}",
                ModItems.WEEPING_VINE_ARROW.get().builtInRegistryHolder().is(ItemTags.ARROWS)
        );

        LOGGER.info("Weeping vine arrow in arrowPlus tag? {}",
                ModItems.WEEPING_VINE_ARROW.get().builtInRegistryHolder().is(MY_TAG)
        );

        BuiltInRegistries.ITEM.getTagOrEmpty(ItemTags.ARROWS).forEach(holder -> {
            LOGGER.info("Item in arrows tag: " + BuiltInRegistries.ITEM.getKey(holder.value()));
        });
    }
}
