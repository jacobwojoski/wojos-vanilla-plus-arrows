package com.wojosvanillaplusarrows.item;

import com.wojosvanillaplusarrows.entity.EnderPearlArrowEntity;
import com.wojosvanillaplusarrows.entity.GlowberryArrowEntity;
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

public class EnderPearlArrowItem extends ArrowItem {
    public EnderPearlArrowItem(Properties properties){
        super(properties);
    }

    public AbstractArrow createArrow(Level level, ItemStack ammo, LivingEntity shooter, @Nullable ItemStack weapon) {
        return new EnderPearlArrowEntity(EntityType.ARROW, shooter, level, ammo.copyWithCount(1), weapon);
    }

    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        EnderPearlArrowEntity arrow = new EnderPearlArrowEntity(EntityType.ARROW, pos.x(), pos.y(), pos.z(), level, stack.copyWithCount(1), (ItemStack)null);
        arrow.pickup = AbstractArrow.Pickup.ALLOWED;
        return arrow;
    }
}
