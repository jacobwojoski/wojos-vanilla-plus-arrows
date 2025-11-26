package com.wojosvanillaplusarrows.entity;

import com.wojosvanillaplusarrows.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

/**
 * EnderPearlArrowEntity is the arrow entity that is flying through the air when the player
 * shoots a bow.
 *
 * When the entity hits something it teleports the player to that location.
 */
public class WaterSourceArrowEntity extends AbstractArrow {

    public WaterSourceArrowEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
        this.pickup = Pickup.ALLOWED;
        this.setBaseDamage(0);
    }

    public WaterSourceArrowEntity(EntityType<? extends AbstractArrow> entityType, double x, double y, double z, Level level, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
        super(entityType, x, y, z, level, pickupItemStack, firedFromWeapon);
        this.pickup = Pickup.ALLOWED;
        this.setBaseDamage(0);
    }

    public WaterSourceArrowEntity(EntityType<? extends AbstractArrow> entityType, LivingEntity owner, Level level, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
        super(entityType, owner, level, pickupItemStack, firedFromWeapon);
        this.pickup = Pickup.ALLOWED;
        this.setBaseDamage(0);
    }

    /* Create a water source block at impact point */
    @Override
    protected void onHitBlock(@NotNull BlockHitResult result) {
        super.onHitBlock(result);

        if (!level().isClientSide()) {
            // Block to set air too
            BlockState waterSrcBlock = Blocks.WATER.defaultBlockState();

            // Create cursor to check locations
            BlockPos impact_position = result.getBlockPos().relative(result.getDirection());
            BlockPos.MutableBlockPos cursor = impact_position.mutable();

            // Only place water if the target block is empty (air)
            if (level().isEmptyBlock(cursor)) {
                // 3 = 1(send neighbor updates) + 2(send client updates)
                level().setBlock(cursor, waterSrcBlock, 3);
            }

            this.discard(); // remove the arrow
        }
    }

    @Override
    protected @NotNull ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.WATER_SOURCE_ARROW.get());
    }
}
