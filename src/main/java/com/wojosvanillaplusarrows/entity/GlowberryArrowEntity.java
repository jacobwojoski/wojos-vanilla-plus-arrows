package com.wojosvanillaplusarrows.entity;

import com.wojosvanillaplusarrows.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CaveVines;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class GlowberryArrowEntity extends AbstractArrow {

    public GlowberryArrowEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
        this.pickup = Pickup.ALLOWED;
        this.setBaseDamage(0);
    }

    public GlowberryArrowEntity(EntityType<? extends AbstractArrow> entityType, double x, double y, double z, Level level, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
        super(entityType, x, y, z, level, pickupItemStack, firedFromWeapon);
        this.pickup = Pickup.ALLOWED;
        this.setBaseDamage(0);
    }

    public GlowberryArrowEntity(EntityType<? extends AbstractArrow> entityType, LivingEntity owner, Level level, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
        super(entityType, owner, level, pickupItemStack, firedFromWeapon);
        this.pickup = Pickup.ALLOWED;
        this.setBaseDamage(0);
    }

    /* Create the hanging vine when the arrow hits a block */
    @Override
    protected void onHitBlock(@NotNull BlockHitResult result) {
        super.onHitBlock(result);
        Level instanceLevel = level();

        if (!instanceLevel.isClientSide) {
            BlockPos impact_position = result.getBlockPos().relative(result.getDirection());

            BlockState berryVine = Blocks.CAVE_VINES.defaultBlockState().setValue(CaveVines.BERRIES,true);

            BlockPos.MutableBlockPos cursor = impact_position.mutable();
            // TODO: Set length from config
            int maxLength = 1;

            // Check for supporting block
            cursor.move(Direction.UP);
            //TODO: Check if supporting block is valid (Not water/Plant/Etc)
            if (!instanceLevel.isEmptyBlock(cursor)){
                cursor.move(Direction.DOWN,1);

                for (int i = 0; i < maxLength; i++) {
                    if (instanceLevel.isEmptyBlock(cursor)) {
                        instanceLevel.setBlock(cursor, berryVine, Block.UPDATE_ALL);
                        cursor.move(Direction.DOWN);
                    } else {
                        break; // Stop if vine cannot keep growing
                    }
                }
                this.discard();
            }else{
                // No supporting block, Arrow unused so leave arrow to be picked up
            }
        }
    }

    @Override
    protected void onHit(@NotNull HitResult result) {
        super.onHit(result);
    }

    @Override
    protected @NotNull ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.GLOW_BERRY_ARROW.get());
    }
}
