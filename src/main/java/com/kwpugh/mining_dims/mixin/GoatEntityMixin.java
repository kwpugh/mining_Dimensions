package com.kwpugh.mining_dims.mixin;

import com.kwpugh.mining_dims.init.MiningDimsRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.GoatEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GoatEntity.class)
public abstract class GoatEntityMixin extends AnimalEntity
{
    public GoatEntityMixin(EntityType<? extends AnimalEntity> entityType, World world)
    {
        super(entityType, world);
    }

    @Inject(method="onGrowUp",at=@At("TAIL"),cancellable = true)
    private void miningDimsOnGrowUp(CallbackInfo ci)
    {
        RegistryKey<World> registryKey = world.getRegistryKey();
        if(registryKey == MiningDimsRegistry.MININGDIMS_WORLD_KEY5)
        {
            this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(6.0D);
        }
    }
}