package com.kwpugh.mining_dims.mixin;

import com.kwpugh.mining_dims.MiningDimsRegistry;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WitherSkeletonEntity.class)
public abstract class WitherSkeletonEntityMixin extends AbstractSkeletonEntity
{
    public WitherSkeletonEntityMixin(EntityType<? extends WitherSkeletonEntity> entityType, World world)
    {
        super(entityType, world);
    }

    @Inject(method="initialize",at=@At("TAIL"),cancellable = true)
    public void initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityTag, CallbackInfoReturnable<EntityData> cir)
    {
        this.miningDimsApplyAttributeModifiers();
        this.updateEnchantments(difficulty);
    }

    private void miningDimsApplyAttributeModifiers()
    {
        RegistryKey<World> registryKey = world.getRegistryKey();
        if(registryKey == MiningDimsRegistry.MININGDIMS_WORLD_KEY2)
        {
            this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).addPersistentModifier(new EntityAttributeModifier("MiningDims Health Bonus", 80.0D, EntityAttributeModifier.Operation.ADDITION));
            this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).addPersistentModifier(new EntityAttributeModifier("MiningDims Attack Bonus", 20.0D, EntityAttributeModifier.Operation.ADDITION));
            this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).addPersistentModifier(new EntityAttributeModifier("MiningDims Armor Bonus", 20.0D, EntityAttributeModifier.Operation.ADDITION));
            this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).addPersistentModifier(new EntityAttributeModifier("MiningDims Movement Bonus", 0.085D, EntityAttributeModifier.Operation.ADDITION));
        }
    }
}
