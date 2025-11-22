package com.wojosvanillaplusarrows.item;

import com.wojosvanillaplusarrows.WojosVanillaPlusArrows;
import com.wojosvanillaplusarrows.entity.ModEntities;
import com.wojosvanillaplusarrows.entity.WeepingVineArrowEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

public class WeepingVineArrowItem extends ArrowItem {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WojosVanillaPlusArrows.MOD_ID);

    public WeepingVineArrowItem(Properties properties){
        super(properties);
    }

    @Override
    public @NotNull AbstractArrow createArrow(@NotNull Level world, @NotNull ItemStack ammo, @NotNull LivingEntity shooter, ItemStack weapon) {
        return new WeepingVineArrowEntity(ModEntities.WEEPING_VINE_ARROW.get(), shooter, world, weapon, ammo);
    }

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}

