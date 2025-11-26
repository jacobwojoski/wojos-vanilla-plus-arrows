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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CaveVines;
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
public class EnderPearlArrowEntity extends AbstractArrow {

    public EnderPearlArrowEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
        this.pickup = Pickup.ALLOWED;
        this.setBaseDamage(0);
        playThorowSound();
    }

    public EnderPearlArrowEntity(EntityType<? extends AbstractArrow> entityType, double x, double y, double z, Level level, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
        super(entityType, x, y, z, level, pickupItemStack, firedFromWeapon);
        this.pickup = Pickup.ALLOWED;
        this.setBaseDamage(0);
        playThorowSound();
    }

    public EnderPearlArrowEntity(EntityType<? extends AbstractArrow> entityType, LivingEntity owner, Level level, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
        super(entityType, owner, level, pickupItemStack, firedFromWeapon);
        this.pickup = Pickup.ALLOWED;
        this.setBaseDamage(0);
        playThorowSound();
    }

    private void playThorowSound(){
        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                SoundEvents.ENDER_PEARL_THROW,
                SoundSource.PLAYERS,
                1.0F,
                1.0F
        );
    }

    /* Create the hanging vine when the arrow hits a block */
    @Override
    protected void onHit(@NotNull HitResult result) {
        super.onHit(result);
        Level instanceLevel = level();

        if (!instanceLevel.isClientSide) {
            Entity owner = this.getOwner();

            if (owner instanceof LivingEntity living) {
                double x = this.getX();
                double y = this.getY();
                double z = this.getZ();

                // Teleport the shooter like an ender pearl
                living.teleportTo(x, y, z);
                living.resetFallDistance();

                // Optional: spawn particles
                ((ServerLevel) level()).sendParticles(
                        ParticleTypes.PORTAL,
                        x, y, z, 32,
                        0.5, 1.0, 0.5, 0.1
                );

                this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                        SoundEvents.PLAYER_TELEPORT,
                        SoundSource.PLAYERS,
                        1.0F,
                        1.0F
                );
            }
            this.discard(); // remove arrow
        }
    }

    @Override
    protected @NotNull ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.GLOW_BERRY_ARROW.get());
    }
}
