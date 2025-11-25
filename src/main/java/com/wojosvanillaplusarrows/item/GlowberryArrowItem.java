package com.wojosvanillaplusarrows.item;

import com.wojosvanillaplusarrows.entity.GlowberryArrowEntity;
import com.wojosvanillaplusarrows.entity.WeepingVineArrowEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class GlowberryArrowItem extends ArrowItem {
    public GlowberryArrowItem(Item.Properties properties){
        super(properties);
    }

    public AbstractArrow createArrow(Level level, ItemStack ammo, LivingEntity shooter, @Nullable ItemStack weapon) {
        return new GlowberryArrowEntity(EntityType.ARROW, shooter, level, ammo.copyWithCount(1), weapon);
    }

    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        GlowberryArrowEntity arrow = new GlowberryArrowEntity(EntityType.ARROW, pos.x(), pos.y(), pos.z(), level, stack.copyWithCount(1), (ItemStack)null);
        arrow.pickup = AbstractArrow.Pickup.ALLOWED;
        return arrow;
    }
}
