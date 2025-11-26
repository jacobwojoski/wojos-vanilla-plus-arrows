package com.wojosvanillaplusarrows.item;

import com.wojosvanillaplusarrows.entity.WaterSourceArrowEntity;
import com.wojosvanillaplusarrows.entity.WeepingVineArrowEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class WaterSourceArrowItem extends ArrowItem {
    public WaterSourceArrowItem(Properties properties){
        super(properties);
    }

    public AbstractArrow createArrow(Level level, ItemStack ammo, LivingEntity shooter, @Nullable ItemStack weapon) {
        return new WaterSourceArrowEntity(EntityType.ARROW, shooter, level, ammo.copyWithCount(1), weapon);
    }

    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        WaterSourceArrowEntity arrow = new WaterSourceArrowEntity(EntityType.ARROW, pos.x(), pos.y(), pos.z(), level, stack.copyWithCount(1), (ItemStack)null);
        arrow.pickup = AbstractArrow.Pickup.DISALLOWED;
        return arrow;
    }
}

