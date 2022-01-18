package com.kwpugh.mining_dims.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.Heightmap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/*
    This is intended to add a spawn restriction for the Piglin Brute (which doesn't
    have one in vanilla.  This restricts the brute to spawning on a block and not
    spawning in mid-air in the hunting dimension.
 */

@Mixin(SpawnRestriction.class)
public abstract class SpawnRestrictionMixin
{
    @Shadow
    private static <T extends MobEntity> void register(EntityType<T> type, SpawnRestriction.Location location, Heightmap.Type heightmapType, SpawnRestriction.SpawnPredicate<T> predicate) {}

    static
    {
        register(EntityType.PIGLIN_BRUTE, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
    }
}