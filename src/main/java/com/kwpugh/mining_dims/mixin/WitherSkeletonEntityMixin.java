package com.kwpugh.mining_dims.mixin;

import com.kwpugh.mining_dims.MiningDims;
import com.kwpugh.mining_dims.init.MiningDimsRegistry;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WitherSkeletonEntity.class)
public abstract class WitherSkeletonEntityMixin extends AbstractSkeletonEntity
{
    public WitherSkeletonEntityMixin(EntityType<? extends WitherSkeletonEntity> entityType, World world)
    {
        super(entityType, world);
    }

    @Inject(method="initEquipment",at=@At("TAIL"),cancellable = true)
    private void miningDimsInitEquipment(LocalDifficulty difficulty, CallbackInfo ci)
    {
        RegistryKey<World> registryKey = world.getRegistryKey();
        if(registryKey == MiningDimsRegistry.MININGDIMS_WORLD_KEY2)
        {
            if(MiningDims.CONFIG.GENERAL.enableWitherSkeletonGear)
            {
                this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.NETHERITE_SWORD));
                this.equipStack(EquipmentSlot.HEAD, new ItemStack(Items.NETHERITE_HELMET));
                this.equipStack(EquipmentSlot.CHEST, new ItemStack(Items.NETHERITE_CHESTPLATE));
                this.equipStack(EquipmentSlot.LEGS, new ItemStack(Items.NETHERITE_LEGGINGS));
                this.equipStack(EquipmentSlot.FEET, new ItemStack(Items.NETHERITE_BOOTS));
            }
        }
    }

    @Inject(method="initialize",at=@At("TAIL"),cancellable = true)
    public void initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityTag, CallbackInfoReturnable<EntityData> cir)
    {
        this.miningDimsApplyAttributeModifiers();
        this.updateEnchantments(difficulty);
    }

    private void miningDimsApplyAttributeModifiers()
    {
        double health = MiningDims.CONFIG.GENERAL.witherSkeletonMaxHealth;
        double attack = MiningDims.CONFIG.GENERAL.witherSkeletonDamageBonus;
        double armor = MiningDims.CONFIG.GENERAL.witherSkeletonArmorBonus;
        double speed = MiningDims.CONFIG.GENERAL.witherSkeletonMovementBonus;

        RegistryKey<World> registryKey = world.getRegistryKey();
        if(registryKey == MiningDimsRegistry.MININGDIMS_WORLD_KEY2)
        {
            this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).addPersistentModifier(new EntityAttributeModifier("MiningDims Health Bonus", health, EntityAttributeModifier.Operation.ADDITION));
            this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).addPersistentModifier(new EntityAttributeModifier("MiningDims Attack Bonus", attack, EntityAttributeModifier.Operation.ADDITION));
            this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).addPersistentModifier(new EntityAttributeModifier("MiningDims Armor Bonus", armor, EntityAttributeModifier.Operation.ADDITION));
            this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).addPersistentModifier(new EntityAttributeModifier("MiningDims Movement Bonus", speed, EntityAttributeModifier.Operation.ADDITION));
        }
    }
}
