package com.kwpugh.mining_dims.mixin;

import com.kwpugh.mining_dims.MiningDims;
import com.kwpugh.mining_dims.init.MiningDimsRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixinDrops extends Entity
{
    double dropChance = MiningDims.CONFIG.GENERAL.gearDropChannce;

    public LivingEntityMixinDrops(EntityType<? extends AnimalEntity> entityType, World world)
    {
        super(entityType, world);
    }

    @Inject(method="onKilledBy",at=@At("HEAD"),cancellable = true)
    public void onKilledBy(@Nullable LivingEntity adversary, CallbackInfo ci)
    {
        if(!this.world.isClient)
        {
            Random random = new Random();

            if (adversary instanceof PlayerEntity)
            {
                RegistryKey<World> registryKey = world.getRegistryKey();
                LivingEntity victim = (LivingEntity) (Object) this;

                double r = random.nextDouble();
                if(r < dropChance && registryKey == MiningDimsRegistry.MININGDIMS_WORLD_KEY2)
                {
                    ItemStack main = victim.getEquippedStack(EquipmentSlot.MAINHAND);
                    victim.dropStack(main);

                    ItemStack off = victim.getEquippedStack(EquipmentSlot.OFFHAND);
                    victim.dropStack(off);

                    ItemStack head = victim.getEquippedStack(EquipmentSlot.HEAD);
                    victim.dropStack(head);

                    ItemStack chest = victim.getEquippedStack(EquipmentSlot.CHEST);
                    victim.dropStack(chest);

                    ItemStack legs = victim.getEquippedStack(EquipmentSlot.LEGS);
                    victim.dropStack(legs);

                    ItemStack feet = victim.getEquippedStack(EquipmentSlot.FEET);
                    victim.dropStack(feet);
                }
            }
        }
    }
}