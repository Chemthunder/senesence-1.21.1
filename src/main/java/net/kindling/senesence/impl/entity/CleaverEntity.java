package net.kindling.senesence.impl.entity;

import net.kindling.senesence.impl.index.SenesenceDataComponents;
import net.kindling.senesence.impl.index.SenesenceItems;
import net.kindling.senesence.impl.index.SenesenceParticles;
import net.kindling.senesence.impl.index.data.SenesenceDamageTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Ownable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class CleaverEntity extends ThrownItemEntity implements Ownable {
    public CleaverEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    protected Item getDefaultItem() {
        return SenesenceItems.AUBURN_CLEAVER;
    }

    public boolean hasNoGravity() {
        return true;
    }

    public void tick() {
        tickParticle();

        if (this.age >= 90) {
            cycleReturn();
        }
        super.tick();
    }

    private void tickParticle() {
        World world = this.getWorld();
        int parSpacing = 1;

        world.addParticle(SenesenceParticles.AUBURN_LEAF, true, this.getX() + this.getRandom().nextBetween(-parSpacing, parSpacing), this.getY() + this.getRandom().nextBetween(-parSpacing, parSpacing), this.getZ() + this.getRandom().nextBetween(-parSpacing, parSpacing), 0, 0, 0);
    }

    protected void onBlockCollision(BlockState state) {
        World world = this.getWorld();

        if (!state.isOf(Blocks.AIR)) {
            this.setVelocity(0, 0, 0);
            world.playSound(this, this.getBlockPos(), SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.HOSTILE , 0.1f, 0.1f);

            if (world instanceof ServerWorld serverWorld) {
                serverWorld.spawnParticles(SenesenceParticles.SPARK,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        3,
                        0,
                        0,
                        0,
                        0.5f
                );

                Box area = new Box(this.getBlockPos()).expand(7);
                List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, area, entity -> true);

                for (LivingEntity entity : entities) {
                    if (entity != this.getOwner()) {
                        entity.damage(entity.getDamageSources().create(SenesenceDamageTypes.SPREADED), 1.5f);
                    }
                }
            }
        }
        super.onBlockCollision(state);
    }

    private void cycleReturn() {
        Entity blehg = this.getOwner();
        if (blehg instanceof PlayerEntity player) {
            ItemStack stack = player.getStackInHand(player.getActiveHand());

            if (stack.isOf(SenesenceItems.AUBURN_CLEAVER)) {
                if (stack.getOrDefault(SenesenceDataComponents.SPLINTERED, false) == true) {
                    stack.set(SenesenceDataComponents.SPLINTERED, false);
                    this.discard();
                }
            }
        }
    }
}
