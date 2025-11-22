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

public class GlowberryArrowEntity extends AbstractArrow {

    /**
     * Default Contructor
     * @param type - Registered entity type when spawning (usually provided by minecraft itself)
     * @param world - The Minecraft world
     */
    public GlowberryArrowEntity(EntityType<? extends AbstractArrow> type, Level world) {
        super(type, world);
    }

    /**
     * Glowberry Arrow Contructor
     * @param type - Registered entity type when spawning (usually provided by minecraft itself)
     * @param shooter - The entity that shot the arrow (Player, pillage, skelly, etc)
     * @param instanceLevel - The world the arrow exists in
     * @param firingWeapon - The firing weapon (Bow, Crossbow)
     * @param arrowItemStack - The ammo Item / item associated with the arrow (Should be WeepingVineArrowItem)
     */
    public GlowberryArrowEntity(EntityType<? extends AbstractArrow> type,
                                LivingEntity shooter, Level instanceLevel,
                                ItemStack firingWeapon, ItemStack arrowItemStack) {
        super(type, shooter, instanceLevel, firingWeapon, arrowItemStack);
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
            int maxLength = 4;

            for (int i = 0; i < maxLength; i++) {
                if (instanceLevel.isEmptyBlock(cursor)) {
                    instanceLevel.setBlock(cursor, berryVine, Block.UPDATE_ALL);
                    cursor.move(Direction.DOWN);
                } else {
                    break; // Stop if vine cannot keep growing
                }
            }
            this.discard(); // Remove arrow after effect
        }
    }

    @Override
    protected void onHit(@NotNull HitResult result) {
        super.onHit(result);
    }

    @Override
    protected @NotNull ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.WEEPING_VINE_ARROW.get());
    }
}
